import React from "react";
import "./ScavengerHuntsPage.css";
import ScavengerHunts from "./ScavengerHunts";
import ProgressSection from "./ProgressSection";
import HamburgerMenu from "../HamburgerMenu/HamburgerMenu";

const ScavengerHuntsPage = () => {
  return (
    <div className="scavenger-hunts-page">
      {/* Wrapper for the top half-circle header and overlay */}
      <div className="top-circle-wrapper">
        <div className="top-circle">
          <div className="menu-button">
          <HamburgerMenu  />
          </div>
          <h1 className="scavenger-title">Scavenger Hunts</h1>
          <div className="progress-overlay">
            <ProgressSection
            />
          </div>
        </div>
      </div>
      {/* Existing component for displaying hunts */}
      <ScavengerHunts />
    </div>
  );
};

export default ScavengerHuntsPage;

//f9ebd7
