package test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import main.*;

public class CheckPaymentsTest {
	
	BigDecimal amount = new BigDecimal("200000");
	BigDecimal years = new BigDecimal("20");
	BigDecimal interest = new BigDecimal("2.5");
	private final CheckPayments cp = new CheckPayments(amount, years, interest);
	
	@Test
	void bulletCalculationIsCorrect() {
		int expected = 300000;
		int actual = (int) cp.checkBulletRepayment().doubleValue();
		
		assertEquals(expected, actual);;
	}
	
	@Test
	void amortizingCalculationIsCorrect() {
		int expected = 250208;
		int actual = cp.checkAmortizingRepayment().intValue();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void annuityCalculationIsCorrect() {
		int expected = 256588;
		int actual = (int) cp.checkAnnuityPayment().doubleValue();
		
		assertEquals(expected, actual);
	}
}