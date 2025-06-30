import {useEffect} from "react";
import {useAuth} from "react-oidc-context";


export default function Login() {

    const auth = useAuth();

    useEffect(() => {
        auth.signinRedirect().finally();
    });

    return (
        ""
    );
}
