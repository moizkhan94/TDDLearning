package com.tdd.test;

public class StockMarketYear {

	private int startingBalance;
	private int interestRate;
	private int totalWithdrawls;
	private int startingPrincipal;

	public StockMarketYear(int startingBalance, int startingPrincipal, int interestRate) {
		this.startingBalance = startingBalance;
		this.startingPrincipal = startingPrincipal;
		this.interestRate = interestRate;
		this.totalWithdrawls = 0;
	}

	public int startingBalance() {
		return startingBalance;
	}

	public int startingPrincipal() {
		return startingPrincipal;
	}

	public int interestRate() {
		return interestRate;
	}

	public void withdraw(int amount) {
		this.totalWithdrawls += amount;
	}

	private int capitalGainsWithdrawn() {
		int result = (startingPrincipal() - totalWithdrawls) * -1;
		return Math.max(0, result);
	}

	public int capitalGainsTaxIncured(int taxRate) {
		return (int) (capitalGainsWithdrawn() / (1 - (taxRate / 100.0)) - capitalGainsWithdrawn());
	}

	public int totalWithdrawn(int capitalGainsTax) {
		return totalWithdrawls + capitalGainsTaxIncured(capitalGainsTax);
	}

	public int interestEraned(int capitalGainTaxRate) {
		return (startingBalance - totalWithdrawls - capitalGainsTaxIncured(capitalGainTaxRate)) * interestRate / 100;
	}

	public int endingBalance(int capitalGainTaxRate) {
		int modifiedStart = startingBalance - totalWithdrawn(capitalGainTaxRate);
		return modifiedStart + interestEraned(capitalGainTaxRate);
	}

	public int endingPrincipal() {
		int result = startingPrincipal() - totalWithdrawls;
		return Math.max(0, result);
	}

	public StockMarketYear nextYear(int capitalGainTaxRate) {
		return new StockMarketYear(this.endingBalance(capitalGainTaxRate), this.endingPrincipal(), this.interestRate());
	}

}
