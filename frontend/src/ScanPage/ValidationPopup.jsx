import React from "react";
import "./ValidationPopup.css";

const ValidationPopup = ({ isCorrect, onRetry, onReveal, onClose }) => {
  return (
    <div className="vp-overlay" onClick={onClose}>
      <div className="vp-modal" onClick={e => e.stopPropagation()}>
        <div className="vp-header">
          <h2 className="vp-title">
            {isCorrect
              ? "Correct Artefact"
              : "Incorrect Artefact"}
            </h2>
        </div>
        <div className="vp-content">
          <p className="vp-subtext">
            {isCorrect
              ? "The image you submitted is the correct artefact."
              : "The image you submitted is not the correct artefact."}
          </p>
          <div className="vp-buttons">
            {!isCorrect && (
            <button className="vp-btn vp-retry" onClick={onRetry}>
              Retry
            </button>
            )}
            <button className="vp-btn vp-reveal" onClick={onReveal}>
              {isCorrect ? "View Artefact" : "Reveal Artefact"}
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ValidationPopup;
