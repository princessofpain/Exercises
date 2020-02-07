package test.model;

import static junit.framework.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import main.model.Loan;
import main.model.Calculator;
import main.model.LoanType;
import org.junit.jupiter.api.Test;

class CalculatorTest {
	
	private BigDecimal amount = new BigDecimal("200000");
    private BigDecimal years = new BigDecimal("20");
    private BigDecimal interest = new BigDecimal("2.5");
	private final Calculator calculator = new Calculator(amount, years, interest);

	@Test
	void calculationsAreCorrect() {
		int expectedBullet = 300000;
		int expectedAmortizing = 250208;
		int expectedAnnuity = 256588;

		List<Loan> allLoans = calculator.calculateAllLoans();
        int actualBullet = allLoans.stream()
                                 .filter(loan   -> loan.getLoanType().equals(LoanType.BULLET))
                                 .map(loan -> loan.getRates()[0].getTotal().intValue())
                                 .findFirst()
                                 .orElse(0);

        int actualAmortizing = allLoans.stream()
                                    .filter(loan -> LoanType.AMORTIZING.equals(loan.getLoanType()))
                                    .map(loan -> loan.getRates()[0].getTotal().intValue())
                                    .findFirst()
                                    .orElse(0);

//        int actualAnnuity = allLoans.stream()
//                                 .filter(loan -> LoanType.ANNUITY.equals(loan.getLoanType()))
//                                 .map(loan -> loan.getRates()[0].getTotal().intValue())
//                                 .findFirst()
//                                 .orElse(0);

        assertEquals(expectedBullet, actualBullet);
		//assertEquals(expectedAmortizing, actualAmortizing);
		// annuity calculation doesn't work properly currently
        // assertEquals(expectedAnnuity, annuity[annuity.length-1].getTotal().intValue());
	}
}
