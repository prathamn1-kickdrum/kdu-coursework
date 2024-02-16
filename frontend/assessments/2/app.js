const express = require("express");
const cors = require("cors");
const { readdirSync } = require("fs");
const path = require("path");
const http = require("http");
const socket = require("socket.io");
require("dotenv").config();
const getRandomPrice = require("./utils/random_price");

const app = express();

const server = http.createServer(app);



const PORT = process.env.PORT || 5000;

const webSocket = new socket.Server(server, {
  cors: {
    origin: "*",
  },
});

// middlewares

app.use(express.json()); // for parsing incoming requests with JSON data
app.use(cors());
app.use(express.static(path.join(__dirname, "./public/")));

// routes

readdirSync("./routes").map((route) =>
  app.use("/api/v1/", require("./routes/" + route))
);

webSocket.on("connection", (connection) => {
  console.log("connection created on socket");
  setInterval(()=>
    webSocket
      .except(connection.id)
      .emit("serverMessage", getRandomPrice(1, 5000)),
    5000
  );
});

server.listen(PORT, () => {
  console.log("server listening on port " + PORT);
});
