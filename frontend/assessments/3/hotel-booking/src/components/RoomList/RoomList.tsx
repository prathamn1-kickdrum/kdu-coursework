import React, { useState } from 'react';
import { v4 as uuidv4 } from 'uuid';
import SectionHeader from '../SectionHeader/SectionHeader';
import { useRoomSelector, useUserRoomDispatch} from '../../redux/Store';
import { setUserRoom, updatePrice } from '../../redux/slice/UserSlice';
import './RoomList.scss'

const RoomList: React.FC = () => {
    const dispatch = useUserRoomDispatch();
    const rooms = useRoomSelector((state) => state.hotel.rooms);
    // const selectedUserRoom = useUserRoomSelector((state) => state.userRoom.userRoom);
    const [selectedRoomName, setSelectedRoomName] = useState<string>("");

    const handleRoomChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        const selectedRoomName = event.target.value;
        if (selectedRoomName === "") {
            // If "Select a room" option is selected, set userRoom to undefined
            dispatch(setUserRoom(undefined));
            setSelectedRoomName(""); // Reset selectedRoomName state
        } else {
            setSelectedRoomName(selectedRoomName); // Update selectedRoomName state
            const selectedRoom = rooms.find(room => room.name === selectedRoomName);
            if (selectedRoom) {
                dispatch(setUserRoom(selectedRoom))
            }
        }
        dispatch(updatePrice());
    };

    return (
        <div className='room-list-container'>
            <SectionHeader heading={'Select Room Type'}/>

            <div className="rooms-container">
                <label htmlFor="room-name">Select Room</label>
                <select name="room-name" id="room-select" value={selectedRoomName} onChange={handleRoomChange}>
                    <option value="">Select a room</option>
                    {rooms.map((room) => (
                        <option key={uuidv4()} value={room.name}>{room.name}</option>
                    ))}
                </select>
            </div>
        </div>
    );
};

export default RoomList;
