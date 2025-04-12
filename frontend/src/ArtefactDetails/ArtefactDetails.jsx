import React from 'react';
import './ArtefactDetails.css';

// Dummy data for the carousel images
const relatedArtefacts = [
  { id: 1, title: 'Artefact 1', img: '/images/Image-courtesy-of-UP-Museums.jpg' },
  { id: 2, title: 'Artefact 2', img: '/images/art-2.jpg' },
  { id: 3, title: 'Artefact 3', img: '/images/art-3.jpg' },
  { id: 4, title: 'Artefact 4', img: '/images/art-4.jpg' },
  // ... add more if needed
];

const ArtefactDetails = () => {
  return (
    <div className="artefact-details">
      {/* Background Museum Setting */}
      <div className="museum-background">
        {/* Upper-Right Close Button */}
        <button className="close-button">✕</button>

        {/* Main Content Overlay */}
        <div className="content-overlay">
          {/* Left Section: Artefact Image & Title */}
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

          {/* Right Section: Details & Audio */}
          <div className="artefact-info">

            {/* Meta Info */}
            <p className="artefact-meta">
              <strong>Estimated date:</strong> 1503 to 1506
            </p>
            <p className="artefact-meta">
              <strong>Where to see it:</strong> Louvre Museum (Paris)
            </p>

            {/* Description */}
            <div className="artefact-description">
              <p>
                It should come as no surprise that the most famous painting in
                the world is the work of the mysterious and groundbreaking Leonardo
                da Vinci. But there's more to this iconic piece than meets the eye.
              </p>
              <p>
                Also known as La Gioconda, the portrait was commissioned by Francesco
                del Giocondo, the husband of Lisa Gherardini. The subtle expression,
                geometric composition, and revolutionary painting techniques have
                fascinated the world for centuries. It was famously stolen from the
                Louvre in 1911 and recovered in 1913.
              </p>
            </div>
          </div>
        </div>

        {/* Bottom Carousel of Related Artefacts */}
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
