import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { useHuntStore } from "../stores/useHuntStore.js";
import TopCircle from "../TopCircleGeneric/TopCircle.jsx";
import "./ArtefactsCollection.css";


const ArtefactsCollection = () => {
  const navigate = useNavigate();
  const { state } = useLocation();
  const huntData = state?.hunt;
  const { hunts } = useHuntStore();
  const [currentIndex, setCurrentIndex] = useState(0);
  const hunt = hunts.find(h => h.id === huntData?.id);

  if (!huntData) {
    navigate(-1);
    return null;
  }

  // Determine which steps to show
  const visibleStepIds = hunt.steps
    .filter(s => s.found || s.id === hunt.currentStepId)
    .map(s => s.id);
  const visibleSteps = huntData.steps.filter(step =>
    visibleStepIds.includes(step.id)
  );

  // Track current index

  const currentStep = visibleSteps[currentIndex];

  // Navigation handlers
  const handlePrev = () => setCurrentIndex(i => Math.max(i - 1, 0));
  const handleNext = () =>
    setCurrentIndex(i => Math.min(i + 1, visibleSteps.length - 1));

  // Scan and reveal
  const handleScan = stepId => {
    navigate("/scan", { state: { huntId: huntData.id, artefactId: stepId } });
  };
  const handleReveal = artefact => {
    navigate("/artefactDetails", { state: { artefact } });
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

      {/* Current Clue Card */}
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

        <button
          className="ac-btn reveal-btn"
          onClick={() => onReveal(step.artefact)}
        >
          Reveal
        </button>
      </div>
      {hintVisible && <div className="ac-hint">{step.hint}</div>}
    </div>
  );
};

export default ArtefactsCollection;
