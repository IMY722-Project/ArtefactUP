import React, { useState } from 'react';
// Update the import path for TourPackages
import TourPackages from './TourPackages/TourPackages.tsx';
import OperatingHours from './OperatingHours.jsx';
import './Home.css';

function Home() {
  const [menuOpen, setMenuOpen] = useState(false);
  const toggleMenu = () => setMenuOpen(prev => !prev);

  return (
    <>
    <div className="home">
    <nav className="navbar">
      <div className="navbar-container">
        <div className="brand">
          <a href="/">University Museum</a>
        </div>
        <div className={`nav-links ${menuOpen ? 'open' : ''}`}>
          <a href="#about">About</a>
          <a href="#exhibits">Exhibits</a>
          <a href="#events">Events</a>
          <a href="#contact">Contact</a>
        </div>
        <button className="menu-btn" onClick={toggleMenu} aria-label="Toggle navigation">
          <span className="menu-icon"></span>
        </button>
      </div>
    </nav>

    <header className="hero">
      <h1>Welcome to the University Museum</h1>
      <p>Discover history, art, and innovation on campus.</p>
      <a href="#walkthrough" className="cta-button">Explore Now</a>
    </header>

    <div>
    <OperatingHours />
      <TourPackages />
    </div>
    </div>
    </>
  );
}

export default Home;
