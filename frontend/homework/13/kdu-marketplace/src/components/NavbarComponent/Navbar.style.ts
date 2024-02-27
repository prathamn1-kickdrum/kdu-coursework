import { CSSProperties } from "react";

export const navbarBox: CSSProperties = {
  height: "7vh",
  width: "100%",
  backgroundColor: "#1a0673",
  color: "white",
  display: "flex",
  justifyContent: "space-between",
  alignItems: "center",
  padding: "0 10vw",
  boxSizing: "border-box",
  fontSize: "2rem",
  position: "sticky",
  top:"0",
  zIndex:100,
};

export const customizationBox: CSSProperties = {
  height: "100%",
  width: "40%",
  boxSizing: "border-box",
  display: "flex",
  justifyContent: "space-between",
  alignItems: "center",
  fontSize: "2rem",
};

export const filterBox: CSSProperties = {
  height: "100%",
  display: "flex",
  justifyContent: "center",
  alignItems: "center",
  fontSize: "2rem",
  paddingLeft: "2rem",
};

export const filterSpan: CSSProperties = {
  fontSize: "1.5rem",
  marginRight: "10px",
};

export const filterSelect: CSSProperties = {
  height: "50%",
  fontSize: "1.2rem",
  outline: "0",
};

export const filterOptions: CSSProperties = {
  fontSize: "1rem",
  border: "0",
  outline: "0",
};
