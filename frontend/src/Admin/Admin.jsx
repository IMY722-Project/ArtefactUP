import React from "react";
import {useAuth} from "react-oidc-context";
import SideNav from "./SideNav";
import {Outlet, useLocation, useNavigate} from "react-router";
import "./Admin.css";

export default function Admin() {
    const auth = useAuth();
    const navigate = useNavigate();
    const location = useLocation();

    if (auth.error) {
        console.log(auth.error);
        return <div>Encountering error... {auth?.error.message}</div>;
    }

    if (!auth.isAuthenticated && !auth?.isLoading && location.pathname !== "/admin/login") {
        auth.signinRedirect().finally();
    }

    return (
        <div className="flex h-screen w-screen overflow-hidden page-wrapper hide-scroll">
            <SideNav/>
            <div className="flex-1 overflow-auto my-4 mx-10 hide-scroll">
                <Outlet/>
            </div>
        </div>
    );
}
