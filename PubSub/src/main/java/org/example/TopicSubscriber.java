package org.example;

import org.example.impl.ISubscriber;

import java.util.concurrent.atomic.AtomicInteger;

public class TopicSubscriber {
    private Topic topic;
    private AtomicInteger offset;
    private ISubscriber subscriber;

    public TopicSubscriber(Topic topic,ISubscriber subscriber){
        this.topic = topic;
        this.subscriber = subscriber;
        this.offset = new AtomicInteger(0);
    }

    public AtomicInteger getOffset() {
        return offset;
    }

    public void setOffset(AtomicInteger offset) {
        this.offset = offset;
    }

    public ISubscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(ISubscriber subscriber) {
        this.subscriber = subscriber;
    }

    public Topic getTopic() {
        return topic;
    }
}
