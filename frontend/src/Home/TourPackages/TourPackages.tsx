import React from 'react';
import TourPackageComponent, { TourPackage } from './TourPackageComponent.tsx';
import './TourPackages.css';

const tourPackages: TourPackage[] = [
  {
    title: 'Introductory Tour',
    price: 'R80pp',
    bullets: ['1-hour guided museum tour', 'Access to main exhibits'],
  },
  {
    title: 'School Tours',
    price: 'R120/hr',
    bullets: ['Includes teachers at R80', 'Tailored for educational needs'],
  },
  {
    title: 'Mapungubwe Exclusive Tour',
    price: 'R100pp',
    bullets: ['Focus on the iconic Mapungubwe Collection', 'Guided by a specialist'],
  },
  {
    title: 'Treasure Tuesday Tours (TTT)',
    price: 'FREE',
    bullets: ['Every Tuesday 11:00 - 12:00', 'Open to UP Students & Staff'],
  },
];

const TourPackages = () => {
    return (
        <div className="tour-packages-container">
            <h2>Museum Tour Packages</h2>
            <div className="tour-packages-list">
                {tourPackages.map((tour) => (
                    <TourPackageComponent tour={tour} />
                ))}
            </div>
        </div>
    );
};

export default TourPackages;
