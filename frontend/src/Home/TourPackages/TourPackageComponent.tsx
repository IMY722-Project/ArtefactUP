import React from 'react';
import './TourPackageComponent.css';

export interface TourPackage {
  title: string;
  price: string;
  bullets: string[];
}

interface TourPackageComponentProps {
  tour: TourPackage;
}

const TourPackageComponent = ({ tour }: TourPackageComponentProps) => {
    return (
        <div className="tour-package-card">
            <h3 className="tour-title">{tour.title}</h3>
            <p className="tour-price">{tour.price}</p>
            <ul className="tour-bullets">
                {tour.bullets.map((bullet, index) => (
                    <li key={index} className="tour-bullet">
                        <span className="bullet-icon">•</span> {bullet}
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default TourPackageComponent;
