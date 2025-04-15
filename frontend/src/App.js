import React from 'react';
import {Route, Routes} from "react-router";
import Home from "./Home/Home";
import Admin from "./Admin/Admin";
import ArtefactDetails from './ArtefactDetails/ArtefactDetails';
import ArtefactsCollection from './ArtefactsCollection/ArtefactsCollection';
import ScanPage from './ScanPage/ScanPage';
import ScavengerHuntsPage from './ScavengerHunts/ScavengerHuntsPage';
import { ParallaxProvider } from 'react-scroll-parallax';

function App() {
  return (
    <ParallaxProvider>
      <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/admin" element={<Admin />} />
          <Route path="/artefactDetails" element={<ArtefactDetails />} />
          <Route path="/artefactsCollection" element={<ArtefactsCollection />} />
          <Route path="/scavengerHunts" element={<ScavengerHuntsPage />} />
          <Route path="/scan" element={<ScanPage />} />

      </Routes>
    </ParallaxProvider>
  );
}

export default App;