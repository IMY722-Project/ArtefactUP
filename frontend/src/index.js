import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import {BrowserRouter} from "react-router";
import {AuthProvider} from "react-oidc-context";


const cognitoAuthConfig = {
    authority: process.env.REACT_APP_AUTH_URL,
    client_id: process.env.REACT_APP_AUTH_CLIENT_ID,
    redirect_uri: process.env.REACT_APP_AUTH_REDIRECT_URL,
    response_type: process.env.REACT_APP_AUTH_RESPONSE_TYPE,
    scope: process.env.REACT_APP_AUTH_SCOPE,
};

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <React.StrictMode>
        <AuthProvider {...cognitoAuthConfig}>
            <BrowserRouter>
                <App/>
            </BrowserRouter>
        </AuthProvider>
    </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
