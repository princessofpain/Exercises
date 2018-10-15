package main;

import java.math.BigDecimal;

public class Loan {
	private BigDecimal interest;
	private BigDecimal restBefore;
	private BigDecimal rate;
	private BigDecimal restAfter;
	private BigDecimal totalPay;
	private String loanType;
	
	Loan(BigDecimal interest, BigDecimal restBefore, BigDecimal rate, String loanType) {
		this.interest = interest;
		this.restBefore = restBefore;
		this.rate = rate.subtract(interest);
		totalPay = rate;		
		restAfter = restBefore.subtract(this.rate).abs();
		this.loanType = loanType;
	}

	BigDecimal getInterest() {
		return interest;
	}
	
	BigDecimal getRestBefore() {
		return restBefore;
	}

	BigDecimal getRate() {
		return rate;
	}

	BigDecimal getRestAfter() {
		return restAfter;
	}
	
	BigDecimal getTotalPay() {
		return totalPay;
	}
	
	String getLoanType() {
		return loanType;
	}
}
