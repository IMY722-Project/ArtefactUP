import React from "react";
import "./Home.css";
import OperatingHours from "./OperatingHours.jsx";
import TourPackages from "./TourPackages/TourPackages.tsx";
import { useNavigate } from "react-router-dom";

function Home() {
  // const [menuOpen, setMenuOpen] = useState(false);
  // const toggleMenu = () => setMenuOpen(prev => !prev);
  
  const navigate = useNavigate();

  return (
    <div className="home">
      <nav className="navbar">
        <div className="navbar-container">
          <div className="brand">
            <a href="/">University of Pretoria</a>
          </div>
        </div>
      </nav>

      <header className="hero">
        <h1>Welcome to the Museum Scavenger hunts</h1>
        <p>Discover history, art, and innovation on campus.</p>
        <button className="cta-button" onClick={() => navigate(`/scavengerHunts`)}>
          Explore Hunts
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
