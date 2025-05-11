import React from "react";
import { useNavigate } from "react-router-dom";
import { useParallax } from "react-scroll-parallax";
import "./ScavengerHunts.css";

const scavengerHunts = [
  {
    id: 1,
    title: "The Crimson Quest",
    description:
      "Uncover artefacts that resonate with passion and history in the red-themed hunt.",
    color: "#ff5a5f",
    orbitImages: [
      "/images/map_red.png"
    ],
  },
  {
    id: 2,
    title: "The Cobalt Quest",
    description:
      "Dive into a cool journey through artefacts imbued with soothing blue tones.",
    color: "#1e90ff",
    orbitImages: [
      "/images/map_blue.png"
    ],
  },
  {
    id: 3,
    title: "The Gold Quest",
    description:
      "Explore the rich details and nature-inspired artefacts in the green hunt.",
    color: "#28a745",
    orbitImages: [
      "/images/map_gold.png"
    ],
  },
];

const ScavengerHunts = () => {
  const navigate = useNavigate();

  // HuntOrbit Component inside ScavengerHunts
  const HuntOrbit = ({ hunt, navigate }) => {
    // We apply parallax rotation only to the container of orbit images.
    // const { ref } = useParallax({
    //   rotate: [-135, 360],
    // });

    return (
      <div className="hunt-card" style={{ '--hunt-color': hunt.color }} onClick={() => navigate(`/artefactsCollection`)}
>
        <div className="hunt-header">
          <h3>{hunt.title}</h3>
        </div>
        {/* <p className="hunt-description">{hunt.description}</p> */}

        {/* Orbit container wrapper */}
        <div className="orbit-container">
          {/* Center Join Hunt button (remains static) */}
          
          {/* Rotating orbit images container */}
          <div 
          className="orbit-icons-container">
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
        {/* <div className="btnDiv">
          <button
            className="hunt-button center-button"
            
            onClick={() => navigate(`/artefactsCollection`)}
          >
            Explore
          </button>
          </div> */}
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
