package main.java.model;

public enum LoanType {
    BULLET("bullet"), AMORTIZING("amortzing"), ANNUITY("annuity");

    private final String type;

    LoanType(String type) {
        this.type = type;
    }
}

