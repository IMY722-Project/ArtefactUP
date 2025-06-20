import {Button} from "primereact/button";
import {DataTable} from "primereact/datatable";
import {Column} from "primereact/column";
import DashboardApi from "../data-access/DashboardAPI";
import React, {useEffect, useState} from "react";
import ArtefactForm from "./ArtefactForm";

export default function Artefact() {

    const [showArtefactForm, setShowArtefactForm] = useState(false);
    const [selectedArtefact, setSelectedArtefact] = useState(null);
    const [artefacts, setArtefacts] = useState([]);
    const [museums, setMuseums] = useState([]);

    useEffect(() => {
        DashboardApi.fetchArtefacts().then(setArtefacts);
        DashboardApi.fetchMuseums().then(setMuseums);
    }, []);


    const actionBodyTemplate = (rowData, type) => (
        <div>
            <Button icon="pi pi-pencil" className="p-button-rounded p-button-text" onClick={() => {
                setSelectedArtefact(rowData);
            }}/>
            <Button icon="pi pi-trash" className="p-button-rounded p-button-text p-button-danger"
                    onClick={() => DashboardApi.deleteArtefact(rowData.id).then(() => setArtefacts(artefacts.filter(a => a.id !== rowData.id)))}/>
        </div>
    );

    return (
        <div>
            <Button label="Create Artefact" onClick={() => {
                setSelectedArtefact(null);
                setShowArtefactForm(true);
            }} className="admin-button"/>
            <DataTable value={artefacts} paginator rows={10} className="p-datatable-gridlines">
                <Column field="title" header="Title"/>
                <Column field="creator" header="Creator"/>
                <Column field="museumName" header="Museum"/>
                <Column field="type" header="Type"/>
                <Column body={(rowData) => actionBodyTemplate(rowData, 'artefact')} header="Actions"/>
            </DataTable>
            <ArtefactForm
                visible={showArtefactForm || !!selectedArtefact}
                onHide={() => {
                    setShowArtefactForm(false);
                    setSelectedArtefact(null);
                }}
                artefact={selectedArtefact}
                museums={museums}
                onSave={(artefact) => {
                    if (selectedArtefact) {
                        DashboardApi.updateArtefact(artefact).then(updated => {
                            setArtefacts(artefacts.map(a => a.id === updated.id ? updated : a));
                        });
                    } else {
                        DashboardApi.createArtefact(artefact).then(newArtefact => setArtefacts([...artefacts, newArtefact]));
                    }
                }}
            />
        </div>
    );
}
