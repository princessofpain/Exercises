package main.java.model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Calculator {
    private BigDecimal interest;
    private BigDecimal years;
    private BigDecimal amount;

    public Calculator(BigDecimal amount, BigDecimal years, BigDecimal interest) {
        this.amount = amount;
        this.years = years;
        this.interest = interest;
    }

    private final BigDecimal MONTH_A_YEAR = new BigDecimal("12");
    private final BigDecimal HUNDRED = new BigDecimal("100");

    public BigDecimal getInterest() {
        return interest;
    }

    public BigDecimal getYears() {
        return years;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public List<Loan> calculateAllLoans() {
        Loan bullet = calculateBullet();
        Loan amortizing = calculateAmortizing();
        Loan annuity = calculateAnnuity();

        return Arrays.asList(bullet, amortizing, annuity);
    }

    // this repayment has to be payed fully at the end of the time period, but the interest has to be calculated for every year
    private Loan calculateBullet() {
        BigDecimal interestInTotal = amount.divide(HUNDRED, MathContext.DECIMAL128).multiply(interest).multiply(years);

        Rate[] rates = {new Rate(interestInTotal, amount, amount, amount.add(interestInTotal))};
        return new Loan(LoanType.BULLET, rates, calculateTotalAmount(rates, true), Optional.empty());
    }


    // this repayment has to be payed monthly including the interest. The total amount decreases every month and for this reason the value of the interest also changes
    private Loan calculateAmortizing() {
        final BigDecimal ALL_MONTHS = years.multiply(MONTH_A_YEAR);
        BigDecimal rest = amount;
        Rate[] rates = new Rate[ALL_MONTHS.intValue()];
        BigDecimal total = BigDecimal.ZERO;
        BigDecimal rate;

        for (int i = 0; i < rates.length; i++) {
            if(i == rates.length -1) rate = rest;

            rate = rest.divide(ALL_MONTHS, MathContext.DECIMAL128);
            BigDecimal monthlyInterest = calculateMonthlyInterest(rest);
            total = total.add(monthlyInterest).add(rate);

            rates[i] = new Rate(monthlyInterest, rest, rate, total);

            rest = rates[i].getRestAfter();
        }

        return new Loan(LoanType.AMORTIZING, rates, calculateTotalAmount(rates, false), Optional.empty());
    }

    // this repayment is like the amortizing but the value of the total rate a month stays the same
    private Loan calculateAnnuity() {
        BigDecimal allMonths = years.multiply(MONTH_A_YEAR);
        BigDecimal interestValue = interest.divide(BigDecimal.valueOf(100), MathContext.DECIMAL32);
        Rate[] rates = new Rate[years.multiply(MONTH_A_YEAR).intValue()];

//        BigDecimal interestFactor = interest.divide(HUNDRED, MathContext.DECIMAL128);

//        BigDecimal partOne = interest.divide(MONTH_A_YEAR, MathContext.DECIMAL32).add(BigDecimal.ONE);
//        BigDecimal powAmount = years.multiply(MONTH_A_YEAR).add(BigDecimal.ONE);
//        BigDecimal partTwo = partOne.pow(powAmount.intValue()).subtract(BigDecimal.ONE);
//        BigDecimal numerator = amount.multiply(partTwo);
//        BigDecimal denominator = MONTH_A_YEAR.divide(years, MathContext.DECIMAL32);
//        BigDecimal monthlyRateWithInterest = numerator.divide(denominator, MathContext.DECIMAL32).subtract(amount);
//        BigDecimal monthlyRate = amount.divide(MONTH_A_YEAR, MathContext.DECIMAL32);
        // |                         annuityLoan                            |
        //          |                         numeratorResult               |
        //            |             powResult            |
        //              |numeratorBase |   |powVal |         |divisionVa|
        // amount / ( ( (1 + 0.025 / 12) ^ (240 + 1) - 1 ) / (0.025 / 12) -1)

        BigDecimal numeratorBase = interestValue.divide(MONTH_A_YEAR, MathContext.DECIMAL32).add(BigDecimal.ONE);
        BigDecimal powValue = allMonths.add(BigDecimal.ONE);
        BigDecimal divisionValue = interestValue.divide(MONTH_A_YEAR, MathContext.DECIMAL32);
        BigDecimal powResult = numeratorBase.pow(powValue.intValue(), MathContext.DECIMAL32).subtract(BigDecimal.ONE);
        BigDecimal numeratorResult = powResult.divide(divisionValue, MathContext.DECIMAL32).subtract(BigDecimal.ONE);
        BigDecimal annuityCalculation = amount.divide(numeratorResult, MathContext.DECIMAL32);

//        BigDecimal basicCalculation = interestFactor.add(BigDecimal.ONE).pow(years.intValue());
//        BigDecimal numerator = basicCalculation.multiply(interestFactor);
//        BigDecimal denominator = basicCalculation.subtract(BigDecimal.ONE);
//        BigDecimal fractionResult = numerator.divide(denominator, MathContext.DECIMAL128);
//
        final BigDecimal monthlyInterest = annuityCalculation.multiply(years).subtract(amount).divide(MONTH_A_YEAR, MathContext.DECIMAL128);
        final BigDecimal monthlyRate = annuityCalculation.divide(MONTH_A_YEAR, MathContext.DECIMAL128);
        BigDecimal monthlyTotalRate = monthlyInterest.add(monthlyRate);
        BigDecimal rest = annuityCalculation;

        for (int i = 0; i < MONTH_A_YEAR.intValue(); i++) {
            rates[i] = new Rate(monthlyInterest, rest, monthlyRate, monthlyTotalRate);
            rest = rates[i].getRestAfter();
        }
        return null;
                //new Loan(LoanType.ANNUITY, rates, calculateTotalAmount(rates, false));
    }

    private int calculateTotalAmount(Rate[] rates, boolean isBullet) {
        Rate lastRate;

        if(isBullet) {
            lastRate = rates[0];
        } else {
            lastRate = rates[rates.length-1];
        }

        return lastRate.getTotal().add(lastRate.getRestAfter()).intValue();
    }

    private BigDecimal calculateMonthlyInterest(BigDecimal rest) {
        return rest.divide(HUNDRED, MathContext.DECIMAL128).multiply(interest).divide(MONTH_A_YEAR, MathContext.DECIMAL128);
    }
}
