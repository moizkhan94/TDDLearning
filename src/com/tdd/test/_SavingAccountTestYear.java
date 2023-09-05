package com.tdd.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;

public class _SavingAccountTestYear {

	@Test
	public void startingBalance() {
		SavingAccountYear account = new SavingAccountYear(10000, 10);
		assertEquals(11000, account.endingBalance());
	}

	@Test
	public void endingBalance() {
		SavingAccountYear account = new SavingAccountYear(10000, 10);
		assertEquals(11000, account.endingBalance());
	}

	@Test
	public void nextYearStartingBalanceShouldEqualThisYearEnndingBalance() {
		SavingAccountYear thisYear = new SavingAccountYear(10000, 10);
		assertEquals(thisYear.endingBalance(), thisYear.nextYear().startingBalance());
	}
	
	@Test
	public void thisYearIntrestRateShouldBeEqualToNextYearIntrestRate() {
		SavingAccountYear thisYear = new SavingAccountYear(10000, 10);
		assertEquals(thisYear.interestRate(), thisYear.nextYear().interestRate());
	}
}
