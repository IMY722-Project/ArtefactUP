import React from 'react';
import {Route, Routes} from "react-router";
import Home from "./Home";
import Admin from "./Admin";

function App() {
  return (
      <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/admin" element={<Admin />} />
      </Routes>
  );
}

export default App;