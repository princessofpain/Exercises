package main;

public class Loan {
	private double interest;
	private double restBefore;
	private double rate;
	private double restAfter;
	private double totalPay;
	
	Loan(double interest, double restBefore, double rate) {
		this.interest = interest;
		this.restBefore = restBefore;
		this.rate = rate - interest;
		totalPay = rate;		
		restAfter = restBefore - this.rate;
	}

	double getInterest() {
		return interest;
	}
	
	double getRestBefore() {
		return restBefore;
	}

	double getRate() {
		return rate;
	}

	double getRestAfter() {
		return restAfter;
	}
	
	double getTotalPay() {
		return totalPay;
	}
}
