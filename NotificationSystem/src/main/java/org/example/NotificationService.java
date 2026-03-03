package org.example;

import org.example.channel.NotificationChannel;
import org.example.channel.NotificationFactory;
import org.example.model.NotificationRequest;

public class NotificationService {
    public void sendNotification(NotificationRequest request) {
        // Validation logic can go here
        NotificationChannel strategy = NotificationFactory.getChannel(request.getType());
        strategy.send(request);
    }
}
