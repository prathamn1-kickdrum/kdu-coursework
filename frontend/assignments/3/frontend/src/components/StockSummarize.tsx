import { useEffect, useMemo, useState } from "react";
import {
    useSummarizeDispatch,
    useSummarizeSelector,
} from "../redux/store/store";
import {
    fetchSummarize,
    setProfitSummary,
} from "../redux/slice/SummarizeStocksSlice";
import { styled } from "styled-components";
import ColorRingSpinner from "./ColorRingSpinner";
import { theme } from "../utils/colors";

const StockSummarize = () => {
    const dispatch = useSummarizeDispatch();
    const apiStatus = useSummarizeSelector((state) => state.summarize.status);
    const transactionSummary = useSummarizeSelector(
        (state) => state.summarize.summarizeTransactions
    );
    const profitSummary = useSummarizeSelector(
        (state) => state.summarize.profitSummary
    );
    const [isLoading, setIsLoading] = useState<Boolean>(false);

    const worker = useMemo(
        () =>
            new Worker(
                new URL("../utils/SummarizeServiceWorker.ts", import.meta.url)
            ),
        []
    );

    useEffect(() => {
        const startComputation = () => {
            setIsLoading(true);
            worker.postMessage(transactionSummary);
            worker.onmessage = (event) => {
                dispatch(setProfitSummary(event.data));
                setIsLoading(false);
            };
        };

        if (apiStatus === "idle") {
            dispatch(fetchSummarize());
        } else if (apiStatus === "success") {
            startComputation();
            return () => {
                worker.terminate();
            };
        }

        if (profitSummary.length > 0) {
            console.log(profitSummary);
        }
    }, [apiStatus, dispatch]);

    return (
        <StockSummarizeStyled>
            {isLoading ? (
                <div className="loader">
                    <ColorRingSpinner />
                </div>
            ) : (
                <div className="company-list">
                    {profitSummary.map((transactoinProfit) => {
                        return (
                            <div
                                className={`profit-summary-company ${
                                    transactoinProfit.maxProfit >= 0
                                        ? "blue"
                                        : "red"
                                }`}
                                key={transactoinProfit.company}
                            >
                                <div className="profit-summary-company-left">
                                    <h1 className="profit-company-tag">
                                        {transactoinProfit.company}
                                    </h1>
                                    <h1>{`Profit margin: ${transactoinProfit.maxProfit}`}</h1>
                                </div>
                                <div className="profit-summary-company-right">
                                    <h1 className="buy-tag">{`Buy $${transactoinProfit.buyAmount} on ${transactoinProfit.buyDay}`}</h1>
                                    <h1>{`Sell $${transactoinProfit.sellAmount} on ${transactoinProfit.sellDay}`}</h1>
                                </div>
                            </div>
                        );
                    })}
                </div>
            )}
        </StockSummarizeStyled>
    );
};

const StockSummarizeStyled = styled.div`

    .company-list {
        margin-top: 2rem;
        display: flex;
        flex-direction: column;
        gap: 2rem;
        align-items: center;
        font-size: 0.5rem;
        overflow-y: scroll;
        height: 900px;
        color: white;

        .blue {
            background-color: ${theme.primaryColor};
        }

        .red {
            background-color: ${theme.redPrimary};
        }

        .profit-summary-company {
            width: 80%;
            border-radius: 1rem;
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            padding: 2rem;

            .profit-company-tag {
                font-size: 2rem;
                margin-bottom: 1rem;
                text-align: center;
            }

            .buy-tag {
                margin-bottom: 1rem;
            }

        }
    }

    .loader {
        width: fit-content;
        margin: auto;
    }
`;

export default StockSummarize;
