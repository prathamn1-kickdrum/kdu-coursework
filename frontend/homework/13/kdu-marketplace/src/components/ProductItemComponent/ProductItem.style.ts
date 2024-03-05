import { CSSProperties } from "react";

export const productCard: CSSProperties = {
  backgroundColor: "white",
  height: "40vh",
  width: "18vw",
  minWidth: "200px",
  marginBottom: "5vh",
  cursor: "pointer",
  position: "relative",
};

export const imageBox: CSSProperties = {
  height: "70%",
  width: "100%",
  display: "flex",
  alignItems: "center",
  justifyContent: "center",
};

export const img: CSSProperties = {
  height: "90%",
  width: "70%",
};

export const titleBox: CSSProperties = {
  display: "flex",
  alignItems: "center",
  justifyContent: "space-evenly",
  margin: "2vh 0",
  fontSize: "1.2rem",
};

export const pricePara: CSSProperties = {
  fontSize: "1.6rem",
  fontWeight: "700",
  color: "blue",
  fontStyle: "italic",
};

export const linkStyle: CSSProperties = {
  position: "absolute",
  top: "0",
  left: "0",
  width: "100%",
  height: "100%",
};
