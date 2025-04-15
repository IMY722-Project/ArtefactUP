import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./ScanPage.css";

const ScanPage = () => {
  const [capturedImage, setCapturedImage] = useState(null);
  const navigate = useNavigate();
  return (
    <div className="scan-page">
      {/* Header with Back Button and Title */}
      <header className="scan-header">
        <button className="back-button" onClick={() => navigate(-1)}>
          ←
        </button>
        <h1 className="scan-title">Scan Artefact</h1>
      </header>

      <div className="scan-container">
        <img
          src={capturedImage}
          alt="Captured Artefact"
          className="captured-image"
        />
        <div className="overlay">
          <p>Align the artefact within the frame</p>
        </div>
      </div>

      <div className="scan-actions">
        {!capturedImage && <button className="capture-button">Capture</button>}
        {capturedImage && (
          <button
            onClick={() => setCapturedImage(null)}
            className="retake-button"
          >
            Retake
          </button>
        )}
      </div>
    </div>
  );
};

export default ScanPage;
