import React from "react";
import "./ScavengerHuntsPage.css";
import ScavengerHunts from "./ScavengerHunts";
import ProgressSection from "./ProgressSection";
import HamburgerMenu from "../HamburgerMenu/HamburgerMenu";




const ScavengerHuntsPage = () => {
  return (
    <div className="scavenger-hunts-page-wrapper">
    <div className="scavenger-hunts-page">
      <div className="top-circle-wrapper">
        <div className="top-circle">
          <div className="menu-button">
            <HamburgerMenu />
          </div>
          <div className="scavenger-header">
            <div className="museum-icon">
              <img src="/images/museum_illustration.png" alt="Museum Illustration"/>
            </div>
            <h1 className="scavenger-title">Museum Quests</h1>
          </div>
          <div className="progress-overlay">
            <ProgressSection
            />
          </div>
        </div>
      </div>
      <ScavengerHunts />
      <div className="hunt-footer"></div>
    </div>
    </div>
  );
};

export default ScavengerHuntsPage;

