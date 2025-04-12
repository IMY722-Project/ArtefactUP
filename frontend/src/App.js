import React from 'react';
import {Route, Routes} from "react-router";
import Home from "./Home/Home";
import Admin from "./Admin/Admin";
import ArtefactDetails from './ArtefactDetails/ArtefactDetails';

function App() {
  return (
      <Routes>
          <Route path="/H" element={<Home />} />
          <Route path="/admin" element={<Admin />} />
          <Route path="/" element={<ArtefactDetails />} />
      </Routes>
  );
}

export default App;