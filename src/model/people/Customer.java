package model.people;

import model.pay.CreditCard;

public class Customer extends User{

	private int loyaltyPts;
	private CreditCard creditCard;

	public Customer(String customerID, String customerPIN, String customerFName, String customerLName) {
		super(customerID, customerPIN, customerFName, customerLName);
		this.loyaltyPts = 0;
	}

	public int getLoyaltyPts() {
		return loyaltyPts;
	}

	public void addLoyaltyPts(int pts) {
		this.loyaltyPts += pts;
	}

	public void deductLoyaltyPts(int pts) {
		this.loyaltyPts -= pts;
	}
	
	public CreditCard getCreditCard()
	{
		return creditCard;
	}
	
	public void setCreditCard(CreditCard creditCard)
	{
		this.creditCard = creditCard;
	}
}
