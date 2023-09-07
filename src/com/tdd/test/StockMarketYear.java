package com.tdd.test;

public class StockMarketYear {

	private int startingBalance;
	private InterestRate interestRate;
	private int totalWithdrawls;
	private int startingPrincipal;
	private TaxRate capitalGainsTaxRate;

	public StockMarketYear(int startingBalance, int startingPrincipal, InterestRate interestRate,
			TaxRate capitalGainsTaxRate) {
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

	public InterestRate interestRate() {
		return interestRate;
	}

	public TaxRate capitalGainsTaxRate() {
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
		return capitalGainsTaxRate.compoundTaxFor(capitalGainsWithdrawn());
	}

	public int totalWithdrawn() {
		return totalWithdrawls + capitalGainsTaxIncured();
	}

	public int interestEraned() {
		return interestRate().interestOnAmount((startingBalance - totalWithdrawn()));
	}

	public int endingBalance() {
		return startingBalance - totalWithdrawn() + interestEraned();
	}

	public int endingPrincipal() {
		int result = startingPrincipal() - totalWithdrawls;
		return Math.max(0, result);
	}

	public StockMarketYear nextYear(int capitalGainTaxRate) {
		return new StockMarketYear(this.endingBalance(), this.endingPrincipal(), this.interestRate(),
				this.capitalGainsTaxRate());
	}

}
