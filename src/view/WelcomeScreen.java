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
import javax.swing.JRootPane;
import javax.swing.border.EmptyBorder;

import control.LoginController;

public class WelcomeScreen extends JFrame {

	private JPanel contentPane, mainPanel;
	private LoginScreen loginScreen;

	public WelcomeScreen() {
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
		leftContent();
		rightContent();
	}

	public void topBotPanel() {
		JPanel dateTimePanel = new JPanel();
		dateTimePanel.setBackground(new Color(102, 102, 102));
		dateTimePanel.setForeground(new Color(102, 102, 102));
		dateTimePanel.setBounds(0, 0, 1200, 50);
		mainPanel.add(dateTimePanel);
		dateTimePanel.setLayout(null);

		JLabel welcomeLbl = new JLabel("WELCOME");
		welcomeLbl.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		welcomeLbl.setForeground(Color.WHITE);
		welcomeLbl.setBounds(548, 8, 104, 30);
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
	}

	public void leftContent() {
		JPanel imagePanel = new JPanel();
		imagePanel.setBounds(75, 105, 450, 520);
		mainPanel.add(imagePanel);
	}
	
	public void rightContent() {
		JLabel grocerySystemTitle = new JLabel("Welcome to Kostco");
		grocerySystemTitle.setForeground(new Color(255, 255, 255));
		grocerySystemTitle.setFont(new Font("Lucida Grande", Font.BOLD, 25));
		grocerySystemTitle.setBounds(777, 98, 245, 65);
		mainPanel.add(grocerySystemTitle);
		
		JLabel checkoutDescription = new JLabel(
				"<html><div style='text-align: center;'>Self Service Checkout<br>(Members Only)<html>");
		checkoutDescription.setForeground(Color.WHITE);
		checkoutDescription.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		checkoutDescription.setBounds(797, 217, 208, 50);
		mainPanel.add(checkoutDescription);
		
		JLabel startDescription = new JLabel("Press \"Start\" to begin Self Service");
		startDescription.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		startDescription.setForeground(new Color(255, 255, 255));
		startDescription.setBounds(772, 437, 264, 24);
		mainPanel.add(startDescription);
		
		JButton btnStart = new JButton("START");
		JRootPane rootPane = contentPane.getRootPane();
		rootPane.setDefaultButton(btnStart);
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loginScreen.setVisible(true);
				loginScreen.setWelcomeScreen(getWelcomeScreen());
				getWelcomeScreen().setVisible(false);
			}
		});
		btnStart.setBounds(788, 465, 225, 81);
		mainPanel.add(btnStart);
	}
	
	
	public void setLoginScreen(LoginScreen loginScreen) {
		this.loginScreen = loginScreen;
	}

	public WelcomeScreen getWelcomeScreen() {
		return this;
	}

}