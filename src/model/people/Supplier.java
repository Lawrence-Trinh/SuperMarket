package model.people;

import java.io.Serializable;

public class Supplier implements Serializable {

	private String supplierCompanyName, supplierContactNo, supplierEmail, supplierLocation;
	
	public Supplier(String supplierCompanyName, String supplierContactNo, String supplierEmail, String supplierLocation)
	{
		this.supplierCompanyName = supplierCompanyName;
		this.supplierContactNo = supplierContactNo;
		this.supplierEmail = supplierEmail;
		this.supplierLocation = supplierLocation;
	}

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

	public void setSupplierCompanyName(String supplierCompanyName) {
		this.supplierCompanyName = supplierCompanyName;
	}

	public void setSupplierContactNo(String supplierContactNo) {
		this.supplierContactNo = supplierContactNo;
	}

	public void setSupplierEmail(String supplierEmail) {
		this.supplierEmail = supplierEmail;
	}

	public void setSupplierLocation(String supplierLocation) {
		this.supplierLocation = supplierLocation;
	}
	

	
}