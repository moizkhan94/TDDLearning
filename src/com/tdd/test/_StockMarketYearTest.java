package com.tdd.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;

public class _StockMarketYearTest {

	private static final int INTEREST_RATE = 10;
	private static final int STARTING_PRINCIPAL = 3000;
	private static final int STARTING_BALANCE = 10000;
	private static final TaxRate CAPITAL_GAINS_TAX_RATE = new TaxRate(25);

	@Test
	public void startingValues() {
		StockMarketYear year = newYear();
		assertEquals("starting balance", STARTING_BALANCE, year.startingBalance());
		assertEquals("starting principal", STARTING_PRINCIPAL, year.startingPrincipal());
		assertEquals("intrest rate", INTEREST_RATE, year.interestRate());
		assertEquals("capital gains tax rate", CAPITAL_GAINS_TAX_RATE.rate(), year.capitalGainsTaxRate());
		assertEquals("total withdrawn default", 0, year.totalWithdrawn());

	}

	@Test
	public void capitalGainTax() {
		StockMarketYear year = newYear();
		year.withdraw(4000);
		assertEquals("capital gains tax includes tax on cover capital gains", 333, year.capitalGainsTaxIncured());
		assertEquals("total withdrawn includes capital gains tax", 4333, year.totalWithdrawn());
	}

	@Test
	public void interestEarned() {
		StockMarketYear year = newYear();
		assertEquals("basic interest earned", 1000, year.interestEraned());
		year.withdraw(2000);
		assertEquals("withdrawls dont earn interest", 800, year.interestEraned());
		year.withdraw(2000);
		assertEquals("capital gains tax withdrawls don't earn interest", 566, year.interestEraned());
	}

	@Test
	public void endingPrincipal() {
		StockMarketYear year = newYear();
		year.withdraw(1000);
		assertEquals("ending principal consider withdrawls", 2000, year.endingPrincipal());
		year.withdraw(500);
		assertEquals("ending principal consider multiplr withdrawls", 1500, year.endingPrincipal());
		year.withdraw(3000);
		assertEquals("ending principal never goes below to zero", 0, year.endingPrincipal());
	}

	@Test
	public void endingBalance() {
		StockMarketYear year = newYear();
		assertEquals("ending balance includes interest", 11000, year.endingBalance());
		year.withdraw(1000);
		assertEquals("ending balance includes withdrawls", 9900, year.endingBalance());
		year.withdraw(3000);
		assertEquals("ending balance includes capital gauns tax withdrawls", 6233, year.endingBalance());
	}

	@Test
	public void nextYearStartingValuesMatchesThisYearEndingValues() {
		StockMarketYear thisYear = newYear();
		StockMarketYear nextYear = thisYear.nextYear(25);
		assertEquals("starting balance", thisYear.endingBalance(), nextYear.startingBalance());
		assertEquals("starting principal", thisYear.endingPrincipal(), nextYear.startingPrincipal());
		assertEquals("interest rate", thisYear.interestRate(), nextYear.interestRate());
		assertEquals("capital gains tax rate", thisYear.capitalGainsTaxRate(), nextYear.capitalGainsTaxRate());
	}

	private StockMarketYear newYear() {
		return new StockMarketYear(STARTING_BALANCE, STARTING_PRINCIPAL, INTEREST_RATE, CAPITAL_GAINS_TAX_RATE.rate());
	}
}
