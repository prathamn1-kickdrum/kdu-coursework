package org.example;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        MessageQueue messageQueue = new MessageQueue();

        MessageSender[] senders = {
                new MessageSender("Sender-1", messageQueue),
                new MessageSender("Sender-2", messageQueue),
                new MessageSender("Sender-3", messageQueue)
        };

        MessageReceiver[] receivers = {
                new MessageReceiver("Receiver-1", messageQueue),
                new MessageReceiver("Receiver-2", messageQueue),
                new MessageReceiver("Receiver-3", messageQueue)
        };

        Thread[] senderThreads = new Thread[senders.length];
        Thread[] receiverThreads = new Thread[receivers.length];

        for (int i = 0; i < senders.length; i++) {
            senderThreads[i] = new Thread(senders[i]);
            senderThreads[i].start();
        }

        for (int i = 0; i < receivers.length; i++) {
            receiverThreads[i] = new Thread(receivers[i]);
            receiverThreads[i].start();
        }

        for (int i = 0; i < senders.length; i++) {
            senderThreads[i].join();
        }

        for (int i = 0; i < receivers.length; i++) {
            receiverThreads[i].join();
        }
    }
}