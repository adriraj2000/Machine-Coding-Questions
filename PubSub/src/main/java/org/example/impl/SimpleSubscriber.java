package org.example.impl;

import org.example.Message;

public class SimpleSubscriber implements ISubscriber{
    private String id;
    public SimpleSubscriber(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
    @Override
    public void consume(Message message) throws InterruptedException {
        System.out.println("Started consuming " + message.getMessage());
        Thread.sleep(500);
        System.out.println("Done consuming" + message.getMessage());
    }
}
