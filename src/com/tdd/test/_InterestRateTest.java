package com.tdd.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class _InterestRateTest {

	@Test
	public void nothing() {
		InterestRate interestRate = new InterestRate(0);
		assertEquals(new Dollars(0), interestRate.interestOnAmount(new Dollars(1000)));  
	}

	@Test
	public void interest() {
		InterestRate rate = new InterestRate(10);
		assertEquals(new Dollars(100), rate.interestOnAmount(new Dollars(1000)));
	}
 
	@Test
	public void valueObject() {
		InterestRate rate1a = new InterestRate(10);
		InterestRate rate1b = new InterestRate(10);
		InterestRate rate2 = new InterestRate(20);
		assertEquals("10.0%", rate1a.toString());
		assertTrue("same values should be equal", rate1a.equals(rate1b));
		assertFalse("doffernt values should be false", rate1a.equals(rate2));
		assertTrue("same values should have same hash code", rate1a.hashCode() == (rate1b.hashCode()));
	}

}
