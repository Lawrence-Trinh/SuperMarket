package model.system;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;

import model.people.Customer;
import model.people.Manager;
import model.people.SalesStaff;
import model.people.User;
import model.people.WarehouseStaff;

public class AccountManager {

	private HashMap<String, Customer> customers = new HashMap<String, Customer>();
	private HashMap<String, SalesStaff> salesStaffs = new HashMap<String, SalesStaff>();
	private HashMap<String, Manager> managers = new HashMap<String, Manager>();
	private HashMap<String, WarehouseStaff> warehouseStaffs = new HashMap<String, WarehouseStaff>();

	public AccountManager() {
		initialiseUsers();
	}

	public Customer getCustomer(String id) {
		if (customers.containsKey(id)) {
			return customers.get(id);
		} else {
			System.out.println("Customer " + id + " not found in database!");
			return null;
		}
	}

	public WarehouseStaff getWarehouseStaff(String id) {
		if (warehouseStaffs.containsKey(id)) {
			return warehouseStaffs.get(id);
		} else {
			System.out.println("Warehouse Staff " + id + " not found in database!");
			return null;
		}
	}
	
	public SalesStaff getSalesStaff(String username) {
		if (salesStaffs.containsKey(username)) {
			return salesStaffs.get(username);
		} else {
			System.out.println("Staff " + username + " not found in database!");
			return null;
		}
	}

	public Manager getManager(String username) {
		if (managers.containsKey(username)) {
			return managers.get(username);
		} else {
			System.out.println("Manager " + username + " not found in database!");
			return null;
		}
	}

	public boolean addCustomer(Customer customer) {
		String temp = customer.getUserID();
		if (customers.containsKey(temp)) {
			System.out.println("Customer exists " + temp + " in database!");
			return false;
		} else {
			customers.put(temp, customer);
			return true;
		}
	}

	public boolean addSalesStaff(SalesStaff salesStaff) {
		String temp = salesStaff.getUserID();
		if (salesStaffs.containsKey(temp)) {
			System.out.println("Sales staff " + temp + " exists in database!");
			return false;
		} else {
			salesStaffs.put(temp, salesStaff);
			return true;
		}
	}

	public boolean addManager(Manager manager) {
		String temp = manager.getUserID();
		if (managers.containsKey(temp)) {
			System.out.println("Manager " + temp + " exists in database!");
			return false;
		} else {
			managers.put(temp, manager);
			return true;
		}
	}
	
	public boolean addWarehouseStaff(WarehouseStaff warehouseStaff) {
		String temp = warehouseStaff.getUserID();
		if (warehouseStaffs.containsKey(temp)) {
			System.out.println("Warehouse Staff " + temp + " exists in database!");
			return false;
		} else {
			warehouseStaffs.put(temp, warehouseStaff);
			return true;
		}
	}

	public boolean removeCustomer(Customer customer) {
		String temp = customer.getUserID();
		if (customers.containsValue(customer)) {
			customers.remove(temp);
			return true;
		} else {
			System.out.println("Customer " + temp + " doesn't exist in database!");
			return false;
		}
	}
	
	public boolean removeWarehouseStaff(WarehouseStaff warehouseStaff) {
		String temp = warehouseStaff.getUserID();
		if (warehouseStaffs.containsValue(warehouseStaff)) {
			warehouseStaffs.remove(temp);
			return true;
		} else {
			System.out.println("Warehouse Staff " + temp + " doesn't exist in database!");
			return false;
		}
	}

	public boolean removeSalesStaff(SalesStaff salesStaff) {
		String temp = salesStaff.getUserID();
		if (salesStaffs.containsValue(salesStaff)) {
			customers.remove(temp);
			return true;
		} else {
			System.out.println("Sales staff " + temp + " doesn't exist in database!");
			return false;
		}
	}

