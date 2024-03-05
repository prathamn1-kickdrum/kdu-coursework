import React from "react";
import { ColorRing } from "react-loader-spinner";

interface ColorRingProps {
    visible?: boolean;
    height?: string;
    width?: string;
    ariaLabel?: string;
    wrapperStyle?: React.CSSProperties;
    wrapperClass?: string;
    colors?: string[];
}

const ColorRingSpinner: React.FC<ColorRingProps> = () => {
    return (
        <ColorRing
            width="fit-content"
            height="fit-content"
            visible={true}
            ariaLabel="color-ring-loading"
            wrapperStyle={{}}
            wrapperClass="color-ring-wrapper"
            colors={["#e15b64", "#f47e60", "#f8b26a", "#abbd81", "#849b87"]}
        />
    );
};

export default ColorRingSpinner;
