import React, { useState } from 'react';
import { v4 as uuidv4 } from 'uuid';
import SectionHeader from '../SectionHeader/SectionHeader';
import { useUserRoomDispatch, useUserRoomSelector } from '../../redux/Store';
import { addAddOn, removeAddOn, updatePrice } from '../../redux/slice/UserSlice';
import './AddOnList.scss'

const AddOnList: React.FC = () => {
    const userRoom = useUserRoomSelector(state => state.userRoom.userRoom);
    const addOns = userRoom?.addOns ?? [];
    const dispatch = useUserRoomDispatch();
    const [selectedAddOns, setSelectedAddOns] = useState<string[]>([]);

    const handleAddOnChange = (addOnName: string) => {
        if (selectedAddOns.includes(addOnName)) {
            setSelectedAddOns(prevSelected => prevSelected.filter(name => name !== addOnName));
            dispatch(removeAddOn(addOnName));
        } else {
            setSelectedAddOns(prevSelected => [...prevSelected, addOnName]);
            dispatch(addAddOn(addOnName));
        }
        dispatch(updatePrice());
    };

    return (
        <div className='add-ons-section'>
            <SectionHeader heading={'Select additional add ons / preferences'}/>
            
            {userRoom ? (
                <div className="add-on-outer-container">
                    <p>Select Add-Ons</p>
                    <div className='main-add-on-container'>
                        {addOns.map((addOn) => (
                            <div key={uuidv4()} className='add-on-item'>
                                <input
                                    type="checkbox"
                                    id={addOn.name}
                                    name={addOn.name}
                                    checked={selectedAddOns.includes(addOn.name)}
                                    onChange={() => handleAddOnChange(addOn.name)}
                                />
                                <label htmlFor={addOn.name}>{addOn.name}</label>
                            </div>
                        ))}
                    </div>
                </div>
            ) : (
                <div className='add-on-outer-container'>
                    <p>Select a room first</p>
                </div>
            )}
        </div>
    );
};

export default AddOnList;



