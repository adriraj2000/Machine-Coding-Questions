package org.example;

import org.example.manager.NotificationEventManager;
import org.example.observer.EmailObservable;
import org.example.observer.NotificationObservable;
import org.example.observer.SmsObservable;

public class Main {
    public static void main(String[] args) {
        NotificationEventManager notificationEventManager = new NotificationEventManager();

        NotificationObservable emailObserver = new EmailObservable();
        NotificationObservable smsObserver = new SmsObservable();

        notificationEventManager.subscribe("USER_REGISTER", emailObserver);
        notificationEventManager.subscribe("PASSWORD_RESET", emailObserver);

        notificationEventManager.subscribe("FLIGHT_BOOKED", smsObserver);
        notificationEventManager.subscribe("FLIGHT_BOOKED", emailObserver);

        String user = "Adriraj";
        notificationEventManager.notify("FLIGHT_BOOKED", user);
    }
}