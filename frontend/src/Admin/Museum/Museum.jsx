import DashboardApi from "../data-access/DashboardAPI";
import React, {useEffect, useState} from "react";
import MuseumForm from "./MuseumForm";

export default function Museum() {
    const [museums, setMuseums] = useState([]);
    const [showMuseumForm, setShowMuseumForm] = useState(false);
    const [selectedMuseum, setSelectedMuseum] = useState(null);

    useEffect(() => {
        DashboardApi.fetchMuseums()
            .then((data) => {
                setMuseums(data.filter(museum => museum.name !== "unknown"));
            })
    }, []);

    const ActionButton = ({ icon, className = '', onClick }) => (
        <button onClick={onClick} className={`inline-flex items-center justify-center h-9 px-3 rounded-md text-sm font-medium transition-colors ${className}`}>
            <i className={`pi ${icon}`} />
        </button>
    );

    const formatAllOpeningHours = (hours) =>
        hours.map(({ day, openingTime, closingTime }) =>
            `${day.slice(0, 3)}: ${openingTime === "Closed" ? "Closed" : `${openingTime}–${closingTime}`}`
        ).join(" • ");

    return (
        <div >
            <div className="m-10">
                <div>
                    <div className="items-center">
                        <div>
                            <h1 className="text-3xl font-bold ">Museums</h1>
                            <p className="text-[#532c2c] mt-4">Manage your museum collections</p>
                        </div>
                        <button
                              className="inline-flex items-center justify-center admin-button"

                            onClick={() => {
                                setSelectedMuseum(null);
                                setShowMuseumForm(true);
                            }}
                        >
                            <span>Create Museum</span>
                        </button>
                    </div>

                    <div className="grid gap-6">
                        {museums.map((museum) => (
                            <div key={museum.id}
                                 className="rounded-lg shadow-lg bg-white/80 backdrop-blur-sm hover:shadow-xl transition-all duration-300">
                                <div className="flex justify-between items-start p-6 pb-3 space-y-1.5 flex-col">
                                    <div className="space-y-2">
                                        <h3 className="text-xl font-semibold tracking-tight text-orange-900 flex items-center gap-2">
                                            <svg className="w-5 h-5" fill="none" stroke="currentColor"
                                                 viewBox="0 0 24 24">
                                                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2}
                                                      d="M19 21V5a2 2 0 00-2-2H7a2 2 0 00-2 2v16m14 0h2m-2 0h-5m-9 0H3m2 0h5M9 7h1m-1 4h1m4-4h1m-1 4h1m-3 10V9a2 2 0 012-2h2a2 2 0 012 2v10M9 21v-7a2 2 0 012-2h2a2 2 0 012 2v7"/>
                                            </svg>
                                            {museum.name}
                                        </h3>
                                        <p className="flex items-center gap-2 text-orange-700 text-sm">
                                            <svg className="w-4 h-4" fill="none" stroke="currentColor"
                                                 viewBox="0 0 24 24">
                                                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2}
                                                      d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z"/>
                                                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2}
                                                      d="M15 11a3 3 0 11-6 0 3 3 0 016 0z"/>
                                            </svg>
                                            {museum.location}
                                        </p>
                                        <p className="flex items-center gap-2 text-orange-700 text-sm">
                                            <svg className="w-4 h-4" fill="none" stroke="currentColor"
                                                 viewBox="0 0 24 24">
                                                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2}
                                                      d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z"/>
                                            </svg>
                                            <span>{formatAllOpeningHours(museum.openingHours)}</span>
                                        </p>
                                    </div>

                                    <div className="flex gap-2 self-end">
                                        {/*Edit Button*/}
                                        <ActionButton
                                            icon="pi-pencil"
                                            className="text-orange-600 hover:text-orange-800 hover:bg-orange-100"
                                            onClick={() => setSelectedMuseum(museum)}
                                        />
                                        <ActionButton
                                            icon="pi-trash"
                                            className="text-red-600 hover:text-red-800 hover:bg-red-100"
                                        />
                                    </div>
                                </div>
                                <div className="p-6 pt-0">
                                    <p className="text-gray-700 leading-relaxed">{museum.description}</p>
                                </div>
                            </div>
                        ))}
                    </div>
                </div>
            </div>


            <MuseumForm
                visible={showMuseumForm || !!selectedMuseum}
                onHide={() => {
                    setShowMuseumForm(false);
                    setSelectedMuseum(null);
                }}
                museum={selectedMuseum}
                onSave={(museum) => {
                    if (selectedMuseum) {
                        DashboardApi.updateMuseum(museum).then(updated => {
                            setMuseums(museums.map(m => m.id === updated.id ? updated : m));
                        });
                    } else {
                        DashboardApi.createMuseum(museum).then(newMuseum => setMuseums([...museums, newMuseum]));
                    }
                }}
            />
        </div>
    );
}
