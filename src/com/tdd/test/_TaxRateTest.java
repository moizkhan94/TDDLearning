package com.tdd.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class _TaxRateTest {

	@Test
	public void nothing() {
		TaxRate taxRate = new TaxRate(0);
		assertEquals(new Dollars(0), taxRate.simpleTaxFor(new Dollars(1000)));
		assertEquals(new Dollars(0), taxRate.compoundTaxFor(new Dollars(1000)));
	}

	@Test
	public void simpleTaxJustAppliesTaxRateToAmount() {
		TaxRate taxRate = new TaxRate(25);
		assertEquals(new Dollars(250), taxRate.simpleTaxFor(new Dollars(1000)));
	}

	@Test
	public void compoundTaxIsTheAmountOfTheTaxThatIsIncurredIfYouAlsoPayTaxonTheTax() {
		TaxRate taxRate = new TaxRate(25);
		assertEquals(new Dollars(333), taxRate.compoundTaxFor(new Dollars(1000)));
	}

	@Test
	public void valueObject() {
		TaxRate taxRate1a = new TaxRate(33);
		TaxRate taxRate1b = new TaxRate(33);
		TaxRate taxRate2 = new TaxRate(40);
		assertEquals("33.0%", taxRate1a.toString());
		assertTrue("same values should be equal", taxRate1a.equals(taxRate1b));
		assertFalse("doffernt values should be false", taxRate1a.equals(taxRate2));
		assertTrue("same values should have same hash code", taxRate1a.hashCode() == (taxRate1b.hashCode()));
	}

}
