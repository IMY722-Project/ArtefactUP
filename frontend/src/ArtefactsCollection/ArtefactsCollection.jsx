import React, { useState } from "react";
import "./ArtefactsCollection.css";

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

  // Placeholder functions – replace these with your actual logic
  const handleScan = (id) => {
    window.location.href = `/scan`;
  };
  const handleReveal = (id) => {
    window.location.href = `/artefactDetails`;
  };

  // Clue card component with local hint visibility state
  const ClueCard = ({ item }) => {
    const [hintVisible, setHintVisible] = useState(false);

    const toggleHint = () => {
      setHintVisible((prev) => !prev);
    };

    return (
      <div className="clue-card">
        <div className="clue-header">
          <span className="clue-number">{item.clueNumber}</span>
          <span className="clue-question">{item.question}</span>
        </div>
        <div className="clue-image-container">
          <img
            src={item.image}
            alt={`Clue #${item.clueNumber}`}
            className="clue-image pixelated"
          />
        </div>
        <div className="clue-actions">
          <button
            className="clue-btn camera-btn"
            onClick={() => handleScan(item.id)}
          >
            <span role="img" aria-label="camera">
              📷
            </span>
          </button>
          <button className="clue-btn hint-btn" onClick={toggleHint}>
            Hint
          </button>
          <button
            className="clue-btn reveal-btn"
            onClick={() => handleReveal(item.id)}
          >
            Reveal
          </button>
        </div>
        {hintVisible && <div className="clue-hint">{item.hint}</div>}
      </div>
    );
  };

  return (
    <div className="scavenger-page">
      {/* Header */}
      <header className="scavenger-header">
        <h1 className="scavenger-title">Red Collection</h1>
      </header>

      {/* Optional progress bar for visual interest */}
      <div className="scavenger-progress"></div>

      {/* Main Content */}
      <main className="scavenger-main">
        {items.map((item) => (
          <ClueCard key={item.id} item={item} />
        ))}
      </main>
    </div>
  );
};

export default ArtefactsCollection;
