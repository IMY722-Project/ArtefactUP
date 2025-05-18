import React from "react";
import "./Home.css";
import OperatingHours from "./OperatingHours.jsx";
import TourPackages from "./TourPackages/TourPackages.tsx";
import { useNavigate } from "react-router-dom";
import HamburgerMenu from "../HamburgerMenu/HamburgerMenu.jsx";

function Home() {
  // const [menuOpen, setMenuOpen] = useState(false);
  // const toggleMenu = () => setMenuOpen(prev => !prev);

  const navigate = useNavigate();

  return (
    <div className="home">
      <div className="menu-button">
        <HamburgerMenu />
      </div>
      <header className="hero">
        {/* <img src="/images/home_art_1.png" className="museum-illustration" /> */}
        <img src="/images/home_art_2.png" className="museum-illustration" />

        <h1>Museum Quest</h1>
        <p>Discover history, art, and innovation in the UP museums.</p>
        <button id="explore-hunts-btn" className="cta-button" onClick={() => navigate(`/scavengerHunts`)}>
          Explore Quests
        </button>
      </header>

      <div className="content-section">
        <OperatingHours />
        <TourPackages />
      </div>
    </div>
  );
}

export default Home;
