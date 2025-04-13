import React, { useState } from "react";

import "./ScanPage.css";

const ScanPage = () => {
  const [capturedImage, setCapturedImage] = useState(null);

  return (
    <div className="scan-page">
      {/* Header with Back Button and Title */}
      <header className="scan-header">
        <button
          className="back-button"
          onClick={() => (window.location.href = "/artefactsCollection")}
        >
          ←
        </button>
        <h1 className="scan-title">Scan Artefact</h1>
      </header>

      {/* Camera Preview / Captured Image */}
      <div className="scan-container">
        {/* {!capturedImage ? (
          <Webcam
            audio={false}
            height={400}
            ref={webcamRef}
            screenshotFormat="image/jpeg"
            width={"100%"}
            videoConstraints={videoConstraints}
            className="webcam-feed"
          />
        ) : (
          <img src={capturedImage} alt="Captured Artefact" className="captured-image" />
        )} */}

        <img
          src={capturedImage}
          alt="Captured Artefact"
          className="captured-image"
        />

        {/* Overlay with instructions */}
        <div className="overlay">
          <p>Align the artefact within the frame</p>
        </div>
      </div>

      {/* Action Buttons */}
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
