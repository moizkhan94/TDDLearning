package com.tdd.test;

import java.util.Objects;

public class TaxRate {

	private double taxRate;

	public TaxRate(double rateAsPercentage) {
		this.taxRate = rateAsPercentage / 100;
	}

	public Dollars simpleTaxFor(Dollars amount) {
		return new Dollars((int) (taxRate * amount.toInt()));
	}

	public Dollars compoundTaxFor(Dollars amount) {
		return new Dollars((int) ((amount.toInt() / (1 - taxRate)) - amount.toInt()));
	}

	@Override
	public String toString() {
		return (taxRate * 100) + "%";
	}

	@Override
	public int hashCode() {
		return Objects.hash(taxRate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaxRate other = (TaxRate) obj;
		return Double.doubleToLongBits(taxRate) == Double.doubleToLongBits(other.taxRate);
	}

}
