package com.tdd.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;

public class _SavingAccountTestYear {

	@Test
	public void startingBalanceMatchingConstructor() {
		assertEquals(11000, newAccount().endingBalance());
	}

	@Test
	public void interestRateMatchingConstructor() {
		assertEquals(10, newAccount().interestRate());
	}

	@Test
	public void endingBalanceAppliesInterestRate() {
		assertEquals(11000, newAccount().endingBalance());
	}

	@Test
	public void nextYearStartingBalanceEqualsThisYearEnndingBalance() {
		SavingAccountYear thisYear = newAccount();
		assertEquals(thisYear.endingBalance(), thisYear.nextYear().startingBalance());
	}

	@Test
	public void withdawingFundsAtTheBeginingOfTheYear() {
		SavingAccountYear year = new SavingAccountYear(10000, 0);
		year.withdraw(1000);
		assertEquals(9000, year.endingBalance());
	}

	@Test
	public void mutipleWithdrawlsInTheYear() {
		SavingAccountYear year = new SavingAccountYear(10000, 7000, 10);
		year.withdraw(1000);
		year.withdraw(2000);
		assertEquals(7700, year.endingBalance());
	}

	@Test
	public void startingPrincipal() {
		SavingAccountYear year = new SavingAccountYear(10000, 7000, 10);
		assertEquals(3000, year.startingPrincipal());
	}

	@Test
	public void endingPrincipal() {
		SavingAccountYear year = new SavingAccountYear(10000, 7000, 10);
		assertEquals("Starting Principal", 3000, year.startingPrincipal());
		year.withdraw(2000);
		assertEquals("ending Principal", 1000, year.endingPrincipal());
	}

	@Test
	public void endingPrincipalNeverGoesBelowZero() {
		SavingAccountYear year = new SavingAccountYear(10000, 7000, 10);
		assertEquals("Starting Principal", 3000, year.startingPrincipal());
		year.withdraw(4000);
		assertEquals("ending Principal", 0, year.endingPrincipal());
	}

	/*
	 * @Test public void canWithdrawPrincipalWithoutIncurringCapitalGainTax() {
	 * SavingAccountYear year = new SavingAccountYear(10000,7000, 10);
	 * year.withdraw(3000); assertEquals(7700, year.endingBalance());
	 * year.withdraw(5000); assertEquals((2000 + 200 -
	 * (1250)),year.endingBalance());
	 * 
	 * }
	 */

	@Test
	public void thisYearIntrestRateShouldBeEqualsToNextYearIntrestRate() {
		SavingAccountYear thisYear = newAccount();
		assertEquals(thisYear.interestRate(), thisYear.nextYear().interestRate());
	}

	public SavingAccountYear newAccount() {
		SavingAccountYear newAccount = new SavingAccountYear(10000, 10);
		return newAccount;
	}
}
