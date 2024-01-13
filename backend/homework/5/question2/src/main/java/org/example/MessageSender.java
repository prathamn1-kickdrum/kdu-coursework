package org.example;

class MessageSender implements Runnable {

    Logger loggerObj = Logger.getLoggerObject();
    private final String name;
    private final MessageQueue messageQueue;

    public MessageSender(String name, MessageQueue messageQueue) {
        this.name = name;
        this.messageQueue = messageQueue;
    }

    public synchronized void sendMessage(String message) {
        loggerObj.debugLog(name + " sending message: " + message);
        messageQueue.enqueue(message);
    }

    @Override
    public void run() {
        String message = "Message-" + MessageQueue.sendCounter.getAndIncrement();
        sendMessage(message);
    }
}