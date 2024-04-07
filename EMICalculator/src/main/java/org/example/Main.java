package org.example;

import org.example.model.UserType;
import org.example.service.LoanService;
import org.example.service.UserService;
import org.example.strategy.SimpleInterestCalculation;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        LoanService loanService = LoanService.getInstance(userService);
        loanService.createUser("u1", UserType.CUSTOMER);
        loanService.createUser("u2", UserType.CUSTOMER);
        loanService.createUser("u3", UserType.CUSTOMER);
        loanService.createUser("u4", UserType.ADMIN);
        loanService.createLoan("u4","u1",100,2,4, new SimpleInterestCalculation());
        System.out.println(loanService.getLoanInfo("u1"));
        String loanId = loanService.getLoanInfo("u1").get(0).getLoanId();
        loanService.makePayment("u1");
        System.out.println(loanService.getAllLoans("u4"));
    }
}