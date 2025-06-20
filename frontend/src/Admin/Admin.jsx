import React from "react";
import {useAuth} from "react-oidc-context";
import SideNav from "./SideNav";
import {Navigate, Outlet} from "react-router";
import {useLocation, useNavigate} from "react-router";
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
        // return (
        //     <Navigate to="/admin/login"/>
        // );
        // console.log("booo")
        navigate("/admin/login");
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
