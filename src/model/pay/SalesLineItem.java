package model.pay;

import model.system.Product;

public class SalesLineItem {

	private int quantity;
	private Product product;
	private double weight;
	private boolean weightable;

	public SalesLineItem(int quantity, Product product) {
		this.quantity = quantity;
		this.product = product;
	}
	
	public SalesLineItem(double weight, Product product, boolean weightable)
	{
		this.weight = weight;
		this.product = product;
		this.weightable = weightable;
	}

	public int getProductQuantity() {
		return quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProductQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getItemPrice() {
		if (this.weightable == true)
			return product.getPricePerGram();
		else 
			return product.getProductPrice();
	}
	
	public boolean getWeightable() {
		return weightable;
	}
	
	public double getWeight()
	{
		return weight;
	}
	
	public void setWeight(double weight)
	{
		this.weight = weight;
	}

	public double getTotalPrice() {
		if (product.getBulkSalesEligible() == true && quantity >= product.getBulkSalesQty()) {
			if (this.weightable == true)
				return (product.getPricePerGram() * weight) * (1 - product.getBulkSalesRate());
			else
				return (product.getProductPrice() * quantity) * (1 - product.getBulkSalesRate());
		} else if (product.getDiscountEligible() == true) {
			if (this.weightable == true)
				return (product.getPricePerGram() * weight) * (1 - product.getDiscountRate());
			else
				return (product.getProductPrice() * quantity) * (1 - product.getDiscountRate());
		} else
			if (this.weightable == true)
				return product.getPricePerGram() * weight;
			else
				return product.getProductPrice() * quantity;
	}

}
