import React from 'react';
import {Route, Routes} from "react-router";
import Home from "./Home/Home";
import Admin from "./Admin/Admin";
import ArtefactDetails from './ArtefactDetails/ArtefactDetails';
import ArtefactsCollection from './ArtefactsCollection/ArtefactsCollection';
import ScanPage from './ScanPage/ScanPage';
import ScavengerHuntsPage from './ScavengerHunts/ScavengerHuntsPage';
import {ParallaxProvider} from 'react-scroll-parallax';
import ChatBot from "./Chat/ChatBot";
import Login from "./Admin/login/Login";
import { PrimeReactProvider } from 'primereact/api';
import 'primereact/resources/themes/lara-light-cyan/theme.css';
import 'primeicons/primeicons.css';
import Artefact from "./Admin/Artefact/Artefact";
import Hunt from "./Admin/Hunt/Hunt";
import Museum from "./Admin/Museum/Museum";

function App() {
    return (
        <PrimeReactProvider>
            <ParallaxProvider>
                <Routes>
                    <Route path="/" element={<Home/>}/>
                    <Route path="/artefactDetails" element={<ArtefactDetails/>}/>
                    <Route path="/artefactsCollection" element={<ArtefactsCollection/>}/>
                    <Route path="/scavengerHunts" element={<ScavengerHuntsPage/>}/>
                    <Route path="/scan" element={<ScanPage/>}/>
                    <Route path="/chat" element={<ChatBot/>}/>


                    {/*    Admin Portal    */}
                    <Route path="/admin" element={<Admin/>}>
                        <Route path="museum" element={<Museum/>}/>
                        <Route path="artefact" element={<Artefact/>}/>
                        <Route path="hunt" element={<Hunt/>}/>
                    </Route>

                    <Route path="/admin/login" element={<Login/>}/>


                </Routes>
            </ParallaxProvider>
        </PrimeReactProvider>
    );
}

export default App;
