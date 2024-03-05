import { createContext } from "react";
import { IProduct } from "../../types/KduMarketPlace";

export const ProductItemContext = createContext<IProduct>({
    id: 0,
    title: "",
    price: 0,
    description: "",
    category: "",
    image: "",
    rating: {
        rate: 0,
        count: 0
    }
});