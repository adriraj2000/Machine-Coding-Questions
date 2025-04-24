package org.example.impl;

import org.example.Message;

public interface ISubscriber {
    public void consume(Message message) throws InterruptedException;
}
