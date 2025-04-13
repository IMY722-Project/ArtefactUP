import React from 'react';
import './ArtefactDetails.css';

const relatedArtefacts = [
  { id: 1, title: 'Artefact 1', img: '/images/Image-courtesy-of-UP-Museums.jpg' },
  { id: 2, title: 'Artefact 2', img: '/images/art-2.jpg' },
  { id: 3, title: 'Artefact 3', img: '/images/art-3.jpg' },
  { id: 4, title: 'Artefact 4', img: '/images/art-4.jpg' },
  // Add more if needed
];

const ArtefactDetails = () => {
  return (
    <div className="artefact-details">
      {/* Header with a Close Button */}
      <header className="artefact-header">
        <button 
          className="close-button" 
          onClick={() => window.location.href = '/artefactsCollection'}
        >
          &times;
        </button>
      </header>

      {/* Museum Background */}
      <div className="museum-background">
        {/* Main Content Overlay */}
        <div className="content-overlay">
          {/* Left: Artefact Visual Card */}
          <div className="artefact-visual">
            <div className="image-card">
              <img 
                src="/images/Image-courtesy-of-UP-Museums.jpg" 
                alt="Mona Lisa" 
                className="artefact-image"
              />
              <div className="artefact-title-box">
                <p className="artefact-index">#3</p>
                <h2 className="artefact-name">Mona Lisa</h2>
                <p className="artefact-subtitle">Leonardo da Vinci</p>
              </div>
            </div>
          </div>

          {/* Right: Artefact Info & Description */}
          <div className="artefact-info">
            <p className="artefact-meta">
              <strong>Estimated date:</strong> 1503 &ndash; 1506
            </p>
            <p className="artefact-meta">
              <strong>Where to see it:</strong> Louvre Museum (Paris)
            </p>
            <div className="artefact-description">
              <p>
                Experience the world-renowned masterpiece – the Mona Lisa.
                Renowned for its enigmatic smile and mysterious allure, this painting 
                continues to fascinate art lovers around the globe. Discover the subtle 
                details, innovative techniques, and historical significance behind da Vinci’s 
                iconic work.
              </p>
            </div>
          </div>
        </div>

        {/* Carousel Directly Under the Overlay */}
        <div className="artefact-carousel">
          {relatedArtefacts.map((item) => (
            <div key={item.id} className="carousel-item">
              <img 
                src={item.img} 
                alt={item.title} 
                className="carousel-image" 
              />
              <p className="carousel-caption">{item.title}</p>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default ArtefactDetails;
