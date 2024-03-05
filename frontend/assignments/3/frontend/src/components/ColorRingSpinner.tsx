import React from "react";
import {theme} from '../utils/colors'
import { Vortex } from "react-loader-spinner";


const ColorRingSpinner: React.FC = () => {
    return (
        <Vortex
            width="fit-content"
            height="fit-content"
            visible={true}
            ariaLabel="color-ring-loading"
            wrapperStyle={{}}
            wrapperClass="color-ring-wrapper"
            colors={[theme.primaryColor,theme.primaryColor,theme.primaryColor,theme.primaryColor,theme.primaryColor,theme.primaryColor,]}
        />
    );
};

export default ColorRingSpinner;
