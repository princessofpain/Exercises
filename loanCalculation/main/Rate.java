package main;

import java.math.BigDecimal;

public class Rate {
	private BigDecimal interest;
	private BigDecimal restBefore;
	private BigDecimal rate;
	private BigDecimal restAfter;
	private BigDecimal totalPay;
	private String loanType;
	private BigDecimal total;

	Rate(BigDecimal interest, BigDecimal restBefore, BigDecimal rate, String loanType, BigDecimal total) {
		this.interest = interest;
		this.restBefore = restBefore;
		this.rate = rate.subtract(interest);
		totalPay = rate;
		this.loanType = loanType;
		this.total = total;
		restAfter = calculateRestAfter();
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
	
	public BigDecimal getTotal() {
		return total;
	}

	public BigDecimal getTotalPay() {
		return totalPay;
	}

	public String getType() { return loanType; }

	BigDecimal calculateRestAfter() {
		if(loanType.equals("annuity")) {
			return restBefore.subtract(rate).add(interest);
		} else {
			return restBefore.subtract(this.rate);
		}
	}
}
