import { Sidebar } from 'primereact/sidebar';
import { InputText } from "primereact/inputtext";
import { Button } from "primereact/button";
import { useEffect, useState } from "react";
import { InputTextarea } from "primereact/inputtextarea";
import { Dropdown } from "primereact/dropdown";
import { Calendar } from "primereact/calendar";

export default function MuseumForm({ visible, onHide, museum, onSave }) {
    const [formData, setFormData] = useState(museum || {
        name: '',
        location: '',
        description: '',
        openingHours: [{ day: '', openingTime: '', closingTime: '' }],
        imageUrl: ''
    });

    const handleSubmit = () => {
        onSave(formData);
        onHide();
    };

    useEffect(() => {
        if (museum) {
            setFormData(museum);
        }
    }, [museum]);

    const addOpeningHour = () => {
        setFormData({
            ...formData,
            openingHours: [...formData.openingHours, { day: '', openingTime: '', closingTime: '' }]
        });
    };

    const daysOfWeek = [
        { label: 'Monday', value: 'Monday' },
        { label: 'Tuesday', value: 'Tuesday' },
        { label: 'Wednesday', value: 'Wednesday' },
        { label: 'Thursday', value: 'Thursday' },
        { label: 'Friday', value: 'Friday' },
        { label: 'Saturday', value: 'Saturday' },
        { label: 'Sunday', value: 'Sunday' },
        { label: 'Public Holiday', value: 'Public Holiday' }
    ];

    return (
        <Sidebar position="right" header={museum ? "Edit Museum" : "Create Museum"} visible={visible} onHide={onHide}
            style={{ width: '50vw' }}>
            <div className="p-fluid form-class">
                <div className="p-field mb-4">
                    <label htmlFor="name">Name</label>
                    <InputText id="name" value={formData.name}
                        onChange={(e) => setFormData({ ...formData, name: e.target.value })} className="hunt-input-field"/>
                </div>
                <div className="p-field mb-4">
                    <label htmlFor="location">Location</label>
                    <InputText id="location" value={formData.location}
                        onChange={(e) => setFormData({ ...formData, location: e.target.value })} className="hunt-input-field"/>
                </div>
                <div className="p-field mb-4">
                    <label htmlFor="description">Description</label>
                    <InputTextarea id="description" value={formData.description}
                        onChange={(e) => setFormData({ ...formData, description: e.target.value })} rows={4} className="hunt-input-field"/>
                </div>
                <div className="p-field mb-4">
                    <label>Opening Hours</label>
                    {formData.openingHours.map((hour, index) => (
                        <div key={index} className="flex gap-2 items-center mb-2">
                            <Dropdown
                                options={daysOfWeek}
                                value={hour.day}
                                onChange={(e) => {
                                    const newHours = [...formData.openingHours];
                                    newHours[index].day = e.value;
                                    setFormData({ ...formData, openingHours: newHours });
                                }}
                                placeholder="Select Day"
                                className="w-40 hunt-input-field"
                            />
                            <Calendar
                                value={hour.openingTime ? new Date(`1970-01-01T${hour.openingTime}`) : null}
                                onChange={(e) => {
                                    const timeString = e.value
                                        ? e.value.toLocaleTimeString([], {
                                            hour: '2-digit',
                                            minute: '2-digit',
                                            hour12: false
                                        })
                                        : '';
                                    const newHours = [...formData.openingHours];
                                    newHours[index].openingTime = timeString;
                                    setFormData({ ...formData, openingHours: newHours });
                                }}
                                timeOnly
                                hourFormat="24"
                                className="w-40"
                                placeholder="09:00"
                                className="hunt-input-field"
                            />
                            <Calendar
                                value={hour.closingTime ? new Date(`1970-01-01T${hour.closingTime}`) : null}
                                onChange={(e) => {
                                    const timeString = e.value
                                        ? e.value.toLocaleTimeString([], {
                                            hour: '2-digit',
                                            minute: '2-digit',
                                            hour12: false
                                        })
                                        : '';
                                    const newHours = [...formData.openingHours];
                                    newHours[index].closingTime = timeString;
                                    setFormData({ ...formData, openingHours: newHours });
                                }}
                                timeOnly
                                hourFormat="24"
                                className="w-40"
                                placeholder="17:00"
                                className="hunt-input-field"
                            />

                            {/* Delete Button */}
                            <Button
                                icon="pi pi-times"
                                className="p-button-text p-button-danger"
                                onClick={() => {
                                    const newHours = formData.openingHours.filter((_, i) => i !== index);
                                    setFormData({ ...formData, openingHours: newHours });
                                }}
                                tooltip="Remove"
                                tooltipOptions={{ position: 'top' }}
                            />
                        </div>
                    ))}
                    <div className='w-full flex items-center justify-center'>

                        <Button label="Add Opening Hour" onClick={addOpeningHour} className="p-button-text  form-button-secondary" />
                    </div>
                </div>
                <div className="p-field mb-4">
                    <label htmlFor="imageUrl">Image URL</label>
                    <InputText id="imageUrl" value={formData.imageUrl}
                        onChange={(e) => setFormData({ ...formData, imageUrl: e.target.value })} className="hunt-input-field"/>
                </div>
                <div className='w-full flex items-center justify-center'>
                    <Button label="Save" className=' form-button' onClick={handleSubmit} />
                </div>

            </div>
        </Sidebar>
    );
};
