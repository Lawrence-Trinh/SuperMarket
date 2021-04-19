package control;

import model.system.Product;
import model.system.ProductManager;
import view.ManagerPanel;

public class AddProductController   {

	ProductManager pm = new ProductManager();

	private String productName;
	private int stockQty;
	private String productID;
	private double productPrice;

	private Product productModel;
	private ManagerPanel view;

	public AddProductController(ManagerPanel view) {
		this.view = view;
//		pm.resetProducts();
		pm.printSize();
	}

	public void addRemoveModifyProduct(String productID, String productName, double productPrice, int productQuantity, boolean remove) {
		try {
			if (remove == false) {
			Product[] products = new Product[0];
			Product newProduct = new Product(productID, productName, productPrice, productQuantity);
			products = addProduct(products, newProduct);

 			printProduct(newProduct);
 			newProduct.toString();

 			pm.addProduct(newProduct);
 			pm.saveProducts();
			}
			else if (remove == true){
			pm.removeProduct(productID);
			pm.saveProducts();
			}

 		} catch (NumberFormatException e) {
			System.err.println("Incorrect Format!");
		}
	}

	private static Product[] addProduct(Product[] products, Product productToAdd) {
		Product[] newProducts = new Product[products.length + 1];
		System.arraycopy(products, 0, newProducts, 0, products.length);
		newProducts[newProducts.length - 1] = productToAdd;

		return newProducts;
	}

	public void setDiscount() {

	}

	public boolean restockNeeded() {
		if (Integer.parseInt(view.getProductQuantity()) <= 5) {
			return true;
		}
		return false;
	}

	private void printProduct(Product product) {
		System.out.println(product.toString());
	}

	public String getProductName() {
		return productModel.getProductName();
	}

	public String getProductID() {
		return productModel.getProductId();
	}

	public Double getProductPrice() {
		return productModel.getProductPrice();
	}

	public int getProductQuantity() {
		return productModel.getStockQty();
	}


	@Override
	public String toString() {
		return String.format("Product ID: %s\r\nProduct Name: %s\r\nProduct Price: %s\r\nProduct Stock: %d\r\n",
				productID, productName, productPrice, stockQty);
	}
}
