import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './ScavengerHunts.css';
import { getSessionId } from '../utils/session.js';
import { API } from '../utils/config.js';
import Spinner from '../Loader/LoadingIndicator.jsx';
import ErrorMessage from '../Error/ErrorMessage.jsx';
import { useHuntStore } from '../stores/useHuntStore.js';
import { FaCameraRetro } from "react-icons/fa";


const COLOR_PALETTE = ['orange', 'blue', 'green'];

const ScavengerHunts = () => {
  const [huntsData, setHuntsData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();
  const { setHunts, startHunt } = useHuntStore();

  useEffect(() => {
    fetch(`${API}/api/hunts/all`)
      .then(res => {
        if (!res.ok) throw new Error(`HTTP ${res.status}`);
        return res.json();
      })
      .then(data => {
        setHuntsData(data);
        const transformed = data.map(h => ({
          id: h.id,
          currentStepId: h.steps[0]?.id ?? null,
          steps: h.steps.map(s => ({ id: s.id, found: false })),
        }));
        setHunts(transformed);
        setLoading(false);
      })
      .catch(err => {
        console.error('Failed to load hunts:', err);
        setError(err.message);
        setLoading(false);
      });
  }, []);

  if (loading) return <Spinner />;
  if (error)
    return (
      <ErrorMessage
        message={`Error loading hunts: ${error}`}
        onRetry={() => window.location.reload()}
      />
    );

  const HuntOrbit = ({ hunt, index }) => {
    const color = COLOR_PALETTE[index % COLOR_PALETTE.length];

    // grab up to 4 artefact images
    // TODO: map to treasuremap images
    const orbitImages = hunt.steps
      .map(step => step.artefact.imageUrl)
      .filter(Boolean)
      .slice(0, 4);

    const handleStart = async () => {
      const sessionId = getSessionId();
      try {
        const res = await fetch(
          `${API}/api/hunts/progress/start/${hunt.id}`,
          {
            method: 'POST',
            headers: { 'Session-id': sessionId },
          }
        );
        if (!res.ok) throw new Error(`Start failed (${res.status})`);
        startHunt(hunt.id);
        navigate('/artefactsCollection', { state: { hunt } });
      } catch (e) {
        console.error('Error starting hunt:', e);
        alert('Unable to start hunt. Please try again.');
      }
    };



    return (
      <div className={`hunt-card ${color}Color`} onClick={handleStart}>
        <div className={`hunt-header ${color}Color`}>
          <h3>{hunt.name}</h3>
        </div>

        <div className="orbit-container">

          <div className="orbit-icons-container">
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

      {/* <div className={`hunt-explanation`}>
        <div className={`hunt-header `}>
          <h3>Choose a Quest</h3>
        </div>
          <p>when you find an artifact, use <FaCameraRetro/> to scan it </p>
      </div> */}

      <div className="scavenger-hunts-list">

        {huntsData.map((hunt, idx) => (
          <HuntOrbit key={hunt.id} hunt={hunt} index={idx} />
        ))}
      </div>
    </section>
  );
};

export default ScavengerHunts;

/*

*/