	public boolean removeManager(Manager manager) {
		String temp = manager.getUserID();
		if (managers.containsValue(manager)) {
			managers.remove(temp);
			return true;
		} else {
			System.out.println("Manager " + temp + " doesn't exist in database!");
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	private void initialiseUsers() {
		try {
			FileInputStream fileInCustomer = new FileInputStream("database/customers.ser");
			FileInputStream fileInWarehouseStaff = new FileInputStream("database/warehousestaffs.ser");
			FileInputStream fileInManager = new FileInputStream("database/managers.ser");
			FileInputStream fileInSalesStaff = new FileInputStream("database/salesstaffs.ser");
			ObjectInputStream objectInCustomer = new ObjectInputStream(fileInCustomer);
			ObjectInputStream objectInWarehouseStaff = new ObjectInputStream(fileInWarehouseStaff);
			ObjectInputStream objectInManager = new ObjectInputStream(fileInManager);
			ObjectInputStream objectInSalesStaff = new ObjectInputStream(fileInSalesStaff);
			customers = (HashMap<String, Customer>) objectInCustomer.readObject();
			warehouseStaffs = (HashMap<String, WarehouseStaff>) objectInWarehouseStaff.readObject();
			managers = (HashMap<String, Manager>) objectInManager.readObject();
			salesStaffs = (HashMap<String, SalesStaff>) objectInSalesStaff.readObject();
			System.out.println("Users are loaded from database!");
			objectInCustomer.close();
			objectInWarehouseStaff.close();
			objectInManager.close();
			objectInSalesStaff.close();
			fileInCustomer.close();
			fileInWarehouseStaff.close();
			fileInManager.close();
			fileInSalesStaff.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return;
		}
	}

	// always call this function before shutdown
	public void saveUsers() {
		try {
			FileOutputStream fileOutCustomer = new FileOutputStream("database/customers.ser");
			FileOutputStream fileOutWarehouseStaff = new FileOutputStream("database/warehousestaffs.ser");
			FileOutputStream fileOutManager = new FileOutputStream("database/managers.ser");
			FileOutputStream fileOutSalesStaff = new FileOutputStream("database/salesstaffs.ser");
			ObjectOutputStream objectOutCustomer = new ObjectOutputStream(fileOutCustomer);
			ObjectOutputStream objectOutWarehouseStaff = new ObjectOutputStream(fileOutWarehouseStaff);
			ObjectOutputStream objectOutManager = new ObjectOutputStream(fileOutManager);
			ObjectOutputStream objectOutSalesStaff = new ObjectOutputStream(fileOutSalesStaff);
			objectOutCustomer.writeObject(customers);
			objectOutWarehouseStaff.writeObject(warehouseStaffs);
			objectOutManager.writeObject(managers);
			objectOutSalesStaff.writeObject(salesStaffs);
			objectOutCustomer.close();
			objectOutWarehouseStaff.close();
			objectOutManager.close();
			objectOutSalesStaff.close();
			fileOutCustomer.close();
			fileOutWarehouseStaff.close();
			fileOutManager.close();
			fileOutSalesStaff.close();
			System.out.println("Users are saved to database!");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	// DEBUG ONLY, call this before saving!
	public void resetUsers() {
		customers = new HashMap<String, Customer>();
		salesStaffs = new HashMap<String, SalesStaff>();
		managers = new HashMap<String, Manager>();
		warehouseStaffs = new HashMap<String, WarehouseStaff>();
		System.out.println("Users reset!");
	}

	// Print registered managers in .ser file
	public void printManagers() {
		Iterator iterator = managers.entrySet().iterator();
		while (iterator.hasNext()) {
			HashMap.Entry pair = (HashMap.Entry) iterator.next();
			System.out.println(pair.getKey() + " = " + getManager(pair.getKey().toString()).getUserPIN());
		}
	}

	// Print registered salesStaffs in .ser file
	public void printSalesStaffs() {
		Iterator iterator = salesStaffs.entrySet().iterator();
		while (iterator.hasNext()) {
			HashMap.Entry pair = (HashMap.Entry) iterator.next();
			System.out.println(pair.getKey() + " = " + getSalesStaff(pair.getKey().toString()).getUserPIN());
		}
	}

	// Print registered customers in .ser file
	public void printCustomers() {
		Iterator iterator = customers.entrySet().iterator();
		while (iterator.hasNext()) {
			HashMap.Entry pair = (HashMap.Entry) iterator.next();
			System.out.println(pair.getKey() + " = " + getCustomer(pair.getKey().toString()).getUserPIN());
		}
	}
	
	// Print registered suppliers in .ser file
		public void printWarehouseStaffs() {
			Iterator iterator = warehouseStaffs.entrySet().iterator();
			while (iterator.hasNext()) {
				HashMap.Entry pair = (HashMap.Entry) iterator.next();
				System.out.println(pair.getKey() + " = " + getWarehouseStaff(pair.getKey().toString()).getUserPIN());
			}
		}

	// Print amount of users in .ser file
	public void printSize() {
		System.out.println("Customers: " + customers.size());
		System.out.println("SalesStaffs: " + salesStaffs.size());
		System.out.println("Managers: " + managers.size());
		System.out.println("Warehouse Staffs: " + warehouseStaffs.size());
	}
	
	public User verify(String username, String pin)
	{
		switch (username.charAt(0)) {
		// Manager Login
		case 'm':
			if (getManager(username) != null) {
				if (getManager(username).getUserPIN().equals(pin)) {
					return getManager(username);
				}
			}

			break;
		// SalesStaff Login
		case 's':
			if (getSalesStaff(username) != null) {
				if (getSalesStaff(username).getUserPIN().equals(pin)) {
					return getSalesStaff(username);
				}
			}
			break;
		// Customer Login
		case 'c':

			if (getCustomer(username) != null) {
				if (getCustomer(username).getUserPIN().equals(pin)) {
					return getCustomer(username);
				}
			}
			break;
		case 'w':
	
			if (getWarehouseStaff(username) != null) {
				if (getWarehouseStaff(username).getUserPIN().equals(pin)) {
					return getWarehouseStaff(username);
				}
			}
			break;
		}
		return null;
		
	}

}
