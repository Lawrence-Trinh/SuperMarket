package client;
// Commented these out because it was giving errors

import java.awt.EventQueue;

import control.LoginController;
import model.system.AccountManager;
import model.system.Product;
import model.system.ProductManager;
import model.system.SalesManager;
import view.LoginScreen;
import view.WelcomeScreen;

public class SupermarketClient {	

	public static void main(String[] arg) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				ProductManager pm = new ProductManager();
				AccountManager am = new AccountManager();
				SalesManager sr = new SalesManager();
				try {
					LoginController loginController = new LoginController();
//					loginController.setAccountManager(am);
					
					LoginScreen loginScreen = new LoginScreen();
					loginScreen.setLoginController(loginController);
					
					loginController.setView(loginScreen);
					
					WelcomeScreen welcomeScreen = new WelcomeScreen();
					welcomeScreen.setLoginScreen(loginScreen);
					welcomeScreen.setVisible(true);
					
					// AccountManager DEBUG
//					System.out.println("----------");
//					am.printSize();
//					am.printCustomers();
//					System.out.println("----------");
					
					// ProductManager Debug
					System.out.println("----------");
					pm.printSize();
					pm.printItems();
					System.out.println("----------");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				// Run Code On Exit
				Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			        public void run() {
			        	pm.saveProducts();
						am.saveUsers();	
						sr.saveSales();
			        }
			    }, "Shutdown-thread"));
				
			}
		});

	}

}