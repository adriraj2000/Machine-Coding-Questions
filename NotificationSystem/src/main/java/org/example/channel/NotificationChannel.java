package org.example.channel;

import org.example.model.NotificationRequest;

public interface NotificationChannel {
    public void send(NotificationRequest notificationRequest);
}
