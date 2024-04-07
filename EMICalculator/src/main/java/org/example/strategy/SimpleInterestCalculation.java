package org.example.strategy;

public class SimpleInterestCalculation implements LoanCalculationStrategy{
    @Override
    public double calculateInterest(double principal, double rate, int time) {
        return principal*rate*time/100;
    }

    @Override
    public double calculateTotalAmount(double principal, double rate, int time) {
        return principal + calculateInterest(principal, rate, time);
    }

    @Override
    public double calculateEMI(double principal, double rate, int time) {
        return calculateTotalAmount(principal,rate,time)/(time*12);
    }
}
