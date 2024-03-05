import { configureStore } from "@reduxjs/toolkit";
import apiStockReducer from "../slice/ApiStocksSlice";
import userStockReducer from "../slice/UserStockSlice";
import stockGraphReducer from "../slice/StockGraphSlice";
import SummarizeStocksSlice from "../slice/SummarizeStocksSlice";
import { useDispatch, TypedUseSelectorHook, useSelector } from "react-redux";

export const store = configureStore({
    reducer: {
        stocks: apiStockReducer,
        user: userStockReducer,
        graph: stockGraphReducer,
        summarize: SummarizeStocksSlice
    },
});

export type RootState = ReturnType<typeof store.getState>;
export type RootDispatch = typeof store.dispatch;
type DispatchFunc = () => RootDispatch;
export const useSummarizeDispatch: DispatchFunc = useDispatch;
export const useSummarizeSelector: TypedUseSelectorHook<RootState> = useSelector;
