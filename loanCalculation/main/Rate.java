package main;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Rate {
	private BigDecimal monthlyInterest;
	private BigDecimal restBefore;
	private BigDecimal rate;
	private BigDecimal restAfter;
	private String loanType;
	private BigDecimal total;

	Rate(BigDecimal monthlyInterest, BigDecimal restBefore, BigDecimal rate, String loanType, BigDecimal total) {
		this.monthlyInterest = monthlyInterest;
		this.restBefore = restBefore;
		this.rate = rate;
		this.loanType = loanType;
		this.total = total;
		restAfter = calculateRestAfter();
	}

	BigDecimal getInterest() {
		return monthlyInterest;
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
	
	public BigDecimal getTotal() { return total; }

	public String getType() { return loanType; }

	BigDecimal calculateRestAfter() {
		if(loanType.equals("annuity")) {
			return restBefore.subtract(rate).add(monthlyInterest);
		} else {
			return restBefore.subtract(rate);
		}
	}
}
