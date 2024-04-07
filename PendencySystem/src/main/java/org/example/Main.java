package org.example;

import org.example.impl.PendencySystem;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        PendencySystem system = new PendencySystem();
        // Example usage
        system.startTracking(1112, Arrays.asList("UPI", "Karnataka", "Bangalore"));
        system.startTracking(2451, Arrays.asList("UPI", "Karnataka", "Mysore"));
        system.startTracking(3421, Arrays.asList("UPI", "Rajasthan", "Jaipur"));
        system.startTracking(1221, Arrays.asList("Wallet", "Karnataka", "Bangalore"));

        // Testing getCounts
        System.out.println("Counts for [\"UPI\"] : " + system.getCounts(Arrays.asList("UPI")));
        System.out.println("Counts for [\"UPI\", \"Karnataka\"] : " + system.getCounts(Arrays.asList("UPI", "Karnataka")));
        System.out.println("Counts for [\"UPI\", \"Karnataka\", \"Bangalore\"] : " + system.getCounts(Arrays.asList("UPI", "Karnataka", "Bangalore")));
        System.out.println("Counts for [\"Bangalore\"] : " + system.getCounts(Arrays.asList("Bangalore")));

        system.startTracking(4221, Arrays.asList("Wallet", "Karnataka", "Bangalore"));
        system.stopTracking(1112);
        system.stopTracking(2451);

        // Testing getCounts after adding and stopping transactions
        System.out.println("Counts for [\"UPI\"] after transactions : " + system.getCounts(Arrays.asList("UPI")));
        System.out.println("Counts for [\"Wallet\"] after transactions : " + system.getCounts(Arrays.asList("Wallet")));
        System.out.println("Counts for [\"UPI\", \"Karnataka\", \"Bangalore\"] after transactions : " + system.getCounts(Arrays.asList("UPI", "Karnataka", "Bangalore")));
    }
}