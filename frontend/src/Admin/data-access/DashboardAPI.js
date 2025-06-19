import {API} from "../../utils/config";

const DashboardApi = {
    fetchMuseums: async () => {
        const response = await fetch(`${API}/api/museum`, {
            method: 'GET',
            headers: { 'Content-Type': 'application/json' }
        });
        return await response.json();
    },
    createMuseum: async (museum) => {
        const response = await fetch(`${API}/api/museum/create`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(museum)
        });
        return await response.json();
    },
    updateMuseum: async (museum) => {
        const response = await fetch(`${API}/api/museum/update`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(museum)
        });
        return await response.json();
    },
    fetchArtefacts: async () => {
        const response = await fetch(`${API}/api/artefact`, {
            method: 'GET',
            headers: { 'Content-Type': 'application/json' }
        });
        return await response.json();
    },
    createArtefact: async (artefact) => {
        const response = await fetch(`${API}/api/artefact/create`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(artefact)
        });
        return await response.json();
    },
    updateArtefact: async (artefact) => {
        const response = await fetch(`${API}/api/artefact/update`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(artefact)
        });
        return await response.json();
    },
    deleteArtefact: async (id) => {
        await fetch(`${API}/api/artefact/delete/${id}`, {
            method: 'DELETE',
            headers: { 'Content-Type': 'application/json' }
        });
    },
    fetchHunts: async () => {
        const response = await fetch(`${API}/api/hunts/all`, {
            method: 'GET',
            headers: { 'Content-Type': 'application/json' }
        });
        return await response.json();
    },
    createHunt: async (hunt) => {
        const response = await fetch(`${API}/api/hunts/create`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(hunt)
        });
        return await response.json();
    },
    deleteHunt: async (id) => {
        await fetch(`${API}/api/hunts/${id}/delete`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' }
        });
    },
    updateHunt: async (hunt) => {
        const response = await fetch(`${API}/api/hunts/update`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(hunt)
        });
        return await response.json();
    }
};

export default DashboardApi;
