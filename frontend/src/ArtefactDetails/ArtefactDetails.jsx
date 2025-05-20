import React from "react";
import { useLocation, useNavigate } from "react-router-dom";
import TopCircle from "../TopCircleGeneric/TopCircle.jsx";
import "./ArtefactDetails.css";

const ArtefactDetails = () => {
  const navigate = useNavigate();
  const { state } = useLocation();
  const artefact = state?.artefact;

  if (!artefact) {
    navigate(-1);
    return null;
  }

  return (
    <div className="artefact-details">
      <TopCircle pageTitle="Artefact" />

      <main className="artefact-content">
        <div className="info-section">
          <div className="image-wrapper">
            <img
              src={artefact.imageUrl}
              alt={artefact.title}
              className="artefact-image"
            />
          </div>
          <div className="text-info">
            <h1 className="artefact-title">{artefact.title}</h1>
            <p className="artefact-subtitle">{artefact.creator}</p>
            <p className="artefact-meta">
              <strong>Estimated date:</strong> {artefact.dateCreated}
            </p>
            <p className="artefact-meta">
              <strong>Location Created:</strong> {artefact.locationCreated}
            </p>
            <p className="artefact-meta">
              <strong>Medium:</strong> {artefact.medium}
            </p>
            <div
              className="artefact-description"
              dangerouslySetInnerHTML={{ __html: artefact.description }}
            />
          </div>
        </div>

        {/* Mascot Section */}
        <div className="mascot-section">
          <img src="/images/mascot.png" alt="Mascot" className="mascot-image" />
          <div className="mascot-bubble">
            <p>
              Hi there! I'm Masey, your guide. Did you know that this artefact
              is wonderfully curated at the {artefact.museumName}?
            </p>
          </div>
        </div>
      </main>
    </div>
  );
};

export default ArtefactDetails;
