package control;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;

import model.pay.Sale;
import model.pay.SalesLineItem;
import model.people.Customer;
import model.system.Product;
import model.system.ProductManager;
import view.CustomerCheckoutPanel;

public class ItemController {
	
	private CustomerCheckoutPanel view;
	private ProductManager productManager;
	private Sale cart;
	
	public ItemController(CustomerCheckoutPanel customerCheckoutPanel, Customer customer) {
		this.productManager = new ProductManager();
		this.view = customerCheckoutPanel;
		this.cart = new Sale(customer);
	}
	
	public boolean addItemID(String id, int quantity) {
		if (productManager.getProduct(id) != null) {
			SalesLineItem item = new SalesLineItem(quantity, productManager.getProduct(id));
			DecimalFormat decimalFormat = new DecimalFormat("0.00");
			if (cart.getSalesLineItem(id) == null) {
				cart.addLineItem(item);
				view.getTableModel().addRow(new Object[] {item.getProduct().getProductName(), quantity, decimalFormat.format(item.getItemPrice() * quantity)});
			}
			else {
				cart.addLineItem(item);
				Object q1 = view.getTableModel().getValueAt(searchForRow(item.getProduct().getProductName()), 1);
				int quant = quantity + Integer.parseInt(q1.toString());
				view.getTableModel().setValueAt(quant, searchForRow(item.getProduct().getProductName()), 1);
				view.getTableModel().setValueAt(decimalFormat.format(quant * item.getItemPrice()), searchForRow(item.getProduct().getProductName()), 2);
			}
			view.getTotalLabel().setText("$" + decimalFormat.format(cart.getTotalPrice()));
			return true;
		}
		System.out.println("Does not exist");
		view.getErrorMessageID().setText("ID does not exist");
		view.getErrorMessageID().setVisible(true);
		return false;
	}
	
	public boolean addItemName(String name, double weight) {
		String id = productManager.getProductID(name);
		SalesLineItem item = new SalesLineItem(1, productManager.getProduct(id));
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		if (productManager.getProduct(id) != null) {
			cart.addLineItem(item);
			view.getTableModel().addRow(new Object[] {item.getProduct().getProductName(), 1, decimalFormat.format(item.getItemPrice())});
			view.getTotalLabel().setText("$" + decimalFormat.format(cart.getTotalPrice()));
		}
		
		return false;
	}
	
	public void addItemSelected(String name, int quantity) {
		String id = productManager.getProductID(name);
		SalesLineItem item = new SalesLineItem(quantity, productManager.getProduct(id));
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		if (cart.getSalesLineItem(id) == null) {
			cart.addLineItem(item);
			view.getTableModel().addRow(new Object[] {item.getProduct().getProductName(), quantity, decimalFormat.format(item.getItemPrice() * quantity)});
		}
		else {
			cart.addLineItem(item);
			Object q1 = view.getTableModel().getValueAt(searchForRow(item.getProduct().getProductName()), 1);
			int quant = quantity + Integer.parseInt(q1.toString());
			view.getTableModel().setValueAt(quant, searchForRow(item.getProduct().getProductName()), 1);
			view.getTableModel().setValueAt(decimalFormat.format(quant * item.getItemPrice()), searchForRow(item.getProduct().getProductName()), 2);
		}
		view.getTotalLabel().setText("$" + decimalFormat.format(cart.getTotalPrice()));
	}
	
	public void removeItem(String name, int row) {
		cart.removeLineItem(cart.getItemId(name));
		DecimalFormat decimalFormat = new DecimalFormat("00.00");
		view.getTableModel().removeRow(row);
		view.getTotalLabel().setText("$" + decimalFormat.format(cart.getTotalPrice()));
		System.out.println(cart.getItemsInCart());
	}
	
	public int searchForRow(String name) {
		for (int i = 0; i < view.getTableModel().getRowCount(); i++) {
			if (view.getTableModel().getValueAt(i, 0).equals(name))
				return i;
		}
		return -1;
	}

	public void displayAllItems() {
		HashMap<String, Product> products = productManager.getProductsMap();
		Iterator iterator = products.entrySet().iterator();
		while (iterator.hasNext()) {
			HashMap.Entry pair = (HashMap.Entry)iterator.next();
			DecimalFormat decimalFormat = new DecimalFormat("0.00");
			String name = productManager.getProduct(pair.getKey().toString()).getProductName();
			String price = decimalFormat.format(productManager.getProduct(pair.getKey().toString()).getProductPrice());
	        view.getSelectItemTableModel().addRow(new Object[] {name, "$" + price});
		}
	}
}
