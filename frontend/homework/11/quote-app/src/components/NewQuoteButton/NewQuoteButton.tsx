import React, { useState } from "react";
import { useQuoteContext } from "../../context/QuoteContext";
import "./NewQuoteButton.scss";
import ColorRing from "../../utils/ColorRingSpinner";

interface IFetchQuote {
    fetchQuotesApi: (numQuotes: number, setLoading: React.Dispatch<React.SetStateAction<boolean>>) => Promise<any>;
}

const NewQuoteButton: React.FC<IFetchQuote> = ({ fetchQuotesApi }) => {
    const { setAllQuotes, allQuotes } = useQuoteContext();
    const [loadingNew, setLoadingNew] = useState<boolean>(false);
    const [loadingRefresh, setLoadingRefresh] = useState<boolean>(false);

    const handleNewQuoteClick = (
        event: React.MouseEvent<HTMLButtonElement>
    ) => {
        fetchQuotesApi(1,setLoadingNew).then((quoteArr) => {
            setAllQuotes([...allQuotes, ...quoteArr]);
        });

    };

    const handleRefreshClick = (event: React.MouseEvent<HTMLButtonElement>) => {
        fetchQuotesApi(5,setLoadingRefresh).then((quoteArr) => {
            setAllQuotes(quoteArr);
        });
    };

    return (
        <div className="new-quote-button-container">
            <button
                className="quote-button"
                type="button"
                onClick={handleNewQuoteClick}
                disabled={loadingNew}
            >
                <div className="button-content">
                    <h2>NEW</h2>
                    <div className="spinner">{loadingNew && <ColorRing />}</div>
                </div>
            </button>
            <button
                className="quote-button"
                type="button"
                onClick={handleRefreshClick}
                disabled={loadingRefresh}
            >
                <div className="button-content">
                    <h2>REFRESH</h2>
                    <div className="spinner">{loadingRefresh && <ColorRing />}</div>
                </div>
            </button>
        </div>
    );
};

export default NewQuoteButton;
