package model.pay;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

import model.people.Customer;
import model.system.Product;
import model.system.SalesManager;

public class Sale implements Serializable {

	private ArrayList<SalesLineItem> lineItems = new ArrayList<SalesLineItem>();
	private boolean paid = false;
	private long orderID;
	private int loyaltyPtsUsed;
	private int loyaltyPtsEarned;
	private double totalDiscountedPrice;
	private double totalPrice;
	private Customer customer;
	private LocalDateTime timePaid;

	public Sale(Customer customer) {
		try {
			FileInputStream fileInOrderID = new FileInputStream("database/orderID.ser");
			ObjectInputStream objectInOrderID = new ObjectInputStream(fileInOrderID);
			orderID = (long) objectInOrderID.readObject();
			objectInOrderID.close();
			fileInOrderID.close();
		} catch (IOException i) {
			i.printStackTrace();
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return;
		}
		orderID++;
		this.customer = customer;
	}

	public ArrayList<SalesLineItem> getSalesLineItems() {
		return lineItems; // for view
	}

	private double calcTotalPrice() {
		double totalPrice = 0;
		for (int i = 0; i < lineItems.size(); i++) {
			totalPrice += lineItems.get(i).getTotalPrice();
		}
		this.totalPrice = totalPrice;
		return totalPrice;
	}

	private double calcTotalDiscountedPrice() {
		int pts = customer.getLoyaltyPts();
		double totalDiscountedPrice = totalPrice;
		while (pts >= 20 && totalDiscountedPrice >= 5) {
			totalDiscountedPrice -= 5;
			pts -= 20;
		}
		this.totalDiscountedPrice = totalDiscountedPrice;
		return totalDiscountedPrice;
	}

	private int calcLoyaltyPtsUsed() {
		double tempPrice = totalPrice;
		tempPrice -= totalDiscountedPrice;
		int tempPtsUsed = 0;
		while (tempPrice >= 5) {
			tempPrice -= 5;
			tempPtsUsed += 20;
		}
		this.loyaltyPtsUsed = tempPtsUsed;
		return tempPtsUsed;
	}

	private int calcLoyaltyPtsEarned() {
		int tempPts = 0;
		double tempPrice = totalDiscountedPrice;
		while (tempPrice >= 10) {
			tempPts++;
			tempPrice -= 10;
		}
		this.loyaltyPtsEarned = tempPts; 
		return tempPts;
	}

	public void addLineItem(SalesLineItem lineItem) {
		lineItems.add(lineItem);
		updatePriceAndPts();
	}

	public void removeLineItem(String productID) {
		for (int i = 0; i < lineItems.size(); i++) {
			if (lineItems.get(i).getProduct().getProductId().equals(productID)) {
				lineItems.remove(i);
			}
		}
		updatePriceAndPts();
	}
	
	public void removeLineItemByName(String name) {
		for (int i = 0; i < lineItems.size(); i++) {
			if (lineItems.get(i).getProduct().getProductName().equals(name)) {
				lineItems.remove(i);
			}
		}
		updatePriceAndPts();
	}

	public SalesLineItem getSalesLineItem(String productID) {
		for (int i = 0; i < lineItems.size(); i++) {
			if (lineItems.get(i).getProduct().getProductId().equals(productID)) {
				return lineItems.get(i);
			}
		}
		return null; // if cant find, throws exception?
	}
	
	public String getItemId(String name) {
		for (int i = 0; i < lineItems.size(); i++) {
			if (lineItems.get(i).getProduct().getProductName().equals(name)) {
				return lineItems.get(i).getProduct().getProductId();
			}
		}
		return null;
	}

	public int getItemsInCart() {
		return lineItems.size();
	}

	public long getOrderID() {
		return orderID;
	}

	public boolean isPaid() {
		return paid;
	}
	
	public int getLoyaltyPtsUsed() {
		return loyaltyPtsUsed;
	}

	public int getLoyaltyPtsEarned() {
		return loyaltyPtsEarned;
	}
	
	private void updatePriceAndPts() {
		calcTotalPrice();
		calcTotalDiscountedPrice();
		calcLoyaltyPtsUsed();
		calcLoyaltyPtsEarned();
	}

	public void pay(SalesManager sr) {
		this.paid = true;
		// deduct product stock
		for (int i = 0; i < lineItems.size(); i++) {
			Product tempProduct = lineItems.get(i).getProduct();
			int quantity = lineItems.get(i).getProductQuantity();
			tempProduct.reduceStockQty(quantity);
			if (tempProduct.getStockQty() <= tempProduct.getRestockLvl())
			{
				tempProduct.restock();
			}
			tempProduct.addAmountSold(quantity);
			tempProduct.increaseRevenueGenerated(lineItems.get(i).getTotalPrice());
		}

		// add loyalty points
		customer.deductLoyaltyPts(loyaltyPtsUsed);
		customer.addLoyaltyPts(loyaltyPtsEarned);
		
		timePaid = LocalDateTime.now();
		
		// increase orderID by 1 after purchase!
		try {
			FileOutputStream fileOutOrderID = new FileOutputStream("database/orderID.ser");
			ObjectOutputStream objectOutOrderID = new ObjectOutputStream(fileOutOrderID);
			objectOutOrderID.writeObject(orderID);
			objectOutOrderID.close();
			fileOutOrderID.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
		
		sr.addSale(this);
	}

	// check if paid is true!
	// if paid is false, null will be returned!
	public LocalDateTime getTimePaid() {
		return timePaid;
	}

	public double getTotalDiscountedPrice() {
		return totalDiscountedPrice;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public Customer getCustomer() {
		return customer;
	}

}
