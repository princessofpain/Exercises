package main;

public class Loan {
	private double interest;
	private double restBefore;
	private double rate;
	private double restAfter;
	private double totalPay;
	private String loanType;
	
	Loan(double interest, double restBefore, double rate, String loanType) {
		this.interest = interest;
		this.restBefore = restBefore;
		this.rate = rate - interest;
		this.loanType = loanType;
		totalPay = rate;		
		restAfter = restBefore - this.rate;
	}
	
	String getLoanType() {
		return loanType;
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
