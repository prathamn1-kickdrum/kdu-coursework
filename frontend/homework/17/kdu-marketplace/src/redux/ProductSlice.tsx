import { PayloadAction, createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { IProduct, IProductSliceInitialState } from "../types/KduMarketPlace";


const initialState: IProductSliceInitialState = {
    apiData: [],
    productsData: [],
    sortValue: "",
    categoryValue: "",
    searchValue: "",
    isLoading: false,
    error: undefined,
    success: undefined,
    productItem: undefined
}

export const fetchApi = createAsyncThunk('product/fetchApi', async () => {
    console.log("api called...");
    const res = await fetch('https://fakestoreapi.com/products');
    const data = await res.json();
    return data;
})


export const productSlice = createSlice({
    name: 'product',
    initialState,
    reducers: {
        setProductsData: (state, action: PayloadAction<IProduct[]>) => {
            console.log("setting prod data...", action.payload.length);
            state.productsData = [...action.payload];
        },
        setApiData: (state, action: PayloadAction<IProduct[]>) => {
            console.log("setting api data...", action.payload.length);
            state.apiData = [...action.payload];
        },
        setProductItem: (state, action: PayloadAction<IProduct>) => {
            state.productItem = { ...action.payload };
        },
        setSortValue: (state, action: PayloadAction<string>) => {
            state.sortValue = action.payload;
        },
        setCategoryValue: (state, action: PayloadAction<string>) => {
            state.categoryValue = action.payload;
        },
        setSearchValue: (state, action: PayloadAction<string>) => {
            state.searchValue = action.payload;
        },
        setError: (state, action: PayloadAction<string>) => {
            state.error = action.payload;
        },
        setSuccess: (state, action: PayloadAction<string>) => {
            state.success = action.payload;
        },
    },
    extraReducers: (builder) => {
        builder.addCase(fetchApi.pending, (state) => {
            state.isLoading = true
        })
        builder.addCase(fetchApi.fulfilled, (state, action) => {
            state.isLoading = false
            state.productsData = action.payload
            state.apiData = action.payload
        })
        builder.addCase(fetchApi.rejected, (state, action) => {
            state.isLoading = false
            state.error = action.error.message
        })
    },
});


export default productSlice.reducer;
export const { setProductsData, setProductItem, setSortValue, setCategoryValue, setSearchValue, setApiData, setError, setSuccess } = productSlice.actions;