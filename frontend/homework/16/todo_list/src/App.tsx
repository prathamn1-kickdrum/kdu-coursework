import React from "react";
import "./App.scss";
import Header from "./components/Header/Header";
import AddItem from "./components/ItemOperation/ItemOperation";
import ItemContainer from "./components/ItemContainer/ItemContainer";
import { Provider } from "react-redux";
import { store, persistor } from "./redux/store";
import { PersistGate } from 'redux-persist/integration/react'; 
import ScrollToTopButton from "./components/ScrollToTopButton/ScrollToTopButton";

const App: React.FC = () => {
    return (
        <Provider store={store}>
            <PersistGate loading={null} persistor={persistor}> 
                <div className="app-container">
                    <Header />
                    <div className="todo-container">
                        <AddItem />
                        <ItemContainer />
                    </div>
                    <ScrollToTopButton/>
                </div>
            </PersistGate>
        </Provider>
    );
};

export default App;
