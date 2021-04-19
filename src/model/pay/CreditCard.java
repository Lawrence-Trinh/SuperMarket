package model.pay;

public class CreditCard {

	private String creditCardID;
	private String pin;
	private double balance;
	private int loyaltyPoints;

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

	public double getLoyaltyPoints() {
		return loyaltyPoints;
	}

	public void setLoyaltyPoints(int loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}

	public void addBalance(double topup) {
		this.balance += topup;
	}

	public void deductBalance(double deductAmount) {
		this.balance += deductAmount;
	}
}
