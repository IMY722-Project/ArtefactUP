import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "./ScavengerHunts.css";
import { getSessionId } from "../utils/session.js";
import { API } from "../utils/config.js";
import Spinner from "../Loader/LoadingIndicator.jsx";
import ErrorMessage from "../Error/ErrorMessage.jsx";
import { useHuntStore } from "../stores/useHuntStore.js";

const ScavengerHunts = () => {
  const [huntsData, setHuntsData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();
  const { hunts, setHunts, startHunt, completeHunt, goToStep, attemptedHunts } =
    useHuntStore();

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
            steps: h.steps.map(s => ({ id: s.id, found: false })),
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

    const sessionId = getSessionId();

    if (local?.completed) {
      return navigate("/artefactsCollection", { state: { hunt } });
    }

    if (attemptedHunts.includes(hunt.id)) {
      // startHunt(hunt.id);
      try {
        const res = await fetch(`${API}/api/hunts/progress/${hunt.id}`, {
          method: "GET",
          headers: { "Session-id": sessionId },
        });
        if (!res.ok) {
          throw new Error(`Failed to fetch hunt progress (${res.status})`);
        }
        const huntData = await res.json();
        console.log("Hunt data:", huntData);
        console.log("Local hunt data:", local);

        if (huntData.completed && !local?.completed) {
          completeHunt(hunt.id);
          console.log("Hunt completed:");
        } else if (huntData.currentStep.id !== local.currentStepId) {
          goToStep(hunt.id, huntData.currentStep.id);
          console.log("Hunt step changed:", huntData.currentStepId);
        }
      } catch (e) {
        console.error("Error hunting hunt:", e);
        alert("Unable to try. Please try again.");
      }
      console.log("Local hunt data:", local);
      console.log("Hunt data:", hunt);
      return navigate("/artefactsCollection", { state: { hunt } });
    }

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
            <img
              src="/images/map_gold.png"
              className="circle-icon"
              alt="map_gold"
            />
            <span className="hunt-label">{hunt.name}</span>
          </div>
        ))}
      </div>
    </section>
  );
};

export default ScavengerHunts;
