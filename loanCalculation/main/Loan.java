package main;

public class Loan {
	private double interest;
	private double restBefore;
	private double rate;
	private double totalPay = interest + rate;
	private double restAfter = restBefore + totalPay;
	
	Loan(double interest, double restBefore, double rate) {
		this.interest = interest;
		this.restBefore = restBefore;
		this.rate = rate;
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
	double getTotalPay() {
		return totalPay;
	}
	double getRestAfter() {
		return restAfter;
	}
}
