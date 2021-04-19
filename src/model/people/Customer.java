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
	
	// if hasCreditCard == false, user will be prompted to create 1 after logging in!
	public boolean hasCreditCard()
	{
		if (creditCard == null)
			return false;
		else
			return true;
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
