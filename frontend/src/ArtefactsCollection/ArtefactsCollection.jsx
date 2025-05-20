import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { useHuntStore } from "../stores/useHuntStore.js";
import TopCircle from "../TopCircleGeneric/TopCircle.jsx";
// import { FaCameraRetro } from "react-icons/fa";
import { FaMagnifyingGlass } from "react-icons/fa6";
import { FaEye } from "react-icons/fa";
import { FaCheck } from "react-icons/fa";



import "./ArtefactsCollection.css";
import { getSessionId } from "../utils/session.js";
import { API } from "../utils/config.js";
import { useEffect } from "react";


const ArtefactsCollection = () => {
  const navigate = useNavigate();
  const { state } = useLocation();
  const huntData = state?.hunt;
  const { hunts, markStepFound, goToStep } = useHuntStore();
  const [currentIndex, setCurrentIndex] = useState(0);
  const [visibleSteps, setVisibleSteps] = useState([]);
  let hunt = hunts.find(h => h.id === huntData?.id);

  useEffect(() => {
    const visibleStepIds = hunt.steps
      .filter(s => s.found || s.id === hunt.currentStepId)
      .map(s => s.id);
    setVisibleSteps(
      huntData.steps.filter(step => visibleStepIds.includes(step.id))
    );
    setCurrentIndex(
      huntData.steps.findIndex(s => s.id === hunt.currentStepId)
    );
  // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [hunts, huntData]);


  if (!huntData) {
    navigate(-1);
    return null;
  }

  const currentStep = visibleSteps[currentIndex];

  const handlePrev = () => setCurrentIndex(i => Math.max(i - 1, 0));
  const handleNext = () =>
    setCurrentIndex(i => Math.min(i + 1, visibleSteps.length - 1));

  const handleScan = stepId => {
    navigate("/scan", { state: { huntId: huntData.id, artefactId: stepId } });
  };
  const handleReveal = async step => {
    const sessionId = getSessionId();
    try {
      const res = await fetch(`${API}/api/hunts/steps/${huntData.id}/reveal`, {
        method: "POST",
        headers: { "Session-id": sessionId },
      });
      if (!res.ok) {
        throw new Error(`Reveal failed (${res.status})`);
      }
      markStepFound(huntData.id, step.id);
      
      
      const thisHunt = hunts.find(h => h.id === huntData.id);
      const idx = thisHunt.steps.findIndex(s => s.id === step.id);
      const nextStepObj = thisHunt.steps[idx + 1];
      if (nextStepObj) {
        goToStep(huntData.id, nextStepObj.id);
      }
      navigate("/artefactDetails", { state: { artefact: step.artefact } });
    } catch (e) {
      console.error("Error revealing artefact:", e);
      alert("Sorry, could not reveal artefact. Please try again.");
    }
  };

  return (
    <div className="artefacts-collection-page">
      <TopCircle pageTitle={huntData.name} />

      <div className="ac-main">
        <div className="ac-navigation">
          <button
            onClick={handlePrev}
            className={currentIndex > 0 ? "nav-btn" : "nav-btn-disabled"}
          >
            Prev
          </button>

          <span className="nav-indicator">
            {currentIndex + 1} / {visibleSteps.length}
          </span>

          <button
            onClick={handleNext}                     // always wired up
            className={currentIndex < visibleSteps.length - 1
              ? "nav-btn"
              : "nav-btn-disabled"}
          >
            Next
          </button>
        </div>

        {currentStep && (
          <ClueCard
            step={currentStep}
            isCurrent={currentStep.id === hunt.currentStepId}
            onScan={handleScan}
            onReveal={handleReveal}
          />
        )}

        {hunt.completed && (
          <div className="congrats-banner">
            🎉 Congratulations, you’ve completed this hunt! 🎉
          </div>
        )}
      </div>
    </div>
  );
}

const ClueCard = ({ step, isCurrent, onScan, onReveal }) => {
  const [hintVisible, setHintVisible] = useState(false);
  const toggleHint = () => setHintVisible(v => !v);

  return (
    <div className="ac-card">
      <div className="ac-card-image">
        <img
          src={step.artefact.imageUrl}
          alt={`Clue #${step.stepNumber}`}
          className="ac-image pixelated"
        />
      </div>
      <div className="ac-card-header">
        <span className="ac-clue">Clue: {step.clue}</span>
      </div>
      <div className="ac-card-actions">
        {isCurrent && (
          <button className="ac-btn hint-btn" onClick={toggleHint}>
            <FaMagnifyingGlass className="btn-icon "/> Hint
          </button>
        )}

        <button
          className="ac-btn reveal-btn"
          onClick={() => onReveal(step)}
        >
          <FaEye className="btn-icon "/> Show
          {/* skip, show, reveal */}
        </button>
        {isCurrent && (
        <button className="ac-btn scan-btn" onClick={() => onScan(step.id)}>
          {/* <FaCameraRetro className="cam-icon" /> */}
          <FaCheck className="btn-icon "/> Got it
          {/* TODO: find more explanatory labels - Confirm, got it, collect, found it is too long */}

        </button>
        )}
      </div>
      {hintVisible && <div className="ac-hint">{step.hint}</div>}
    </div>
  );
};

export default ArtefactsCollection;
