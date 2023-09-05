package com.tdd.test;

public class SavingAccountYear {

	private int startingBalance = 0;
	private int interestRate;

	public SavingAccountYear() {
	}

	public SavingAccountYear(int balance, int interestRate) {
		// TODO Auto-generated constructor stub
		this.startingBalance = balance;
		this.interestRate = interestRate;
	}

	public int startingBalance() {
		// TODO Auto-generated method stub
		return startingBalance;
	}

	public void deposit(int ammount) {
		startingBalance += ammount;
	}

	public int balance() {
		return startingBalance;
	}

	public void withdraw(int ammount) {
		startingBalance -= ammount;
	}

	public SavingAccountYear nextYear() {
		return new SavingAccountYear(this.endingBalance(), interestRate);
	}

	public int endingBalance() {
		return balance() + (balance() * interestRate / 100);
	}

	public int interestRate() {
		return interestRate;
	}

}
