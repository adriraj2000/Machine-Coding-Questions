package org.example;

import org.example.Observable.IphoneStockObservable;
import org.example.Observable.StockObservable;
import org.example.Observer.EmailAlertObserver;
import org.example.Observer.MobileAlertObserver;
import org.example.Observer.NotificationAlertObserver;

public class Main {
    public static void main(String args[]){
        StockObservable iphoneStockObservable = new IphoneStockObservable();

        NotificationAlertObserver obs1 = new EmailAlertObserver("adrirajc@gmail.com",iphoneStockObservable);
        NotificationAlertObserver obs2 = new EmailAlertObserver("adriraj@yahoo.com",iphoneStockObservable);
        NotificationAlertObserver obs3 = new MobileAlertObserver("9812018910",iphoneStockObservable);

        iphoneStockObservable.add(obs1);
        iphoneStockObservable.add(obs2);
        iphoneStockObservable.add(obs3);

        iphoneStockObservable.setStockCount(100);
    }

}