import { PayloadAction, createSlice } from '@reduxjs/toolkit'
import { GraphType } from '../../app'

export interface GraphState {
    graph: GraphType[]
}

const initialState: GraphState = {
    graph: []
}

const StockGraphSlice = createSlice({
    name: 'graph',
    initialState,
    reducers: {
        updateGraph: (state, action: PayloadAction<GraphType>) => {
            return {
                graph: [...state.graph, action.payload]
            }
        },
        resetGraph: () => {
            return {
                graph: []
            }
        }
    },
})

export const { updateGraph, resetGraph } = StockGraphSlice.actions
export default StockGraphSlice.reducer