package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import control.EmployeeLoginController;
import control.ItemController;
import model.people.Customer;

public class CustomerCheckoutPanel extends JFrame {

	private JPanel contentPane, mainPanel, enterItemIDPanel, enterItemNamePanel, selectItemPanel;
	private JButton logoutBtn, finishAndPayBtn, removeItemBtn, requireAssistanceBtn, enterItemIDBtn, enterItemNameBtn,
			selectItemBtn;
	private JTextField idTextField, nameTextField, weightTextField, quantityTextField;
	private JLabel welcomeLbl, errorMessageID, errorMessageName, errorMessageSelect, totalLabel;
	private Customer customer;
	private WelcomeScreen welcomeScreen;
	private JTable itemList, selectItemList;
	private DefaultTableModel tableModel, selectItemTableModel;
	ItemController itemController;

	public CustomerCheckoutPanel(Customer customer) {
		this.customer = customer;
		this.itemController = new ItemController(this, customer);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);

		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(30, 144, 255));
		mainPanel.setBounds(0, 0, 1200, 750);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);

		topBotPanel();
		leftSection();
		rightSection();
		enterItemIDPanel();
		enterItemNamePanel();
		selectItemPanel();

	}

	public void topBotPanel() {
		JPanel dateTimePanel = new JPanel();
		dateTimePanel.setBackground(new Color(102, 102, 102));
		dateTimePanel.setForeground(new Color(102, 102, 102));
		dateTimePanel.setBounds(0, 0, 1200, 50);
		mainPanel.add(dateTimePanel);
		dateTimePanel.setLayout(null);

		welcomeLbl = new JLabel("Welcome, " + customer.getUserFName());
		welcomeLbl.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		welcomeLbl.setForeground(Color.WHITE);
		welcomeLbl.setBounds(18, 8, 234, 30);
		dateTimePanel.add(welcomeLbl);

		// Need to fix so the time changes value overtime. Integrate ClockPanel if you
		// know how
		String date = new SimpleDateFormat("[dd/MM/yyyy] [hh:mm:ss]").format(new Date());
		JLabel labelTime = new JLabel(date);
		labelTime.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		labelTime.setForeground(Color.WHITE);
		labelTime.setBounds(975, 9, 220, 30);
		dateTimePanel.add(labelTime);

		JPanel assisstancePanel = new JPanel();
		assisstancePanel.setForeground(new Color(102, 102, 102));
		assisstancePanel.setBackground(new Color(102, 102, 102));
		assisstancePanel.setBounds(0, 678, 1200, 50);
		mainPanel.add(assisstancePanel);
		assisstancePanel.setLayout(null);

		requireAssistanceBtn = new JButton("I NEED ASSISTANCE");
		requireAssistanceBtn.setBackground(new Color(102, 102, 102));
		requireAssistanceBtn.setOpaque(true);
		requireAssistanceBtn.setBounds(970, 7, 219, 38);
		requireAssistanceBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EmployeeLoginController employeeLoginController = new EmployeeLoginController(
						getCustomerCheckoutPanel());
				JLabel idLabel = new JLabel("Username:");
				JTextField id = new JTextField();

				JLabel pwLabel = new JLabel("Password:");
				JPasswordField pw = new JPasswordField();

				Object[] array = { idLabel, id, pwLabel, pw };
				int response;

				do {
					response = JOptionPane.showConfirmDialog(null, array, "Employee Login",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

					if (response == JOptionPane.YES_OPTION) {
						if (employeeLoginController.checkCredentials(id.getText(), pw.getPassword())) {
							employeeLogin();
							break;
						} else {
							JOptionPane.showMessageDialog(null, "Login Failed. Please try again.");
							id.setText("");
							pw.setText("");
						}
					}
				} while (response != JOptionPane.CANCEL_OPTION && response != JOptionPane.CLOSED_OPTION);
			}
		});
		assisstancePanel.add(requireAssistanceBtn);

		JButton cancelOrderBtn = new JButton("CANCEL ORDER");
		cancelOrderBtn.setOpaque(true);
		cancelOrderBtn.setBackground(new Color(102, 102, 102));
		cancelOrderBtn.setBounds(720, 7, 187, 38);
		cancelOrderBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int cancelResp = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel your order?",
						"Cancel Order", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
				if (cancelResp == JOptionPane.YES_OPTION) {
					employeeLogout();
					welcomeScreen.setVisible(true);
					dispose();
					validate();
					revalidate();
				}
			}
		});
		assisstancePanel.add(cancelOrderBtn);

		logoutBtn = new JButton("LOGOUT");
		logoutBtn.setOpaque(true);
		logoutBtn.setBackground(new Color(102, 102, 102));
		logoutBtn.setBounds(18, 7, 150, 38);
		logoutBtn.setVisible(false);
		logoutBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int cancelResp = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout",
						JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
				if (cancelResp == JOptionPane.YES_OPTION) {
					employeeLogout();
				}
			}
		});
		assisstancePanel.add(logoutBtn);
	}

	public void leftSection() {
		JPanel imagePanel = new JPanel();
		imagePanel.setBounds(66, 90, 471, 158);
		mainPanel.add(imagePanel);

		JPanel itemListPanel = new JPanel();
		itemListPanel.setBounds(66, 283, 471, 357);
		itemListPanel.setLayout(null);
		itemListPanel.setBackground(Color.WHITE);
		mainPanel.add(itemListPanel);

		tableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		itemList = new JTable(tableModel);
		itemList.setBounds(25, 0, 420, 315);
		itemList.setFocusable(false);
		itemList.setRowSelectionAllowed(false);
		itemListPanel.add(itemList);

		tableModel.addColumn("ITEM");
		tableModel.addColumn("QUANTITY");
		tableModel.addColumn("PRICE");
		tableModel.addRow(new Object[] { "ITEM", "QUANTITY", "PRICE" });

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		itemList.getColumnModel().getColumn(0).setPreferredWidth(245);
		itemList.getColumnModel().getColumn(1).setPreferredWidth(100);
		itemList.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		itemList.getColumnModel().getColumn(2).setPreferredWidth(100);
		itemList.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

		JLabel totalTitleLabel = new JLabel("Total");
		totalTitleLabel.setBounds(10, 330, 61, 16);
		itemListPanel.add(totalTitleLabel);

		totalLabel = new JLabel("$00.00");
		totalLabel.setBounds(375, 330, 61, 16);
		itemListPanel.add(totalLabel);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 311, 471, 12);
		itemListPanel.add(separator);
		separator.setBackground(Color.BLACK);
		separator.setForeground(Color.BLACK);
	}

	public void rightSection() {
		JLabel grocerySystemTitle = new JLabel("Kostco Market");
		grocerySystemTitle.setForeground(new Color(255, 255, 255));
		grocerySystemTitle.setFont(new Font("Lucida Grande", Font.BOLD, 25));
		grocerySystemTitle.setBounds(806, 96, 188, 70);
		mainPanel.add(grocerySystemTitle);

		enterItemIDBtn = new JButton("Enter Item ID and Quantity");
		enterItemIDBtn.setBounds(751, 320, 298, 43);
		enterItemIDBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				enterItemIDPanel.setVisible(true);
				displayButtons(false);
				idTextField.setText("");
				quantityTextField.setText("");
			}
		});
		mainPanel.add(enterItemIDBtn);

		enterItemNameBtn = new JButton("Enter Item Name and Weight");
		enterItemNameBtn.setBounds(751, 390, 298, 43);
		enterItemNameBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				enterItemNamePanel.setVisible(true);
				displayButtons(false);
				nameTextField.setText("");
				weightTextField.setText("");
			}
		});
		mainPanel.add(enterItemNameBtn);

		selectItemBtn = new JButton("Select Item");
		selectItemBtn.setBounds(751, 460, 298, 43);
		selectItemBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectItemPanel.setVisible(true);
				displayButtons(false);
			}
		});
		mainPanel.add(selectItemBtn);

		finishAndPayBtn = new JButton("Finish and Pay");
		finishAndPayBtn.setBounds(751, 530, 298, 43);
		mainPanel.add(finishAndPayBtn);

		removeItemBtn = new JButton("Remove Item");
		removeItemBtn.setBounds(751, 530, 298, 43);
		removeItemBtn.setVisible(false);
		removeItemBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (itemList.getSelectedRow() != -1 && itemList.getSelectedRow() != 0) {
					Object item = tableModel.getValueAt(itemList.getSelectedRow(), 0);
					itemController.removeItem(item.toString(), itemList.getSelectedRow());
				}
			}
		});
		mainPanel.add(removeItemBtn);
	}

	public void enterItemIDPanel() {
		enterItemIDPanel = new JPanel();
		enterItemIDPanel.setBounds(701, 329, 398, 265);
		enterItemIDPanel.setBackground(Color.WHITE);
		enterItemIDPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		enterItemIDPanel.setLayout(null);
		enterItemIDPanel.setVisible(false);
		mainPanel.add(enterItemIDPanel);

		JLabel enterItemLbl = new JLabel("Enter Item ID and Quantity");
		enterItemLbl.setBounds(47, 25, 304, 30);
		enterItemLbl.setFont(new Font("Lucida Grande", Font.BOLD, 22));
		enterItemIDPanel.add(enterItemLbl);

		errorMessageID = new JLabel();
		errorMessageID.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		errorMessageID.setForeground(Color.RED);
		errorMessageID.setBounds(25, 168, 350, 30);
		errorMessageID.setHorizontalAlignment(JLabel.CENTER);
		errorMessageID.setVisible(false);
		enterItemIDPanel.add(errorMessageID);

		JLabel idLbl = new JLabel("ID", SwingConstants.CENTER);
		idLbl.setBounds(48, 80, 76, 22);
		idLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		enterItemIDPanel.add(idLbl);

		idTextField = new JTextField();
		idTextField.setBounds(149, 75, 163, 32);
		idTextField.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				errorMessageID.setVisible(false);
			}

			public void focusLost(java.awt.event.FocusEvent evt) {
				idTextField.setText(idTextField.getText().toUpperCase());
			}
		});
		enterItemIDPanel.add(idTextField);

		JLabel quantityLbl = new JLabel("Quantity", SwingConstants.CENTER);
		quantityLbl.setBounds(48, 132, 76, 22);
		quantityLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		enterItemIDPanel.add(quantityLbl);

		quantityTextField = new JTextField();
		quantityTextField.setBounds(149, 127, 163, 32);
		quantityTextField.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				errorMessageID.setVisible(false);
			}
		});
		enterItemIDPanel.add(quantityTextField);

		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setBounds(25, 207, 122, 38);
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				enterItemIDPanel.setVisible(false);
				displayButtons(true);
				errorMessageID.setVisible(false);
			}
		});
		enterItemIDPanel.add(cancelBtn);

		JButton addBtn = new JButton("Add");
		addBtn.setBounds(251, 207, 122, 38);
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = null;
				int quantity = 0;
				boolean error = false;
				try {
					id = idTextField.getText();
					quantity = Integer.parseInt(quantityTextField.getText());
				} catch (NumberFormatException e1) {
					System.err.println(e1);
					errorMessageID.setText("Please enter all information correctly");
					errorMessageID.setVisible(true);
					error = true;
				}

				if (!error) {
					if (itemController.addItemID(id, quantity)) {
						errorMessageID.setVisible(false);
						enterItemIDPanel.setVisible(false);
						displayButtons(true);
					}
				}
			}
		});
		enterItemIDPanel.add(addBtn);
	}

	public void enterItemNamePanel() {
		enterItemNamePanel = new JPanel();
		enterItemNamePanel.setBounds(701, 329, 398, 265);
		enterItemNamePanel.setBackground(Color.WHITE);
		enterItemNamePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		enterItemNamePanel.setLayout(null);
		enterItemNamePanel.setVisible(false);
		mainPanel.add(enterItemNamePanel);

		JLabel enterItemLbl = new JLabel("Enter Item Name and Weight");
		enterItemLbl.setBounds(37, 25, 324, 30);
		enterItemLbl.setFont(new Font("Lucida Grande", Font.BOLD, 22));
		enterItemNamePanel.add(enterItemLbl);

		errorMessageName = new JLabel();
		errorMessageName.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		errorMessageName.setForeground(Color.RED);
		errorMessageName.setBounds(25, 168, 350, 30);
		errorMessageName.setHorizontalAlignment(JLabel.CENTER);
		errorMessageName.setVisible(false);
		enterItemNamePanel.add(errorMessageName);

		JLabel nameLbl = new JLabel("Name", SwingConstants.CENTER);
		nameLbl.setBounds(48, 80, 76, 22);
		nameLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		enterItemNamePanel.add(nameLbl);

		nameTextField = new JTextField();
		nameTextField.setBounds(149, 75, 163, 32);
		nameTextField.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				errorMessageName.setVisible(false);
			}
		});
		enterItemNamePanel.add(nameTextField);

		JLabel weightLbl = new JLabel("Weight", SwingConstants.CENTER);
		weightLbl.setBounds(48, 132, 76, 22);
		weightLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		enterItemNamePanel.add(weightLbl);

		weightTextField = new JTextField();
		weightTextField.setBounds(149, 127, 163, 32);
		weightTextField.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				errorMessageName.setVisible(false);
			}
		});
		enterItemNamePanel.add(weightTextField);

		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setBounds(25, 207, 122, 38);
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				enterItemNamePanel.setVisible(false);
				displayButtons(true);
				errorMessageName.setVisible(false);
			}
		});
		enterItemNamePanel.add(cancelBtn);

		JButton addBtn = new JButton("Add");
		addBtn.setBounds(251, 207, 122, 38);
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean error = false;
				String name = null;
				double weight = 0;
				try {
					name = nameTextField.getText();
					weight = Double.parseDouble(weightTextField.getText());
				} catch (NumberFormatException e1) {
					System.err.println(e1);
					errorMessageName.setText("Please enter all information correctly");
					errorMessageName.setVisible(true);
					error = true;
				}

				if (!error) {
					itemController.addItemName(name, weight);
					errorMessageName.setVisible(false);
					enterItemNamePanel.setVisible(false);
					displayButtons(true);
				}
			}
		});
		enterItemNamePanel.add(addBtn);
	}

	public void selectItemPanel() {
		selectItemPanel = new JPanel();
		selectItemPanel.setBounds(701, 200, 398, 442);
		selectItemPanel.setBackground(Color.WHITE);
		selectItemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		selectItemPanel.setLayout(null);
		selectItemPanel.setVisible(false);
		mainPanel.add(selectItemPanel);

		errorMessageSelect = new JLabel();
		errorMessageSelect.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		errorMessageSelect.setForeground(Color.RED);
		errorMessageSelect.setBounds(25, 350, 350, 30);
		errorMessageSelect.setHorizontalAlignment(JLabel.CENTER);
		errorMessageSelect.setVisible(false);
		selectItemPanel.add(errorMessageSelect);

		JTextArea itemTextArea = new JTextArea();
		itemTextArea.setEditable(false);

		selectItemTableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		selectItemList = new JTable(selectItemTableModel);
		selectItemList.setBounds(25, 25, 348, 275);
		selectItemPanel.add(selectItemList);

		selectItemTableModel.addColumn("ITEM");
		selectItemTableModel.addColumn("PRICE");
		selectItemTableModel.addRow(new Object[] { "ITEM", "PRICE" });

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		selectItemList.getColumnModel().getColumn(0).setPreferredWidth(225);
		selectItemList.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

		JLabel quantityLbl = new JLabel("Quantity");
		quantityLbl.setBounds(30, 320, 76, 22);
		quantityLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		selectItemPanel.add(quantityLbl);

		JTextField quantityTextField = new JTextField();
		quantityTextField.setBounds(125, 315, 60, 32);
		quantityTextField.setText("1");
		quantityTextField.setHorizontalAlignment(JTextField.CENTER);
		selectItemPanel.add(quantityTextField);

		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setBounds(25, 381, 122, 38);
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectItemPanel.setVisible(false);
				displayButtons(true);
			}
		});
		selectItemPanel.add(cancelBtn);

		JButton addBtn = new JButton("Add");
		addBtn.setBounds(251, 381, 122, 38);
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean error = false;
				int quantity = 0;
				try {
					quantity = Integer.parseInt(quantityTextField.getText());
				} catch (NumberFormatException e1) {
					System.err.println(e1);
					if (quantity <= 0) {
						errorMessageSelect.setText("Quantity must be positive");
						errorMessageSelect.setVisible(true);
					}
					error = true;
				}

				if (!error) {
					if (selectItemList.getSelectedRow() != -1 && selectItemList.getSelectedRow() != 0) {
						int row = selectItemList.getSelectedRow();
						Object item = selectItemTableModel.getValueAt(row, 0);
						itemController.addItemSelected(item.toString(), quantity);
						errorMessageSelect.setVisible(false);
						selectItemPanel.setVisible(false);
						displayButtons(true);
					}
				}

			}
		});
		selectItemPanel.add(addBtn);
		

		itemController.displayAllItems();
	}

	public void employeeLogin() {
		itemList.setFocusable(true);
		itemList.setRowSelectionAllowed(true);
		logoutBtn.setVisible(true);
		finishAndPayBtn.setVisible(false);
		removeItemBtn.setVisible(true);
		requireAssistanceBtn.setVisible(false);
		mainPanel.setBackground(new Color(25, 150, 125));
	}

	public void employeeLogout() {
		itemList.setFocusable(false);
		itemList.setRowSelectionAllowed(false);
		logoutBtn.setVisible(false);
		removeItemBtn.setVisible(false);
		enterItemIDBtn.setVisible(true);
		enterItemNameBtn.setVisible(true);
		selectItemBtn.setVisible(true);
		;
		finishAndPayBtn.setVisible(true);
		requireAssistanceBtn.setVisible(true);
		welcomeLbl.setText("Welcome, " + customer.getUserFName());
		mainPanel.setBackground(new Color(30, 144, 255));
	}

	public void displayButtons(Boolean display) {
		enterItemIDBtn.setVisible(display);
		enterItemNameBtn.setVisible(display);
		selectItemBtn.setVisible(display);
		finishAndPayBtn.setVisible(display);
		if (logoutBtn.isVisible()) {
			removeItemBtn.setVisible(display);
			finishAndPayBtn.setVisible(false);
		}
	}

	public void setWelcomeScreen(WelcomeScreen welcomeScreen) {
		this.welcomeScreen = welcomeScreen;
	}

	public CustomerCheckoutPanel getCustomerCheckoutPanel() {
		return this;
	}

	public JLabel getWelcomeLbl() {
		return welcomeLbl;
	}

	public JLabel getErrorMessageID() {
		return errorMessageID;
	}

	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	public DefaultTableModel getSelectItemTableModel() {
		return selectItemTableModel;
	}

	public JLabel getTotalLabel() {
		return totalLabel;
	}

}
