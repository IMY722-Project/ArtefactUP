import React from 'react';
import './ProgressSection.css';

const ProgressSection = ({
  completedHunts = 2,
  totalHunts = 5,
  totalArtefactsFound = 10,
  totalArtefacts = 20,
}) => {
  // Calculate hunt progress as a percentage
  const huntProgressPercent = Math.round((completedHunts / totalHunts) * 100);

  return (
    <section className="progress-section">
      <h2 className="progress-title">Hunt Progress</h2>
      
      <div className="progress-bar-container">
        <div
          className="progress-bar"
          style={{ width: `${huntProgressPercent}%` }}
        />
      </div>
      
      <p className="progress-text">
        {completedHunts} out of {totalHunts} hunts completed ({huntProgressPercent}%)
      </p>
      <p className="progress-text">
        {totalArtefactsFound} artefacts found out of {totalArtefacts}
      </p>
    </section>
  );
};

export default ProgressSection;
