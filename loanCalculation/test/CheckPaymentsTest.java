package test;

import static junit.framework.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;

import main.*;

public class CheckPaymentsTest {
	
	BigDecimal amount = new BigDecimal("200000");
	BigDecimal years = new BigDecimal("20");
	BigDecimal interest = new BigDecimal("2.5");
	private final Loan cp = new Loan(interest, years, amount);

	@Test
	void calculationsAreCorrect() {
		int expectedBullet = 300000;
		int expectedAmortizing = 250208;
		int expectedAnnuity = 256588;

		List<Rate[]> allLoans = cp.calculateAllLoans();

		for(int i = 0; i < allLoans.size(); i++) {
			Rate[] loan = allLoans.get(i);
			switch(loan[i].getType()) {
				case "bullet": 		assertEquals(expectedBullet, loan[i].getTotal().intValue());
							   		break;
				case "amortizing": 	assertEquals(expectedAmortizing, loan[i].getTotal().intValue());
								   	break;
				case "annuity": 	assertEquals(expectedAnnuity, loan[i].getTotal().intValue());
									break;
			}
		}
	}
}
