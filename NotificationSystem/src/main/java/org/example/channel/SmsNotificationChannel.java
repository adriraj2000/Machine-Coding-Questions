package org.example.channel;

import org.example.model.NotificationRequest;
import org.example.model.SmsNotificationRequest;

public class SmsNotificationChannel implements NotificationChannel{
    @Override
    public void send(NotificationRequest notificationRequest) {
        SmsNotificationRequest smsNotificationRequest = (SmsNotificationRequest) notificationRequest;
        System.out.println("Sending sms :" + smsNotificationRequest.getMessage() + " on " +
                smsNotificationRequest.getDevice());
    }
}
