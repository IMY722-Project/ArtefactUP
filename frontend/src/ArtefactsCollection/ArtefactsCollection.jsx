import React, { useState } from "react";
import {
  FaArrowCircleLeft,
  FaArrowCircleRight,
  FaCheck,
  FaEye,
} from "react-icons/fa";
import { useLocation, useNavigate } from "react-router-dom";
import { useHuntStore } from "../stores/useHuntStore.js";
import TopCircle from "../TopCircleGeneric/TopCircle.jsx";

import { useEffect } from "react";
import { API } from "../utils/config.js";
import { getSessionId } from "../utils/session.js";
import "./ArtefactsCollection.css";

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
    setCurrentIndex(huntData.steps.findIndex(s => s.id === hunt.currentStepId));
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
    navigate("/scan", {
      state: { huntId: huntData.id, artefactId: stepId, current: currentStep },
    });
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
    <div className="artefacts-collection-page-wrapper">
      <div className="artefacts-collection-page">
        <TopCircle pageTitle={huntData.name} />

        <div className="ac-main">
          {hunt.completed && (
            <div className="congrats-banner">
              {/* <img
                className="banner-img"
                src="/images/complete-banner-4.png"
                alt="
            Quest Completed"
              /> */}
            </div>
          )}

          <div className="ac-navigation">
            <button
              onClick={handlePrev}
              className={currentIndex > 0 ? "nav-btn" : "nav-btn-disabled"}
            >
              <FaArrowCircleLeft />
            </button>

            <span className="nav-indicator">
              {currentIndex + 1} / {visibleSteps.length}
            </span>

            <button
              onClick={handleNext}
              className={
                currentIndex < visibleSteps.length - 1
                  ? "nav-btn"
                  : "nav-btn-disabled"
              }
            >
              <FaArrowCircleRight />
            </button>
          </div>

          {currentStep && (
            <ClueCard
              step={currentStep}
              isCurrent={currentStep.id === hunt.currentStepId}
              isFound={hunt.steps.find(s => s.id === currentStep.id).found}
              onScan={handleScan}
              onReveal={handleReveal}
            />
          )}
        </div>
      </div>
    </div>
  );
};

const ClueCard = ({ step, isCurrent, isFound, onScan, onReveal }) => {
  return (
    <div className="ac-card">
      <div className="ac-card-image">
        <img
          src={step.artefact.imageUrl}
          alt={`Clue #${step.stepNumber}`}
          className={`ac-card-img ${isFound ? "" : "pixelated"}`}
        />
      </div>
      <div className="ac-card-header">
        <span className="ac-clue">Clue: {step.clue}</span>
      </div>
      <div className="ac-card-actions">
        {isCurrent && !isFound && (
          <>
            <button
              className="ac-btn reveal-btn button"
              onClick={() => onReveal(step)}
            >
              <FaEye className="btn-icon " /> Reveal
            </button>

            <button
              className="ac-btn scan-btn button"
              onClick={() => onScan(step.id)}
            >
              <FaCheck className="btn-icon " /> Got it
            </button>
          </>
        )}
      </div>
      {isFound && (
        <div className="info-section">
          <div className="text-info">
            <h1 className="artefact-title">{step.artefact.title}</h1>
            <p className="artefact-subtitle">{step.artefact.creator}</p>
            <p className="artefact-meta">
              <strong>Estimated date:</strong> {step.artefact.dateCreated}
            </p>
            <p className="artefact-meta">
              <strong>Location Created:</strong> {step.artefact.locationCreated}
            </p>
            <p className="artefact-meta">
              <strong>Medium:</strong> {step.artefact.medium}
            </p>
            <div
              className="artefact-description"
              dangerouslySetInnerHTML={{ __html: step.artefact.description }}
            />
          </div>
        </div>
      )}
    </div>
  );
};

export default ArtefactsCollection;
