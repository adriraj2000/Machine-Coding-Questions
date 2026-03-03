package org.example.channel;

import org.example.model.NotificationType;

public class NotificationFactory {
    public static NotificationChannel getChannel(NotificationType type) {
        return switch (type) {
            case EMAIL -> new EmailNotificationChannel();
            case SMS -> new SmsNotificationChannel();
            default -> throw new IllegalArgumentException("Unknown notification type");
        };
    }
}
