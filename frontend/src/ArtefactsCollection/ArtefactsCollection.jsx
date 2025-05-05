import React, { useState } from "react";
import TopCircle from "../TopCircleGeneric/TopCircle.jsx";
import { useNavigate, useLocation } from "react-router-dom";
import "./ArtefactsCollection.css";

const ArtefactsCollection = () => {
  const navigate = useNavigate();
  const { state } = useLocation();
  const hunt = state?.hunt;

  if (!hunt) {
    navigate(-1);
    return null;
  }

  const handleScan = (id) => {
    navigate("/scan");
  };
  const handleReveal = (artefact) => {
    navigate("/artefactDetails", { state: { artefact } });
  };

  const ClueCard = ({ step }) => {
    const [hintVisible, setHintVisible] = useState(false);
    const toggleHint = () => setHintVisible((v) => !v);

    return (
      <div className="ac-card">
        <div className="ac-card-header">
          <span className="ac-clue-number">Clue #{step.stepNumber}</span>
          <span className="ac-clue-question">???</span>
        </div>
        <div className="ac-card-image">
          <img
            src={step.artefact.imageUrl}
            alt={`Clue #${step.stepNumber}`}
            className="ac-image pixelated"
          />
        </div>
        <div className="ac-card-actions">
          <button
            className="ac-btn scan-btn"
            onClick={() => handleScan(step.id)}
          >
            <span role="img" aria-label="camera">
              📷
            </span>
          </button>
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
        {hintVisible && <div className="ac-hint">{step.clue}</div>}
      </div>
    );
  };

  return (
    <div className="artefacts-collection-page">
       <TopCircle pageTitle={hunt.name} />

      <main className="ac-main">
        {hunt.steps.map((step) => (
          <ClueCard key={step.id} step={step} />
        ))}
      </main>
    </div>
  );
};

export default ArtefactsCollection;
