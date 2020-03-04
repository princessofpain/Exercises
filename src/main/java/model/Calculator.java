package main.java.model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.math.BigDecimal.*;

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

        BigDecimal total = amount.add(interestInTotal);
        Rate[] rates = {new Rate(interestInTotal, amount, amount, total)};
        return new Loan(LoanType.BULLET, rates, total, Optional.of(total));
    }

    // this repayment has to be payed monthly including the interest. The total amount decreases every month and for this reason the value of the interest also changes
    private Loan calculateAmortizing() {
        BigDecimal allMonths = years.multiply(MONTH_A_YEAR);
        BigDecimal rest = amount;
        Rate[] rates = new Rate[allMonths.intValue()];
        BigDecimal total = ZERO;
        BigDecimal rate;

        for (int i = 0; i < rates.length; i++) {
            rate = calculateRate(allMonths, rest);
            BigDecimal monthlyInterest = calculateMonthlyInterest(rest);
            total = total.add(monthlyInterest).add(rate);

            rates[i] = new Rate(monthlyInterest, rest, rate, total);

            rest = rates[i].getRestAfter();
            allMonths = allMonths.subtract(ONE);
        }
        return new Loan(LoanType.AMORTIZING, rates, total, Optional.empty());
    }

    // this repayment is like the amortizing but the value of the total rate a month stays the same
    private Loan calculateAnnuity() {
        final MathContext ROUNDING_MODE = MathContext.DECIMAL32;
        final BigDecimal HUNDRED = valueOf(100);
        final BigDecimal TWELVE = valueOf(12);
        final BigDecimal ALL_MONTHS = years.multiply(MONTH_A_YEAR);
        Rate[] rates = new Rate[years.multiply(MONTH_A_YEAR).intValue()];
        BigDecimal total = ZERO;

        BigDecimal interestPerMonth = interest.divide(HUNDRED, ROUNDING_MODE).divide(TWELVE, ROUNDING_MODE);
        int powValue = years.negate().multiply(TWELVE, ROUNDING_MODE).intValue();
        BigDecimal rate = interestPerMonth.multiply(amount, ROUNDING_MODE).divide((ONE.subtract((ONE.add(interestPerMonth).pow(powValue, ROUNDING_MODE)))), ROUNDING_MODE);
        BigDecimal monthlyInterest = rate.multiply(ALL_MONTHS, ROUNDING_MODE).subtract(amount).divide(ALL_MONTHS, ROUNDING_MODE);
        BigDecimal rest = amount.subtract(rate);

        for (int i = 0; i < rates.length; i++) {
            total = total.add(rate);
            rates[i] = new Rate(monthlyInterest, rest, rate, total);
            rest = rates[i].getRestAfter();
        }
        return new Loan(LoanType.ANNUITY, rates, total, Optional.of(rate.doubleValue()));
    }

    private BigDecimal calculateRate(BigDecimal remainingMonths, BigDecimal rest) {
        BigDecimal rate = rest.divide(remainingMonths, MathContext.DECIMAL128);

        if(remainingMonths.intValue() == 1) {
            return rate.add(rest);
        }
        return rate;
    }

    private BigDecimal calculateMonthlyInterest(BigDecimal rest) {
        return rest.divide(HUNDRED, MathContext.DECIMAL128).multiply(interest).divide(MONTH_A_YEAR, MathContext.DECIMAL128);
    }
}
