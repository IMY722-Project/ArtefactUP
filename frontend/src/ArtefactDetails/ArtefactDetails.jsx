import React from 'react';
import './ArtefactDetails.css';
import { FaTimes } from 'react-icons/fa';

const ArtefactDetails = () => {
  return (
    <div className="artefact-details">
      {/* Header with a Close Button */}
      <header className="artefact-header">
        <button
            className="close-button"
            onClick={() => (window.location.href = "/artefactsCollection")}
          >
            <FaTimes />
          </button>
      </header>

      {/* Main Content Container */}
      <main className="artefact-content">
        {/* Info Section: Artefact Image and Details */}
        <div className="info-section">
          <div className="image-wrapper">
            <img 
              src="/images/Image-courtesy-of-UP-Museums.jpg" 
              alt="Mona Lisa" 
              className="artefact-image"
            />
          </div>
          <div className="text-info">
            <h1 className="artefact-title">Mona Lisa</h1>
            <p className="artefact-subtitle">Leonardo da Vinci</p>
            <p className="artefact-meta"><strong>Estimated date:</strong> 1503 – 1506</p>
            <p className="artefact-meta"><strong>Location:</strong> Louvre Museum (Paris)</p>
            <div className="artefact-description">
              <p>
                The Mona Lisa is one of the world’s most enigmatic masterpieces. Known for its subtle smile and innovative techniques, this painting has captured the imagination of art enthusiasts for centuries.
              </p>
            </div>
          </div>
        </div>

        {/* Mascot Section */}
        <div className="mascot-section">
          <img 
            src="/images/mascot.png" 
            alt="Mascot" 
            className="mascot-image"
          />
          <div className="mascot-bubble">
            <p>
              Hi there! I'm Masey, your guide. Did you know that this iconic painting holds secrets that inspire art lovers around the world?
            </p>
          </div>
        </div>
      </main>
    </div>
  );
};

export default ArtefactDetails;
