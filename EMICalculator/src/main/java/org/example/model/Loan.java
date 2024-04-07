package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.strategy.LoanCalculationStrategy;

import java.util.List;

@Data
@AllArgsConstructor
public class Loan {
    private String loanId;
    private String customerUsername;
    private double principal;
    private double rate;
    private int time;
    private LoanCalculationStrategy calculationStrategy;
    private List<Double> emiPayments;
    public void addPayment(Double payment){
        emiPayments.add(payment);
    }
}
