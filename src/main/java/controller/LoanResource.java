package controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Calculator;
import model.Loan;
import model.PDFGenerator;

public class LoanResource {
    Calculator calculator;
    List<Loan> loans;

    public Map<String, List<Integer>> getLoans(int amount, int years, double interest) {
        calculator = new Calculator(BigDecimal.valueOf(amount), BigDecimal.valueOf(years), BigDecimal.valueOf(interest));
        loans = calculator.calculateAllLoans();
        Map<String, List<Integer>> result = new HashMap<>();
        loans.forEach(loan -> result.put(loan.getLoanType().toString(), Arrays.asList(loan.getTotal().intValue(), loan.getMonthlyRate().orElse(0))));
        return result;
    }

    public void getLoanDownloads(String loanType) {
        PDFGenerator pdfGenerator =  new PDFGenerator();
        loans.stream().filter(loan -> loan.getLoanType().toString().equals(loanType)).forEach(pdfGenerator::generatePDF);
    }
}
