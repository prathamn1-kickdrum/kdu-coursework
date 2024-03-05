import React from "react";
import { useQuoteContext } from "../../context/QuoteContext";
import { ITag } from "../../types/tag";
import './TagButton.scss';


const TagButton = (props: ITag) => {

	const {searchTags,setSearchTags} = useQuoteContext();

    const handleTagClick = (event: React.MouseEvent<HTMLButtonElement>) => {
		const tagButton = event.target as HTMLButtonElement;
	
		if (tagButton.textContent === null) return;
	
		const newSearchTags = new Set(searchTags);
		const isFilterTag = tagButton.getAttribute('data-is-filter-tag');
	
		if (isFilterTag==='true') {
			newSearchTags.delete(tagButton.textContent);
			tagButton.classList.remove('filter-tag');
		} else {
			newSearchTags.add(tagButton.textContent);
			tagButton.classList.add('filter-tag');
		}
	
		setSearchTags(newSearchTags); 
		console.log('onclick tagButton', newSearchTags);
	};
    return (
        <button type="button" onClick={handleTagClick} data-is-filter-tag = {props.isFilterTag} className="tag-button">
            {props.tagName}
        </button>
    );
};

export default TagButton;
