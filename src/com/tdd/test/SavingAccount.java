package com.tdd.test;

public class SavingAccount {

	private int balance = 0;

	public void deposit(int ammount) {
		balance += ammount;
	}

	public int balance() {
		return balance;
	}

	public void withdraw(int ammount) {
		balance -= ammount;

	}

	public SavingAccount nextYear(double interestRate) {
		SavingAccount nextYearAccount = new SavingAccount();
		nextYearAccount.deposit((int)(balance() + (balance() * (interestRate / 100))));
		return nextYearAccount;
	}

}
