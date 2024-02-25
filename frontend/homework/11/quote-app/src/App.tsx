import React from "react";
import { QuoteProvider } from "./context/QuoteContext";
import NewQuoteButton from "./components/NewQuoteButton/NewQuoteButton";
import SearchFilter from "./components/SearchFilter/SearchFilter";
import QuoteContainer from "./components/QuoteContainer/QuoteContainer";
import axios from "axios";
import { ApiQuote, IQuote } from "./types/quote";
import './App.scss';

const QUOTE_API_URL = "https://api.quotable.io/quotes/random";

const App = () => {
    
    const fetchQuotesApi = async (numQuotes: number, setLoading: React.Dispatch<React.SetStateAction<boolean>>): Promise<IQuote[]> => {
        setLoading(true);
        const response = await axios.get(`${QUOTE_API_URL}?limit=${numQuotes}`);
        const apiQuotes: ApiQuote[] = response.data;
    
        const quotes: IQuote[] = apiQuotes.map((apiQuote: ApiQuote) => ({
            _id: apiQuote._id,
            content: apiQuote.content,
            author: apiQuote.author,
            tags: apiQuote.tags,
            dateAdded: apiQuote.dateAdded,
        }));
        setLoading(false);
        return quotes;
    };

    return (
        <QuoteProvider>
            <div className="app-container">
                <NewQuoteButton fetchQuotesApi={fetchQuotesApi} />
                <SearchFilter />
                <QuoteContainer fetchQuotesApi={fetchQuotesApi} />
            </div>
        </QuoteProvider>
    );
};

export default App;
