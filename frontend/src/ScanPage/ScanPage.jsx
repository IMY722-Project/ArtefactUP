import React, { useState, useRef, useEffect } from "react";
import "./ScanPage.css";
import TopCircle from "../TopCircleGeneric/TopCircle.jsx";
import { getSessionId } from "../utils/session.js";
import ValidationPopup from "./ValidationPopup.jsx";
import { useNavigate } from "react-router-dom";
import { useLocation } from "react-router-dom";
import { API } from "../utils/config.js";
import { useHuntStore } from "../stores/useHuntStore.js";

const ScanPage = () => {
  const { state } = useLocation();
  const { huntId, artefactId,current} = state || {};
  const navigate = useNavigate();
  const [capturedImage, setCapturedImage] = useState(null);
  const [submitting, setSubmitting] = useState(false);
  const videoRef = useRef(null);
  const streamRef = useRef(null);
  const [showCorrect, setShowCorrect] = useState(false);
  const [showIncorrect, setShowIncorrect] = useState(false);
  const { hunts, markStepFound, goToStep } = useHuntStore();

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

      const response = await fetch(
        `${API}/api/hunts/steps/${huntId}/validate`,
        {
          method: "POST",
          headers: {
            "Session-id": sessionId,
          },
          body: formData,
        }
      );

      if (!response.ok) {
        throw new Error(`Server returned ${response.status}`);
      }

      const result = await response.json();
      if (!result.matched) {
        setShowIncorrect(true);
      } else {
        markStepFound(huntId, artefactId);
        setShowCorrect(true);
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
  const reveal = async () => {
    setShowIncorrect(false);
    const sessionId = getSessionId();
        try {
          const res = await fetch(`${API}/api/hunts/steps/${huntId}/reveal`, {
            method: "POST",
            headers: { "Session-id": sessionId },
          });
          if (!res.ok) {
            throw new Error(`Reveal failed (${res.status})`);
          }
          markStepFound(huntId, artefactId);
    
          const thisHunt = hunts.find(h => h.id === huntId);
          const idx = thisHunt.steps.findIndex(s => s.id === current.id);
          const nextStepObj = thisHunt.steps[idx + 1];
          if (nextStepObj) {
            goToStep(huntId, nextStepObj.id);
          }
          navigate(-1);
        } catch (e) {
          console.error("Error revealing artefact:", e);
          alert("Sorry, could not reveal artefact. Please try again.");
        }
  };

  const navCorrect = () => {
    setShowCorrect(false);
    navigate(-1);
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
          isCorrect={false}
          onRetry={retry}
          onReveal={reveal}
          onClose={() => setShowIncorrect(false)}
        />
      )}
      {showCorrect && (
        <ValidationPopup
          isCorrect={true}
          onRetry={null}
          onReveal={navCorrect}
          onClose={() => setShowCorrect(false)}
        />
      )}
    </div>
  );
};

export default ScanPage;
