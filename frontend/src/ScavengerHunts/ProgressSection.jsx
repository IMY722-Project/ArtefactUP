import React from 'react';
import './ProgressSection.css';
// import { GiAchievement } from "react-icons/gi";
import { FaTrophy } from "react-icons/fa";
import { GiTiedScroll } from "react-icons/gi";
import { GiTreasureMap } from "react-icons/gi";

import { RiTreasureMapLine } from "react-icons/ri";
import { RiCopperCoinLine } from "react-icons/ri";







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
      {/* <h2 className="progress-title">Quest Meter</h2> */}

      <div className="progress-bar-container">
        <div
          className="progress-bar"
          style={{ width: `${huntProgressPercent}%` }}
        />
      </div>

      <div className='progress-markers-wrapper'>
        <div>
          <p className="progress-text">
            {completedHunts} / {totalHunts}
          </p>

          <div className='progress-icon'>
            <GiTreasureMap />
            <RiTreasureMapLine/>
          </div>
        </div>
        <div>
          <p className="progress-text">
            {totalArtefactsFound} / {totalArtefacts}
          </p>

          <div className='progress-icon'>
            <GiTiedScroll />
            <RiCopperCoinLine/>
          </div>
        </div>
      </div>
    </section>
  );
};

export default ProgressSection;
// TODO: replace w more descriptive icons
// museum quests, museum missions, quest meter, quest log, 
