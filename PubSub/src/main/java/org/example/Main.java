package org.example;


import org.example.controller.KafkaController;
import org.example.impl.SimplePublisher;
import org.example.impl.SimpleSubscriber;

public class Main {
    public static void main(String[] args) {
        KafkaController kafkaController = new KafkaController();
        // Create topics.
        Topic topic1 = kafkaController.createTopic("Topic1");
        Topic topic2 = kafkaController.createTopic("Topic2");

        // Create subscribers.
        SimpleSubscriber subscriber1 = new SimpleSubscriber("Subscriber1");
        SimpleSubscriber subscriber2 = new SimpleSubscriber("Subscriber2");
        SimpleSubscriber subscriber3 = new SimpleSubscriber("Subscriber3");

        kafkaController.subscribe(subscriber1, topic1.getTopicId());
        kafkaController.subscribe(subscriber1, topic2.getTopicId());
        kafkaController.subscribe(subscriber2, topic1.getTopicId());
        kafkaController.subscribe(subscriber3, topic2.getTopicId());
        // Create publishers.
        SimplePublisher publisher1 = new SimplePublisher("Publisher1", kafkaController);
        SimplePublisher publisher2 = new SimplePublisher("Publisher2", kafkaController);
        // Publish some messages.
        publisher1.publish(topic1.getTopicId(), new Message("Message m1"));
        publisher1.publish(topic1.getTopicId(), new Message("Message m2"));
        publisher2.publish(topic2.getTopicId(), new Message("Message m3"));

        // Allow time for subscribers to process messages.
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        kafkaController.shutdown();
    }
}