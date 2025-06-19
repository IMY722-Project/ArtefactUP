import {useEffect, useState} from "react";
import {Sidebar} from "primereact/sidebar";
import {InputText} from "primereact/inputtext";
import {InputTextarea} from "primereact/inputtextarea";
import {Dropdown} from "primereact/dropdown";
import {Button} from "primereact/button";

export default function HuntForm ({ visible, onHide, hunt, onSave, artefacts, museums }) {
    const [formData, setFormData] = useState(hunt || { name: '', description: '', steps: [{ scavengerHuntName: '', stepNumber: 1, artefact: null, museumName: '', clue: '', hint: '' }] });

    useEffect(() => {
        if (hunt) {
            setFormData(hunt);
        }
    }, [hunt]);

    const handleSubmit = () => {
        onSave(formData);
        onHide();
    };

    const addStep = () => {
        setFormData({ ...formData, steps: [...formData.steps, { scavengerHuntName: formData.name, stepNumber: formData.steps.length + 1, artefact: null, museumName: '', clue: '', hint: '' }] });
    };

    return (
        <Sidebar position="right" header={hunt ? "Edit Scavenger Hunt" : "Create Scavenger Hunt"} visible={visible} onHide={onHide} style={{ width: '50vw' }}>
            <div className="p-fluid">
                <div className="p-field mb-4">
                    <label htmlFor="name">Name</label>
                    <InputText id="name" value={formData.name} onChange={(e) => setFormData({ ...formData, name: e.target.value })} />
                </div>
                <div className="p-field mb-4">
                    <label htmlFor="description">Description</label>
                    <InputTextarea id="description" value={formData.description} onChange={(e) => setFormData({ ...formData, description: e.target.value })} rows={4} />
                </div>
                <div className="p-field mb-4">
                    <label>Steps</label>
                    {formData.steps.map((step, index) => (
                        <div key={index} className="border p-2 mb-2">
                            <div className="p-field mb-2">
                                <label htmlFor={`artefact-${index}`}>Artefact</label>
                                <Dropdown id={`artefact-${index}`} value={step.artefact} options={artefacts.map(a => ({ label: a.title, value: a }))} onChange={(e) => {
                                    const newSteps = [...formData.steps];
                                    newSteps[index].artefact = e.value;
                                    newSteps[index].museumName = e.value?.museumName || '';
                                    setFormData({ ...formData, steps: newSteps });
                                }} placeholder="Select an Artefact" />
                            </div>
                            <div className="p-field mb-2">
                                <label htmlFor={`clue-${index}`}>Clue</label>
                                <InputText id={`clue-${index}`} value={step.clue} onChange={(e) => {
                                    const newSteps = [...formData.steps];
                                    newSteps[index].clue = e.target.value;
                                    setFormData({ ...formData, steps: newSteps });
                                }} />
                            </div>
                            <div className="p-field mb-2">
                                <label htmlFor={`hint-${index}`}>Hint</label>
                                <InputText id={`hint-${index}`} value={step.hint} onChange={(e) => {
                                    const newSteps = [...formData.steps];
                                    newSteps[index].hint = e.target.value;
                                    setFormData({ ...formData, steps: newSteps });
                                }} />
                            </div>
                        </div>
                    ))}
                    <Button label="Add Step" onClick={addStep} className="p-button-text" />
                </div>
                <Button label="Save" onClick={handleSubmit} />
            </div>
        </Sidebar>
    );
};
