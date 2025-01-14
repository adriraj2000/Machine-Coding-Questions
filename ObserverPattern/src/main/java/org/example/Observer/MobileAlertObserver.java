package org.example.Observer;

import org.example.Observable.StockObservable;

public class MobileAlertObserver implements NotificationAlertObserver{
    String userName;
    StockObservable stockObservable;

    public MobileAlertObserver(String userName, StockObservable stockObservable){
        this.userName = userName;
        this.stockObservable = stockObservable;
    }

    @Override
    public void update() {
        sendSms(userName, "Product in stock");
    }

    private void sendSms(String userName, String msg) {
        System.out.println(msg);
        System.out.println("SMS Sent to :" + userName);
    }
}
