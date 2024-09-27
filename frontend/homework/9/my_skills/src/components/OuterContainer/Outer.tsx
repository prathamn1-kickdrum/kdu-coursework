import React from "react";
import Inner from "../InnerContainer/Inner";
import "./Outer.css";

const Outer = () => {
  return (
    <div className="outer-container">
      <div className="info-container">
        <h2>Pratham</h2>
        <p>Pratham Nagaria</p>
        <h2>SDE</h2>
      </div>
      <div className="bio-container">
        <div className="skill-container">
          <Inner title={"Skills"} items={["Python", "React"]} />
        </div>
        <div className="hobby-container">
          <Inner title={"Hobbies"} items={["Cricket"]} />
        </div>
      </div>
    </div>
  );
};

export default Outer;
