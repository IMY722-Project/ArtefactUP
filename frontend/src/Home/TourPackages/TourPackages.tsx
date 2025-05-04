import React, { useState } from "react";
import TourPackageComponent from "./TourPackageComponent.tsx";
import "./TourPackages.css";
import { tourPackages } from "./tourPackagesData.ts";

const TourPackages: React.FC = () => {
  const [showAll, setShowAll] = useState(false);

  // Pick either first 3 or all
  const visiblePackages = showAll
    ? tourPackages
    : tourPackages.slice(0, 3);

  return (
    <div className="tour-packages-container">
      <h2>Museum Tour Packages</h2>

      <div className="tour-packages-list">
        {visiblePackages.map((tour, idx) => (
          <TourPackageComponent key={idx} tour={tour} />
        ))}
      </div>

      {tourPackages.length > 3 && (
        <button
          className="show-all-btn"
          onClick={() => setShowAll((prev) => !prev)}
        >
          {showAll ? "Show Less" : "Show All"}
        </button>
      )}
    </div>
  );
};

export default TourPackages;
