import React, { useEffect } from "react";
import {
    useRoomDispatch,
    useRoomSelector,
} from "../../redux/Store";
import { fetchRooms } from "../../redux/slice/HotelSlice";
import RoomList from "../RoomList/RoomList";
import AddOnList from "../AddOnList/AddOnList";
import { DateSelect } from "../DateSelect/DateSelect";
import ShowPrice from "../ShowPrice/ShowPrice";
import SubmitButton from "../SubmitButton/SubmitButton";
import './HotelBookingMain.scss'
import MainHeading from "../MainHeading/MainHeading";

const HotelBookingMain:React.FC = () => {
    const dispatch = useRoomDispatch();
    const roomsStatus = useRoomSelector((state) => state.hotel.status);
    useEffect(() => {
        if (roomsStatus === "idle") {
            dispatch(fetchRooms());
        }
    }, [roomsStatus, dispatch]);


    return (
        <div className="main-layout-container">
            <MainHeading/>
            {roomsStatus === "success" && <RoomList />}
            {roomsStatus==="success" && <DateSelect />}
            {roomsStatus==="success" && <AddOnList />}
            <div className="price-container">
                {<ShowPrice/>}
                {<SubmitButton/>}
            </div>
        </div>
    );
};

export default HotelBookingMain;
