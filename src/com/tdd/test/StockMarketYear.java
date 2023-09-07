package com.tdd.test;

public class StockMarketYear {

	private int startingBalance;
	private InterestRate interestRate;
	private int totalWithdrawls;
	private Dollars startingPrincipal;
	private TaxRate capitalGainsTaxRate;

	public StockMarketYear(int startingBalance, Dollars startingPrincipal, InterestRate interestRate,
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

	public Dollars startingPrincipal() {
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
		int result = (startingPrincipal().amount() - totalWithdrawls) * -1;
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
		return startingPrincipal().subtractToZero(new Dollars(totalWithdrawls)).amount();
	}

	public StockMarketYear nextYear(int capitalGainTaxRate) {
		return new StockMarketYear(this.endingBalance(), new Dollars(this.endingPrincipal()), this.interestRate(),
				this.capitalGainsTaxRate());
	}

}
