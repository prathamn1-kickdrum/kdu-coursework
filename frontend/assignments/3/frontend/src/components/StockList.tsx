import styled from "styled-components";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../redux/store/store";
import { Stock } from "../app";
import {
    Pagination,
    Stack,
    TableHead,
    TableContainer,
    TableBody,
    Table,
} from "@mui/material";
import { useState } from "react";
import { paginate } from "../redux/slice/ApiStocksSlice";
import { useNavigate } from "react-router-dom";
import { resetGraph } from "../redux/slice/StockGraphSlice";
import AddRemoveWatchButton from "./AddRemoveWatchButton";

const CustomTableContainer = styled(TableContainer)`
    border: 2px solid black;
    border-radius: 10px;
    max-width: 1550px;
    margin: 40px auto;
    div {
        padding: 0.3rem;
        font-size: 1.4rem;
    }
`;

const CustomTableHead = styled(TableHead)`
    border-bottom: 2px solid black;
`;

const TableColumnContainer = styled.div`
    display: flex;
    gap: 5rem;
    width: 100%;
    justify-content: flex-end;

    div {
        width: 20%;
    }
`;

const TableRowContainer = styled.div`
    display: flex;
    width: 100%;
    justify-content: space-between;
    border-bottom: 1px solid black;

    & > :first-child {
        width: 60%;
    }
`;

const StockList = () => {
    const paginatedStocks: Stock[] = useSelector(
        (state: RootState) => state.stocks.page
    );
    const [page, setPage] = useState<number | null>(null);
    const dispatch = useDispatch();
    const navigate = useNavigate();

    function handleChange(_: React.ChangeEvent<unknown>, page: number) {
        dispatch(paginate(page - 1));
        setPage(page);
    }

    return (
        <CustomTableContainer>
            <Table
                sx={{ maxWidth: 1550, height: 650 }}
                aria-label="simple table"
            >
                <CustomTableHead>
                    <TableRowContainer>
                        <div>Company</div>
                        <TableColumnContainer>
                            <div>Base Price</div>
                            <div>Watchlist</div>
                        </TableColumnContainer>
                    </TableRowContainer>
                </CustomTableHead>
                <TableBody>
                    {paginatedStocks?.map((row, index) => (
                        <TableRowContainer key={row.stock_name}>
                            <div
                                onClick={() => {
                                    navigate(`/details/${row.stock_symbol}`);
                                    dispatch(resetGraph());
                                }}
                                style={{ cursor: "pointer" }}
                            >
                                {row.stock_name}
                            </div>
                            <TableColumnContainer>
                                <div>₹{row.base_price}</div>
                                <div>
                                    <AddRemoveWatchButton
                                        index={index}
                                        wishlist={row?.wishlist}
                                        page={page!}
                                    />
                                </div>
                            </TableColumnContainer>
                        </TableRowContainer>
                    ))}
                </TableBody>
            </Table>

            <Stack
                sx={{ marginBlock: 2 }}
                alignItems={"center"}
                spacing={10}
            >
                <Pagination
                    onChange={handleChange}
                    sx={{ fontWeight: 900 }}
                    count={10}
                    color="primary"
                />
            </Stack>
        </CustomTableContainer>
    );
};

export default StockList;
