package model;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertEquals;

class CalculatorTest {

    private BigDecimal amount = new BigDecimal("200000");
    private BigDecimal years = new BigDecimal("20");
    private BigDecimal interest = new BigDecimal("2.5");
    private final Calculator calculator = new Calculator(amount, years, interest);

    @Test
    void calculationsAreCorrect() {
        int expectedBullet = 300000;
        int expectedAmortizing = 250208;
        int expectedAnnuity = 254353;

        List<Loan> allLoans = calculator.calculateAllLoans();
        int actualBullet = allLoans.stream()
                                   .filter(loan -> loan.getLoanType().equals(LoanType.BULLET))
                                   .map(loan -> loan.getRates()[0].getTotal().intValue())
                                   .findFirst()
                                   .orElse(0);

        int actualAmortizing = allLoans.stream()
                                       .filter(loan -> LoanType.AMORTIZING.equals(loan.getLoanType()))
                                       .map(loan -> loan.getRates()[loan.getRates().length - 1].getTotal().intValue())
                                       .findFirst()
                                       .orElse(0);

        int actualAnnuity = allLoans.stream()
                                    .filter(loan -> LoanType.ANNUITY.equals(loan.getLoanType()))
                                    .map(loan -> loan.getRates()[loan.getRates().length - 1].getTotal().intValue())
                                    .findFirst()
                                    .orElse(0);

        assertEquals(expectedBullet, actualBullet);
        assertEquals(expectedAmortizing, actualAmortizing);
        assertEquals(expectedAnnuity, actualAnnuity);
    }

    @Test
    public void ratesAreCorrect() {
        int expectedAnnuityRate = 1059;
        int expectedAmortizingRate = 833;

        List<Loan> allLoans = calculator.calculateAllLoans();

        int actualAnnuityRate = allLoans.stream()
                                        .filter(loan -> LoanType.ANNUITY.equals(loan.getLoanType()))
                                        .map(loan -> loan.getRates()[0].getRate().intValue())
                                        .findFirst()
                                        .orElse(0);

        int actualAmortizingRate = allLoans.stream()
                                           .filter(loan -> LoanType.AMORTIZING.equals(loan.getLoanType()))
                                           .map(loan -> loan.getRates()[0].getRate().intValue())
                                           .findFirst()
                                           .orElse(0);

        assertEquals(expectedAnnuityRate, actualAnnuityRate);
        assertEquals(expectedAmortizingRate, actualAmortizingRate);
    }

    @Test
    public void lastTotalRateIsCorrect() {
        int expectedLastTotalRateAnnuity = (int) (1059.81 + 226.47);
        int expectedLastTotalRateAmortizing = (int) (833.33 + 1.74);

        List<Loan> allLoans = calculator.calculateAllLoans();

        int actualLastTotalRateAnnuity = allLoans.stream()
                                                 .filter(loan -> LoanType.ANNUITY.equals(loan.getLoanType()))
                                                 .map(loan -> loan.getRates()[loan.getRates().length - 1].getRate()
                                                                                                         .add(loan.getRates()[loan.getRates().length - 1].getInterest())
                                                                                                         .intValue())
                                                 .findFirst()
                                                 .orElse(0);

        int actualLastTotalRateAmortizing = allLoans.stream()
                                                    .filter(loan -> LoanType.AMORTIZING.equals(loan.getLoanType()))
                                                    .map(loan -> loan.getRates()[loan.getRates().length - 1].getRate()
                                                                                                            .add(loan.getRates()[loan.getRates().length - 1].getInterest())
                                                                                                            .intValue())
                                                    .findFirst()
                                                    .orElse(0);

        assertEquals(expectedLastTotalRateAnnuity, actualLastTotalRateAnnuity);
        assertEquals(expectedLastTotalRateAmortizing, actualLastTotalRateAmortizing);
    }

    @Test
    public void numberOfRatesIsCorrect() {
        int expectedRatesAmortizing = years.multiply(BigDecimal.valueOf(12)).intValue();
        int expectedRatesAnnuity = expectedRatesAmortizing;
        int expectedRatesBullet = 1;

        List<Loan> allLoans = calculator.calculateAllLoans();
        int actualRatesAmortizing = allLoans.stream()
                                            .filter(loan -> LoanType.AMORTIZING.equals(loan.getLoanType()))
                                            .map(loan -> loan.getRates().length)
                                            .findFirst()
                                            .orElse(0);

        int actualRatesAnnuity = allLoans.stream()
                                         .filter(loan -> LoanType.ANNUITY.equals(loan.getLoanType()))
                                         .map(loan -> loan.getRates().length)
                                         .findFirst()
                                         .orElse(0);

        int actualRatesBullet = allLoans.stream()
                                        .filter(loan -> LoanType.BULLET.equals(loan.getLoanType()))
                                        .map(loan -> loan.getRates().length)
                                        .findFirst()
                                        .orElse(0);

        assertEquals(expectedRatesAmortizing, actualRatesAmortizing);
        assertEquals(expectedRatesAnnuity, actualRatesAnnuity);
        assertEquals(expectedRatesBullet, actualRatesBullet);
    }

    @Test
    public void ratesAreNotZero() {
        List<Loan> allLoans = calculator.calculateAllLoans();
        Loan bullet = allLoans.stream()
                              .filter(loan -> loan.getLoanType() == LoanType.BULLET)
                              .findFirst()
                              .orElse(null);
        Loan amortizing = allLoans.stream()
                                  .filter(loan -> loan.getLoanType() == LoanType.AMORTIZING)
                                  .findFirst()
                                  .orElse(null);
        Loan annuity = allLoans.stream()
                               .filter(loan -> loan.getLoanType() == LoanType.ANNUITY)
                               .findFirst()
                               .orElse(null);

        assertEquals(1, checkRatesAreNotZero(bullet));
        assertEquals(1, checkRatesAreNotZero(amortizing));
        assertEquals(1, checkRatesAreNotZero(annuity));
    }

    private int checkRatesAreNotZero(Loan loan) {
        Rate[] rates = loan.getRates();
        List<Integer> evaluatedRates = new LinkedList();

        for (Rate rate : rates) {
            boolean rateIsBiggerZero = rate.getRate().intValue() > 0;

            if (rateIsBiggerZero) {
                evaluatedRates.add(1);
            } else {
                evaluatedRates.add(0);
            }
        }

        int result = evaluatedRates.stream().mapToInt(Integer::intValue).sum() / rates.length;
        return result;
    }
}
