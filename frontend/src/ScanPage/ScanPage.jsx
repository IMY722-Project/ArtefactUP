// ScanPage.jsx
import React, { useState, useRef, useEffect } from "react";
import "./ScanPage.css";
import TopCircle from "../TopCircleGeneric/TopCircle.jsx";
import { getSessionId } from "../utils/session.js";

const ScanPage = () => {
  const [capturedImage, setCapturedImage] = useState(null);
  const [submitting, setSubmitting] = useState(false);
  const videoRef = useRef(null);
  const streamRef = useRef(null);

  useEffect(() => {
    if (!capturedImage) {
      navigator.mediaDevices
        .getUserMedia({ video: { facingMode: "environment" }, audio: false })
        .then((stream) => {
          streamRef.current = stream;
          videoRef.current.srcObject = stream;
        })
        .catch((err) => {
          console.error("Error accessing camera:", err);
        });
    }
    return () => {
      streamRef.current?.getTracks().forEach((t) => t.stop());
    };
  }, [capturedImage]);

  const handleCapture = () => {
    const video = videoRef.current;
    const canvas = document.createElement("canvas");
    canvas.width = video.videoWidth;
    canvas.height = video.videoHeight;
    canvas.getContext("2d").drawImage(video, 0, 0);
    setCapturedImage(canvas.toDataURL("image/jpeg"));
    streamRef.current?.getTracks().forEach((t) => t.stop());
  };

  const handleRetake = () => setCapturedImage(null);

const huntId = 1  //TODO set this

const handleSubmit = async () => {
  if (!capturedImage) return;
  setSubmitting(true);

  try {
    
    const resBlob = await fetch(capturedImage);
    const blob = await resBlob.blob();

    const formData = new FormData();
    formData.append("imageFile", blob, "scan.jpg");


    const sessionId =  getSessionId();

    const response = await fetch(
      `/api/hunts/steps/${huntId}/validate`,
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
    console.log("Validation result:", result);
    // TODO: navigate onward or show success message

  } catch (err) {
    console.error("Validation failed:", err);

    // TODO: show an error to the user
  } finally {
    setSubmitting(false);
  }
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
    </div>
  );
};

export default ScanPage;
