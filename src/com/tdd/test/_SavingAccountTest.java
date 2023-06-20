package com.tdd.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class _SavingAccountTest {


	@Test
	public void testAmountDepositToBankAccount() {
		SavingAccount account = new SavingAccount();
		account.deposit(1000);
		assertEquals(1000, account.balance());
	}
	
	@Test
	public void testAmountWithdrawnFromBankAccount() {
		SavingAccount account = new SavingAccount();
		account.deposit(1000);
		assertEquals(1000, account.balance());
		account.withdraw(500);
		assertEquals(500, account.balance());
	}
	
	@Test
	public void testAmountWithdrawnFromBankAccountAndHaveNegativeBalance() {
		SavingAccount account = new SavingAccount();
		account.deposit(1000);
		assertEquals(1000, account.balance());
		account.withdraw(1500);
		assertEquals(-500, account.balance());
	}
}
