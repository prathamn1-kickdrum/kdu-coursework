import React, { useEffect } from "react";
import { useQuoteContext } from "../../context/QuoteContext";
import TagButton from "../TagButton/TagButton";
import './SearchFilter.scss';

const SearchFilter = () => {
    const { searchTags } = useQuoteContext();
    
    useEffect(()=> {
        console.log(searchTags);
    },[searchTags]);

    return (
        <div className="search-filter-container">
            <h3 className="filter-heading">Filters</h3>
            <div className="search-tags-container">
                {Array.from(searchTags).map((tagName) => (
                    <TagButton key={tagName} tagName={tagName} isFilterTag={true}/>
                ))}
            </div>
            <div className="partition-line"></div>
        </div>
    );
};

export default SearchFilter;
