const express = require('express');
const cors = require('cors');
const http = require('http');
const socket = require('socket.io');

const app = express();
const server = http.createServer(app);

const port = 4000;

const webSocket = new socket.Server(server,{
    cors: {
        origin: "*"
    }
});

webSocket.on("connection",(connection)=> {
    console.log("connection created on socket");
    connection.on("clientMessage",(msg)=>{
        webSocket.except(connection.id).emit("serverMessage",msg);
    });
});

app.use(cors());
app.use(express.json());

app.get('/',(req,res)=> {
    res.status(200).json({message: "hello pratham"});
})


server.listen(port,()=> {
    console.log("Listening of port "+port);
})
