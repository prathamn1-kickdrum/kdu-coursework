package org.example;

import java.util.concurrent.atomic.AtomicInteger;

class MessageReceiver implements Runnable {
    private final String name;
    Logger loggerObj = Logger.getLoggerObject();
    private final MessageQueue messageQueue;




    public MessageReceiver(String name, MessageQueue messageQueue) {
        this.name = name;
        this.messageQueue = messageQueue;
    }

    public void receiveMessages() throws InterruptedException {
        while(MessageQueue.receiveCounter.get()!=4) {
            if(MessageQueue.receiveCounter.get()==4) {
                break;
            }
            String message = messageQueue.dequeue();
            if (message != null) {
                loggerObj.debugLog(name + " received message: " + message);
            }else {
                Thread.sleep(100);
            }
        }
    }

    @Override
    public void run() {
        try {
            receiveMessages();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
