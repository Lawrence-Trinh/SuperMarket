package model.system;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import model.pay.Sale;

public class SalesManager {

	public SalesManager()
	{
		initialiseSales();
	}
	
	private ArrayList <Sale> sales = new ArrayList<Sale>();

	public ArrayList<Sale> getSales() {
		return sales;
	}

	public void addSale(Sale sale) {
		sales.add(sale);
	}
	
	public Sale getSaleViaOrderID(long orderID)
	{
		for (int i = 0; i < sales.size(); i++)
		{
			if (orderID == sales.get(i).getOrderID())
			{
				return sales.get(i);
			}
		}
		return null;
	}
	
	public Sale getSales(Sale sale)
	{
		for (int i = 0; i < sales.size(); i++)
		{
			if (sale.equals(sales.get(i)))
			{
				return sales.get(i);
			}
		}
		return null;
	}
	
	public void removeSale(Sale sale)
	{
		sales.remove(sale);
	}
	
	private void initialiseSales()
	{
		try {
	         FileInputStream fileIn = new FileInputStream("database/sales.ser");
	         ObjectInputStream objectIn = new ObjectInputStream(fileIn);
	         sales = (ArrayList<Sale>) objectIn.readObject();
	         System.out.println("Sales are loaded from database!");
	         objectIn.close();
	         fileIn.close();
	      } catch (IOException i) {
	         i.printStackTrace();
	         return;
	      } catch (ClassNotFoundException c) {
	         System.out.println("Sale class not found");
	         c.printStackTrace();
	         return;
	      }
	}
	
	public void saveSales()
	{
		try {
	         FileOutputStream fileOut = new FileOutputStream("database/sales.ser");
	         ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
	         objectOut.writeObject(sales);
	         objectOut.close();
	         fileOut.close();
	         System.out.println("Sales are saved to database!");
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
	}
	
	// DEBUG ONLY, call this before saving!
	public void resetSales()
	{
		sales = new ArrayList<Sale>();
		System.out.println("Sales reset!");
	}
}
