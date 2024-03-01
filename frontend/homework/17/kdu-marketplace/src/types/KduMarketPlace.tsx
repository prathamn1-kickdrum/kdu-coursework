import { Dispatch, SetStateAction } from "react";

export interface Rating {
    rate:  number;
    count: number;
}

export interface IProduct {
    id:          number;
    title:       string;
    price:       number;
    description: string;
    category:    string;
    image:       string;
    rating:      Rating;
}

export interface IProductList{
    productsData: IProduct[];
    sortValue: string;
    setSortValue: Dispatch<SetStateAction<string>>;
    categoryValue: string;
    setCategoryValue: Dispatch<SetStateAction<string>>;
    searchValue: string;
    setSearchValue: Dispatch<SetStateAction<string>>;
}

export interface IProductsProvider{
    children: React.ReactNode 
}

export interface IProductItemProvider{
    children: React.ReactNode 
}

export interface IProductSliceInitialState{
    apiData: IProduct[];
    productsData: IProduct[];
    sortValue: string;
    categoryValue: string;
    searchValue: string;
    isLoading: boolean;
    error: string | undefined;
    success: string | undefined;
    productItem: IProduct | undefined;
}
