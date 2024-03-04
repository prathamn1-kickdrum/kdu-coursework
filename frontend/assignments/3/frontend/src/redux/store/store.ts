import { configureStore } from "@reduxjs/toolkit";
import apiStockReducer from "../slice/ApiStocksSlice";
import userStockReducer from "../slice/UserStockSlice";
import stockGraphReducer from "../slice/StockGraphSlice";

export const store = configureStore({
    reducer: {
        stocks: apiStockReducer,
        user: userStockReducer,
        graph: stockGraphReducer,
    },
});

export type RootState = ReturnType<typeof store.getState>;
export type RootDispatch = typeof store.dispatch;
