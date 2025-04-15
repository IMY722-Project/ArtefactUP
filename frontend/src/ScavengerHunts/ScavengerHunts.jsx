import React from "react";
import { useNavigate } from "react-router-dom";
import { useParallax } from "react-scroll-parallax";
import "./ScavengerHunts.css";

const scavengerHunts = [
  {
    id: 1,
    title: "Red Hunt",
    description:
      "Uncover artefacts that resonate with passion and history in the red-themed hunt.",
    color: "#ff5a5f",
    orbitImages: [
      "/images/mascot.png",
      "/images/mascot.png",
      "/images/mascot.png",
      "/images/mascot.png",
    ],
  },
  {
    id: 2,
    title: "Blue Hunt",
    description:
      "Dive into a cool journey through artefacts imbued with soothing blue tones.",
    color: "#1e90ff",
    orbitImages: [
      "/images/mascot.png",
      "/images/mascot.png",
      "/images/mascot.png",
      "/images/mascot.png",
    ],
  },
  {
    id: 3,
    title: "Green Hunt",
    description:
      "Explore the rich details and nature-inspired artefacts in the green hunt.",
    color: "#28a745",
    orbitImages: [
      "/images/mascot.png",
      "/images/mascot.png",
      "/images/mascot.png",
      "/images/mascot.png",
    ],
  },
  {
    id: 4,
    title: "Yellow Hunt",
    description:
      "Follow the bright trail of clues and artefacts in the yellow-themed hunt.",
    color: "#ffc107",
    orbitImages: [
      "/images/mascot.png",
      "/images/mascot.png",
      "/images/mascot.png",
      "/images/mascot.png",
    ],
  },
];

const ScavengerHunts = () => {
  const navigate = useNavigate();

  // HuntOrbit Component inside ScavengerHunts
  const HuntOrbit = ({ hunt, navigate }) => {
    // We apply parallax rotation only to the container of orbit images.
    const { ref } = useParallax({
      rotate: [-135, 360],
    });

    return (
      <div className="hunt-card" style={{ borderColor: hunt.color }}>
        <div className="hunt-header" style={{ backgroundColor: hunt.color }}>
          <h3>{hunt.title}</h3>
        </div>
        <p className="hunt-description">{hunt.description}</p>

        {/* Orbit container wrapper */}
        <div className="orbit-container">
          {/* Center Join Hunt button (remains static) */}
          <button
            className="hunt-button center-button"
            style={{ backgroundColor: hunt.color }}
            onClick={() => navigate(`/artefactsCollection`)}
          >
            Join Hunt
          </button>
          
          {/* Rotating orbit images container */}
          <div ref={ref} className="orbit-icons-container">
            {hunt.orbitImages.map((imgUrl, index) => (
              <img
                key={index}
                src={imgUrl}
                alt={`Orbit ${index + 1}`}
                className={`orbit-icon orbit-icon-${index + 1}`}
              />
            ))}
          </div>
        </div>
      </div>
    );
  };

  return (
    <section className="scavenger-hunts">
      <div className="scavenger-hunts-list">
        {scavengerHunts.map((hunt) => (
          <HuntOrbit key={hunt.id} hunt={hunt} navigate={navigate} />
        ))}
      </div>
    </section>
  );
};

export default ScavengerHunts;
