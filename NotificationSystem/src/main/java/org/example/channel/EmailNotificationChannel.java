package org.example.channel;

import org.example.model.EmailNotificationRequest;
import org.example.model.NotificationRequest;

public class EmailNotificationChannel implements NotificationChannel{
    @Override
    public void send(NotificationRequest notificationRequest) {
        EmailNotificationRequest emailNotificationRequest = (EmailNotificationRequest) notificationRequest;
        System.out.println("Sending Email to " + emailNotificationRequest.getRecipients()
                + ": " + emailNotificationRequest.getBody());
    }
}
