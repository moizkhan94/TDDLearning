package com.tdd.test;

import java.util.Objects;

public class Dollars {

	private int amount;

	public Dollars(int amount) {
		this.amount = amount;
	}
	
	public int amount() {
		return amount;
		//TODO: Delete Me
	}

	public Dollars add(Dollars dollars) {
		return new Dollars(this.amount + dollars.amount);
	}

	public Dollars subtract(Dollars dollars) {
		return new Dollars(this.amount - dollars.amount);
	}

	public Dollars subtractToZero(Dollars dollars) {
		int subtractionResult = this.amount - dollars.amount;//new Dollars(this.amount - dollars.amount);
		return new Dollars(Math.max(0, subtractionResult));
		/*
		 * Dollars subtractionResult = subtract(dollars); if (subtractionResult.amount <
		 * 0) return new Dollars(0); return subtractionResult;
		 */
	}

	@Override
	public String toString() {
		return "$" + amount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dollars other = (Dollars) obj;
		return amount == other.amount;
	}

}
