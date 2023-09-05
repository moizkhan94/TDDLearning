package com.tdd.test;

public class SavingAccountYear {

	private int startingBalance = 0;
	private int interestRate;
	private int capitalGains;

	public SavingAccountYear() {
	}

	public SavingAccountYear(int balance, int interestRate) {
		// TODO Auto-generated constructor stub
		this.startingBalance = balance;
		this.interestRate = interestRate;
	}

	public SavingAccountYear(int balance, int capitalGains, int interestRate) {
		// TODO Auto-generated constructor stub
		this.startingBalance = balance;
		this.capitalGains = capitalGains;
		this.interestRate = interestRate;
	}

	public int startingBalance() {
		// TODO Auto-generated method stub
		return startingBalance;
	}

	public int interestRate() {
		return interestRate;
	}

	public int endingBalance() {
		return startingBalance + (startingBalance * interestRate / 100);
	}

	public SavingAccountYear nextYear() {
		return new SavingAccountYear(this.endingBalance(), interestRate);
	}

	public void withdraw(int amount) {
		startingBalance -= amount;
	}

}
