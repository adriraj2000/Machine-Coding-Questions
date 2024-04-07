package org.example.service;

import org.example.model.Loan;
import org.example.model.User;
import org.example.model.UserType;
import org.example.strategy.LoanCalculationStrategy;

import java.util.*;
import java.util.stream.Collectors;

public class LoanService {
    private static LoanService instance;
    private Map<String , Loan> loanMap;
    private UserService userService;
    private LoanService(UserService userService){
        loanMap = new HashMap<>();
        this.userService = userService;
    }

    public void createUser(String userName, UserType userType){
        userService.createUser(userName, userType);
    }

    public void makePayment(String customerId){
        List<Loan> userLoans = loanMap.values().stream().filter(loan -> loan.getCustomerUsername().equals(customerId))
                .collect(Collectors.toList());
        Loan loan = userLoans.get(0);
        Double payment = loan.getCalculationStrategy().calculateEMI(loan.getPrincipal(), loan.getRate(), loan.getTime());
        loan.addPayment(payment);
    }

    public List<Loan> getLoanInfo(String customerName){
        return loanMap.values().stream().filter(loan -> loan.getCustomerUsername().equals(customerName))
                .collect(Collectors.toList());
    }

    public void createLoan(String adminName, String customerName, double principal, double interest,
                           int loanTenure, LoanCalculationStrategy loanCalculationStrategy){
        User admin = userService.getUser(adminName);
        User customer = userService.getUser(customerName);
        if (admin != null && customer != null && admin.getUserType() == UserType.ADMIN && customer.getUserType() == UserType.CUSTOMER) {
            Loan loan = new Loan(UUID.randomUUID().toString(), customerName,
                    principal, interest, loanTenure, loanCalculationStrategy, new ArrayList<>());
            loanMap.put(loan.getLoanId(), loan);
        }
    }

    public List<Loan> getAllLoans(String adminName) {
        User admin = userService.getUser(adminName);
        if(!admin.getUserType().equals(UserType.ADMIN)){
            System.out.println("Given user cannot access loan info");
            return new ArrayList<>();
        }
        return loanMap.values().stream().toList();
    }

    public static LoanService getInstance(UserService userService){
        if(instance == null){
            instance = new LoanService(userService);
        }
        return instance;
    }
}
