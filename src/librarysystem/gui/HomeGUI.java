package librarysystem.gui;

import librarysystem.LibrarySystem;
import librarysystem.users.UserType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeGUI extends JPanel {
	private LibrarySystem librarySystem;

	/**
	 * Create the panel.
	 * @param librarySystem The system to base the GUI on
	 */
	public HomeGUI(LibrarySystem librarySystem) {
		setForeground(new Color(0, 0, 102));

		this.librarySystem = librarySystem;
		
		/**
		 * Setting panel characteristics 
		 */
		JPanel panel = new JPanel();
		panel.setBounds(15, 220, 1250, 707);
		panel.setBackground(new Color(255, 255, 255));
		setBackground(new Color(220, 220, 220));
		
		/**
		 * Setting labels that display information as well as inserting pictures on to the panel
		 */
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
		
		JLabel lblAccountInfo = new JLabel("Account Information");
		lblAccountInfo.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblAccountInfo.setBounds(650, 350, 239, 46);
		add(lblAccountInfo); 
		
		JLabel accountInfoWelcome = new JLabel("Welcome Back");
		accountInfoWelcome.setFont(new Font("Times New Roman", Font.BOLD, 20));
		JLabel accountName = new JLabel("");
		accountName.setFont(new Font("Times New Roman", Font.BOLD, 20));
		accountInfoWelcome.setHorizontalAlignment(SwingConstants.LEFT);
		accountName.setHorizontalAlignment(SwingConstants.LEFT);
		String CurrentUserName = librarySystem.getUserManager().getCurrentUser().getName();
		accountName.setText(CurrentUserName);
		accountName.setBounds(790, 375, 468, 78);
		accountInfoWelcome.setBounds(650, 375, 468, 78);
		
		JLabel idNum = new JLabel("ID Number:");
		idNum.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		JLabel accountID = new JLabel("");
		accountID.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		idNum.setHorizontalAlignment(SwingConstants.LEFT);
		accountID.setHorizontalAlignment(SwingConstants.LEFT);
		int CurrentUserID = librarySystem.getUserManager().getCurrentUser().getId();
		String currID = Integer.toString(CurrentUserID);
		accountID.setText(currID);
		idNum.setBounds(650, 400, 468, 78);
		accountID.setBounds(790, 400, 468, 78);
		
		JLabel overdueFee = new JLabel("Overdue Fee:");
		overdueFee.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		JLabel accountFee = new JLabel("");
		accountFee.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		overdueFee.setHorizontalAlignment(SwingConstants.LEFT);
		accountFee.setHorizontalAlignment(SwingConstants.LEFT);
		double CurrentUserFee = librarySystem.getUserManager().getCurrentUser().getOverdueFee();
		String currFee = String.valueOf(CurrentUserFee);
		accountFee.setText(currFee);
		overdueFee.setBounds(650, 425, 468, 78);
		accountFee.setBounds(790, 425, 468, 78);
		/*
		JLabel borrowItems = new JLabel("Overdue Fee:");
		borrowItems.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		JLabel accountBorrow = new JLabel("");
		accountBorrow.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		borrowItems.setHorizontalAlignment(SwingConstants.LEFT);
		accountBorrow.setHorizontalAlignment(SwingConstants.LEFT);
		ArrayList<Material> CurrentUserBorrow = (ArrayList<Material>) librarySystem.getUserManager().getCurrentUser().getBorrowed();
		String currBorrow = String.valueOf(CurrentUserBorrow);
		accountFee.setText(currBorrow);
		overdueFee.setBounds(650, 450, 468, 78);
		accountFee.setBounds(790, 450, 468, 78);
		*/
		//add(borrowItems);
		//add(accountBorrow);
		add(overdueFee);
		add(accountFee);
		add(accountInfoWelcome);
		add(accountName);
		add(idNum);
		add(accountID);
		
		
		/**
		 * General buttons constant throughout all panels
		 */
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
		btnReceived.setBounds(1080, 225, 120, 30);
		btnReceived.setForeground(new Color(0, 0, 128));
		
		JButton btnOrder = new JButton("Order");
		btnOrder.setBounds(880, 225, 120, 30);
		btnOrder.setForeground(new Color(0, 0, 128));
		
		JButton btnAccount = new JButton("Account");
		btnAccount.setBounds(680, 225, 120, 30);
		btnAccount.setForeground(new Color(0, 0, 128));
		
		/**
		 * Action listeners to invoke any panel called 
		 */
		
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				librarySystem.updateGUI(new HomeGUI(librarySystem));
			}
		});
		btnReturned.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				librarySystem.updateGUI(new ReturnGUI(librarySystem));
			}
		});
		
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BrowseGUI(librarySystem);
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
		
		/**
		 * Adding all components onto the panel
		 */
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
		
		librarySystem.updateGUI(this);
		
		if (librarySystem.getUserManager().getCurrentUser().getUserType() == UserType.STUDENT || librarySystem.getUserManager().getCurrentUser().getUserType() == UserType.INSTRUCTOR ) {
			btnOrder.setVisible(false);
			btnReceived.setVisible(false);
		}

	}
}

