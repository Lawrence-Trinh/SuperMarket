package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import control.AddProductController;
import control.AddSupplierController;
import model.people.Manager;

public class ManagerPanel extends JFrame {

	private JPanel contentPane, parentPanel, sideBarPanel, dashboardPanel, todaySalesPanel, productsPanel,
			suppliersPanel, salesPanel, reportPanel, viewSupplier;

	public static final String DOLLAR_SIGN = "$";

	private AddProductController addProduct = new AddProductController(this);
	private AddSupplierController addSupplier = new AddSupplierController(this);

	private JTable table;
	private WelcomeScreen welcomeScreen;
	private DefaultTableModel model;
	private Manager manager;

	private JTextField productNamefield;
	private JTextField productPricefield;
	private JTextField productQuantityField;
	private JTextField productiDfield;
	private JTextField supplierIDField;
	private JTextField supplierCompanyNameField;
	private JTextField supplierContactNoField;
	private JTextField supplierEmailField;
	private JTextField supplierLocationField;
	private JTable supplierTable;
	private JTable salesTable;
	private JTable reportTable;

	public ManagerPanel(Manager manager) {
		this.manager = manager;
		setTitle("SEF Assignment");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);

		parentPanel = new JPanel();
		parentPanel.setBounds(164, 0, 1036, 750);
		contentPane.add(parentPanel);
		parentPanel.setLayout(new CardLayout(0, 0));

