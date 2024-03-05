import React, { useContext, useMemo, useState } from "react";
import { IQuote } from "../types/quote";

const QuoteContext = React.createContext<QuoteContextType | null>(null);



type QuoteContextType = {
    quotes: IQuote[];
    setQuotes: React.Dispatch<React.SetStateAction<IQuote[]>>;
    searchTags: Set<string>;
    setSearchTags: React.Dispatch<React.SetStateAction<Set<string>>>;
    allQuotes: IQuote[];
    setAllQuotes: React.Dispatch<React.SetStateAction<IQuote[]>>;

}


export const QuoteProvider: React.FC<{ children: React.ReactNode }> = ({
    children,
}) => {
    const [quotes, setQuotes] = useState<IQuote[]>([]);
    const [searchTags,setSearchTags] = useState<Set<string>>(new Set());
    const [allQuotes, setAllQuotes] = useState<IQuote[]>([]);


    return useMemo(
        () => (
            <QuoteContext.Provider
                value={{
                    quotes,
                    setQuotes,
                    searchTags,
                    setSearchTags,
                    allQuotes,
                    setAllQuotes,
                }}
            >
                {children}
            </QuoteContext.Provider>
        ),
        [quotes, children,searchTags,setSearchTags,allQuotes,setAllQuotes]
    );
};


export const useQuoteContext = () => {
    return useContext(QuoteContext) as QuoteContextType;
}
