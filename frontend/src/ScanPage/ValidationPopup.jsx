import React from "react";
import "./ValidationPopup.css";

const ValidationPopup = ({ onRetry, onReveal, onClose }) => {
  return (
    <div className="vp-overlay" onClick={onClose}>
      <div className="vp-modal" onClick={e => e.stopPropagation()}>
        <h2 className="vp-title">Incorrect</h2>
        <p className="vp-subtext">
          The image you submitted is not the correct artefact.
        </p>
        <div className="vp-buttons">
          <button className="vp-btn vp-retry" onClick={onRetry}>
            Retry
          </button>
          <button className="vp-btn vp-reveal" onClick={onReveal}>
            Reveal Artefact
          </button>
        </div>
      </div>
    </div>
  );
};

export default ValidationPopup;
