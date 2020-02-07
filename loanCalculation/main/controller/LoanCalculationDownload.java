package main.controller;

import java.util.List;
import java.util.Map;

import main.view.GenerateXMLFile;
import main.model.Loan;
import main.model.Calculator;
import main.model.LoanType;

public class LoanCalculationDownload extends Calculator {
    public LoanCalculationDownload(Calculator loan) {
        super(loan.getInterest(), loan.getYears(), loan.getAmount());
    }

    private List<Loan> loans = calculateAllLoans();

    public void generateBulletPlanForDownload() {
        GenerateXMLFile generator = new GenerateXMLFile();
        //generator.generateXML("Bullet Repayment", loans.get(LoanType.BULLET).getRates());
    }

    public void generateAmortizingPlanForDownload(String loanType) {
        GenerateXMLFile generator = new GenerateXMLFile();
        //generator.generateXML("Amortizing Repayment", loans.get(LoanType.AMORTIZING).getRates());
    }

    public void generateAnnuityPlanForDownload() {
        GenerateXMLFile generator = new GenerateXMLFile();
        //generator.generateXML("Annuity Repayment", loans.get(LoanType.ANNUITY).getRates());
    }
}
