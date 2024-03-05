import { PayloadAction, createAsyncThunk, createSlice } from '@reduxjs/toolkit'
import axios from 'axios'

const SUMMARIZE_URL = 'https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/all-stocks-transactions.json';

export const fetchSummarize = createAsyncThunk('fetchSummarize', async () => {
    const response = await axios.get(SUMMARIZE_URL);
    return response.data
})

export interface ISummarizeData {
    company: string;
    symbol:  string;
    data:    Datum[];
}

export interface Datum {
    date:   Date;
    prices: number[];
}

export interface IInitialState {
    status: string;
    summarizeTransactions: ISummarizeData[];
    profitSummary: IProfitSummary[];
}

const initialState: IInitialState = {
    status: 'idle',
    summarizeTransactions: [],
    profitSummary: []
}

export interface IProfitSummary {
    company: string;
    symbol: string;
    maxProfit: number;
    buyDay: string | Date;
    sellDay: string | Date;
    buyAmount: number;
    sellAmount: number;
}

const SummarizeStocksSlice = createSlice({
    name: 'summarize',
    initialState,
    reducers: {
        setProfitSummary: (state, action: PayloadAction<IProfitSummary[]>) => {
            state.profitSummary = action.payload;
        },
    },
    extraReducers: builder => {
        builder
            .addCase(fetchSummarize.pending, (state) => {
                state.status = 'loading'
            })
            .addCase(fetchSummarize.fulfilled, (state, action: PayloadAction<ISummarizeData[]>) => {
                state.summarizeTransactions = action.payload;
                state.status = 'success';
            })
            .addCase(fetchSummarize.rejected, (state) => {
                state.status = 'error'
            })
    }
})

export const {setProfitSummary} = SummarizeStocksSlice.actions;
export default SummarizeStocksSlice.reducer
