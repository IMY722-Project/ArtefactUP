import React, { useState } from "react";
import { FaTimes } from "react-icons/fa";
import "./HamburgerMenu.css";
import { TiThMenu } from "react-icons/ti";

const HamburgerMenu = () => {
  const [menuOpen, setMenuOpen] = useState(false);
  const toggleMenu = () => setMenuOpen((prev) => !prev);
  const links = [
        { label: "Home", href: "/" },
        { label: "Quests", href: "/scavengerHunts" },
        { label: "About", href: "/about" },
        { label: "Chat", href: "/chat" },
      ];
  return (
    <div className="hamburger-menu">
      {/* Toggle button (hamburger icon) */}
      <button className="menu-toggle" onClick={toggleMenu} aria-label="Toggle menu">
      <TiThMenu size={50}/>
      </button>

      {/* Side Menu */}
      <nav className={`menu ${menuOpen ? "open" : ""}`}>
        <button className="close-menu" onClick={toggleMenu} aria-label="Close menu">
          <FaTimes />
        </button>
        <ul>
          {links.map((link, index) => (
            <li key={index} onClick={toggleMenu}>
              <a href={link.href}>{link.label}</a>
            </li>
          ))}
        </ul>
      </nav>
    </div>
  );
};

export default HamburgerMenu;