		sideBarPanel();
		dashboardPanel();
		productsPanel();
		salesPanel();
		suppliersPanel();
		reportPanel();
		viewSupplier();

	}

	private void sideBarPanel() {
		sideBarPanel = new JPanel();
		sideBarPanel.setBackground(Color.DARK_GRAY);
		sideBarPanel.setBounds(0, 0, 174, 750);
		contentPane.add(sideBarPanel);
		sideBarPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("", SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(ManagerPanel.class.getResource("/images/Sef4.png")));
		lblNewLabel.setBounds(50, 28, 74, 72);
		sideBarPanel.add(lblNewLabel);

		JLabel dashboardLabel = new JLabel("DASHBOARD", SwingConstants.CENTER);
		dashboardLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dashboardLabel.setForeground(Color.WHITE);
		dashboardLabel.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
		dashboardLabel.setBounds(0, 163, 164, 37);
		sideBarPanel.add(dashboardLabel);
		dashboardLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				parentPanel.removeAll();
				parentPanel.add(dashboardPanel);
				parentPanel.repaint();
				parentPanel.revalidate();
			}
		});

		JLabel productsLabel = new JLabel("PRODUCTS", SwingConstants.CENTER);
		productsLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		productsLabel.setForeground(Color.WHITE);
		productsLabel.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
		productsLabel.setBounds(0, 263, 164, 37);
		sideBarPanel.add(productsLabel);
		productsLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				parentPanel.removeAll();
				parentPanel.add(productsPanel);
				parentPanel.repaint();
				parentPanel.revalidate();

			}
		});

		JLabel salesLabel = new JLabel("SALES", SwingConstants.CENTER);
		salesLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		salesLabel.setForeground(new Color(255, 255, 255));
		salesLabel.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
		salesLabel.setBounds(0, 363, 164, 37);
		sideBarPanel.add(salesLabel);
		salesLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				parentPanel.removeAll();
				parentPanel.add(salesPanel);
				parentPanel.repaint();
				parentPanel.revalidate();
			}
		});

		JLabel supplierLabel = new JLabel("SUPPLIER", SwingConstants.CENTER);
		supplierLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		supplierLabel.setForeground(Color.WHITE);
		supplierLabel.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
		supplierLabel.setBounds(0, 463, 164, 37);
		sideBarPanel.add(supplierLabel);
		supplierLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				parentPanel.removeAll();
				parentPanel.add(suppliersPanel);
				parentPanel.repaint();
				parentPanel.revalidate();
			}
		});

		JLabel reportLabel = new JLabel("REPORT", SwingConstants.CENTER);
		reportLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		reportLabel.setForeground(Color.WHITE);
		reportLabel.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
		reportLabel.setBounds(0, 563, 164, 37);
		reportLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				parentPanel.removeAll();
				parentPanel.add(reportPanel);
				parentPanel.repaint();
				parentPanel.revalidate();
			}
		});
		sideBarPanel.add(reportLabel);

		JLabel logoutLbl = new JLabel("LOGOUT", SwingConstants.CENTER);
		logoutLbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		logoutLbl.setForeground(Color.WHITE);
		logoutLbl.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 20));
		logoutLbl.setBounds(0, 663, 164, 37);
		logoutLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int cancelResp = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout",
						JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
				if (cancelResp == JOptionPane.YES_OPTION) {
					welcomeScreen.setVisible(true);
					dispose();
					validate();
					revalidate();
				}
			}
		});

		sideBarPanel.add(logoutLbl);
		reportLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				parentPanel.removeAll();
				parentPanel.add(reportPanel);
				parentPanel.repaint();
				parentPanel.revalidate();
			}
		});

	}

	private void dashboardPanel() {
		dashboardPanel = new JPanel();
		dashboardPanel.setBackground(new Color(0, 128, 128));
		parentPanel.add(dashboardPanel);
		dashboardPanel.setLayout(null);

		JPanel welcomeDateTimePanel = new JPanel();
		welcomeDateTimePanel.setBackground(new Color(102, 102, 102));
		welcomeDateTimePanel.setBounds(0, 0, 1036, 50);
		dashboardPanel.add(welcomeDateTimePanel);
		welcomeDateTimePanel.setLayout(null);

		JLabel welcomeLbl = new JLabel("Welcome, " + manager.getUserFName());
		welcomeLbl.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		welcomeLbl.setForeground(Color.WHITE);
		welcomeLbl.setBounds(18, 8, 400, 30);
		welcomeDateTimePanel.add(welcomeLbl);

		// Need to fix so the time changes value overtime. Integrate ClockPanel if you
		// know how
		String date = new SimpleDateFormat("[dd/MM/yyyy] [hh:mm:ss]").format(new Date());
		JLabel labelTime = new JLabel(date);
		labelTime.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		labelTime.setForeground(Color.WHITE);
		labelTime.setBounds(811, 9, 220, 30);
		welcomeDateTimePanel.add(labelTime);

		todaySalesPanel = new JPanel();
		todaySalesPanel.setBounds(44, 96, 396, 229);
		dashboardPanel.add(todaySalesPanel);
		todaySalesPanel.setLayout(null);

		JLabel dashboardLbl = new JLabel("Dashboard");
		dashboardLbl.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		dashboardLbl.setBounds(44, 52, 174, 32);
		dashboardPanel.add(dashboardLbl);

		JPanel todaySalesBox = new JPanel();
		todaySalesBox.setBackground(new Color(0, 128, 0));
		todaySalesBox.setBounds(30, 55, 326, 42);
		todaySalesPanel.add(todaySalesBox);
		todaySalesBox.setLayout(null);

		JLabel temporaySalesLbl = new JLabel("$43244");
		temporaySalesLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		temporaySalesLbl.setForeground(new Color(255, 255, 255));
		temporaySalesLbl.setBounds(132, 5, 123, 31);
		todaySalesBox.add(temporaySalesLbl);

		JLabel lblTodaysSales = new JLabel("Todays Sales");
		lblTodaysSales.setBounds(142, 24, 95, 19);
		todaySalesPanel.add(lblTodaysSales);

		JPanel grossProfitBox = new JPanel();
		grossProfitBox.setBackground(new Color(0, 128, 0));
		grossProfitBox.setBounds(30, 157, 147, 30);
		todaySalesPanel.add(grossProfitBox);

		JLabel label_1 = new JLabel("$43244");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		grossProfitBox.add(label_1);

		JPanel grossProfit1Box = new JPanel();
		grossProfit1Box.setBackground(new Color(0, 128, 0));
		grossProfit1Box.setBounds(209, 157, 147, 30);
		todaySalesPanel.add(grossProfit1Box);

		JLabel label_2 = new JLabel("$43244");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		grossProfit1Box.add(label_2);

		JLabel grossProfit = new JLabel("Gross Profit");
		grossProfit.setBounds(72, 130, 95, 16);
		todaySalesPanel.add(grossProfit);

		JLabel lblNetProfit = new JLabel("Net Profit");
		lblNetProfit.setBounds(249, 130, 95, 16);
		todaySalesPanel.add(lblNetProfit);

		JPanel topSellingProductBox = new JPanel();
		topSellingProductBox.setBounds(44, 356, 396, 351);
		dashboardPanel.add(topSellingProductBox);
		topSellingProductBox.setLayout(null);

		JLabel topSellingProductsLbl = new JLabel("Top Selling Products");
		topSellingProductsLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		topSellingProductsLbl.setBounds(6, 6, 352, 51);
		topSellingProductBox.add(topSellingProductsLbl);

		JLabel lblNewLabel_2 = new JLabel("1.  Milk");
		lblNewLabel_2.setBounds(6, 86, 136, 26);
		topSellingProductBox.add(lblNewLabel_2);

		JLabel lblEggs = new JLabel("2.  Wholemeal Bread");
		lblEggs.setBounds(6, 139, 136, 26);
		topSellingProductBox.add(lblEggs);

		JLabel lblCoke = new JLabel("3.  Coke");
		lblCoke.setBounds(6, 205, 136, 26);
		topSellingProductBox.add(lblCoke);

		JLabel lblSugar = new JLabel("4.  Sugar");
		lblSugar.setBounds(6, 271, 136, 26);
		topSellingProductBox.add(lblSugar);

		JLabel lblIcecream = new JLabel("5.  Icecream");
		lblIcecream.setBounds(6, 319, 136, 26);
		topSellingProductBox.add(lblIcecream);

		JPanel dashboardDisplaySomething = new JPanel();
		dashboardDisplaySomething.setBounds(603, 96, 363, 335);
		dashboardPanel.add(dashboardDisplaySomething);
		dashboardDisplaySomething.setLayout(null);

		JLabel employeesWorkingLbl = new JLabel("Employee Roster");
		employeesWorkingLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		employeesWorkingLbl.setBounds(6, 6, 352, 51);
		dashboardDisplaySomething.add(employeesWorkingLbl);

		JLabel lblPeter = new JLabel("1.  Peter - Support Staff");
		lblPeter.setBounds(16, 70, 207, 26);
		dashboardDisplaySomething.add(lblPeter);

		JLabel lblJackson = new JLabel("2.  Jackson - Warehouse Staff");
		lblJackson.setBounds(16, 128, 241, 26);
		dashboardDisplaySomething.add(lblJackson);

		JLabel lblKonrad = new JLabel("3.  Konrad - Sales Staff");
		lblKonrad.setBounds(16, 189, 241, 26);
		dashboardDisplaySomething.add(lblKonrad);

		JLabel lblAdrian = new JLabel("4. Adrian - Manager");
		lblAdrian.setBounds(16, 247, 136, 26);
		dashboardDisplaySomething.add(lblAdrian);

		JLabel lblCharlie = new JLabel("5. Charlie - Manager");
		lblCharlie.setBounds(16, 303, 136, 26);
		dashboardDisplaySomething.add(lblCharlie);

		JPanel dashBoardDisplaySomething2 = new JPanel();
		dashBoardDisplaySomething2.setBounds(603, 515, 363, 192);
		dashboardPanel.add(dashBoardDisplaySomething2);
		dashBoardDisplaySomething2.setLayout(null);

		JLabel customerLoggedInLbl = new JLabel("Customers currently logged in:");
		customerLoggedInLbl.setBounds(6, 6, 351, 36);
		dashBoardDisplaySomething2.add(customerLoggedInLbl);

		JLabel customer1lbl = new JLabel("1.  c3605044");
		customer1lbl.setBounds(6, 54, 132, 25);
		dashBoardDisplaySomething2.add(customer1lbl);

		JLabel lblC = new JLabel("2.  c3605044");
		lblC.setBounds(6, 101, 132, 25);
		dashBoardDisplaySomething2.add(lblC);

		JLabel lblC_1 = new JLabel("3.  c3605044");
		lblC_1.setBounds(6, 146, 132, 25);
		dashBoardDisplaySomething2.add(lblC_1);
	}

	private void productsPanel() {

		productsPanel = new JPanel();
		productsPanel.setBackground(new Color(0, 128, 128));
		parentPanel.add(productsPanel);
		productsPanel.setLayout(null);

		JLabel productsTitle = new JLabel("Products Panel");
		productsTitle.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		productsTitle.setBounds(19, 6, 188, 38);
		productsPanel.add(productsTitle);

		JPanel productPanelSection = new JPanel();
		productPanelSection.setBackground(new Color(128, 128, 128));
		productPanelSection.setBounds(6, 56, 1030, 106);
		productsPanel.add(productPanelSection);
		productPanelSection.setLayout(null);

		JLabel IDLbl = new JLabel("ID");
		IDLbl.setBounds(22, 6, 99, 16);
		productPanelSection.add(IDLbl);

		JLabel producTNmeLbl = new JLabel("Name");
		producTNmeLbl.setBounds(119, 6, 99, 16);
		productPanelSection.add(producTNmeLbl);

		JLabel productPriceLbl = new JLabel("Price");
		productPriceLbl.setBounds(260, 6, 99, 16);
		productPanelSection.add(productPriceLbl);

		JLabel productQuantityLbl = new JLabel("Quantity");
		productQuantityLbl.setBounds(371, 6, 99, 16);
		productPanelSection.add(productQuantityLbl);

		productNamefield = new JTextField();
		productNamefield.setBounds(108, 40, 130, 26);
		productPanelSection.add(productNamefield);
		productNamefield.setColumns(10);

		productPricefield = new JTextField();
		productPricefield.setColumns(10);
		productPricefield.setBounds(250, 40, 99, 26);
		productPanelSection.add(productPricefield);

		productQuantityField = new JTextField();
		productQuantityField.setColumns(10);
		productQuantityField.setBounds(361, 40, 130, 26);
		productPanelSection.add(productQuantityField);

		productiDfield = new JTextField();
		productiDfield.setColumns(10);
		productiDfield.setBounds(22, 40, 72, 26);
		productPanelSection.add(productiDfield);

		JButton AddButton = new JButton("Add");
		JButton updateButton = new JButton("Update");
		updateButton.setBounds(888, 37, 117, 29);
		productPanelSection.add(updateButton);

		JLabel discountLabel = new JLabel("Discount");
		discountLabel.setBounds(536, 6, 99, 16);
		productPanelSection.add(discountLabel);

		JComboBox<String> discountBox = new JComboBox<String>();
		discountBox.addItem(" ");
		discountBox.addItem("10% for 5 items");
		discountBox.addItem("20% for 1 items");
		discountBox.setSelectedIndex(0);

		discountBox.setBounds(519, 41, 99, 27);
		productPanelSection.add(discountBox);

		Object[] row = new Object[6];

		AddButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				row[0] = productiDfield.getText();
				row[1] = productNamefield.getText();
				row[2] = DOLLAR_SIGN + productPricefield.getText();
				row[3] = productQuantityField.getText();
				row[4] = discountBox.getSelectedItem();

				model.addRow(row);

				addProduct.addRemoveModifyProduct(productiDfield.getText(), productNamefield.getText(),
						Double.parseDouble(productPricefield.getText()),
						Integer.parseInt(productQuantityField.getText()), false);

				if (addProduct.restockNeeded() == true) {
					productQuantityField.setText("Restock Needed");
					row[3] = productQuantityField.getText();
				}

			}
		});

		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int getSelectedRow = table.getSelectedRow();

				if (getSelectedRow >= 0) {
					model.setValueAt(productiDfield.getText(), getSelectedRow, 0);
					model.setValueAt(productNamefield.getText(), getSelectedRow, 1);
					model.setValueAt(DOLLAR_SIGN + productPricefield.getText(), getSelectedRow, 2);
					model.setValueAt(productQuantityField.getText(), getSelectedRow, 3);

				}
			}
		});

		AddButton.setBounds(888, 1, 117, 29);
		productPanelSection.add(AddButton);

		JButton removeBtn = new JButton("Remove");
		removeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int getSelectedRow = table.getSelectedRow();
				if (getSelectedRow >= 0) {
					model.removeRow(getSelectedRow);

					addProduct.addRemoveModifyProduct(productiDfield.getText(), productNamefield.getText(),
							Double.parseDouble(productPricefield.getText()),
							Integer.parseInt(productQuantityField.getText()), true);
				}
			}
		});
		removeBtn.setBounds(888, 71, 117, 29);
		productPanelSection.add(removeBtn);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBounds(19, 208, 1008, 460);

		Object[] columns = { "ID", "Name", "Price", "Quantity", "Discount", "Restock Needed" };
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		table.setBackground(Color.LIGHT_GRAY);
		table.setForeground(Color.black);
		Font font = new Font("", 1, 16);
		table.setFont(font);
		table.setRowHeight(30);

		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(24, 240, 938, 432);
		productsPanel.add(pane);

