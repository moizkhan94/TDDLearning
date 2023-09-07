package com.tdd.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;

public class _StockMarketYearTest {

	@Test
	public void startingValues() {
		StockMarketYear year = new StockMarketYear(10000, 3000, 10);
		assertEquals("starting balance", 11000, year.endingBalance(25));
		assertEquals("starting principal", 3000, year.startingPrincipal());
		assertEquals("intrest rate", 10, year.interestRate());
		assertEquals("total withdrawn default", 0, year.totalWithdrawn(25));

	}

	@Test
	public void endingPrincipal() {
		StockMarketYear year = new StockMarketYear(10000, 3000, 10);
		year.withdraw(1000);
		assertEquals("ending principal consider withdrawls", 2000, year.endingPrincipal());
		year.withdraw(500);
		assertEquals("ending principal consider multiplr withdrawls", 1500, year.endingPrincipal());
		year.withdraw(3000);
		assertEquals("ending principal never goes below to zero", 0, year.endingPrincipal());
	}

	@Test
	public void interestEarned() {
		StockMarketYear year = new StockMarketYear(10000, 3000, 10);
		assertEquals("basic interest earned", 1000, year.interestEraned(25));
		year.withdraw(2000);
		assertEquals("withdrawls dont earn interest", 800, year.interestEraned(25));
	}

	@Test
	public void interestEarnedIsStartingBalanceCombinedWithInterestRate() {
		StockMarketYear year = new StockMarketYear(10000, 3000, 10);
		assertEquals(1000, year.interestEraned(25));
	}

	@Test
	public void withdawingFundsDoNotEarnInterest() {
		StockMarketYear year = newAccount();
		year.withdraw(1000);
		assertEquals(900, year.interestEraned(25));
	}

	@Test
	public void totalWithdrawnIncudingCapitalGains() {
		StockMarketYear year = new StockMarketYear(10000, 0, 10);
		year.withdraw(1000);
		assertEquals("capital gains tax", 333, year.capitalGainsTaxIncured(25));
		assertEquals("total withdrawn", 1333, year.totalWithdrawn(25));
	}

	@Test
	public void capitalGainTaxesDoNotEarnInterest() {
		StockMarketYear year = new StockMarketYear(10000, 0, 10);
		year.withdraw(1000);
		assertEquals("capital gains withdrawn", 1000, year.capitalGainsWithdrawn());
		assertEquals("capital gains tax", 333, year.capitalGainsTaxIncured(25));
		assertEquals("total withdrawn", 1333, year.totalWithdrawn(25));
		assertEquals("interest earned", 866, year.interestEraned(25));
	}

	@Test
	public void endingBalanceAppliesInterestRate() {
		assertEquals(11000, newAccount().endingBalance(25));
	}

	@Test
	public void mutipleWithdrawlsInTheYearAreTotaled() {
		StockMarketYear year = new StockMarketYear(10000, 3000, 10);
		year.withdraw(1000);
		year.withdraw(2000);
		assertEquals(3000, year.totalWithdrawn(25));
	}

	@Test
	public void startingPrincipal() {
		StockMarketYear year = new StockMarketYear(10000, 3000, 10);
		assertEquals(3000, year.startingPrincipal());
	}

	@Test
	public void withdrawingMoreThanPrincipalTakesFromCapitalGains() {
		StockMarketYear year = new StockMarketYear(10000, 3000, 10);
		year.withdraw(1000);
		assertEquals(0, year.capitalGainsWithdrawn());
		year.withdraw(3000);
		assertEquals(1000, year.capitalGainsWithdrawn());
	}

	@Test
	public void capitalGainsTaxIncured_NeedsToCoverCapitalGainsWithdrawn_AND_theAdditionalCapitalGainsWithdrawnToPayCapitalGainsTax() {
		StockMarketYear year = new StockMarketYear(10000, 3000, 10);
		year.withdraw(5000);
		assertEquals(2000, year.capitalGainsWithdrawn());
		assertEquals(666, year.capitalGainsTaxIncured(25));
	}

	@Test
	public void capitaalGainTaxIsIncludedInEndingBalance() {
		int taxRate = 10;
		StockMarketYear year = new StockMarketYear(10000, 3000, taxRate);
		int amountWithdrawn = 5000;
		int expectedCapitalGainsTax = 666;
		year.withdraw(5000);
		assertEquals(666, year.capitalGainsTaxIncured(25));
		int expectedStartingBalanceAfterWithdrawls = 10000 - amountWithdrawn - expectedCapitalGainsTax;
		assertEquals((int) (expectedStartingBalanceAfterWithdrawls * 1.10), year.endingBalance(25));
	}

	@Test
	public void nextYear() {
		StockMarketYear thisYear = newAccount();
		StockMarketYear nextYear = thisYear.nextYear(25);
		assertEquals("starting balance", thisYear.endingBalance(25), nextYear.startingBalance());
		assertEquals("starting principal", thisYear.endingPrincipal(), nextYear.startingPrincipal());
		assertEquals(thisYear.interestRate(), nextYear.interestRate());
	}

	@Test
	public void nextYearStartingPrincipalEqualsThisYearEnndingPrincipal() {
		StockMarketYear thisYear = newAccount();
	}

	public StockMarketYear newAccount() {
		StockMarketYear newAccount = new StockMarketYear(10000, 10000, 10);
		return newAccount;
	}
}
