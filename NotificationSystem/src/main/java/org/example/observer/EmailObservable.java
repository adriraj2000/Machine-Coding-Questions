package org.example.observer;

import org.example.NotificationService;
import org.example.model.EmailNotificationRequest;
import org.example.model.NotificationRequest;
import org.example.model.NotificationType;

import java.util.List;

public class EmailObservable implements NotificationObservable{
    private final NotificationService notificationService = new NotificationService();
    @Override
    public void doProcess(String event, String user) {
        //parse the data to extract relevant fields, for now construct here
        EmailNotificationRequest emailNotificationRequest =
                new EmailNotificationRequest.Builder()
                        .recipients(List.of("user2"))
                        .author("user1")
                        .body("Hello there")
                        .subject("Informal greeting")
                        .build();
        notificationService.sendNotification(emailNotificationRequest);
    }
}
