package org.example.Observer;

import org.example.Observable.StockObservable;

public class EmailAlertObserver implements NotificationAlertObserver{

    String emailId;
    StockObservable stockObservable;

    public EmailAlertObserver(String emailId, StockObservable stockObservable){
        this.emailId = emailId;
        this.stockObservable = stockObservable;
    }

    @Override
    public void update() {
        sendMail(emailId,"Product is in stock");
    }

    private void sendMail(String emailId, String msg) {
        System.out.println(msg);
        System.out.println("Mail sent to : " + emailId);
    }
}
