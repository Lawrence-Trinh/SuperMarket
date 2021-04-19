package control;

import model.people.Supplier;
import model.system.AccountManager;
import view.ManagerPanel;

public class AddSupplierController {

	AccountManager am = new AccountManager();

	private ManagerPanel view;

	public AddSupplierController(ManagerPanel view) {
		this.view = view;

	}

	public void addRemoveUpdateSupplier(String supplierID,String pin, String firstName, String lName, String companyName, String contactNo, String email,String location, boolean remove) {
		if (remove == false) {
		Supplier[] supplier = new Supplier[0];
		Supplier newSupplier = new Supplier(supplierID,"", "", "", companyName, contactNo, email, location);
		supplier = addSupply(supplier, newSupplier);

		System.out.println("\nSupplier successfully added to the database\n");
  		am.addSupplier(newSupplier);
		am.saveUsers();

 		}
		else {
  		am.removeSupplier(supplierID);
  		am.saveUsers();
		}


	}

	private static Supplier[] addSupply(Supplier[] supply, Supplier addSupplier) {
		Supplier[] newSupplier = new Supplier[supply.length + 1];
		System.arraycopy(supply, 0, newSupplier, 0, supply.length);
		newSupplier[newSupplier.length - 1] = addSupplier;

		return newSupplier;
	}

	public void printSuppliers() {
		am.printSuppliers();
	}

}
