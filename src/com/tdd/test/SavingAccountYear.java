package com.tdd.test;

public class SavingAccountYear {

	private int startingBalance = 0;
	private int interestRate;
	private int capitalGainsAmount;
	private int totalWithdrawn = 0;

	public SavingAccountYear() {
	}

	public SavingAccountYear(int balance, int interestRate) {
		this.startingBalance = balance;
		this.interestRate = interestRate;
	}

	public SavingAccountYear(int balance, int capitalGainsAmount, int interestRate) {
		this.startingBalance = balance;
		this.capitalGainsAmount = capitalGainsAmount;
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

	public int endingPrincipal() {
		int result = startingPrincipal() - totalWithdrawn;
		return (result < 0) ? 0 : result;
	}

	public int endingBalance() {
		int modifiedStart = startingBalance - totalWithdrawn;
		return modifiedStart + (modifiedStart * interestRate / 100);
	}

	public SavingAccountYear nextYear() {
		return new SavingAccountYear(this.endingBalance(), interestRate);
	}

	public void withdraw(int amount) {
		this.totalWithdrawn += amount;
	}

}
