import { configureStore } from '@reduxjs/toolkit'
import productSlice from './ProductSlice.tsx'
import { TypedUseSelectorHook, useDispatch, useSelector } from 'react-redux';

export const store = configureStore({
  reducer: {
    product: productSlice,
  },
})


export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
type DispatchFunc = () => AppDispatch;
export const useProductDispatch: DispatchFunc = useDispatch;
export const useProductSelector: TypedUseSelectorHook<RootState> = useSelector;