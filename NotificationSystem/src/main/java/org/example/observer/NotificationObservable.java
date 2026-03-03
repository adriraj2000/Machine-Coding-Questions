package org.example.observer;

import org.example.model.NotificationRequest;
import org.example.model.NotificationType;

public interface NotificationObservable {
    public void doProcess(String event, String user);
}
