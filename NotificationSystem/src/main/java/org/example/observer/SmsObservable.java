package org.example.observer;

import org.example.NotificationService;
import org.example.model.EmailNotificationRequest;
import org.example.model.SmsNotificationRequest;

import java.util.List;

public class SmsObservable implements NotificationObservable{
    NotificationService notificationService = new NotificationService();
    @Override
    public void doProcess(String event, String user) {
        //parse the data to extract relevant fields, ideally event should be coming from Kafka
        SmsNotificationRequest smsNotificationRequest = new SmsNotificationRequest.Builder()
                .setSenderId("AUTH_MSG")
                .setRecipentId("+123456789")
                .setDevice("Android-v12")
                .setMessage("CSO Alert")
                .build();
        notificationService.sendNotification(smsNotificationRequest);
    }
}
