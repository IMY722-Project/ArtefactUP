import React, { useState, useRef, useEffect } from "react";
import "./ScanPage.css";
import TopCircle from "../TopCircleGeneric/TopCircle.jsx";
import { getSessionId } from "../utils/session.js";
import ValidationPopup from "./ValidationPopup.jsx";
import { useNavigate } from "react-router-dom";
import { useLocation } from "react-router-dom";
import { API } from "../utils/config.js";

const ScanPage = () => {
  const { state } = useLocation();
   // Will remove after discussion regarding scavenger hunt
  // eslint-disable-next-line no-unused-vars
  const { huntId, artefactId } = state || {};
  const navigate = useNavigate();
  const [capturedImage, setCapturedImage] = useState(null);
  const [submitting, setSubmitting] = useState(false);
  const videoRef = useRef(null);
  const streamRef = useRef(null);
  const [showIncorrect, setShowIncorrect] = useState(false);
  const [currentStep, setCurrentStep] = useState({});

  useEffect(() => {
    if (!capturedImage) {
      navigator.mediaDevices
        .getUserMedia({ video: { facingMode: "environment" }, audio: false })
        .then(stream => {
          streamRef.current = stream;
          videoRef.current.srcObject = stream;
        })
        .catch(err => {
          console.error("Error accessing camera:", err);
        });
    }
    return () => {
      streamRef.current?.getTracks().forEach(t => t.stop());
    };
  }, [capturedImage]);

  const handleCapture = () => {
    const video = videoRef.current;
    const canvas = document.createElement("canvas");
    canvas.width = video.videoWidth;
    canvas.height = video.videoHeight;
    canvas.getContext("2d").drawImage(video, 0, 0);
    setCapturedImage(canvas.toDataURL("image/jpeg"));
    streamRef.current?.getTracks().forEach(t => t.stop());
  };

  const handleRetake = () => setCapturedImage(null);

  const handleSubmit = async () => {
    if (!capturedImage) return;
    setSubmitting(true);

    try {
      const resBlob = await fetch(capturedImage);
      const blob = await resBlob.blob();

      const formData = new FormData();
      formData.append("imageFile", blob, "scan.jpg");

      const sessionId = getSessionId();

      const response = await fetch(`${API}/api/hunts/steps/${huntId}/validate`, {
        method: "POST",
        headers: {
          "Session-id": sessionId,
        },
        body: formData,
      });

      if (!response.ok) {
        throw new Error(`Server returned ${response.status}`);
      }

      const result = await response.json();
      setCurrentStep(result.scavengerHuntStep);
      if (!result.matched) {
        setShowIncorrect(true);
      } else {
        navigate("/artefactDetails", { state: { artefact: currentStep.artefact } });
      }
    } catch (err) {
      console.error("Validation failed:", err);
      console.error(err);
      alert("Error validating image.");
    } finally {
      setSubmitting(false);
    }
  };

  const retry = () => {
    setShowIncorrect(false);
    handleRetake();
  };
  const reveal = () => {
    setShowIncorrect(false);
    navigate("/artefactDetails", { state: { artefact: currentStep.artefact } });
  };

  return (
    <div className="scan-page">
      <TopCircle pageTitle="Scan" />

      <div className="scan-container">
        {!capturedImage ? (
          <video ref={videoRef} className="webcam-feed" autoPlay playsInline />
        ) : (
          <img
            src={capturedImage}
            alt="Captured Artefact"
            className="captured-image"
          />
        )}
        <div className="overlay">
          <p>Align the artefact within the frame</p>
        </div>
      </div>

      <div className="scan-actions">
        {!capturedImage ? (
          <button className="capture-button" onClick={handleCapture}>
            Capture
          </button>
        ) : (
          <>
            <button className="retake-button" onClick={handleRetake}>
              Retake
            </button>
            <button
              className="submit-button"
              onClick={handleSubmit}
              disabled={submitting}
            >
              {submitting ? "Submitting..." : "Submit"}
            </button>
          </>
        )}
      </div>

      {showIncorrect && (
        <ValidationPopup
          onRetry={retry}
          onReveal={reveal}
          onClose={() => setShowIncorrect(false)}
        />
      )}
    </div>
  );
};

export default ScanPage;
