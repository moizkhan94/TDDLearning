package com.tdd.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class _DollarsTest {

	@Test
	public void addition() {
		// assertEquals("$40",new Dollars(10).add(new Dollars(30)).toString());
		assertEquals(new Dollars(40), new Dollars(10).add(new Dollars(30)));
	}

	@Test
	public void subtraction() {
		// assertEquals("$20",new Dollars(30).add(new Dollars(10)).toString());
		assertEquals("positive result", new Dollars(20), new Dollars(30).subtract(new Dollars(10)));
		assertEquals("negative result", new Dollars(-20), new Dollars(10).subtract(new Dollars(30)));
	}

	@Test
	public void subtractToZero() {
		assertEquals("positive result", new Dollars(20), new Dollars(30).subtractToZero(new Dollars(10)));
		assertEquals("no negative return ---- return zero instead", new Dollars(0),
				new Dollars(10).subtractToZero(new Dollars(30)));
	}
	
	@Test
	public void toInt() {
		assertEquals(20, new Dollars(20).toInt());
	}

	@Test
	public void valueObject() {
		Dollars dollars1a = new Dollars(10);
		Dollars dollars1b = new Dollars(10);
		Dollars dollars1a2 = new Dollars(20);
		assertEquals("$10", dollars1a.toString());
		assertTrue("same values should be equal", dollars1a.equals(dollars1b));
		assertFalse("doffernt values should be false", dollars1a.equals(dollars1a2));
		assertTrue("same values should have same hash code", dollars1a.hashCode() == (dollars1b.hashCode()));
	}

}
