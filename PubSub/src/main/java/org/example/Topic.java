package org.example;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    String topicId;
    String topicName;
    List<Message> messages;

    public Topic(String topicId, String topicName) {
        this.topicId = topicId;
        this.topicName = topicName;
        this.messages = new ArrayList<>();
    }

    public String getTopicId() {
        return topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public synchronized void addMessage(Message message){
        messages.add(message);
    }
}
