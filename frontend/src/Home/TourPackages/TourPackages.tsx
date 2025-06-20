// import React from "react";
import TourPackageComponent from "./TourPackageComponent.tsx";
import "./TourPackages.css";
import { tourPackages } from "./tourPackagesData.ts";

const TourPackages: React.FC = () => {
  return (
    <div className="tour-packages-container">
      <h2>Museum Tour Packages</h2>

      <div className="tour-packages-list">
        {tourPackages.map((tour, idx) => (
          <TourPackageComponent key={idx} tour={tour} />
        ))}
      </div>
    </div>
  );
};

export default TourPackages;
