import React from "react";
import { IQuote } from "../../types/quote";
import TagButton from "../TagButton/TagButton";
import './Quote.scss';

const Quote = (props: IQuote) => {
    return (
        <div className="quote-container">
            <div className="data-container">
                <h1 className="content">{props.content}</h1>
            </div>
            <div className="metadata-container">
                <p className="author">~{props.author}</p>
                <p className="date">{props.dateAdded}</p>
            </div>
            <div className="tags-container">
                {props.tags.map((tagName:string)=> (
                    <TagButton key={tagName} tagName={tagName} isFilterTag={false} />
                ))}
            </div>
        </div>
    );
};

export default Quote;
