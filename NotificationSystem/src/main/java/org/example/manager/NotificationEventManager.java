package org.example.manager;

import org.example.model.NotificationType;
import org.example.observer.NotificationObservable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationEventManager {
    private final Map<String, List<NotificationObservable>> observers = new HashMap<>();

    public void subscribe(String eventType, NotificationObservable observer) {
        observers.computeIfAbsent(eventType, k -> new ArrayList<>()).add(observer);
    }

    public void unsubscribe(String eventType, NotificationObservable observer) {
        observers.get(eventType).remove(observer);
    }

    public void notify(String event, String user) {
        List<NotificationObservable> users = observers.get(event);
        if (users != null) {
            for (NotificationObservable observer : users) {
                observer.doProcess(event, user);
            }
        }
    }
}
