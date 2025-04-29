import React from 'react';
import './ProgressSection.css';
import { TiStar } from "react-icons/ti";


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
      <h2 className="progress-title">Quest Meter</h2>
      
      <div className="progress-bar-container">
        <div
          className="progress-bar"
          style={{ width: `${huntProgressPercent}%` }}
        />
      </div>
      
      <p className="progress-text">
        {completedHunts} / {totalHunts} <TiStar/> 
      </p>
      <p className="progress-text">
        {totalArtefactsFound} / {totalArtefacts} <TiStar/> 
      </p>
    </section>
  );
};

export default ProgressSection;
// TODO: replace w more descriptive icons
// museum quests, museum missions, quest meter, quest log, 
