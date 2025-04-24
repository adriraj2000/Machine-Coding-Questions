package org.example.controller;

import org.example.Message;
import org.example.Topic;
import org.example.TopicSubscriber;
import org.example.impl.ISubscriber;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class KafkaController {
    private Map<String, Topic> topics;
    private Map<String, List<TopicSubscriber>> topicSubscriberMap;
    private ExecutorService executorService;
    private AtomicInteger topicIdCounter;

    public KafkaController(){
        this.topics = new ConcurrentHashMap<>();
        this.topicSubscriberMap = new ConcurrentHashMap<>();
        this.executorService = Executors.newCachedThreadPool();
        topicIdCounter = new AtomicInteger(0);
    }

    public Topic createTopic(String topicName){
        String topicId = String.valueOf(topicIdCounter.incrementAndGet());
        Topic topic = new Topic(topicName, topicId);
        topics.put(topicId, topic);
        this.topicSubscriberMap.put(topicId, new CopyOnWriteArrayList<>());
        System.out.println(topic + "Created");
        return topic;
    }

    public void subscribe(ISubscriber subscriber, String topicId){
        Topic topic = topics.get(topicId);
        TopicSubscriber ts = new TopicSubscriber(topic,subscriber);
        this.topicSubscriberMap.get(topicId).add(ts);

        executorService.submit(new TopicSubscriberController(ts));
        System.out.println(
                "Subscriber " + subscriber + " subscribed to topic: " + topic.getTopicName());
    }

    public void publish(Message message, String topicId){
        Topic topic = topics.get(topicId);
        topic.addMessage(message);
        List<TopicSubscriber> topicSubscribers = topicSubscriberMap.get(topicId);
        for (TopicSubscriber topicSubscriber : topicSubscribers) {
            synchronized (topicSubscriber) {
                topicSubscriber.notify();
            }
        }
        System.out.println(
                "Message " + message.getMessage() + " published to topic: " + topic.getTopicName());
    }

    public void shutdown() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }
}
