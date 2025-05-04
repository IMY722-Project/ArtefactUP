import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { MdArrowBackIosNew } from "react-icons/md";
import "./ScanPage.css";

const ScanPage = () => {
  const [capturedImage, setCapturedImage] = useState(null);
  const navigate = useNavigate();
  return (
    <div className="scan-page">
      {/* Header with Back Button and Title */}
      <div className="top-circle-ac">
        <div className="close-button-container-scan">
          <button
            className="close-button-ac"
            onClick={() => navigate(-1)}
          >
            <MdArrowBackIosNew size={30} />
          </button>
        </div>
        <div className="header-title-container">
        <h1 className="page-title">Scan</h1>
        </div>
      </div>

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
