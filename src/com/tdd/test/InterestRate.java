package com.tdd.test;

import java.util.Objects;

public class InterestRate {

	private double rate;

	public InterestRate(double ratePercentage) {
		this.rate = ratePercentage / 100;
	}

	public int interestOnAmount(int amount) {
		return (int) (rate * amount);
	}

	@Override
	public String toString() {
		return (rate * 100) + "%";
	}

	@Override
	public int hashCode() {
		return Objects.hash(rate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InterestRate other = (InterestRate) obj;
		return Double.doubleToLongBits(rate) == Double.doubleToLongBits(other.rate);
	}
}
