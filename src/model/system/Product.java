package model.system;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Product implements Serializable {

	// manages product details, manager can modify most, supplier modifies quantity
	// productLoad and productSave not implemented yet! (product.txt read/write)
	// product array can be stored in SupportSystem..?

	// TODO

	private String productName;
	private int stockQty;
	private String productID;
	private double productPrice;
	private int amountSold;
	private int totalQtyRestocked = 0;
	private double revenueGenerated = 0;
	

	// to be overwritten by methods
	private boolean discountEligible = false;
	private double discountRate = 0;
	private double discountedPrice = 0;

	private boolean bulkSalesEligible = false;
	private int bulkSalesQty = 0;
	private double bulkSalesRate = 0;
	private boolean weightable = false;
	private double pricePerGram = 0;

	private int restockLvl = 0;
	private int reorderQty = 0;

	private static NumberFormat formatter = new DecimalFormat("#0.00");

	public Product(String productID, String productName, double productPrice, int stockQty) {
		this.productName = productName;
		this.stockQty = stockQty;
		this.productID = productID;
		this.productPrice = productPrice;
		// product name is ID, quantitySold is written to sales.txt and read from there
	}

	public void setProductName(String name) {
		this.productName = name;
	}

	public String getProductName() {
		return productName;
	}

	public int getStockQty() {
		return stockQty;
	}

	public void addStockQty(int quantity) {
		this.stockQty += quantity;
	}

	public void reduceStockQty(int quantity) {
		this.stockQty -= quantity;
	}

	public String getProductId() {
		return productID;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double price) {
		this.productPrice = price;
	}

	public boolean getDiscountEligible() {
		return discountEligible;
	}

	public double getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(double rate) {
		this.discountRate = rate;
		if (rate > 0)
			this.discountEligible = true;
		else if (rate == 0)
			this.discountEligible = false;
	}

	public double getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(double discountPercentage) {
//		this.discountedPrice = discountPercentage;
		double price = this.getProductPrice();
		this.discountRate = discountPercentage;
		double total = 0;
		double s;
		s = 100 - discountPercentage;
		total = (s * price) / 100;

		this.discountedPrice = total;
	}

	public boolean getBulkSalesEligible() {
		return bulkSalesEligible;
	}

	public double getBulkSalesRate() {
		return bulkSalesRate;
	}

	public double getBulkSalesQty() {
		return bulkSalesQty;
	}

	public void setBulkSales(double rate, int qty) {
		this.bulkSalesRate = rate;
		this.bulkSalesQty = qty;
		if (rate > 0 && qty != 0)
			this.bulkSalesEligible = true;
		else if (rate == 0 && qty == 0)
			this.bulkSalesEligible = false;
	}

	public boolean getWeightable() {
		return weightable;
	}

	public double getPricePerGram() {
		return pricePerGram;
	}

	public void setWeightable(double pricePerGram) {
		this.pricePerGram = pricePerGram;
		if (pricePerGram > 0)
			this.weightable = true;
		else if (pricePerGram == 0)
			this.weightable = false;
	}

	public void setRestockLvl(int restockLvl) {
		this.restockLvl = restockLvl;
	}

	public int getRestockLvl() {
		return restockLvl;
	}

	public void setReorderQty(int qty) {
		this.reorderQty = qty;
	}

	public int getReorderQty() {
		return reorderQty;
	}
	
	public int getAmountSold()
	{
		return amountSold;
	}
	
	public void addAmountSold(int quantity) {
		this.amountSold += quantity;
	}
	
	public void increaseRevenueGenerated(double revenue) {
		this.revenueGenerated += revenue;
	}
	
	public int getTotalQtyRestocked() {
		return totalQtyRestocked;
	}
	
	public double getRevenueGenerated() {
		return revenueGenerated;
	}

	public void restock()
	{
		addStockQty(reorderQty); 
		totalQtyRestocked += reorderQty;
	}
	
	@Override
	public String toString() {
		return String.format("Product ID: %s\r\nProduct Name: %s\r\nProduct Price: %s\r\nProduct Stock: %d\r\n",
				productID, productName, formatter.format(productPrice), stockQty);
	}

}
