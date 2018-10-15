package main;

import java.math.BigDecimal;
import java.math.MathContext;

public class CheckPayments {
	private final BigDecimal INTEREST;
	private final BigDecimal YEARS;
	private final BigDecimal AMOUNT;
	
	private Loan[] bulletCalculation;
	private Loan[] amortizingCalculation;
	private Loan[] annuityCalculation;
	
	private final BigDecimal monthAYear = new BigDecimal("12");
	private final BigDecimal hundred = new BigDecimal("100");
	
	public CheckPayments(BigDecimal amount, BigDecimal years, BigDecimal interest) {
		AMOUNT = amount;
		YEARS = years;
		INTEREST = interest;
	}
	
	// this repayment has to be payed fully at the end of the time period, but the interest has to be calculated for every year
	public BigDecimal checkBulletRepayment() {
		bulletCalculation = new Loan[1];
		
		BigDecimal interestInTotal = new BigDecimal(AMOUNT.divide(hundred, MathContext.DECIMAL128).multiply(INTEREST).multiply(YEARS).toString());
		BigDecimal result = new BigDecimal(AMOUNT.add(interestInTotal).toString());
		
		Loan bullet = new Loan(interestInTotal, AMOUNT, result, "bullet");
		bulletCalculation[0] = bullet;
		
		return result;
	}
	
	// this repayment has to be payed monthly including the interest. The total amount decreases every month and for this reason the value of the interest also changes
	public BigDecimal checkAmortizingRepayment() {
		final BigDecimal allMonths = YEARS.multiply(monthAYear);
		BigDecimal totalInterest = new BigDecimal(0);
		BigDecimal rest = new BigDecimal(AMOUNT.toString());
		final BigDecimal rate = AMOUNT.divide(allMonths, MathContext.DECIMAL128);
		
		amortizingCalculation = new Loan[allMonths.intValue()];
		
		for(int i = 0; i < allMonths.intValue(); i++) {
			BigDecimal originalRest = rest;
			BigDecimal monthlyInterest = originalRest.divide(hundred, MathContext.DECIMAL128).multiply(INTEREST).divide(monthAYear, MathContext.DECIMAL128);
			totalInterest = totalInterest.add(monthlyInterest);
			rest = originalRest.subtract(rate);
			
			if(i == allMonths.intValue() - 1 && rest.doubleValue() > 0) {
				rate.add(rest);
			}
			
			BigDecimal rateWithInterest = rate.add(monthlyInterest);
			Loan amortizing = new Loan(monthlyInterest, originalRest, rateWithInterest, "amortizing");
			amortizingCalculation[i] = amortizing;
		}
		
		BigDecimal result = AMOUNT.add(totalInterest).add(rest);	
		return result;
	}
	
	// this repayment is like the amortizing but the value of the total rate a month stays the same
	public BigDecimal checkAnnuityPayment() {
		final BigDecimal allMonths = new BigDecimal(YEARS.multiply(monthAYear).toString());
		annuityCalculation = new Loan[allMonths.intValue()];
		
		BigDecimal interest = new BigDecimal(INTEREST.divide(hundred, MathContext.DECIMAL128).toString());
		BigDecimal part1 = new BigDecimal(interest.add(BigDecimal.ONE).pow(YEARS.intValue()).toString());
		BigDecimal part2 = new BigDecimal(part1.subtract(BigDecimal.ONE).toString());
		BigDecimal part3 = new BigDecimal(part1.multiply(interest).divide(part2, MathContext.DECIMAL128).toString());
		BigDecimal result = new BigDecimal(AMOUNT.multiply(part3).toString());

		final BigDecimal monthlyInterest = new BigDecimal(result.multiply(YEARS).subtract(AMOUNT).divide(allMonths, MathContext.DECIMAL128).toString());
		final BigDecimal rate = new BigDecimal(result.divide(monthAYear, MathContext.DECIMAL128).toString());
		BigDecimal rest = new BigDecimal(AMOUNT.toString());
		
		for(int i = 0; i < allMonths.intValue(); i++) {
			BigDecimal originalRest = rest;	
			Loan annuity = new Loan(monthlyInterest, originalRest , rate, "annuity");	
			annuityCalculation[i] = annuity;
			rest = originalRest.subtract(rate).add(monthlyInterest);
		}
		return result.multiply(YEARS);
	}
	
	public void generateBulletPlanForDownload() {
		GenerateXMLFile generator = new GenerateXMLFile();
		generator.generateXML("Bullet Repayment", bulletCalculation);
	}
	
	public void generateAmortizingPlanForDownload() {
		GenerateXMLFile generator = new GenerateXMLFile();
		generator.generateXML("Amortizing Repayment", amortizingCalculation);
	}
	
	public void generateAnnuityPlanForDownload() {
		GenerateXMLFile generator = new GenerateXMLFile();
		generator.generateXML("Annuity Repayment", annuityCalculation);
	}
}