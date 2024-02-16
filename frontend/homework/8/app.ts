const express = require('express');
const cors = require('cors');
const dotenv = require('dotenv');
const {readDirSync} = require('fs');

dotenv.config();

const app = express();

app.use(express.json());
app.use(cors())

readDirSync('./routes').map((routeFile: string)=> {
    app.use('/api/v1/',require(`./routes/${routeFile}`));
})

const PORT : number = parseInt(process.env.PORT ?? "5000");

const server = ()=> {
    app.listen()
    console.log(`server listening on port ${PORT}`);
}

server();