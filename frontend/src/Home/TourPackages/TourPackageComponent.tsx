import React from "react";
import "./TourPackageComponent.css";
import { TourPackage } from "./tourPackagesData";



interface TourPackageComponentProps {
  tour: TourPackage;
}

const TourPackageComponent: React.FC<TourPackageComponentProps> = ({ tour }) => {
  return (
    <div className="tour-package-card">
      <h3 className="tour-title">{tour.title}</h3>
      <p className="tour-price">{tour.price}</p>
      <ul className="tour-bullets">
        {tour.bullets.map((bullet, index) => (
          <li key={index} className="tour-bullet">
            {bullet}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default TourPackageComponent;
