import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import styled from 'styled-components';
import { useDispatch, useSelector } from 'react-redux';
import { RootState } from '../redux/store/store';
import { Stock } from '../app';
import { useMemo } from 'react';
import { useNavigate } from 'react-router-dom';
import { resetGraph } from '../redux/slice/StockGraphSlice';
import AddRemoveWatchButton  from './AddRemoveWatchButton';


const CustomTableContainer = styled(TableContainer)`
    border: 2px solid black;
    border-radius: 10px;
    max-width: 700px;
    margin: 40px auto;
`

const CustomTableHead = styled(TableHead)`
    border-bottom: 2px solid black;    
`

const EmptyList = styled.div`
    text-align: center;
    padding-block: 20px;
    width: 100%;
    margin: 0 auto;
`

const UserWatchlist = () => {
    const stocks: Stock[] = useSelector((state: RootState) => state.stocks.stocks);
    const page = useMemo(() => 0, []);
    const displayStocks = stocks.filter(stocks => stocks.wishlist)
    const dispatch = useDispatch();
    const navigate = useNavigate();


    return (
        <CustomTableContainer>
            <Table sx={{ maxWidth: 700 }} aria-label="simple table">
                <CustomTableHead>
                    <TableRow>
                        <TableCell>Company</TableCell>
                        <TableCell align="center">Base Price</TableCell>
                        <TableCell align="center">wishlist</TableCell>
                    </TableRow>
                </CustomTableHead>
                <TableBody>
                    {
                        displayStocks.length ?
                            displayStocks?.map((row, index) => (
                                <TableRow
                                    key={row.stock_name}
                                    sx={{ '&:last-child td, &:last-child th': { border: 0 }, cursor: "pointer" }}
                                >
                                    <TableCell onClick={() => {
                                        navigate(`/details/${row.stock_symbol}`)
                                        dispatch(resetGraph());
                                    }} component="th" scope="row">
                                        {row.stock_name}
                                    </TableCell>
                                    <TableCell align="center">₹{row.base_price}</TableCell>
                                    <TableCell align="center">
                                        <AddRemoveWatchButton index={index} page={page} wishlist={row?.wishlist} />
                                    </TableCell>
                                </TableRow>
                            )) :
                            <EmptyList>
                                No items added so far
                            </EmptyList>
                    }
                </TableBody>
            </Table>

        </CustomTableContainer>
    );
}


export default UserWatchlist;