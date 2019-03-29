package librarysystem.gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.LayoutStyle.ComponentPlacement;

import librarysystem.LibrarySystem;
import librarysystem.users.User;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Panel;

public class HomeGUI extends JPanel {
	private LibrarySystem librarySystem;
	//private CardLayout cardLayout;
	
	/*
	void initImage() {
		String[] fileName = {"pic1.png", "pic2.png", "pic3.png"};
		for (String s : fileName) {
			Icon icon = new ImageIcon("src/resources/"+s);
			JLabel label = new JLabel(icon);
			panel.add(label);
		}
		cardLayout = new CardLayout();
		panel.setLayout(cardLayout);
		// https://www.youtube.com/watch?v=MVlQnHMV5w8
	}

	/**
	 * Create the panel.
	 */
	public HomeGUI(LibrarySystem librarySystem) {
		
		int BUTTONS_Y=240;
		int BUTTONS_W=120;
		int BUTTONS_H=30;
		int BUTTONS_D=BUTTONS_W+80;
		int INFOS_W=70;
		this.librarySystem = librarySystem;
		setBackground(new Color(220, 220, 220));
	
		// JLabels
		JLabel lblHours = new JLabel("");
		lblHours.setBounds(260, BUTTONS_Y+BUTTONS_H+15, 814, 78);
		lblHours.setIcon(new ImageIcon("resources/picHours.png"));
		
		JLabel lblContact = new JLabel("Contact Us");
		lblContact.setBounds(INFOS_W, 600, 90, 16);
		lblContact.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		
		JLabel lblGeneralInfo = new JLabel("General Information   403.222.3333");
		lblGeneralInfo.setBounds(INFOS_W, 622, 239, 16);
		
		JLabel lblService = new JLabel("Library Service Desk   403.333.4444");
		lblService.setBounds(INFOS_W,644, 251, 16);
		
		JLabel lblEmail = new JLabel("Email Address     library@mtroyal.ca");
		lblEmail.setBounds(INFOS_W, 666, 226, 16);
		
		JLabel lblBanner = new JLabel("");
		lblBanner.setBounds(40, 0, 1197, 233);
		lblBanner.setIcon(new ImageIcon("resources/b.png"));
		
		JLabel lblPicture = new JLabel("");
		lblPicture.setIcon(new ImageIcon("resources/pic1.png"));
		lblPicture.setBounds(INFOS_W,  BUTTONS_Y+BUTTONS_H+110, 474, 160);
		add(lblPicture);
		
		JLabel lblAccountInfo = new JLabel("Account Informations");
		lblAccountInfo.setBounds(650,  BUTTONS_Y+BUTTONS_H+110, 239, 46);
		add(lblAccountInfo); 
		
		JLabel accountInfo = new JLabel("");
		User CurrentUser=librarySystem.getUserManager().getCurrentUser();
		String accountInfoTxt=CurrentUser.getUsername()+ " \r\n "+CurrentUser.getOverdueFee()+ " \r\n " + CurrentUser.getBorrowedMaterialString();
		accountInfo.setText(accountInfoTxt);
		accountInfo.setBounds(650,  BUTTONS_Y+BUTTONS_H+150, 266, 107);
		add(accountInfo);
		
		//JButtons
		JButton btnHome = new JButton("Home");
		btnHome.setBounds(80 , BUTTONS_Y, BUTTONS_W, BUTTONS_H);
		btnHome.setForeground(new Color(0, 0, 128));
		
		JButton btnReturn = new JButton("Returned");
		btnReturn.setBounds(80+BUTTONS_D, BUTTONS_Y, BUTTONS_W, BUTTONS_H);
		btnReturn.setForeground(new Color(0, 0, 128));
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(80+BUTTONS_D*2, BUTTONS_Y, BUTTONS_W, BUTTONS_H);
		btnBrowse.setForeground(new Color(0, 0, 128));
		
		JButton btnReceive = new JButton("Received");
		btnReceive.setBounds(80+BUTTONS_D*3, BUTTONS_Y, BUTTONS_W, BUTTONS_H);
		btnReceive.setForeground(new Color(0, 0, 128));
		
		JButton btnOrder = new JButton("Order");
		btnOrder.setBounds(80+BUTTONS_D*4, BUTTONS_Y, BUTTONS_W, BUTTONS_H);
		btnOrder.setForeground(new Color(0, 0, 128));
		
		JButton btnAccount = new JButton("Account");
		btnAccount.setBounds(80+BUTTONS_D*5, BUTTONS_Y, BUTTONS_W, BUTTONS_H);
		btnAccount.setForeground(new Color(0, 0, 128));
		
		setLayout(null);
		add(lblHours);
		add(lblEmail);
		add(lblService);
		add(btnHome);
		add(btnReturn);
		add(btnBrowse);
		add(btnReceive);
		add(btnOrder);
		add(btnAccount);
		add(lblContact);
		add(lblGeneralInfo);
		add(lblBanner);
		
		// Buttons Action Listeners
		btnAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccountGUI ac= new AccountGUI();
				//librarySystem.updateGUI(ac);
				librarySystem.updateGUI(new PasswordGUI(librarySystem));
			}
		});

		
		
		//Panel panel = new Panel();
		//panel.setBounds(16, 228, 474, 160);
		//add(panel);

	}
}
