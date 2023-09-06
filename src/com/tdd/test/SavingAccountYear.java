package com.tdd.test;

public class SavingAccountYear {

	private int startingBalance = 0;
	private int interestRate;
	private int totalWithdrawn = 0;
	private int startingPrincipal;

	public SavingAccountYear(int startingBalance, int startingPrincipal, int interestRate) {
		this.startingBalance = startingBalance;
		this.startingPrincipal = startingPrincipal;
		this.interestRate = interestRate;
	}

	public int startingBalance() {
		return startingBalance;
	}

	public int startingPrincipal() {
		return startingPrincipal;
		// startingBalance - capitalGainsAmount;
	}

	public int startingCapitalGains() {
		return startingBalance - startingPrincipal;
	}

	public int interestRate() {
		return interestRate;
	}

	public int interestEraned(int capitalGainTaxRate) {
		return (startingBalance - totalWithdrawn - capitalGainsTaxIncured(capitalGainTaxRate)) * interestRate/100;
	}
	
	public int totalWithdrawnExceptCapitalGains() {
		return totalWithdrawn;
	}
	
	public int totalWithdrawn(int capitalGainsTax) {
		return totalWithdrawnExceptCapitalGains() + capitalGainsTaxIncured(capitalGainsTax);
	}

	public int endingPrincipal() {
		int result = startingPrincipal() - totalWithdrawnExceptCapitalGains();
		return Math.max(0, result);
	}

	public int endingCapitalGains() {
		return 0;
	}

	public int endingBalance(int capitalGainTaxRate) {
		int modifiedStart = startingBalance - totalWithdrawnExceptCapitalGains() - capitalGainsTaxIncured(capitalGainTaxRate);
		return modifiedStart + interestEraned(capitalGainTaxRate);
	}

	public SavingAccountYear nextYear(int capitalGainTaxRate) {
		return new SavingAccountYear(this.endingBalance(capitalGainTaxRate), 0, interestRate);
	}

	public void withdraw(int amount) {
		this.totalWithdrawn += amount;
	}

	public int capitalGainsWithdrawn() {
		int result = (startingPrincipal() - totalWithdrawnExceptCapitalGains()) * -1;
		return Math.max(0, result);
	}

	public int capitalGainsTaxIncured(int taxRate) {
		return (int) (capitalGainsWithdrawn() / (1 - (taxRate / 100.0)) - capitalGainsWithdrawn());
	}

}
