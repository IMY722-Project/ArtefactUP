import React, { useState } from "react";
import TopCircle from "../TopCircleGeneric/TopCircle.jsx";
import { useNavigate, useLocation } from "react-router-dom";
import "./ArtefactsCollection.css";
import { useHuntStore } from "../stores/useHuntStore.js";

const ArtefactsCollection = () => {
  const navigate = useNavigate();
  const { state } = useLocation();
  const huntData = state?.hunt;
  const { hunts } = useHuntStore();
  const hunt = hunts.find(h => h.id === huntData.id);

  if (!huntData) {
    navigate(-1);
    return null;
  }

  const handleScan = stepId => {
    const huntId = huntData.id;
    const artefactId = stepId;
    navigate("/scan", {
      state: { huntId, artefactId },
    });
  };

  const handleReveal = artefact => {
    navigate("/artefactDetails", { state: { artefact } });
  };

  const ClueCard = ({ step }) => {
    const [hintVisible, setHintVisible] = useState(false);
    const toggleHint = () => setHintVisible(v => !v);
    const isCurrent = step.id === hunt.currentStepId;

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
              <button
                className="ac-btn scan-btn"
                onClick={() => handleScan(step.id)}
              >
                Scan 📷
              </button>
              <div className="ac-current-actions">
                <button className="ac-btn hint-btn" onClick={toggleHint}>
                  Hint
                </button>
                <button
                  className="ac-btn reveal-btn"
                  onClick={() => handleReveal(step.artefact)}
                >
                  Reveal
                </button>
              </div>
            </>
          )}

          {!isCurrent && (
            <button
              className="ac-btn reveal-btn"
              onClick={() => handleReveal(step.artefact)}
            >
              Reveal
            </button>
          )}
        </div>
        {hintVisible && <div className="ac-hint">{step.hint}</div>}
      </div>
    );
  };

  const visibleStepIds = hunt.steps
    .filter(s => s.found || s.id === hunt.currentStepId)
    .map(s => s.id);
  const visibleSteps = (huntData?.steps || []).filter(step =>
    visibleStepIds.includes(step.id)
  );

  return (
    <div className="artefacts-collection-page">
      <TopCircle pageTitle={huntData.name} />
      <main className="ac-main">
        {visibleSteps.map(step => (
          <ClueCard key={step.id} step={step} />
        ))}
      </main>
    </div>
  );
};

export default ArtefactsCollection;
