import { configureStore, combineReducers } from "@reduxjs/toolkit";
import { persistReducer, persistStore } from "redux-persist";
import storage from "redux-persist/lib/storage"; 

import todoReducer from './slice/todoSlice';
import searchReducer from './slice/searchSlice';

const rootReducer = combineReducers({
    todos: todoReducer,
    search: searchReducer
});

const persistConfig = {
  key: 'root',
  storage,
  blacklist: ['search']
};

const persistedReducer = persistReducer(persistConfig, rootReducer);

export const store = configureStore({
    reducer: persistedReducer
});

export const persistor = persistStore(store);

export type StoreDispatch = typeof store.dispatch;
export type StoreState = ReturnType<typeof store.getState>;
