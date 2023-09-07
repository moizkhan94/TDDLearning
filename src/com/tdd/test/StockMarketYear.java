package com.tdd.test;

public class StockMarketYear {

	private int startingBalance;
	private int interestRate;
	private int totalWithdrawls;
	private int startingPrincipal;
	private int capitalGainsTaxRate;

	public StockMarketYear(int startingBalance, int startingPrincipal, int interestRate, int capitalGainsTaxRate) {
		this.startingBalance = startingBalance;
		this.startingPrincipal = startingPrincipal;
		this.interestRate = interestRate;
		this.capitalGainsTaxRate = capitalGainsTaxRate;
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
	
	public int capitalGainsTaxRate() {
		return capitalGainsTaxRate;
	}

	public void withdraw(int amount) {
		this.totalWithdrawls += amount;
	}

	private int capitalGainsWithdrawn() {
		int result = (startingPrincipal() - totalWithdrawls) * -1;
		return Math.max(0, result);
	}

	public int capitalGainsTaxIncured() {
		return (int) (capitalGainsWithdrawn() / (1 - (capitalGainsTaxRate / 100.0)) - capitalGainsWithdrawn());
	}

	public int totalWithdrawn() {
		return totalWithdrawls + capitalGainsTaxIncured();
	}

	public int interestEraned() {
		return (startingBalance - totalWithdrawls - capitalGainsTaxIncured()) * interestRate / 100;
	}

	public int endingBalance() {
		int modifiedStart = startingBalance - totalWithdrawn();
		return modifiedStart + interestEraned();
	}

	public int endingPrincipal() {
		int result = startingPrincipal() - totalWithdrawls;
		return Math.max(0, result);
	}

	public StockMarketYear nextYear(int capitalGainTaxRate) {
		return new StockMarketYear(this.endingBalance(), this.endingPrincipal(), this.interestRate(),
				this.capitalGainsTaxRate);
	}


}
