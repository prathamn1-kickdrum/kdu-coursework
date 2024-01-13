package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Logger loggerObj = Logger.getLoggerObject();
        MessageQueue messageQueue = new MessageQueue();

        ExecutorService senderThreadPool = Executors.newFixedThreadPool(3);
        ExecutorService receiverThreadPool = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 3; i++) {
            senderThreadPool.execute(new MessageSender("Sender "+i,messageQueue));
            receiverThreadPool.execute(new MessageReceiver("Receiver "+i,messageQueue));
        }

        // Shutdown the thread pools after a delay to allow messages to be processed
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        senderThreadPool.shutdown();
        receiverThreadPool.shutdown();
    }
}