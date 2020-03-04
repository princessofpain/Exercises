package main.java.model;

import java.math.BigDecimal;
import java.util.Optional;

public class Loan {

    private LoanType loanType;
    private Rate[] rates;
    private BigDecimal total;
    private Optional<Double> monthlyRate;

    public Loan(LoanType loanType, Rate[] rates, BigDecimal total, Optional<Double> monthlyRate) {
        this.loanType = loanType;
        this.rates = rates;
        this.total = total;
        this.monthlyRate = monthlyRate;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public Rate[] getRates() {
        return rates;
    }

    public BigDecimal getTotal() { return total; }

    public Optional<Double> getMonthlyRate() { return monthlyRate; }
}