//
	}

	private void salesPanel() {
		DefaultTableModel salesModel = new DefaultTableModel();
		Font font = new Font("", 1, 16);

		salesPanel = new JPanel();
		parentPanel.add(salesPanel, "name_105730085137343");
		salesPanel.setBackground(new Color(0, 128, 128));
		salesPanel.setLayout(null);

		JPanel smallSalesPanel = new JPanel();
		smallSalesPanel.setBackground(new Color(128, 128, 128));
		smallSalesPanel.setBounds(21, 118, 986, 92);
		salesPanel.add(smallSalesPanel);
		smallSalesPanel.setLayout(null);

		JLabel salesLbl = new JLabel("Sales");
		salesLbl.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		salesLbl.setBounds(21, 27, 156, 34);
		salesPanel.add(salesLbl);

		salesTable = new JTable();
		salesTable.setBounds(21, 278, 975, 423);
		salesPanel.add(salesTable);
		salesTable.setModel(salesModel);
		salesTable.setBackground(Color.LIGHT_GRAY);
		salesTable.setForeground(Color.black);
		salesTable.setFont(font);
		salesTable.setRowHeight(30);
		JScrollPane pane = new JScrollPane(salesTable);
		pane.setBounds(21, 246, 986, 444);
		salesPanel.add(pane);

		Object[] salesColumn = { "Most Revenue" };
		salesModel.setColumnIdentifiers(salesColumn);

	}

	private void suppliersPanel() {
		DefaultTableModel supplyModel = new DefaultTableModel();

		suppliersPanel = new JPanel();
		suppliersPanel.setBackground(new Color(0, 128, 128));
		parentPanel.add(suppliersPanel);
		suppliersPanel.setLayout(null);

		JLabel supplierLabel = new JLabel("Supplier Details");
		supplierLabel.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		supplierLabel.setBounds(6, 21, 156, 36);
		suppliersPanel.add(supplierLabel);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.GRAY);
		panel.setBounds(6, 54, 1030, 99);
		suppliersPanel.add(panel);

		JLabel supplierID = new JLabel("ID");
		supplierID.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		supplierID.setBounds(12, 6, 22, 27);
		panel.add(supplierID);

		JLabel supplierCompanyName = new JLabel("Company Name");
		supplierCompanyName.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		supplierCompanyName.setBounds(96, 6, 123, 27);
		panel.add(supplierCompanyName);

		JLabel supplierContactNo = new JLabel("Contact Number");
		supplierContactNo.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		supplierContactNo.setBounds(262, 6, 123, 27);
		panel.add(supplierContactNo);

		JLabel supplierEmail = new JLabel("Email");
		supplierEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		supplierEmail.setBounds(488, 6, 123, 27);
		panel.add(supplierEmail);

		JLabel supplierLocation = new JLabel("Location");
		supplierLocation.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		supplierLocation.setBounds(699, 6, 77, 27);
		panel.add(supplierLocation);

		JButton addBtn = new JButton("Add");

		Object[] row = new Object[5];
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				row[0] = supplierIDField.getText();
				row[1] = supplierCompanyNameField.getText();
				row[2] = supplierContactNoField.getText();
				row[3] = supplierEmailField.getText();
				row[4] = supplierLocationField.getText();

				supplyModel.addRow(row);

