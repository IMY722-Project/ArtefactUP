import React, { useState, useEffect } from "react";
import { FaStar } from "react-icons/fa";
import { useNavigate } from "react-router-dom";
import "./ScavengerHunts.css";
import { getSessionId } from "../utils/session.js";
import { API } from "../utils/config.js";
import Spinner from "../Loader/LoadingIndicator.jsx";
import ErrorMessage from "../Error/ErrorMessage.jsx";
import { useHuntStore } from "../stores/useHuntStore.js";

const ScavengerHunts = () => {
  const [huntsData, setHuntsData] = useState([]);
  const [loading, setLoading]   = useState(true);
  const [error, setError]       = useState(null);
  const navigate = useNavigate();
  const { hunts, setHunts, startHunt } = useHuntStore();

  useEffect(() => {
    fetch(`${API}/api/hunts/all`)
      .then(res => {
        if (!res.ok) throw new Error(`HTTP ${res.status}`);
        return res.json();
      })
      .then(data => {
        setHuntsData(data);
        if (!hunts.length) {
          const transformed = data.map(h => ({
            id: h.id,
            currentStepId: h.steps[0]?.id ?? null,
            steps: h.steps.map(s => ({ id: s.id, found: false }))
          }));
          setHunts(transformed);
        }
        setLoading(false);
      })
      .catch(err => {
        console.error("Failed to load hunts:", err);
        setError(err.message);
        setLoading(false);
      });
  }, [hunts.length, setHunts]);

  if (loading) return <Spinner />;
  if (error)
    return (
      <ErrorMessage
        message={`Error loading hunts: ${error}`}
        onRetry={() => window.location.reload()}
      />
    );

    const handleStartHunt = async hunt => {
      const local = hunts.find(h => h.id === hunt.id);
      if (
        local?.started
      ) {
        return navigate("/artefactsCollection", { state: { hunt } });
      }

      const sessionId = getSessionId();
      try {
        const res = await fetch(`${API}/api/hunts/progress/start/${hunt.id}`, {
          method: "POST",
          headers: { "Session-id": sessionId },
        });
        if (!res.ok) {
          throw new Error(`Failed to start hunt (${res.status})`);
        }

        startHunt(hunt.id);
        navigate("/artefactsCollection", { state: { hunt } });
      } catch (e) {
        console.error("Error starting hunt:", e);
        alert("Unable to start hunt. Please try again.");
      }
    };

  return (
    <section className="scavenger-hunts">
      <div className="hunt-path">
        {huntsData.map((hunt, idx) => (
          <div
            key={hunt.id}
            className="hunt-circle"
            onClick={() => handleStartHunt(hunt)}
          >
            <FaStar className="circle-icon" />
            <span className="hunt-label">{hunt.name}</span>
          </div>
        ))}
      </div>
    </section>
  );
};

export default ScavengerHunts;
