package org.example;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class MessageQueue {
    public static final AtomicInteger sendCounter = new AtomicInteger(1);
    public static final AtomicInteger receiveCounter = new AtomicInteger(1);

    private final Queue<String> queue = new LinkedList<>();

    public synchronized void enqueue(String message) {
        sendCounter.getAndIncrement();
        queue.add(message);
    }

    public synchronized String dequeue() {
        if(!queue.isEmpty()) {
            receiveCounter.getAndIncrement();
            return queue.poll();
        }else {
            return null;
        }
    }
}