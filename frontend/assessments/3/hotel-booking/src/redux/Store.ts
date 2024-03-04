import { configureStore } from '@reduxjs/toolkit'
import { TypedUseSelectorHook, useDispatch, useSelector } from 'react-redux';
import roomReducer  from './slice/HotelSlice';
import userRoomReducer from './slice/UserSlice';

export const store = configureStore({
  reducer: {
    hotel: roomReducer,
    userRoom: userRoomReducer
  },
})


export type StoreState = ReturnType<typeof store.getState>;
export type StoreDispatch = typeof store.dispatch;
type DispatchFunc = () => StoreDispatch;
export const useRoomDispatch: DispatchFunc = useDispatch;
export const useRoomSelector: TypedUseSelectorHook<StoreState> = useSelector;
export const useUserRoomDispatch: DispatchFunc = useDispatch;
export const useUserRoomSelector: TypedUseSelectorHook<StoreState> = useSelector;