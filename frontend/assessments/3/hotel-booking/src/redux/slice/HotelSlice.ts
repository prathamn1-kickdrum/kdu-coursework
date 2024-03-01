import {createSlice, PayloadAction, createAsyncThunk} from '@reduxjs/toolkit';
import {IHotelApiData,IHotelInitialState} from '../../types/HotelTypes'
import axios from 'axios';


const initialState: IHotelInitialState = {
    rooms: [],
    status: 'idle',
    isLoading: false,
    error: undefined
}

export const fetchRooms = createAsyncThunk('hotel/fetchApi', async () => {
    console.log("api called...");
    const response = axios.get('https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/assessment-3.json');
    const data = (await response).data;
    return data;
})

export const hotelSlice = createSlice({
    name: 'hotel',
    initialState,
    reducers: {
        setRoomData: (state,action: PayloadAction<IHotelApiData>) => {
            console.log("set room data", action.payload.roomTypes);
            state.rooms = action.payload.roomTypes;
        }
    },
    extraReducers: (builder) => {
        builder.addCase(fetchRooms.pending, (state) => {
            state.isLoading = true;
            state.status='pending';
        })
        builder.addCase(fetchRooms.fulfilled, (state, action) => {
            state.isLoading = false;
            state.rooms = action.payload.roomTypes;
            state.status='success';
        })
        builder.addCase(fetchRooms.rejected, (state, action) => {
            state.isLoading = false
            state.error = action.error.message;
            state.status='error';
        })
    },
});

export default hotelSlice.reducer;
export const { setRoomData } = hotelSlice.actions;



