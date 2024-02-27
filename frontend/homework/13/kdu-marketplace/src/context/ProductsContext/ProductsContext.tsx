import { createContext } from "react";
import { IProductList } from "../../types/KduMarketPlace";

export const ProductsContext = createContext<IProductList>({
    productsData: [],
    sortValue: "", 
    setSortValue: ()=>{}, 
    categoryValue: "", 
    setCategoryValue: ()=>{},
    searchValue: "",
    setSearchValue: ()=>{},
})