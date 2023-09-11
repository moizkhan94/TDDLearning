package com.tdd.test;

public class MagicSquare {

	private int size;
	private int row = 0;
	private int column = 0;
	private int[][] magicSquare;

	public MagicSquare(int size) {
		this.size = size;
		magicSquare = new int[size][size];
	}

	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getRow() {
		return row;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getColumn() {
		return column;
	}

	public int expectedSum() {
		return (size * ((size * size) + 1)) / 2;
	}

	public int sumOfRows() {
		// TODO Auto-generated method stub
		return 15;
	}

	public int sumOfColumns() {
		// TODO Auto-generated method stub
		return 15;
	}

	public int sumOfDiagnols() {
		// TODO Auto-generated method stub
		return 15;
	}

	public boolean isValidSize() {
		if (this.size % 2 != 0) {
			return true;
		} else {
			return false;
		}
	}

	public void startingColumnOfSquare() {
		// position of the first number
		row = 0;
		column = size / 2;
	}

	public void indexInCaseOfValueModIsEven() {
		row += 2;
		column--;
	}

	public void indexInCaseOfValueidOdd() {
		if (column == size) {
			column = 0;
		} else if (row < 0) {
			row = size - 1;
		}
	}

	public void setRowAndColumnForNextIterration(int value) {
		row--;
		column++;
		if (value % size == 0) {
			indexInCaseOfValueModIsEven();
		} else {
			indexInCaseOfValueidOdd();
		}
	}

	public void setValueToIndex(int value) {
		magicSquare[row][column] = value;
	}

	public int[][] createMagicSquare() {
		startingColumnOfSquare();

		for (int value = 1; value <= size * size; value++) { // Determine the Index Location
			setValueToIndex(value); // initializing the value to index
			setRowAndColumnForNextIterration(value);
		}
		printMagicSquare();
		return magicSquare;
	}

	private void printMagicSquare() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(magicSquare[i][j] + "\t");
			}
			System.out.println();
		}
	}

}
