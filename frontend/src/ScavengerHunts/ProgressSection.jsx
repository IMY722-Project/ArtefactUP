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

  const { completedHunts, totalHunts, artefactsFound, totalArtefacts } =
    progress;
  const artefactsFoundNoCurrent = artefactsFound - 1;

  const huntPercent =
    totalHunts > 0 ? Math.round((completedHunts / totalHunts) * 100) : 0;

  return (
    <section className="progress-section">
      <h2 className="progress-title">Hunt Progress</h2>
      {totalHunts > 0 ? (
        <>
          <div className="progress-bar-container">
            <div
              className="progress-bar"
              style={{ width: `${huntPercent}%` }}
            />
          </div>
          <p className="progress-text">
            {completedHunts} of {totalHunts} hunts completed ({huntPercent}%)
          </p>
          <p className="progress-text">
            {artefactsFoundNoCurrent}{" "}
            {artefactsFoundNoCurrent === 1 ? "artefact" : "artefacts"} found of{" "}
            {totalArtefacts}
          </p>
        </>
      ) : (
        <p className="progress-text">Begin a hunt!</p>
      )}
    </section>
  );
};

export default ProgressSection;
// TODO: replace w more descriptive icons
// museum quests, museum missions, quest meter, quest log, 
