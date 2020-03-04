package main.java.controller;

import java.math.BigDecimal;
import java.util.List;

import main.java.model.Calculator;
import main.java.model.Loan;
import main.java.view.XMLGenerator;

public class LoanResource {
    Calculator calculator;

    public List<Loan> getLoans(int amount, int years, double interest) {
        calculator = new Calculator(BigDecimal.valueOf(amount), BigDecimal.valueOf(years), BigDecimal.valueOf(interest));
        return calculator.calculateAllLoans();
    }

    public void getLoanDownloads() {
        XMLGenerator generator = new XMLGenerator();
        generator.generatePDF();
    }
}
