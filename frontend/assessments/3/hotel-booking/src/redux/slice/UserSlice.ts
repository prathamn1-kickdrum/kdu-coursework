import {createSlice, PayloadAction} from '@reduxjs/toolkit';
import { IUserRoom } from '../../types/UserRoomType';
import { IRoomType } from '../../types/HotelTypes';
import calculateTotalCost from '../../Utils/calculateTotalCost';

const initialState:IUserRoom = {
    userRoom: undefined,
    addOns: [],
    cost:0,
    bookingDays: 0,
}



export const userRoomSlice = createSlice({
    name: 'hotel',
    initialState,
    reducers: {
        setUserRoom: (state,action: PayloadAction<IRoomType | undefined>) => {
            state.userRoom=action.payload;
            state.addOns=[];
        },
        addAddOn: (state,action: PayloadAction<string>) => {
            state.addOns.push(action.payload);
        },
        removeAddOn: (state,action: PayloadAction<string>) => {
            state.addOns.splice(state.addOns.indexOf(action.payload),1);
        },
        updatePrice: (state) => {
            if(state.userRoom===undefined) return;
            state.cost = calculateTotalCost(state.userRoom,state.addOns,state.bookingDays);
        },
        setBookingDays: (state,action: PayloadAction<number>) => {
            state.bookingDays = action.payload;
        }
    },
});

export default userRoomSlice.reducer;
export const { setUserRoom, addAddOn, removeAddOn, updatePrice, setBookingDays } = userRoomSlice.actions;



