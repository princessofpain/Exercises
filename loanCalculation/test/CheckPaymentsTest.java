package test;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

import main.*;

public class CheckPaymentsTest {
	
	private final CheckPayments cp = new CheckPayments(200000, 20, 2.5);
	
	@Test
	void bulletCalculationIsCorrect() {
		int expected = 300000;
		int actual = (int) cp.checkBulletRepayment();
		
		assertEquals(expected, actual);;
	}
	
	@Test
	void amortizingCalculationIsCorrect() {
		int expected = 238424;
		int actual = (int) cp.checkAmortizingRepayment();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void annuityCalculationIsCorrect() {
		int expected = 256588;
		int actual = (int) cp.checkAnnuityPayment();
		
		assertEquals(expected, actual);
	}
}