import express from 'express';
import dotenv from 'dotenv';
import cors from 'cors';
import morgan from 'morgan';
import { createServer } from 'node:http';
import { Server } from 'socket.io';
import type { Transaction } from './types';

dotenv.config();

const app = express();
const server = createServer(app);
const io = new Server(server, {
    cors: {
        origin: "http://localhost:5173"
    }
});

app.use(cors());
app.use(morgan('dev'))

const userMap = new Map<string,string>();
let userNum = 0;


io.on('connection', (socket) => {
    console.log('Client connected with ID - ' + socket.id);

    socket.on('joinSocketRoom', (stockCode) => {
        socket.join(stockCode);
        console.log(socket.id + " joined " + stockCode)
        const userName = `user ${++userNum}`;
        userMap.set(socket.id,userName);
    })

    socket.on('transaction', (transaction: Transaction) => {

        if (transaction.status === 'Failed') {
            return;
        }

        io.to(transaction.stock_symbol).except(socket.id).emit('newTransaction', { name: userMap.get(socket.id), ...transaction });
    })

    socket.on('disconnect', () => {
        userMap.delete(socket.id);
        console.log(socket.id + " left")
    })
});

const PORT = process.env.PORT ?? 3000;
server.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
});


