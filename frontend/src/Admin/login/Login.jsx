import React, {useEffect} from "react";
import {useAuth} from "react-oidc-context";


export default function Login() {

    const auth = useAuth();

    const signOutRedirect = () => {
        const clientId = process.env.REACT_APP_AUTH_CLIENT_ID;
        const logoutUri = process.env.REACT_APP_AUTH_LOGOUT_URI;
        const cognitoDomain = process.env.REACT_APP_AUTH_COGNITO_DOMAIN;
        window.location.href = `${cognitoDomain}/logout?client_id=${clientId}&logout_uri=${encodeURIComponent(logoutUri)}`;
    };

    useEffect(() => {
        auth.signinRedirect().finally();
    }, []);

    // if (auth?.isLoading) {
    //     return <div>Loading...</div>;
    // }
    //
    // if (auth.error) {
    //     return <div>Encountering error... {auth?.error.message}</div>;
    // }
    //
    // if (auth.isAuthenticated) {
    //     return (
    //         <div>
    //             <pre> Hello: {auth.user?.profile.email} </pre>
    //             <button onClick={() => auth.removeUser()}>Sign out</button>
    //         </div>
    //     );
    // }
    return (
        ""
            // <div>
            //     <button onClick={() => auth.signinRedirect()}>Sign in</button>
            //     <button onClick={() => signOutRedirect()}>Sign out</button>
            // </div>
    );
}
