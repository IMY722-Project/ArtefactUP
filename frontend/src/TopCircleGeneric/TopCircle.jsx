import React from "react";
import { useNavigate } from "react-router-dom";
import { MdArrowBackIosNew } from "react-icons/md";
import "./TopCircle.css";


const TopCircle = ({ pageTitle }) => {
  const navigate = useNavigate();
  return (
    <div className="top-circle-gen">
      <div className="back-button-container">
        <button
          className="back-button"
          onClick={() => navigate(-1)}
        >
          <MdArrowBackIosNew size={30} />
        </button>
      </div>
      <div className="title-container-gen">
        <h1 className="title">{pageTitle}</h1>
      </div>
    </div>
  );
}

export default TopCircle;