package main.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import main.model.Calculator;
import main.model.Loan;
import main.model.LoanType;
import main.view.XMLGenerator;

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
