package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import control.LoginController;

public class LoginScreen extends JFrame {

	private JPanel contentPane, mainPanel;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel errorMessage;
	private LoginController loginController;
	private WelcomeScreen welcomeScreen;

	public LoginScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 750);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(30, 144, 255));
		mainPanel.setBounds(0, 0, 1200, 750);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);

		topBotPanel();
		centerPanel();

	}

	public void topBotPanel() {
		JPanel dateTimePanel = new JPanel();
		dateTimePanel.setBackground(new Color(102, 102, 102));
		dateTimePanel.setForeground(new Color(102, 102, 102));
		dateTimePanel.setBounds(0, 0, 1200, 50);
		mainPanel.add(dateTimePanel);
		dateTimePanel.setLayout(null);

		JLabel loginTitle = new JLabel("LOGIN");
		loginTitle.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		loginTitle.setForeground(Color.WHITE);
		loginTitle.setBounds(566, 8, 67, 30);
		dateTimePanel.add(loginTitle);

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

		JButton requireAssistanceBtn = new JButton("I NEED ASSISTANCE");
		requireAssistanceBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(null, "An employee is on their way to assist you.", "Assistance Required",
						JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE);
			}
		});
		requireAssistanceBtn.setBackground(new Color(102, 102, 102));
		requireAssistanceBtn.setOpaque(true);
		requireAssistanceBtn.setBounds(970, 7, 219, 38);
		assisstancePanel.add(requireAssistanceBtn);

		JButton backBtn = new JButton("BACK");
		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearField();
				errorMessage.setVisible(false);
				getLoginScreen().setVisible(false);
				welcomeScreen.setVisible(true);
			}
		});
		backBtn.setOpaque(true);
		backBtn.setBackground(new Color(102, 102, 102));
		backBtn.setBounds(10, 7, 142, 38);
		assisstancePanel.add(backBtn);
	}

	public void centerPanel() {
		JLabel grocerySystemTitle = new JLabel("Welcome to Kostco");
		grocerySystemTitle.setForeground(new Color(255, 255, 255));
		grocerySystemTitle.setFont(new Font("Lucida Grande", Font.BOLD, 25));
		grocerySystemTitle.setBounds(485, 88, 245, 65);
		mainPanel.add(grocerySystemTitle);

		JLabel pinLabel = new JLabel("PIN");
		pinLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		pinLabel.setForeground(new Color(255, 255, 255));
		pinLabel.setBounds(440, 277, 35, 43);
		mainPanel.add(pinLabel);

		JLabel idLabel = new JLabel("ID");
		idLabel.setForeground(Color.WHITE);
		idLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		idLabel.setBounds(450, 215, 25, 50);
		mainPanel.add(idLabel);

		JButton loginBtn = new JButton("Login");
		JRootPane rootPane = contentPane.getRootPane();
		rootPane.setDefaultButton(loginBtn);
		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = textField.getText();
				char[] pw = passwordField.getPassword();
				loginController.setWelcomeScreen(welcomeScreen);
				loginController.checkCredentials(id, pw);
			}
		});
		loginBtn.setBounds(530, 350, 141, 56);
		mainPanel.add(loginBtn);

		errorMessage = new JLabel("Login Failed. Please try again.");
		errorMessage.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		errorMessage.setForeground(Color.RED);
		errorMessage.setBounds(457, 450, 286, 50);
		errorMessage.setVisible(false);
		mainPanel.add(errorMessage);

		textField = new JTextField();
		textField.setBounds(478, 222, 257, 37);
		mainPanel.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(478, 283, 257, 37);
		mainPanel.add(passwordField);
	}

	public void setErrorMessageVisible(Boolean value) {
		errorMessage.setVisible(!value);
	}

	public JTextField getIDTextField() {
		return textField;
	}

	public void setLoginController(LoginController loginController) {
		this.loginController = loginController;
	}

	public void setWelcomeScreen(WelcomeScreen welcomeScreen) {
		this.welcomeScreen = welcomeScreen;
	}

	public LoginScreen getLoginScreen() {
		return this;
	}

	public void clearField() {
		textField.setText("");
		passwordField.setText("");
	}

}
