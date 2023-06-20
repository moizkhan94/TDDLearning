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

}
