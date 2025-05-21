// src/components/ProgressSection.jsx
import React, { useEffect, useState } from "react";
import Spinner from "../Loader/LoadingIndicator.jsx";
import { API } from "../utils/config.js";
import { getSessionId } from "../utils/session.js";
import "./ProgressSection.css";

const ProgressSection = () => {
  const [progress, setProgress] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    const sessionId = getSessionId();
    fetch(`${API}/api/hunts/progress/completed/count`, {
      headers: { "Session-id": sessionId },
    })
      .then(res => {
        if (!res.ok) throw new Error(`HTTP ${res.status}`);
        return res.json();
      })
      .then(data => {
        setProgress({
          completedHunts: data.huntsCompleted,
          totalHunts: data.huntsAttempted,
          artefactsFound: data.artefactsFound,
          totalArtefacts: data.totalArtefactsAvailable,
        });
      })
      .catch(err => {
        console.error("Progress fetch failed:", err);
        setError("Unable to load progress");
      });
  }, []);

  if (error) {
    return <p className="progress-error">{error}</p>;
  }
  if (!progress) {
    return <Spinner />;
  }

  const { completedHunts, totalHunts, artefactsFound,  totalArtefacts, } =
    progress;

  const huntPercent =
    totalHunts > 0 ? Math.round((completedHunts / totalHunts) * 100) : 0;

  return (
    <section className="progress-section">
      {totalHunts > 0 ? (
        <>
          <div className="progress-bar-container">
            <div
              className="progress-bar"
              style={{ width: `${huntPercent}%` }}
            />
          </div>
          <div className='progress-markers-wrapper'>
            <div>
              <div className='progress-icon-div'>
                <img src='/images/map_brown.png' className='progress-icon' alt="Map icon" />
              </div>

              <p className="progress-text">
                {completedHunts} / {totalHunts} Quests
              </p>
            </div>
            <div>
              <div className='progress-icon-div'>
                <img src='/images/frame_brown.png' className='progress-icon' alt="Frame icon" />
              </div>
              <p className="progress-text">
                {artefactsFound}{" "}
                / {totalArtefacts} Artefacts
              </p>

            </div>
          </div>
        </>
      ) : (
        <p className="progress-text">Begin a hunt!</p>
      )}
    </section>
  );
};

export default ProgressSection;
