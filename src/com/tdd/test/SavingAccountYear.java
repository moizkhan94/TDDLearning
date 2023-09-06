package com.tdd.test;

public class SavingAccountYear {

	private int startingBalance = 0;
	private int interestRate;
	private int capitalGainsAmount;
	private int totalWithdrawn = 0;
	private int startingPrincipal;

	public SavingAccountYear(int balance, int interestRate) {
		this.startingBalance = balance;
		this.interestRate = interestRate;
	}

	public SavingAccountYear(int startingBalance, int startingPrincipal, int interestRate) {
		this.startingBalance = startingBalance;
		this.startingPrincipal = startingPrincipal;
		this.capitalGainsAmount = startingBalance - startingPrincipal;
		this.interestRate = interestRate;
	}

	public int startingBalance() {
		return startingBalance;
	}

	public int startingPrincipal() {
		return startingBalance - capitalGainsAmount;
	}

	public int interestRate() {
		return interestRate;
	}

	public int totalWithdrawn() {
		return totalWithdrawn;
	}

	public int endingPrincipal() {
		int result = startingPrincipal() - totalWithdrawn();
		return Math.max(0, result);
	}

	public int endingBalance(int capitalGainTaxRate) {
		int modifiedStart = startingBalance - totalWithdrawn() - capitalGainsTaxIncured(capitalGainTaxRate);
		return modifiedStart + (modifiedStart * interestRate / 100);
	}

	public SavingAccountYear nextYear(int capitalGainTaxRate) {
		return new SavingAccountYear(this.endingBalance(capitalGainTaxRate), interestRate);
	}

	public void withdraw(int amount) {
		this.totalWithdrawn += amount;
	}

	public int capitalGainsWithdrawn() {
		int result = (startingPrincipal() - totalWithdrawn()) * -1;
		return Math.max(0, result);
	}

	public int capitalGainsTaxIncured(int taxRate) {
		return (int)(capitalGainsWithdrawn() / (1 -  (taxRate/100.0)) - capitalGainsWithdrawn());
	}

}
