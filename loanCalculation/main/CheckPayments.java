package main;

import java.text.DecimalFormat;
import java.util.Arrays;

public class CheckPayments {
	private final double INTEREST;
	private int YEARS;
	private final int AMOUNT;
	
	private Loan[] bulletCalculation;
	private Loan[] amortizingCalculation;
	private Loan[] annuityCalculation;
	
	public CheckPayments(int amount, int years, double interest) {
		AMOUNT = amount;
		YEARS = years;
		INTEREST = interest;
	}
	
	// this repayment has to be payed fully at the end of the time period, but the interest has to be calculated for every year
	public double checkBulletRepayment() {
		double result;
		bulletCalculation = new Loan[1];
		
		double interestInTotal = ((AMOUNT / 100) * INTEREST) * YEARS;
		result = AMOUNT + interestInTotal;
		
		Loan bullet = new Loan(interestInTotal, AMOUNT, result);
		bulletCalculation[0] = bullet;
		
		return result;
	}
	
	// this repayment has to be payed monthly including the interest. The total amount decreases every month and for this reason the value of the interest also changes
	public double checkAmortizingRepayment() {
		double result;
		double totalInterest = 0;
		double rest = AMOUNT;
		double rate = AMOUNT / (YEARS * 12);
		amortizingCalculation = new Loan[YEARS * 12];
		
		for(int i = 0; i < YEARS * 12 && rest > 0; i++) {
			double originalRest = rest;
			double monthlyInterest = ((rest / 100) * INTEREST) / 12;
			rate += monthlyInterest;
			totalInterest = totalInterest + monthlyInterest;
			rest = originalRest - rate + monthlyInterest;
			
			Loan amortizing = new Loan(monthlyInterest, originalRest, rate);
			amortizingCalculation[i] = amortizing;
		}
		
		result = AMOUNT + totalInterest;	
		return result;
	}
	
	// this repayment is like the amortizing but the value of the total rate a month stays the same
	public double checkAnnuityPayment() {
		double result;
		int months = YEARS * 12;
		annuityCalculation = new Loan[months];
		
		double interest = INTEREST / 100;
		double exponent = Math.pow(interest + 1, YEARS);
		result = AMOUNT * (exponent * interest / (exponent - 1));
		
		final double interestAMonth = ((result * YEARS) - AMOUNT) / months;
		final double rate = result / 12;
		double rest = AMOUNT;
		
		for(int i = 0; i < YEARS * 12; i++) {
			double originalRest = rest;	
			Loan annuity = new Loan(interestAMonth, originalRest , rate);	
			annuityCalculation[i] = annuity;
			rest = originalRest - rate + interestAMonth;
		}

		return result * YEARS;
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