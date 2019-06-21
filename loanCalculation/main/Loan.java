package main;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;
import java.util.List;

public class Loan {
    protected BigDecimal interest;
    protected BigDecimal years;
    protected BigDecimal amount;

    public Loan(BigDecimal interest, BigDecimal years, BigDecimal amount) {
        this.interest = interest;
        this.years = years;
        this.amount = amount;
    }

    private final BigDecimal MONTH_A_YEAR = new BigDecimal("12");
    private final BigDecimal HUNDRED = new BigDecimal("100");

    public List<Rate[]> calculateAllLoans() {
        return Arrays.asList(calculateBullet(), calculateAmortizing(), calculateAnnuity());
    }

    // this repayment has to be payed fully at the end of the time period, but the interest has to be calculated for every year
    protected Rate[] calculateBullet() {
        BigDecimal interestInTotal = amount.divide(HUNDRED, MathContext.DECIMAL128).multiply(interest).multiply(years);
        BigDecimal result = amount.add(interestInTotal);

        return new Rate[]{new Rate(interestInTotal, amount, result, "bullet", result)};
    }

    // this repayment has to be payed monthly including the interest. The total amount decreases every month and for this reason the value of the interest also changes
    protected Rate[] calculateAmortizing() {
        final BigDecimal ALL_MONTHS = years.multiply(MONTH_A_YEAR);
        BigDecimal rest = amount;
        Rate[] rates = new Rate[ALL_MONTHS.intValue()];
        BigDecimal rate = amount.divide(ALL_MONTHS, MathContext.DECIMAL128);

        for (int i = 0; i < ALL_MONTHS.intValue() - 1; i++) {
            rates[i] = addRateWithInterest(rest, rate, "amortizing", amount.subtract(rest).add(rate));
            rest = rates[i].getRestAfter();
        }

        rates[ALL_MONTHS.intValue() - 1] = addRateWithInterest(rest, BigDecimal.ZERO, "amortizing", amount.add(rest));

        return rates;
    }

    private Rate addRateWithInterest(BigDecimal rest, BigDecimal rate, String type, BigDecimal total) {
        BigDecimal monthlyInterest = rest.divide(HUNDRED, MathContext.DECIMAL128).multiply(interest).divide(MONTH_A_YEAR, MathContext.DECIMAL128);

//        if(i == ALL_MONTHS.intValue() - 1 && rest.doubleValue() > 0) {
//            rate.add(rest);
//        }

        return new Rate(monthlyInterest, rest, rate, type, total.add(monthlyInterest));
    }

    // this repayment is like the amortizing but the value of the total rate a month stays the same
    protected Rate[] calculateAnnuity() {
        final BigDecimal ALL_MONTHS = years.multiply(MONTH_A_YEAR);
        Rate[] rates = new Rate[ALL_MONTHS.intValue()];

        BigDecimal interestFactor = interest.divide(HUNDRED, MathContext.DECIMAL128);

        BigDecimal basicCalculation = interestFactor.add(BigDecimal.ONE).pow(years.intValue());
        BigDecimal numerator = basicCalculation.multiply(interestFactor);
        BigDecimal denominator = basicCalculation.subtract(BigDecimal.ONE);
        BigDecimal fractionResult = numerator.divide(denominator, MathContext.DECIMAL128);

        BigDecimal monthlyInterest = fractionResult.multiply(years).subtract(amount).divide(ALL_MONTHS, MathContext.DECIMAL128);
        final BigDecimal monthlyRate = fractionResult.divide(ALL_MONTHS, MathContext.DECIMAL128);
        BigDecimal rest = fractionResult;

        for (int i = 0; i < ALL_MONTHS.intValue(); i++) {
            rates[i] = new Rate(monthlyInterest, rest, monthlyRate, "annuity", fractionResult);
            rest = rates[i].getRestAfter();
        }
        return rates;
    }
}
