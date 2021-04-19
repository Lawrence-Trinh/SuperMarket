package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import control.EmployeeLoginController;

public class INeedAssistanceLogin extends JFrame {

	private JPanel contentPane, mainPanel;
	private JLabel idLabel, pinLabel, titleLabel, errorMessage;
	private JButton cancelBtn, loginBtn;
	private JTextField textField;
	private JPasswordField passwordField;
//	private EmployeeLoginController employeeLoginController = new EmployeeLoginController(this);

	public INeedAssistanceLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(new Dimension(400, 250));
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		setAlwaysOnTop(true);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 1200, 750);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);

		titleLabel = new JLabel("Employee Login");
		titleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		titleLabel.setBounds(110, 5, 195, 50);
		mainPanel.add(titleLabel);

		idLabel = new JLabel("ID");
		idLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		idLabel.setBounds(100, 65, 30, 50);
		mainPanel.add(idLabel);

		textField = new JTextField();
		textField.setBounds(140, 72, 170, 37);
		mainPanel.add(textField);

		pinLabel = new JLabel("PIN");
		pinLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		pinLabel.setBounds(88, 115, 45, 50);
		mainPanel.add(pinLabel);

		passwordField = new JPasswordField();
		passwordField.setBounds(140, 122, 170, 37);
		mainPanel.add(passwordField);

		errorMessage = new JLabel("Login Failed. Please try again.");
		errorMessage.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		errorMessage.setForeground(Color.RED);
		errorMessage.setBounds(88, 155, 235, 50);
		errorMessage.setVisible(false);
		mainPanel.add(errorMessage);

		cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelBtn.setBounds(10, 200, 75, 35);
		mainPanel.add(cancelBtn);

		loginBtn = new JButton("Login");
//		This part should be in controller
		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = textField.getText();
				char[] pw = passwordField.getPassword();
//				employeeLoginController.checkCredentials(id, pw);
			}
		});
		loginBtn.setBounds(310, 200, 75, 35);
		mainPanel.add(loginBtn);

	}

	public void setErrorMessageVisible(Boolean value) {
		errorMessage.setVisible(!value);
	}

}
