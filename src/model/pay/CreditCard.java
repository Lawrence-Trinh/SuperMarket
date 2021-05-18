package model.pay;

import java.io.Serializable;

public class CreditCard implements Serializable{

	private String creditCardID;
	private String pin;
	private double balance;
	
	public CreditCard(String creditCardID, String pin) {
		this.creditCardID = creditCardID;
		this.pin = pin;
		this.balance = 0;
	}

	public String getCreditCardID() {
		return creditCardID;
	}

	public String getPin() {
		return pin;
	}

	public double getBalance() {
		return balance;
	}

	public void addBalance(double topup) {
		this.balance += topup;
	}

	public void deductBalance(double deductAmount) {
		this.balance -= deductAmount;
	}
}
