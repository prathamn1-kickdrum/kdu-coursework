package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue();
        Logger loggerObj = Logger.getLoggerObject();
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
            Thread.currentThread().interrupt();
            loggerObj.errorLog("Thread interrupted",e);        }

        senderThreadPool.shutdown();
        receiverThreadPool.shutdown();
    }
}