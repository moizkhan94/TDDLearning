package com.tdd.test;

public class Main {
	
	public static void main(String[] args) {
		SavingAccount account = new SavingAccount();
		account.deposit(1000);
		for (int i =0; i<60; i++) { 
			System.out.println("Year " + (i+1) + "Rs " + account.balance());
			account = account.nextYear(10);
			//System.out.println("Year " + i+1 + "After intrest -> Rs " + account.balance());
		}
	}

}
