import { NavLink, useNavigate } from 'react-router-dom';
import { useAuth } from 'react-oidc-context';

const navItems = [
    { to: 'museum', label: 'Museums', icon: 'pi pi-home' },
    { to: 'artefact', label: 'Artefacts', icon: 'pi pi-building' },
    { to: 'hunt', label: 'Hunts', icon: 'pi pi-folder' },
];

export default function SideNav() {
    const auth = useAuth();
    const navigate = useNavigate();

    // const signOutRedirect = () => {
    //     const clientId = process.env.REACT_APP_AUTH_CLIENT_ID;
    //     const logoutUri = process.env.REACT_APP_AUTH_LOGOUT_URI;
    //     const cognitoDomain = process.env.REACT_APP_AUTH_COGNITO_DOMAIN;
    //     window.location.href = `${cognitoDomain}/logout?client_id=${clientId}&logout_uri=${encodeURIComponent(logoutUri)}`;
    // };

    const signOutRedirect = () => {
        const clientId = "4rvbqp39tna28ef1rhh43rdpei";
        const logoutUri = process.env.REACT_APP_AUTH_LOGOUT_URI;
        const cognitoDomain = "https://af-south-1nybjqizip.auth.af-south-1.amazoncognito.com";
        window.location.href = `${cognitoDomain}/logout?client_id=${clientId}&logout_uri=${encodeURIComponent(logoutUri)}`;
    };

    return (
        <div className="flex flex-col justify-between h-screen w-64 bg-gradient-to-b from-orange-100 to-orange-200 p-4 text-orange-900 shadow-md">
            {/* Top Section */}
            <div>
                <h2 className="text-xl font-bold mb-6">ArtefactUP Admin</h2>

                <nav className="space-y-2">
                    {navItems.map(({ to, label, icon }) => (
                        <NavLink
                            key={to}
                            to={to}
                            className={({ isActive }) =>
                                `flex items-center gap-2 px-3 py-2 rounded-md transition-colors hover:bg-orange-300 hover:text-orange-900 ${
                                    isActive
                                        ? 'bg-orange-300 font-semibold text-orange-900'
                                        : 'text-orange-800'
                                }`
                            }
                        >
                            <i className={`${icon} text-sm`} />
                            <span>{label}</span>
                        </NavLink>
                    ))}
                </nav>
            </div>

            {/* Bottom Section */}
            <div>
                <button
                    onClick={() => signOutRedirect()}
                    className="w-full flex items-center gap-2 px-3 py-2 text-red-600 hover:bg-red-100 hover:text-red-800 rounded-md transition-colors"
                >
                    <i className="pi pi-sign-out text-sm" />
                    <span>Logout</span>
                </button>
            </div>
        </div>
    );
}
