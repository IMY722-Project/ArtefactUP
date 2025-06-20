import { Sidebar } from "primereact/sidebar";
import { InputText } from "primereact/inputtext";
import { Button } from "primereact/button";
import { Dropdown } from "primereact/dropdown";
import { InputTextarea } from "primereact/inputtextarea";
import { useEffect, useState } from "react";

export default function ArtefactForm({ visible, onHide, artefact, onSave, museums }) {
    const [formData, setFormData] = useState(artefact || {
        title: '', creator: '', description: '', museumName: '', dateCreated: '',
        locationCreated: '', physicalDimensions: '', type: '', rights: '', medium: '', imageUrl: ''
    });

    useEffect(() => {
        if (artefact) {
            setFormData(artefact);
        }
    }, [artefact]);

    const handleSubmit = () => {
        onSave(formData);
        onHide();
    };

    return (
        <Sidebar position="right" header={artefact ? "Edit Artefact" : "Create Artefact"} visible={visible} onHide={onHide} style={{ width: '50vw' }} >
            <div className="p-fluid form-class">
                <div className="p-field mb-4">
                    <label htmlFor="title">Title</label>
                    <InputText id="title" value={formData.title} onChange={(e) => setFormData({ ...formData, title: e.target.value })} />
                </div>
                <div className="p-field mb-4">
                    <label htmlFor="creator">Creator</label>
                    <InputText id="creator" value={formData.creator} onChange={(e) => setFormData({ ...formData, creator: e.target.value })} />
                </div>
                <div className="p-field mb-4">
                    <label htmlFor="description">Description</label>
                    <InputTextarea id="description" value={formData.description} onChange={(e) => setFormData({ ...formData, description: e.target.value })} rows={4} />
                </div>
                <div className="p-field mb-4">
                    <label htmlFor="museumName">Museum</label>
                    <Dropdown id="museumName" value={formData.museumName} options={museums.map(m => ({ label: m.name, value: m.name }))} onChange={(e) => setFormData({ ...formData, museumName: e.value })} placeholder="Select a Museum" />
                </div>
                <div className="p-field mb-4">
                    <label htmlFor="dateCreated">Date Created</label>
                    <InputText id="dateCreated" value={formData.dateCreated} onChange={(e) => setFormData({ ...formData, dateCreated: e.target.value })} />
                </div>
                <div className="p-field mb-4">
                    <label htmlFor="locationCreated">Location Created</label>
                    <InputText id="locationCreated" value={formData.locationCreated} onChange={(e) => setFormData({ ...formData, locationCreated: e.target.value })} />
                </div>
                <div className="p-field mb-4">
                    <label htmlFor="physicalDimensions">Physical Dimensions</label>
                    <InputText id="physicalDimensions" value={formData.physicalDimensions} onChange={(e) => setFormData({ ...formData, physicalDimensions: e.target.value })} />
                </div>
                <div className="p-field mb-4">
                    <label htmlFor="type">Type</label>
                    <InputText id="type" value={formData.type} onChange={(e) => setFormData({ ...formData, type: e.target.value })} />
                </div>
                <div className="p-field mb-4">
                    <label htmlFor="rights">Rights</label>
                    <InputText id="rights" value={formData.rights} onChange={(e) => setFormData({ ...formData, rights: e.target.value })} />
                </div>
                <div className="p-field mb-4">
                    <label htmlFor="medium">Medium</label>
                    <InputText id="medium" value={formData.medium} onChange={(e) => setFormData({ ...formData, medium: e.target.value })} />
                </div>
                <div className="p-field mb-4">
                    <label htmlFor="imageUrl">Image URL</label>
                    <InputText id="imageUrl" value={formData.imageUrl} onChange={(e) => setFormData({ ...formData, imageUrl: e.target.value })} />
                </div>
                <div className='w-full flex items-center justify-center'>

                    <Button label="Save" className=" form-button" onClick={handleSubmit} />
                </div>
            </div>
        </Sidebar>
    );
};

