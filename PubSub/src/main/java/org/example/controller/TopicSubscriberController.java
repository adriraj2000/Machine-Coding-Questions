package org.example.controller;

import org.example.Message;
import org.example.Topic;
import org.example.TopicSubscriber;
import org.example.impl.ISubscriber;

public class TopicSubscriberController implements Runnable{
    private TopicSubscriber topicSubscriber;
    public TopicSubscriberController(TopicSubscriber topicSubscriber) {
        this.topicSubscriber = topicSubscriber;
    }

    @Override
    public void run() {
        Topic topic = topicSubscriber.getTopic();
        ISubscriber subscriber = topicSubscriber.getSubscriber();
        while (true){
            Message message = null;
            synchronized (topicSubscriber){
                while(topicSubscriber.getOffset().get() >= topic.getMessages().size()){
                    try {
                        topicSubscriber.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    int currentOffset = topicSubscriber.getOffset().getAndIncrement();
                    message = topic.getMessages().get(currentOffset);
                }
            }

            try {
                subscriber.consume(message);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
