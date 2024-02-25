import React, { useEffect, useState } from "react";
import { useQuoteContext } from "../../context/QuoteContext";
import Quote from "../Quote/Quote";
import { IFetchQuote, IQuote } from "../../types/quote";
import "./QuoteContainer.scss";
import ColorRingSpinner from "../../utils/ColorRingSpinner";

const QuoteContainer: React.FC<IFetchQuote> = ({ fetchQuotesApi }) => {
    const { quotes, setQuotes, allQuotes, searchTags, setAllQuotes } =
        useQuoteContext();
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        const filterQuotes = () => {
            if (searchTags.size === 0) {
                return allQuotes;
            } else {
                return allQuotes.filter((quote) =>
                    quote.tags.some((tag) => searchTags.has(tag))
                );
            }
        };
        setQuotes(filterQuotes());
    }, [searchTags, allQuotes, setQuotes]);

    useEffect(() => {
        fetchQuotesApi(5, setLoading).then((quoteArr) => {
            setAllQuotes(quoteArr);
        });
    }, [setAllQuotes, fetchQuotesApi]);

    return (
        <>
            <div className="spinner">
                {loading && <ColorRingSpinner/>}
            </div>
            <div className="quotes-container">
                {quotes.map((quote: IQuote) => (
                    <Quote key={quote._id} {...quote} />
                ))}
            </div>
        </>
    );
};

export default QuoteContainer;
