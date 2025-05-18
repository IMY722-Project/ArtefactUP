import React from "react";
import "./ErrorMessage.css";

const ErrorMessage = ({ message = "Something went wrong.", onRetry }) => (
  <div className="error-message">
    <p className="error-text">{message}</p>
    {onRetry && (
      <button className="error-retry-btn" onClick={onRetry}>
        Retry
      </button>
    )}
  </div>
);

export default ErrorMessage;
