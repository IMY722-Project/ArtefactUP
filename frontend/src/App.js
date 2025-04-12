import React from 'react';
import {Route, Routes} from "react-router";
import Home from "./Home/Home";
import Admin from "./Admin/Admin";
import ArtefactDetails from './ArtefactDetails/ArtefactDetails';
import ArtefactsCollection from './ArtefactsCollection/ArtefactsCollection';

function App() {
  return (
      <Routes>
          <Route path="/H" element={<Home />} />
          <Route path="/admin" element={<Admin />} />
          <Route path="/a" element={<ArtefactDetails />} />
          <Route path="/" element={<ArtefactsCollection />} />

      </Routes>
  );
}

export default App;