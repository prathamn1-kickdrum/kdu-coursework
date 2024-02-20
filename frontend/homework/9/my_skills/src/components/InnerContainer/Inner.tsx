import React, { FC } from "react";
import "./Inner.css";

interface BioProps {
  title: string;
  items: Array<string>;
}

const Inner: FC<BioProps> = (props): JSX.Element => {
  return (
    <>
      <h2 className="bio-title">{props.title}</h2>
      <div className="bio-items">
        {props.items.map((value) => {
          return <h2 key={value}>{value}</h2>;
        })}
      </div>
    </>
  );
};

export default Inner;
