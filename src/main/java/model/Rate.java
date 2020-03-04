package main.java.model;

import java.math.BigDecimal;

public class Rate {
	private BigDecimal monthlyInterest;
	private BigDecimal restBefore;
	private BigDecimal rate;
	private BigDecimal restAfter;
	private BigDecimal total;

	Rate(BigDecimal monthlyInterest, BigDecimal restBefore, BigDecimal rate, BigDecimal total) {
		this.monthlyInterest = monthlyInterest;
		this.restBefore = restBefore;
		this.rate = rate;
		this.total = total;
		restAfter = calculateRestAfter();
	}

	public BigDecimal getInterest() {
		return monthlyInterest;
	}

    public BigDecimal getRestBefore() {
		return restBefore;
	}

    public BigDecimal getRate() {
		return rate;
	}

    public BigDecimal getRestAfter() {
		return restAfter;
	}
	
	public BigDecimal getTotal() { return total; }

	private BigDecimal calculateRestAfter() {
	    if(rate.intValue() > restBefore.intValue()) {
	        return BigDecimal.ZERO;
        }
        return restBefore.subtract(rate);
	}
}
