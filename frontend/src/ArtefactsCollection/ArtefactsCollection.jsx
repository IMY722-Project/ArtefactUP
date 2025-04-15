import React, { useState } from "react";
import "./ArtefactsCollection.css";
import { FaTimes } from "react-icons/fa";


const ArtefactsCollection = () => {
  // Sample artefact data
  const items = [
    {
      id: 1,
      clueNumber: 1,
      question: "???",
      image: "/images/Image-courtesy-of-UP-Museums.jpg",
      hint: "This artefact is from the 14th Century.",
    },
    {
      id: 2,
      clueNumber: 2,
      question: "???",
      image: "/images/Image-courtesy-of-UP-Museums.jpg",
      hint: "This vase originates from Ancient Greece.",
    },
    {
      id: 3,
      clueNumber: 3,
      question: "???",
      image: "/images/Image-courtesy-of-UP-Museums.jpg",
      hint: "This painting is rumored to be cursed!",
    },
  ];

  // Placeholder functions – replace with your actual scanning/hint/reveal logic
  const handleScan = id => {
    window.location.href = "/scan";
  };
  const handleReveal = id => {
    window.location.href = "/artefactDetails";
  };

  // ClueCard component that uses local state for the hint drop-down
  const ClueCard = ({ item }) => {
    const [hintVisible, setHintVisible] = useState(false);
    const toggleHint = () => setHintVisible(prev => !prev);

    return (
      <div className="ac-card">
        <div className="ac-card-header">
          <span className="ac-clue-number">Clue #{item.clueNumber}</span>
          <span className="ac-clue-question">{item.question}</span>
        </div>
        <div className="ac-card-image">
          <img
            src={item.image}
            alt={`Clue #${item.clueNumber}`}
            className="ac-image pixelated"
          />
        </div>
        <div className="ac-card-actions">
          <button
            className="ac-btn scan-btn"
            onClick={() => handleScan(item.id)}
          >
            <span role="img" aria-label="camera">
              📷
            </span>
          </button>
          <button className="ac-btn hint-btn" onClick={toggleHint}>
            Hint
          </button>
          <button
            className="ac-btn reveal-btn"
            onClick={() => handleReveal(item.id)}
          >
            Reveal
          </button>
        </div>
        {hintVisible && <div className="ac-hint">{item.hint}</div>}
      </div>
    );
  };

  return (
    <div className="artefacts-collection-page">
      {/* Half-circle header */}
      <div className="top-circle-ac">
        <div className="close-button-container-ac">
        <button
            className="close-button-ac"
            onClick={() => (window.location.href = "/scavengerHunts")}
          >
            <FaTimes />
          </button>
        </div>
        <h1 className="artefacts-title">Artefact Collection</h1>
      </div>
      <main className="ac-main">
        {items.map(item => (
          <ClueCard key={item.id} item={item} />
        ))}
      </main>
    </div>
  );
};

export default ArtefactsCollection;
