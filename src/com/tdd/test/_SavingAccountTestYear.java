package com.tdd.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;

public class _SavingAccountTestYear {

	@Test
	public void startingBalanceMatchingConstructor() {
		assertEquals(11000, newAccount().endingBalance(25));
	}

	@Test
	public void interestRateMatchingConstructor() {
		assertEquals(10, newAccount().interestRate());
	}

	@Test
	public void endingBalanceAppliesInterestRate() {
		assertEquals(11000, newAccount().endingBalance(25));
	}

	@Test
	public void nextYearStartingBalanceEqualsThisYearEnndingBalance() {
		SavingAccountYear thisYear = newAccount();
		assertEquals(thisYear.endingBalance(25), thisYear.nextYear(25).startingBalance());
	}

	@Test
	public void withdawingFundsAtTheBeginingOfTheYear() {
		SavingAccountYear year = new SavingAccountYear(10000, 0);
		year.withdraw(1000);
		assertEquals(9000, year.endingBalance(25));
	}

	@Test
	public void mutipleWithdrawlsInTheYear() {
		SavingAccountYear year = new SavingAccountYear(10000, 3000, 10);
		year.withdraw(1000);
		year.withdraw(2000);
		assertEquals(3000, year.totalWithdrawn());
	}

	@Test
	public void startingPrincipal() {
		SavingAccountYear year = new SavingAccountYear(10000, 3000, 10);
		assertEquals(3000, year.startingPrincipal());
	}

	@Test
	public void endingPrincipal() {
		SavingAccountYear year = new SavingAccountYear(10000, 3000, 10);
		year.withdraw(2000);
		assertEquals("ending Principal", 1000, year.endingPrincipal());
	}

	@Test
	public void endingPrincipalNeverGoesBelowZero() {
		SavingAccountYear year = new SavingAccountYear(10000, 3000, 10);
		year.withdraw(4000);
		assertEquals("ending Principal", 0, year.endingPrincipal());
	}

	@Test
	public void capitalGainsWithdrawn() {
		SavingAccountYear year = new SavingAccountYear(10000, 3000, 10);
		year.withdraw(1000);
		assertEquals(0, year.capitalGainsWithdrawn());
		year.withdraw(3000);
		assertEquals(1000, year.capitalGainsWithdrawn());
	}

	@Test
	public void capitalGainsTaxIncured_NeedsToCoverCapitalGainsWithdrawn_AND_theAdditionalCapitalGainsWithdrawnToPayCapitalGainsTax() {
		SavingAccountYear year = new SavingAccountYear(10000, 3000, 10);
		year.withdraw(5000);
		assertEquals(2000, year.capitalGainsWithdrawn());
		assertEquals(666, year.capitalGainsTaxIncured(25));
	}

	@Test
	public void capitaalGainTaxIsIncludedInEndingBalance() {
		int taxRate = 10;
		SavingAccountYear year = new SavingAccountYear(10000, 3000, taxRate);
		int amountWithdrawn = 5000;
		int expectedCapitalGainsTax = 666;
		year.withdraw(5000);
		assertEquals(666, year.capitalGainsTaxIncured(25));
		int expectedStartingBalanceAfterWithdrawls = 10000 - amountWithdrawn - expectedCapitalGainsTax;
		assertEquals((int) (expectedStartingBalanceAfterWithdrawls * 1.10), year.endingBalance(25));

		// TODO: Need to withdraw enough money to cover capital gain tax; that money
		// will also be taxed
	}

	@Test
	public void thisYearIntrestRateShouldBeEqualsToNextYearIntrestRate() {
		SavingAccountYear thisYear = newAccount();
		assertEquals(thisYear.interestRate(), thisYear.nextYear(25).interestRate());
	}

	public SavingAccountYear newAccount() {
		SavingAccountYear newAccount = new SavingAccountYear(10000, 10);
		return newAccount;
	}
}
