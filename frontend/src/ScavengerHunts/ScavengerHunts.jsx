import React from "react";
import "./ScavengerHunts.css";
import { useNavigate } from "react-router-dom";

const scavengerHunts = [
  {
    id: 1,
    title: "Red Hunt",
    description: "Uncover artefacts that resonate with passion and history in the red-themed hunt.",
    color: "#ff5a5f",
  },
  {
    id: 2,
    title: "Blue Hunt",
    description: "Dive into a cool journey through artefacts imbued with soothing blue tones.",
    color: "#1e90ff",
  },
  {
    id: 3,
    title: "Green Hunt",
    description: "Explore the rich details and nature-inspired artefacts in the green hunt.",
    color: "#28a745",
  },
  {
    id: 4,
    title: "Yellow Hunt",
    description: "Follow the bright trail of clues and artefacts in the yellow-themed hunt.",
    color: "#ffc107",
  },
];

const ScavengerHunts = () => {
const navigate = useNavigate();

return (
    <section className="scavenger-hunts">
        <h2 className="scavenger-hunts-title">Scavenger Hunts</h2>
        <div className="scavenger-hunts-list">
            {scavengerHunts.map((hunt) => (
                <div key={hunt.id} className="hunt-card" style={{ borderColor: hunt.color }}>
                    <div className="hunt-header" style={{ backgroundColor: hunt.color }}>
                        <h3>{hunt.title}</h3>
                    </div>
                    <p className="hunt-description">{hunt.description}</p>
                    <button
                        className="hunt-button"
                        style={{ backgroundColor: hunt.color }}
                        onClick={() => navigate(`/artefactsCollection`)}
                    >
                        Join Hunt
                    </button>
                </div>
            ))}
        </div>
    </section>
);
};

export default ScavengerHunts;
