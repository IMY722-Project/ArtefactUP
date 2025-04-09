import React from 'react';
import './OperatingHours.css';

const OperatingHours = () => {
  return (
    <section className="operating-hours">
      <div className="operating-hours-container">
        <h2>Operating Hours</h2>
        <p>
          <strong>Opening Times:</strong> Mondays to Fridays from 08:00 to 16:00.
        </p>
        <p>
          Closed on Public Holidays, Weekends and for the end of the academic year (usually mid-December).
        </p>
        <p>
          Kindly note the updated rates per person and the new museum packages as combined fees.
        </p>
      </div>
    </section>
  );
};

export default OperatingHours;
