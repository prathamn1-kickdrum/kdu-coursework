import { PayloadAction, createAsyncThunk, createSlice } from '@reduxjs/toolkit'
import { APIState, Transaction, UserPortfolio } from '../../app'
import { generateRandomBalance } from '../../utils/UserBalance';
import axios from 'axios'


const PORTFOLIO_URL = 'https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/portfolio-transactions.json'

export const fetchPortfolio = createAsyncThunk('fetchPortfolio', async () => {
    const response = await axios.get(PORTFOLIO_URL);
    return response.data
})

export interface UserState {
    walletBalance: number;
    stocks: UserPortfolio[],
    allTransactions: Transaction[],
    filteredTransactions: Transaction[],
    status: APIState;
    transactedStocks: string[];
}

const initialState: UserState = {
    walletBalance: generateRandomBalance(),
    stocks: [],
    allTransactions: [],
    filteredTransactions: [],
    status: null,
    transactedStocks: []
}

const UserStockSlice = createSlice({
    name: 'user',
    initialState,
    reducers: {
        buyAction: (state, action: PayloadAction<number>) => {
            return {
                ...state,
                walletBalance: state.walletBalance - action.payload
            }
        },
        sellAction: (state, action: PayloadAction<number>) => {
            return {
                ...state,
                walletBalance: state.walletBalance + action.payload
            }
        },
        addStock: (state, action: PayloadAction<UserPortfolio>) => {
            const index = state.stocks.findIndex(stock => stock.stock_symbol === action.payload.stock_symbol)

            if (index !== -1) {
                state.stocks[index].quantity += action.payload.quantity;
                return;
            }

            state.stocks.push(action.payload)
        },
        removeStock: (state, action: PayloadAction<UserPortfolio>) => {
            const index = state.stocks.findIndex(stock => stock.stock_symbol === action.payload.stock_symbol)

            if (state.stocks[index].quantity >= action.payload.quantity) {
                state.stocks[index].quantity -= action.payload.quantity;
                return;
            }

            state.stocks[index].quantity = 0;
        },
        addTransaction: (state, action: PayloadAction<Transaction>) => {
            return {
                ...state,
                allTransactions: [...state.allTransactions, action.payload]
            }
        },
        findStocks: (state) => {
            const uniqueStocks = new Set();

            state.allTransactions.forEach(transaction => {
                uniqueStocks.add(transaction.stock_name);
            });

            return {
                ...state,
                transactedStocks: Array.from(uniqueStocks) as string[]
            }
        }
    },
    extraReducers: builder => {
        builder
            .addCase(fetchPortfolio.pending, (state) => {
                state.status = 'loading'
            })
            .addCase(fetchPortfolio.fulfilled, (state, action: PayloadAction<Transaction[]>) => {
                state.allTransactions = action.payload
                state.status = 'success'
            })
            .addCase(fetchPortfolio.rejected, (state) => {
                state.status = 'error'
            })
    }
})

export const { buyAction, sellAction, addStock, removeStock, addTransaction, findStocks } = UserStockSlice.actions
export default UserStockSlice.reducer