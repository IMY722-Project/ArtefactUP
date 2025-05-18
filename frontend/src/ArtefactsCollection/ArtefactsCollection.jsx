import React, { useState } from "react";
import TopCircle from "../TopCircleGeneric/TopCircle.jsx";
import { useNavigate, useLocation } from "react-router-dom";
import { useHuntStore } from "../stores/useHuntStore.js";
import "./ArtefactsCollection.css";
import { getSessionId } from "../utils/session.js";
import { API } from "../utils/config.js";
import { useEffect } from "react";

const ArtefactsCollection = () => {
  const navigate = useNavigate();
  const { state } = useLocation();
  const huntData = state?.hunt;
  const { hunts, markStepFound, goToStep, setHunts } = useHuntStore();
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
          {currentIndex > 0 && (
            <button onClick={handlePrev} className="nav-btn">
              Prev
            </button>
          )}
          <span className="nav-indicator">
            {currentIndex + 1} / {visibleSteps.length}
          </span>
          {currentIndex < visibleSteps.length - 1 && (
            <button onClick={handleNext} className="nav-btn">
              Next
            </button>
          )}
        </div>
        {currentStep && (
          <ClueCard
            step={currentStep}
            isCurrent={currentStep.id === hunt.currentStepId}
            onScan={handleScan}
            onReveal={handleReveal}
          />
        )}
      </div>
    </div>
  );
};

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
          <>
            <button className="ac-btn scan-btn" onClick={() => onScan(step.id)}>
              Scan 📷
            </button>
            <button className="ac-btn hint-btn" onClick={toggleHint}>
              Hint
            </button>
          </>
        )}

        <button className="ac-btn reveal-btn" onClick={() => onReveal(step)}>
          Reveal
        </button>
      </div>
      {hintVisible && <div className="ac-hint">{step.hint}</div>}
    </div>
  );
};

export default ArtefactsCollection;
