import React from 'react';
import './ArtefactsCollection.css';

const ArtefactsCollection = () => {
  // Sample data for illustration
  const items = [
    {
      id: 1,
      clueNumber: 1,
      question: '???',
      image: '/images/Image-courtesy-of-UP-Museums.jpg',
      hint: 'This artefact is from the 14th Century.',
    },
    {
      id: 2,
      clueNumber: 2,
      question: '???',
      image: '/images/Image-courtesy-of-UP-Museums.jpg',
      hint: 'This vase originates from Ancient Greece.',
    },
    {
      id: 3,
      clueNumber: 3,
      question: '???',
      image: '/images/Image-courtesy-of-UP-Museums.jpg',
      hint: 'This painting is rumored to be cursed!',
    },
  ];

  // Placeholder handlers (in a real app, these would toggle hint or pixelation)
  const handleScan = (id) => alert(`Camera scanning for item ${id}`);
  const handleHint = (hint) => alert(`Hint: ${hint}`);
  const handleReveal = (id) => alert(`Reveal artefact #${id}!`);

  return (
    <div className="scavenger-page">
      {/* Header / Top Bar */}
      <header className="scavenger-header">
        <h1 className="scavenger-title">Collection</h1>
      </header>

      {/* Progress bar or line (optional) */}
      <div className="scavenger-progress" />

      {/* Main Content */}
      <main className="scavenger-main">
        {items.map((item) => (
          <div key={item.id} className="clue-card">
            <div className="clue-header">
              <span className="clue-number">{item.clueNumber}</span>
              <span className="clue-question">{item.question}</span>
            </div>

            <div className="clue-image-container">
              {/* Pixelated or blurred image */}
              <img
                src={item.image}
                alt={`Clue #${item.clueNumber}`}
                className="clue-image pixelated"
              />
            </div>

            {/* Actions row (camera, hint, reveal) */}
            <div className="clue-actions">
              <button
                className="clue-btn camera-btn"
                onClick={() => handleScan(item.id)}
              >
                <span role="img" aria-label="camera">
                  📷
                </span>
              </button>

              <button
                className="clue-btn hint-btn"
                onClick={() => handleHint(item.hint)}
              >
                Hint
              </button>

              <button
                className="clue-btn reveal-btn"
                onClick={() => handleReveal(item.id)}
              >
                Reveal
              </button>
            </div>
          </div>
        ))}
      </main>
    </div>
  );
};

export default ArtefactsCollection;
