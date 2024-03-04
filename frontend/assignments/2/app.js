const express = require("express");
const http = require("http");
const path = require("path");
const { Server } = require("socket.io");
const morgan = require("morgan");
const { readdirSync } = require("fs");
const socketConnection = require("./utils/messageSocket");
const cors = require('cors');

require("dotenv").config();

const app = express();
const server = http.createServer(app);
const PORT = process.env.PORT || 8000;
const io = new Server(server);

app.use(cors());
app.use(express.json());
app.use(morgan("tiny"));
app.use(express.static(path.join(__dirname, "public")));

readdirSync("./routes").map((route) =>
  app.use("/api/v1/", require("./routes/" + route))
);

server.listen(PORT, () => {
  socketConnection(io);
  console.log(`Server up and running : ${PORT}`);
});
