import { Checkbox, FormControlLabel, FormGroup } from "@mui/material";
import { DatePicker } from "@mui/x-date-pickers";
import SearchIcon from "@mui/icons-material/Search";
import styled from "styled-components";
import { useEffect, useMemo, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { RootDispatch, RootState } from "../redux/store/store";
import { findStocks } from "../redux/slice/UserStockSlice";
import { Transaction } from "../app";
import moment, { Moment } from "moment-timezone";
import { groupSortAndSortNewestFirst } from "../utils/TransactionFilter";
import { v4 as uuidv4 } from "uuid";

const TransactionContainerStyled = styled.div`
    max-width: 1500px;
    padding: 1rem;
    margin: 30px auto;
    display: flex;
    gap: 40px;
    overflow-y: auto; /* change to auto to let the browser handle the scrollbar */
    height: 800px;
    scrollbar-width: none; /* for Firefox */
    -ms-overflow-style: none; /* for IE and Edge */
    &::-webkit-scrollbar {
        display: none; /* Hide scrollbar for WebKit browsers */
    }
    @media (max-width: 560px) {
        flex-wrap: wrap;
        padding-inline: 20px;
    }
`;


const FilterWrapper = styled.div`
    flex-basis: 30%;
    border: 1px solid ${(props) => props.theme.colors.greyPrimary};
    border-radius: 10px;
    display: flex;
    flex-direction: column;
    background-color: ${(props) => props.theme.colors.greySecondary};
    max-height: 500px;
    position: sticky;
    top: 20px;

    @media (max-width: 560px) {
        position: inherit;
        flex-basis: 100%;
    }
`;

const FilterHeader = styled.div`
    font-size: 1.2rem;
    display: flex;
    justify-content: space-between;
    padding-inline: 25px;
    padding-block: 10px;
    align-items: center;
    border-bottom: 1px solid ${(props) => props.theme.colors.greyPrimary};

    button {
        color: ${(props) => props.theme.colors.primaryColor};
        font-size: 1.2rem;
        font-weight: 400;
        cursor: pointer;
    }
`;

const FilterInputContainer = styled.div`
    border-bottom: 1px solid ${(props) => props.theme.colors.greyPrimary};
    padding-inline: 10px;
    padding-block: 15px;
`;

const FilterInput = styled.div`
    display: flex;
    border: 1px solid ${(props) => props.theme.colors.greyPrimary};
    border-radius: 5px;
    align-items: center;
    justify-content: center;
    gap: 30px;
    color: ${(props) => props.theme.colors.greyPrimary};
`;

const FilterDate = styled.div`
    border-bottom: 1px solid ${(props) => props.theme.colors.greyPrimary};
    display: flex;
    padding: 10px;
    gap: 10px;
`;

const StyledDatePicker = styled(DatePicker)`
    .MuiOutlinedInput-root {
        background-color: ${(props) => props.theme.colors.greySecondary};
    }
`;

const FilterCheckbox = styled.div`
    border-bottom: 1px solid ${(props) => props.theme.colors.greyPrimary};
    padding-inline: 10px;
    color: ${(props) => props.theme.colors.greyPrimary};

    &:last-child {
        border-bottom: none;
        overflow-y: scroll;

        ::-webkit-scrollbar {
            background-color: transparent;
        }

        ::-webkit-scrollbar-thumb {
            background: ${(props) => props.theme.colors.greyPrimary};
            border-radius: 10px;
        }
    }
`;

const TransactionWrapper = styled.div`
    display: flex;
    flex-direction: column;
    flex-grow: 1;
`;

const TransactionContainer = styled.div`
    display: flex;
    flex-direction: column;
`;

const TransactionDate = styled.div`
    color: ${(props) => props.theme.colors.greyPrimary};
    border-bottom: 2px dotted ${(props) => props.theme.colors.greyPrimary};
    padding-bottom: 20px;

    width: 100%;
`;

const TransactionItem = styled.div`
    margin-top: 20px;
    display: flex;
    align-items: center;
    padding-bottom: 20px;
    border-bottom: 1px solid black;
    /* justify-content: space-between; */

    h1 {
        flex-grow: 1;
        font-size: 1.2rem;
        word-wrap: break-word;
    }
    h1:nth-child(1) {
        width: 10%;
    }

    p {
        margin-left: auto;
        font-size: 1.2rem;
    }

    &:last-child {
        margin-bottom: 60px;
    }
`;

const TransactionItemType = styled.div<{ $type: string }>`
    width: 15px;
    height: 15px;
    border-radius: 50%;
    margin-left: 20px;
    background-color: ${(props) =>
        props.$type === "Passed"
            ? props.theme.colors.greenPrimary
            : props.theme.colors.redPrimary};
`;

const TransactionList = () => {
    const dispatch: RootDispatch = useDispatch();
    const transactions: Transaction[] = useSelector(
        (state: RootState) => state.user.allTransactions
    );
    const transactedStocks: string[] = useSelector(
        (state: RootState) => state.user.transactedStocks
    );

    const [startDate, setStartDate] = useState<Moment | null>(null);
    const [endDate, setEndDate] = useState<Moment | null>(null);

    const [searchInput, setSearchInput] = useState<string>("");
    const [checkboxValues, setCheckboxValues] = useState({
        Passed: false,
        Failed: false,
    });

    useEffect(() => {
        dispatch(findStocks());
    }, []);

    const groupedTransactions = useMemo(
        () => groupSortAndSortNewestFirst(transactions),
        [transactions]
    );

    const handleCheckboxChange = (
        event: React.ChangeEvent<HTMLInputElement>
    ) => {
        const { name, checked } = event.target;
        setCheckboxValues((prevState) => ({
            ...prevState,
            [name]: checked,
        }));
    };

    const filteredTransactions = useMemo(() => {
        return groupedTransactions.map((group) => ({
            ...group,
            transactions: group.transactions.filter((transaction) => {
                if (
                    (checkboxValues.Passed &&
                        transaction.status === "Passed") ||
                    (checkboxValues.Failed && transaction.status === "Failed")
                ) {
                    return true;
                }
                if (!checkboxValues.Passed && !checkboxValues.Failed) {
                    return true;
                }
                return false;
            }),
        }));
    }, [groupedTransactions, checkboxValues]);

    const clearAllFilters = () => {
        setCheckboxValues({ Passed: false, Failed: false });
        setSearchInput("");
        setStartDate(null);
        setEndDate(null);
    };

    const filteredAndSearchedTransactions = useMemo(() => {
        return filteredTransactions
            .map((group) => ({
                ...group,
                transactions: group.transactions.filter((transaction) =>
                    transaction.stock_name
                        .toLowerCase()
                        .includes(searchInput.toLowerCase())
                ),
            }))
            .filter((group) => {
                if (!startDate && !endDate) return true;
                const groupDate = moment(group.date, 'MM/DD/YYYY'); // Adjust parsing format
                return (
                    (!startDate || groupDate.isSameOrAfter(startDate, 'day')) &&
                    (!endDate || groupDate.isSameOrBefore(endDate, 'day'))
                );
            })
            .filter((group) => group.transactions.length > 0);
    }, [filteredTransactions, searchInput, startDate, endDate]);

    return (
        <TransactionContainerStyled>
            <FilterWrapper>
                <FilterHeader>
                    <p>Filters</p>
                    <button onClick={clearAllFilters}>Clear All</button>
                </FilterHeader>

                <FilterInputContainer>
                    <FilterInput>
                        <SearchIcon color="secondary" />
                        <input
                            type="text"
                            placeholder="Search for a Stock"
                            value={searchInput}
                            onChange={(e) => setSearchInput(e.target.value)}
                        />
                    </FilterInput>
                </FilterInputContainer>

                <FilterDate>
                    <StyledDatePicker
                        value={startDate}
                        onChange={(value) =>
                            setStartDate(value as Moment | null)
                        }
                        label="Start Date"
                    />
                    <StyledDatePicker
                        value={endDate}
                        onChange={(value) => setEndDate(value as Moment | null)}
                        label="End Date"
                    />
                </FilterDate>

                <FilterCheckbox>
                    <FormGroup>
                        <FormControlLabel
                            control={
                                <Checkbox
                                    checked={checkboxValues.Passed}
                                    onChange={handleCheckboxChange}
                                    name="Passed"
                                />
                            }
                            label="Passed"
                        />
                        <FormControlLabel
                            control={
                                <Checkbox
                                    checked={checkboxValues.Failed}
                                    onChange={handleCheckboxChange}
                                    name="Failed"
                                />
                            }
                            label="Failed"
                        />
                    </FormGroup>
                </FilterCheckbox>

                <FilterCheckbox>
                    <FormGroup>
                        {transactedStocks.map((item) => (
                            <FormControlLabel
                                key={uuidv4()}
                                control={<Checkbox />}
                                label={`${item}`}
                            
                            />
                        ))}
                    </FormGroup>
                </FilterCheckbox>
            </FilterWrapper>

            <TransactionWrapper>
                {filteredAndSearchedTransactions.map((item) => (
                    <TransactionContainer key={uuidv4()}>
                        <TransactionDate>
                            {moment(item.date, "DD/MM/YYYY").format(
                                "DD MMM YYYY"
                            )}
                        </TransactionDate>
                        {item.transactions.map((transaction) => (
                            <TransactionItem key={uuidv4()}>
                                <h1>{transaction.stock_name}</h1>
                                <h1>{transaction.stock_symbol}</h1>
                                <h1>₹{transaction.transaction_price}</h1>
                                <p>
                                    {moment(transaction.timestamp).format(
                                        "h:mm A"
                                    )}
                                </p>
                                <TransactionItemType
                                    $type={`${transaction.status}`}
                                />
                            </TransactionItem>
                        ))}
                    </TransactionContainer>
                ))}
            </TransactionWrapper>
        </TransactionContainerStyled>
    );
};

export default TransactionList;
