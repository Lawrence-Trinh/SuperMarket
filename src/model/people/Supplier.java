package model.people;

public class Supplier extends User{

	private String supplierCompanyName, supplierContactNo, supplierEmail, supplierLocation;

	public Supplier(String supplierID, String supplierPIN, String supplierFName, String supplierLName,
			String supplierCompanyName, String supplierContactNo, String supplierEmail, String supplierLocation) {
		super(supplierID, supplierPIN, supplierFName, supplierLName);
		this.supplierCompanyName = supplierCompanyName;
		this.supplierContactNo = supplierContactNo;
		this.supplierEmail = supplierEmail;
		this.supplierLocation = supplierLocation;
	}

//	public Supplier(String supplierID, String supplierCompanyName, String supplierContactNo, String supplierEmail, String supplierLocation) {
//		super(supplierID);
//		this.supplierCompanyName
//	}


	public String getSupplierCompanyName() {
		return supplierCompanyName;
	}

	public String getSupplierContactNo() {
		return supplierContactNo;
	}

	public String getSupplierEmail() {
		return supplierEmail;
	}

	public String getSupplierLocation() {
		return supplierLocation;
	}
}