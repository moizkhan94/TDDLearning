package com.tdd.test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class _MagicSquareTest {

	@Test
	public void startingValues() {
		MagicSquare magicSquare = new MagicSquare(3);
		assertEquals("size of magic square", 3, magicSquare.size());
	}

	@Test
	public void sumofAllRowsColumnDiagnolsShouldBeEqual() {
		MagicSquare magicSquare = new MagicSquare(3);
		assertEquals("sum of all rows should be same", 15, magicSquare.sumOfRows());
		assertEquals("sum of all columns should be same", 15, magicSquare.sumOfColumns());
		assertEquals("sum of all diagnols should be same", 15, magicSquare.sumOfDiagnols());
		// TODO: Add all sum test cases in this test case and delete others
	}

	@Test
	public void theSizeShouldBeAnOddNumber() {
		MagicSquare magicSquare = new MagicSquare(3);
		assertTrue("number is odd; magic square is possible", magicSquare.isValidSize());

		MagicSquare magicSquare2 = new MagicSquare(4);
		assertFalse("number is even; magic square is not possible", magicSquare2.isValidSize());
	}

	@Test
	public void startSquareFromFirstRowSecondIndex_CenterFromFirstRow() {
		MagicSquare magicSquare = new MagicSquare(3);
		magicSquare.startingColumnOfSquare();
		assertEquals("start magic square from first rown", 0, magicSquare.getRow());
		assertEquals("start magic square from second column", 1, magicSquare.getColumn());
	}

	@Test
	public void ifColumIsEqualToSize_SetColumnValueToZero() {
		MagicSquare magicSquare = new MagicSquare(3);
		magicSquare.startingColumnOfSquare();
		// assertEquals("start magic square from first row second coulumn",1,
		// magicSquare.ge());
	}

	@Test
	public void sumOfRows_Columns_Diagnols() {
		MagicSquare magicSquare = new MagicSquare(3);
		assertEquals("sum of all rows, columns and diagnols", 15, magicSquare.expectedSum());
	}

	@Test
	public void determineTheNextIndexWhereNewValueHasToBePlaced() {

		MagicSquare magicSquare = new MagicSquare(3);
		magicSquare.startingColumnOfSquare();

		assertEquals("size of magic square", 3, magicSquare.size());

		// Calculate where to place 2
		magicSquare.setRowAndColumnForNextIterration(1);
		assertEquals("next row of entry", 2, magicSquare.getRow());
		assertEquals("next column of entry", 2, magicSquare.getColumn());

		// Calculate where to place 3
		magicSquare.setRowAndColumnForNextIterration(2);
		assertEquals("next row of entry", 1, magicSquare.getRow());
		assertEquals("next column of entry", 0, magicSquare.getColumn());

		// Calculate where to place 4
		magicSquare.setRowAndColumnForNextIterration(3);
		assertEquals("next row of entry", 2, magicSquare.getRow());
		assertEquals("next column of entry", 0, magicSquare.getColumn());
	}

	@Test
	public void checkIsTheMagicSquareCorrect() {
		MagicSquare magicSquare = new MagicSquare(3);
		int[][] square = magicSquare.createMagicSquare();
		int[][] validMagicSquare = { { 8, 1, 6 }, { 3, 5, 7 }, { 4, 9, 2 } };

		assertTrue(Arrays.deepEquals(validMagicSquare, square));

	}

}
