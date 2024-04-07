package org.example.strategy;

public interface LoanCalculationStrategy {
    double calculateInterest(double principal, double rate, int time);
    double calculateTotalAmount(double principal, double rate, int time);
    double calculateEMI(double principal, double rate, int time);
}