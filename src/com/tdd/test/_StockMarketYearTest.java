package com.tdd.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;

public class _StockMarketYearTest {

	@Test
	public void startingBalanceMatchingConstructor() {
		assertEquals(11000, newAccount().endingBalance(25));
	}

	@Test
	public void startingPrincipalMatchesConstructor() {
		StockMarketYear year = new StockMarketYear(10000, 3000, 10);
		assertEquals(3000, year.startingPrincipal());
	}

	@Test
	public void interestRateMatchingConstructor() {
		assertEquals(10, newAccount().interestRate());
	}

	@Test
	public void startingCapitalGainsIsStartingBalanceMinusStartingPrincipal() {
		StockMarketYear year = new StockMarketYear(10000, 3000, 10);
		assertEquals(7000, year.startingCapitalGains());
	}

	@Test
	public void endingPrincipalConsiderWithdawls() {
		StockMarketYear year = new StockMarketYear(10000, 3000, 10);
		year.withdraw(2000);
		assertEquals("ending Principal", 1000, year.endingPrincipal());
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
	public void endingCapitalGainsIncludesInterestEarned() {
		StockMarketYear year = new StockMarketYear(10000, 3000, 10);
		assertEquals("starting capital gains",7000, year.startingCapitalGains());
		assertEquals("ending capaital gains",8000, year.endingCapitalGains(25));
	}

	@Test
	public void endingCapitalGainsIncludesCapitalGainsWithdrawn() {
		StockMarketYear year = new StockMarketYear(10000, 0, 10);
		assertEquals("starting capital gains",10000, year.startingCapitalGains());
		year.withdraw(1000);
		assertEquals("capital Gains Withdrawn",1000, year.capitalGainsWithdrawn());
		assertEquals("capital gains tax",333, year.capitalGainsTaxIncured(25));
		assertEquals("intrest earned",866, year.interestEraned(25));
		assertEquals("ending capital gains",9533, year.endingCapitalGains(25));

	}

	@Test
	public void endingPrincipalNeverGoesBelowZero() {
		StockMarketYear year = new StockMarketYear(10000, 3000, 10);
		year.withdraw(4000);
		assertEquals("ending Principal", 0, year.endingPrincipal());
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
	public void nextYearStartingBalanceEqualsThisYearEnndingBalance() {
		StockMarketYear thisYear = newAccount();
		assertEquals(thisYear.endingBalance(25), thisYear.nextYear(25).startingBalance());
	}

	@Test
	public void nextYearsIntrestRateShouldBeEqualsToThisYearIntrestRate() {
		StockMarketYear thisYear = newAccount();
		assertEquals(thisYear.interestRate(), thisYear.nextYear(25).interestRate());
	}

	public StockMarketYear newAccount() {
		StockMarketYear newAccount = new StockMarketYear(10000, 10000, 10);
		return newAccount;
	}
}