//				addProduct.addRemoveModifyProduct(productiDfield.getText(), productNamefield.getText(),
//						Double.parseDouble(productPricefield.getText()),
//						Integer.parseInt(productQuantityField.getText()), true);
//			}

//				Supplier newSupplier = new Supplier(supplierID,"", "", "", companyName, contactNo, email, location);
				addSupplier.addRemoveUpdateSupplier(supplierID.getText(), "", "", "",
						supplierCompanyNameField.getText(), supplierContactNoField.getText(),
						supplierEmailField.getText(), supplierLocationField.getText(), false);

			}
		});
		addBtn.setBounds(894, 7, 117, 29);
		panel.add(addBtn);

		JButton removeBtn = new JButton("Remove");
		removeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int getSelectedRow = supplierTable.getSelectedRow();
				if (getSelectedRow >= 0) {
					supplyModel.removeRow(getSelectedRow);

					addSupplier.addRemoveUpdateSupplier(supplierID.getText(), "", "", "",
							supplierCompanyNameField.getText(), supplierContactNoField.getText(),
							supplierEmailField.getText(), supplierLocationField.getText(), true);

				}

			}
		});
		removeBtn.setBounds(894, 39, 117, 29);
		panel.add(removeBtn);

		JButton updateBtn = new JButton("Update");
		updateBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int getSelectedRow = supplierTable.getSelectedRow();

				if (getSelectedRow >= 0) {
					supplyModel.setValueAt(supplierIDField.getText(), getSelectedRow, 0);
					supplyModel.setValueAt(supplierCompanyNameField.getText(), getSelectedRow, 1);
					supplyModel.setValueAt(supplierContactNoField.getText(), getSelectedRow, 2);
					supplyModel.setValueAt(supplierEmailField.getText(), getSelectedRow, 3);
					supplyModel.setValueAt(supplierLocationField.getText(), getSelectedRow, 4);

					addSupplier.addRemoveUpdateSupplier(supplierID.getText(), "", "", "",
							supplierCompanyNameField.getText(), supplierContactNoField.getText(),
							supplierEmailField.getText(), supplierLocationField.getText(), false);

				}

			}
		});
		updateBtn.setBounds(894, 70, 117, 29);
		panel.add(updateBtn);

		supplierIDField = new JTextField();
		supplierIDField.setBounds(0, 56, 57, 26);
		panel.add(supplierIDField);
		supplierIDField.setColumns(10);

		supplierCompanyNameField = new JTextField();
		supplierCompanyNameField.setColumns(10);
		supplierCompanyNameField.setBounds(84, 56, 144, 26);
		panel.add(supplierCompanyNameField);

		supplierContactNoField = new JTextField();
		supplierContactNoField.setColumns(10);
		supplierContactNoField.setBounds(262, 56, 137, 26);
		panel.add(supplierContactNoField);

		supplierEmailField = new JTextField();
		supplierEmailField.setColumns(10);
		supplierEmailField.setBounds(466, 56, 145, 26);
		panel.add(supplierEmailField);

		supplierLocationField = new JTextField();
		supplierLocationField.setColumns(10);
		supplierLocationField.setBounds(649, 56, 201, 26);
		panel.add(supplierLocationField);

		supplierTable = new JTable();

		supplierTable.setShowHorizontalLines(true);
		supplierTable.setBounds(17, 224, 991, 469);

		Object[] columns = { "ID", "Company Name", "Contact No", "Email", "Location" };
		supplyModel.setColumnIdentifiers(columns);
		supplierTable.setModel(supplyModel);
		supplierTable.setBackground(Color.LIGHT_GRAY);
		supplierTable.setForeground(Color.black);
		Font font = new Font("", 1, 16);
		supplierTable.setFont(font);
		supplierTable.setRowHeight(30);

		JScrollPane pane = new JScrollPane(supplierTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pane.setBounds(17, 190, 992, 518);
		suppliersPanel.add(pane);

	}

	private void reportPanel() {
		DefaultTableModel reportModel = new DefaultTableModel();
		reportPanel = new JPanel();
		reportPanel.setBackground(new Color(0, 128, 128));

		parentPanel.add(reportPanel);
		reportPanel.setLayout(null);

		JLabel lblReport = new JLabel("Report");
		lblReport.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblReport.setBounds(10, 7, 123, 61);
		reportPanel.add(lblReport);

		JPanel smallReportPanel = new JPanel();
		smallReportPanel.setBackground(new Color(128, 128, 128));
		smallReportPanel.setBounds(19, 80, 990, 109);
		reportPanel.add(smallReportPanel);
		smallReportPanel.setLayout(null);

		JButton generateReportBtn = new JButton("Generate Report");
		generateReportBtn.setBounds(6, 37, 153, 29);
		smallReportPanel.add(generateReportBtn);

		reportTable = new JTable();
		reportTable.setBounds(45, 275, 956, 426);
		reportPanel.add(reportTable);

		Object[] columns = { "Total Revenue Today", "Amount of Products Sold Today", "Amount of Customers logged in",
				"Amount of Users saved in the Database" };
		reportModel.setColumnIdentifiers(columns);
		reportTable.setModel(reportModel);
		reportTable.setBackground(Color.LIGHT_GRAY);
		reportTable.setForeground(Color.BLACK);
		Font font = new Font("", 1, 16);
		reportTable.setFont(font);
		reportTable.setRowHeight(30);

		JScrollPane pane = new JScrollPane(reportTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pane.setBounds(21, 239, 988, 469);
		reportPanel.add(pane);

	}

	private void viewSupplier() {
		viewSupplier = new JPanel();
		viewSupplier.setVisible(true);
	}

	public void setWelcomeScreen(WelcomeScreen welcomeScreen) {
		this.welcomeScreen = welcomeScreen;
	}

	public String getProductQuantity() {
		return productQuantityField.getText();
	}
}
