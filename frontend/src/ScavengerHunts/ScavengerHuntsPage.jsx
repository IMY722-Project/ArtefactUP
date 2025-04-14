import React from "react";
import "./ScavengerHuntsPage.css";
import ScavengerHunts from "./ScavengerHunts";
import ProgressSection from "./ProgressSection";

const ScavengerHuntsPage = () => {
  return (
    <div className="scavenger-hunts-page">
      {/* Wrapper for the top half-circle header and overlay */}
      <div className="top-circle-wrapper">
        <div className="top-circle">
          <h1 className="scavenger-title">Scavenger Hunts</h1>
        </div>
        <div className="progress-overlay">
          <ProgressSection
            completedHunts={3}
            totalHunts={5}
            totalArtefactsFound={12}
            totalArtefacts={20}
          />
        </div>
      </div>
      {/* Existing component for displaying hunts */}
      <ScavengerHunts />
    </div>
  );
};

export default ScavengerHuntsPage;


//f9ebd7