import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useParallax } from "react-scroll-parallax";
import "./ScavengerHunts.css";

const COLOR_PALETTE = [
  "#FF5C0C",
];

const ScavengerHunts = () => {
  const [hunts, setHunts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    fetch("/api/hunts/all")
      .then(res => {
        if (!res.ok) throw new Error(`HTTP ${res.status}`);
        return res.json();
      })
      .then(data => {
        setHunts(data);
        setLoading(false);
      })
      .catch(err => {
        console.error("Failed to load hunts:", err);
        setError(err.message);
        setLoading(false);
      });
  }, []);

  if (loading) return <p className="loading">Loading hunts…</p>;
  if (error) return <p className="error">Error loading hunts: {error}</p>;

  const HuntOrbit = ({ hunt, index }) => {
    // pick a color based on index
    const color = COLOR_PALETTE[index % COLOR_PALETTE.length];

    // parallax rotate only the orbit images
    const { ref } = useParallax({ rotate: [-135, 360] });

    // take up to 4 images from steps
    const orbitImages = hunt.steps
      .map(step => step.artefact.imageUrl)
      .filter(url => url)
      .slice(0, 4);

    return (
      <div className="hunt-card" style={{ borderColor: color }}>
        <div className="hunt-header" style={{ backgroundColor: color }}>
          <h3>{hunt.name}</h3>
        </div>
        <p className="hunt-description">{hunt.description}</p>

        <div className="orbit-container">
          {/* Static center button */}
          <button
            className="hunt-button center-button"
            style={{ backgroundColor: color }}
            onClick={() =>
              navigate("/artefactsCollection", { state: { hunt } })
            }
          >
            Try Hunt
          </button>
          {/* Rotating orbit images */}
          <div ref={ref} className="orbit-icons-container">
            {orbitImages.map((url, i) => (
              <img
                key={i}
                src={url}
                alt={`${hunt.name} artefact ${i + 1}`}
                className={`orbit-icon orbit-icon-${i + 1}`}
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
        {hunts.map((hunt, idx) => (
          <HuntOrbit key={hunt.id} hunt={hunt} index={idx} />
        ))}
      </div>
    </section>
  );
};

export default ScavengerHunts;
