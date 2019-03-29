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
import javax.swing.SwingConstants;

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
		setForeground(new Color(0, 0, 102));
		//Upper Tab Buttons Settings
		int BUTTONS_Y=225;
		int BUTTONS_W=120;
		int BUTTONS_H=30;
		int BUTTONS_D=BUTTONS_W+80;
		
		int INFOS_W=70;
		this.librarySystem = librarySystem;
		
	
		JPanel panel = new JPanel();
		panel.setBounds(15, 220, 1250, 707);
		panel.setBackground(new Color(255, 255, 255));
		setBackground(new Color(220, 220, 220));
		
		
		JLabel lblHours = new JLabel("");
		lblHours.setBounds(260, 270, 814, 78);
		lblHours.setIcon(new ImageIcon("resources/picHours.png"));
		
		JLabel lblContact = new JLabel("Contact Us");
		lblContact.setBounds(70, 600, 90, 16);
		lblContact.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		
		JLabel lblGeneralInfo = new JLabel("General Information   403.222.3333");
		lblGeneralInfo.setBounds(70, 622, 239, 16);
		
		JLabel lblService = new JLabel("Library Service Desk   403.333.4444");
		lblService.setBounds(70, 644, 251, 16);
		
		JLabel lblEmail = new JLabel("Email Address     library@mtroyal.ca");
		lblEmail.setBounds(70, 666, 226, 16);
		
		JLabel lblBanner = new JLabel("");
		lblBanner.setBounds(15, 15, 1250, 200);
		lblBanner.setIcon(new ImageIcon("resources/banner_img.png"));
		setLayout(null);
		
		JLabel lblPicture = new JLabel("");
		lblPicture.setBounds(70, 365, 474, 160);
		lblPicture.setIcon(new ImageIcon("resources/pic1.png"));
		add(lblPicture);
		
		JLabel lblAccountInfo = new JLabel("Account Informations");
		lblAccountInfo.setBounds(650, 365, 239, 46);
		add(lblAccountInfo); 
		
		JLabel accountInfo = new JLabel("");
		accountInfo.setBounds(650, 420, 468, 78);
		accountInfo.setHorizontalAlignment(SwingConstants.LEFT);
		User CurrentUser=librarySystem.getUserManager().getCurrentUser();
		String accountInfoTxt=CurrentUser.toString();
		accountInfo.setText(accountInfoTxt);
		add(accountInfo);
		
		//JButtons
		JButton btnHome = new JButton("Home");
		btnHome.setBounds(80, 225, 120, 30);
		btnHome.setForeground(new Color(0, 0, 128));
		
		JButton btnReturned = new JButton("Returned");
		btnReturned.setBounds(280, 225, 120, 30);
		btnReturned.setForeground(new Color(0, 0, 128));
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(480, 225, 120, 30);
		btnBrowse.setForeground(new Color(0, 0, 128));
		
		JButton btnReceived = new JButton("Received");
		btnReceived.setBounds(680, 225, 120, 30);
		btnReceived.setForeground(new Color(0, 0, 128));
		
		JButton btnOrder = new JButton("Order");
		btnOrder.setBounds(880, 225, 120, 30);
		btnOrder.setForeground(new Color(0, 0, 128));
		
		JButton btnAccount = new JButton("Account");
		btnAccount.setBounds(1080, 225, 120, 30);
		btnAccount.setForeground(new Color(0, 0, 128));
		
		// Buttons Action Listeners
		
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				librarySystem.updateGUI(new HomeGUI(librarySystem));
			}
		});
		btnReturned.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				librarySystem.updateGUI(new ReturnedGUI(librarySystem));
			}
		});
		
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				librarySystem.updateGUI(new BrowseGUI(librarySystem));
			}
		});
		
		btnReceived.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				librarySystem.updateGUI(new ReceiveGUI(librarySystem));
			}
		});
		
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				librarySystem.updateGUI(new OrderGUI(librarySystem));
			}
		});
		
		btnAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				librarySystem.updateGUI(new ProfileGUI(librarySystem));
			}
		});
		add(lblHours);
		add(lblEmail);
		add(lblService);
		add(btnHome);
		add(btnReturned);
		add(btnBrowse);
		add(btnReceived);
		add(btnOrder);
		add(btnAccount);
		add(lblContact);
		add(lblGeneralInfo);
		add(lblBanner);
		
		
		add(panel);
		// JLabels
		

				
		
		
		
		librarySystem.updateGUI(this);

	}
}