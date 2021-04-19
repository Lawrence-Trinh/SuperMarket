	package control;

import model.people.Customer;
import model.people.Manager;
import model.people.SalesStaff;
import model.people.Supplier;
import model.system.AccountManager;
import view.CustomerCheckoutPanel;
import view.LoginScreen;
import view.ManagerPanel;
import view.WelcomeScreen;

public class LoginController {

	private LoginScreen view;
	private AccountManager accountManager;
	private Boolean loginPass;
	private WelcomeScreen welcomeScreen;

	// Originally no constructor
	public LoginController() {
		accountManager = new AccountManager();
	}

	public void checkCredentials(String id, char[] pass) {
		try {
			loginPass = false;
			id = id.toUpperCase();
			switch (id.charAt(0)) {
			// Manager Login
			case 'M':
				if (accountManager.getManager(id) != null) {
					if (accountManager.getManager(id).getUserPIN().equals(new String(pass))) {
						loginPass = true;
						Manager manager = accountManager.getManager(id);
						ManagerPanel managerPanel = new ManagerPanel(manager);
						managerPanel.setWelcomeScreen(welcomeScreen);
						managerPanel.setVisible(true);
						view.setVisible(false);;
					}
				}

				break;
			// SalesStaff Login
			case 'S':
				if (accountManager.getSalesStaff(id) != null) {
					if (accountManager.getSalesStaff(id).getUserPIN().equals(new String(pass))) {
						loginPass = true;
						SalesStaff salesStaff = accountManager.getSalesStaff(id);
					}
				}
				break;
			// Customer Login
			case 'C':

				if (accountManager.getCustomer(id) != null) {
					if (accountManager.getCustomer(id).getUserPIN().equals(new String(pass))) {
						loginPass = true;
						Customer customer = accountManager.getCustomer(id);
						CustomerCheckoutPanel customerCheckoutPanel = new CustomerCheckoutPanel(customer);
						customerCheckoutPanel.setWelcomeScreen(welcomeScreen);
						customerCheckoutPanel.setVisible(true);
						view.setVisible(false);
					}
				}
				break;
			// Supplier Login
			case 'P':
				if (accountManager.getSupplier(id) != null) {
					if (accountManager.getSupplier(id).getUserPIN().equals(new String(pass))) {
						loginPass = true;
						Supplier supplier = accountManager.getSupplier(id);
					}
				}

				break;
			}
		} catch(IndexOutOfBoundsException e) {
			System.err.println("Must fill out the form");
		}

		view.clearField();
		view.setErrorMessageVisible(loginPass);

	}

	public void setAccountManager(AccountManager am) {
		this.accountManager = am;
	}

	public void setView(LoginScreen view) {
		this.view = view;
	}

	public void setWelcomeScreen(WelcomeScreen welcomeScreen) {
		this.welcomeScreen = welcomeScreen;
	}

}
