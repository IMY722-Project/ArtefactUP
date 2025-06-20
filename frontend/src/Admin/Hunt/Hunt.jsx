import { Button } from "primereact/button";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import DashboardApi from "../data-access/DashboardAPI";
import React, { useEffect, useState } from "react";
import HuntForm from "./HuntForm";

export default function Hunt() {
    const [showHuntForm, setShowHuntForm] = useState(false);
    const [selectedHunt, setSelectedHunt] = useState(null);
    const [hunts, setHunts] = useState([]);
    const [artefacts, setArtefacts] = useState([]);
    const [museums, setMuseums] = useState([]);

    useEffect(() => {
        DashboardApi.fetchHunts().then(setHunts);
        DashboardApi.fetchArtefacts().then(setArtefacts);
        DashboardApi.fetchMuseums().then(setMuseums);
    }, []);

    const actionBodyTemplate = (rowData, type) => (
        <div>
            <Button icon="pi pi-pencil" className="p-button-rounded p-button-text" onClick={() => {
                setSelectedHunt(rowData);
            }} />
            <Button icon="pi pi-trash" className="p-button-rounded p-button-text p-button-danger" onClick={() => DashboardApi.deleteHunt(rowData.id).then(() => setHunts(hunts.filter(h => h.id !== rowData.id)))} />
        </div>
    );

    return (
        <div className="m-10">
            <h1 className="text-3xl font-bold ">Quests</h1>

            <Button label="Create Scavenger Hunt" onClick={() => {
                setSelectedHunt(null);
                setShowHuntForm(true);
            }} className="admin-button" />
            <DataTable value={hunts} paginator rows={10} className="p-datatable-gridlines  rounded-lg overflow-hidden">
                <Column field="name" header="Name" />
                <Column field="description" header="Description" />
                <Column body={(rowData) => actionBodyTemplate(rowData, 'hunt')} header="Actions" />
            </DataTable>
            <HuntForm
                visible={showHuntForm || !!selectedHunt}
                onHide={() => {
                    setShowHuntForm(false);
                    setSelectedHunt(null);
                }}
                hunt={selectedHunt}
                artefacts={artefacts}
                museums={museums}
                onSave={(hunt) => {
                    if (selectedHunt) {
                        DashboardApi.updateHunt(hunt).then(updated => {
                            setHunts(hunts.map(h => h.id === updated.id ? updated : h));
                        });
                    } else {
                        DashboardApi.createHunt(hunt).then(newHunt => setHunts([...hunts, newHunt]));
                    }
                }}
            />
        </div>
    );
}
