import React from 'react';
import {Route, Routes} from "react-router";
import Home from "./Home/Home";
import Admin from "./Admin/Admin";
import ArtefactDetails from './ArtefactDetails/ArtefactDetails';
import ArtefactsCollection from './ArtefactsCollection/ArtefactsCollection';
import ScanPage from './ScanPage/ScanPage';

function App() {
  return (
      <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/admin" element={<Admin />} />
          <Route path="/artefactDetails" element={<ArtefactDetails />} />
          <Route path="/artefactsCollection" element={<ArtefactsCollection />} />
          <Route path="/scan" element={<ScanPage />} />

      </Routes>
  );
}

export default App;