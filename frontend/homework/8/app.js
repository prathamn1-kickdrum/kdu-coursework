"use strict";
var _a;
const express = require('express');
const cors = require('cors');
const dotenv = require('dotenv');
const { readDirSync } = require('fs');
dotenv.config();
const app = express();
app.use(express.json());
app.use(cors());
readDirSync('./routes').map((routeFile) => {
    app.use('/api/v1/', require(`./routes/${routeFile}`));
});
const PORT = parseInt((_a = process.env.PORT) !== null && _a !== void 0 ? _a : "5000");
const server = () => {
    app.listen();
    console.log(`server listening on port ${PORT}`);
};
server();
