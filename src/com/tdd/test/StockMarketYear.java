package com.tdd.test;

public class StockMarketYear {

	private Dollars startingBalance;
	private InterestRate interestRate;
	private Dollars totalWithdrawls;
	private Dollars startingPrincipal;
	private TaxRate capitalGainsTaxRate;

	public StockMarketYear(Dollars startingBalance, Dollars startingPrincipal, InterestRate interestRate,
			TaxRate capitalGainsTaxRate) {
		this.startingBalance = startingBalance;
		this.startingPrincipal = startingPrincipal;
		this.interestRate = interestRate;
		this.capitalGainsTaxRate = capitalGainsTaxRate;
		this.totalWithdrawls = new Dollars(0);
	}

	public Dollars startingBalance() {
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

	public void withdraw(Dollars amount) {
		this.totalWithdrawls = this.totalWithdrawls.add(amount);
	}

	private Dollars capitalGainsWithdrawn() {
		return totalWithdrawls.subtractToZero(startingPrincipal());

	}

	public Dollars capitalGainsTaxIncured() {
		return capitalGainsTaxRate.compoundTaxFor(capitalGainsWithdrawn());
	}

	public Dollars totalWithdrawn() {
		return totalWithdrawls.add(capitalGainsTaxIncured());
	}

	public Dollars interestEraned() {
		return interestRate().interestOnAmount((startingBalance.subtract(totalWithdrawn())));
	}

	public Dollars endingBalance() {
		return startingBalance.subtract(totalWithdrawn()).add(interestEraned());
	}

	public Dollars endingPrincipal() {
		return startingPrincipal().subtractToZero(totalWithdrawls);
	}

	public StockMarketYear nextYear(int capitalGainTaxRate) {
		return new StockMarketYear(this.endingBalance(), this.endingPrincipal(), this.interestRate(),
				this.capitalGainsTaxRate());
	}

}
