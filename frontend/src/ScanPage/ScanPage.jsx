import React, { useState } from "react";
import "./ScanPage.css";
import TopCircle from "../TopCircleGeneric/TopCircle.jsx";

const ScanPage = () => {
  const [capturedImage, setCapturedImage] = useState(null);
  return (
    <div className="scan-page">
      <TopCircle pageTitle="Scan" />
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
