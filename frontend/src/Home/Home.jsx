import React from "react";
import "./Home.css";
import OperatingHours from "./OperatingHours.jsx";
import TourPackages from "./TourPackages/TourPackages.tsx";
import { useNavigate } from "react-router-dom";
import HamburgerMenu from "../HamburgerMenu/HamburgerMenu.jsx";

function Home() {

  const navigate = useNavigate();

  return (
    <div className="home">
      <div className="menu-button">
        <HamburgerMenu />
      </div>
      <header className="hero">
        <img
          src="/images/home_art_3.png"
          className="museum-illustration"
          alt="Museum illustration"
        />
        <h1>Museum Quest</h1>
        <p>Discover history, art, and innovation in the UP museums.</p>
        <button
          id="explore-hunts-btn"
          className="cta-button button"
          onClick={() => navigate(`/scavengerHunts`)}
        >
          Explore Quests
        </button>
      </header>
      <div className="wavy-div">
        <svg  xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 320">
          <path  fill="#d05252" fill-opacity="1" d="M0,96L30,106.7C60,117,120,139,180,149.3C240,160,300,160,360,138.7C420,117,480,75,540,85.3C600,96,660,160,720,197.3C780,235,840,245,900,250.7C960,256,1020,256,1080,218.7C1140,181,1200,107,1260,85.3C1320,64,1380,96,1410,112L1440,128L1440,0L1410,0C1380,0,1320,0,1260,0C1200,0,1140,0,1080,0C1020,0,960,0,900,0C840,0,780,0,720,0C660,0,600,0,540,0C480,0,420,0,360,0C300,0,240,0,180,0C120,0,60,0,30,0L0,0Z"></path>
        </svg>
      </div>

      <div className="content-section">
        <OperatingHours />
        <TourPackages />
      </div>

      <div className="footer">
      </div>
    </div>
  );
}

export default Home;
