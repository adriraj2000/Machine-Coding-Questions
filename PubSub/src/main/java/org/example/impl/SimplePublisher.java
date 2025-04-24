package org.example.impl;

import org.example.Message;
import org.example.controller.KafkaController;

public class SimplePublisher implements IPublisher{

    private final String id;
    private final KafkaController kafkaController;

    public SimplePublisher(String id, KafkaController kafkaController) {
        this.id = id;
        this.kafkaController = kafkaController;
    }
    @Override
    public void publish(String topicId, Message message) {
        kafkaController.publish(message, topicId);
    }
}
