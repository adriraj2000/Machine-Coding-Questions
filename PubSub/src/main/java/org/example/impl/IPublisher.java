package org.example.impl;

import org.example.Message;

public interface IPublisher {
    public void publish(String topicId, Message message);
}
