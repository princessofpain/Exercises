package main;

import java.text.DecimalFormat;

public class CheckPayments {
	private final double INTEREST;
	private final int YEARS;
	private final int AMOUNT;
	
	public CheckPayments(int amount, int years, double interest) {
		AMOUNT = amount;
		YEARS = years;
		INTEREST = interest;
	}
	
	// this repayment has to be payed fully at the end of the time period, but the interest has to be calculated for every year
	public double checkBulletRepayment() {
		double result;
		
		double interestInTotal = ((AMOUNT / 100) * INTEREST) * YEARS;
		result = AMOUNT + interestInTotal;
		
		return result;
	}
	
	// this repayment has to be payed monthly including the interest. The total amount decreases every month and for this reason the value of the interest also changes
	public double checkAmortizingRepayment() {
		double result;
		double totalInterest = 0;
		double rest = AMOUNT;
		double rate = AMOUNT / (YEARS * 12);
//		System.out.println("This is the monthly rate: " + rate);
//		System.out.println();
		DecimalFormat df = new DecimalFormat("0.00");
		
		for(int i = YEARS * 12; i > 0 && rest > 0; i--) {
			double monthlyInterest = ((rest / 100) * INTEREST) / 12;
//			System.out.println("Monthly interest: " + df.format(monthlyInterest));
			totalInterest = totalInterest + monthlyInterest;
			rest = rest - rate - monthlyInterest;
//			System.out.println("Rest - Rate - Interest: " + df.format(rest));
		}
//		System.out.println();
//		System.out.println("Total interest: " + df.format(totalInterest));
//		System.out.println("Rest: " + df.format(rest));
		result = AMOUNT + totalInterest -rest;	
		//System.out.println("Result: " + df.format(result));
		return result;
	}
	
	// this repayment is like the amortizing but the value of the total rate a month stays the same
	public double checkAnnuityPayment() {
		double result;
		
		double interest = INTEREST / 100;
		double annuity = Math.pow(interest + 1, YEARS);
		result = AMOUNT * (annuity * interest / (annuity - 1));

		return result * YEARS;
	}
}