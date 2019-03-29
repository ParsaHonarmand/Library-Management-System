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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		this.librarySystem = librarySystem;
		setBackground(new Color(220, 220, 220));
	
		
		JLabel lblHours = new JLabel("");
		lblHours.setBounds(6, 138, 814, 78);
		lblHours.setIcon(new ImageIcon("/Users/zobiaasad/Desktop/picHours.png"));
		
		JLabel lblContact = new JLabel("Contact Us");
		lblContact.setBounds(6, 400, 90, 16);
		lblContact.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		
		JLabel lblGeneralInfo = new JLabel("General Information   403.222.3333");
		lblGeneralInfo.setBounds(6, 422, 239, 16);
		
		JLabel lblService = new JLabel("Library Service Desk   403.333.4444");
		lblService.setBounds(6, 444, 251, 16);
		
		JLabel lblEmail = new JLabel("Email Address     library@mtroyal.ca");
		lblEmail.setBounds(6, 466, 226, 16);
		
		JLabel lblBanner = new JLabel("");
		lblBanner.setBounds(0, 0, 791, 97);
		lblBanner.setIcon(new ImageIcon("/Users/zobiaasad/Desktop/Banner.png"));
		
		JButton btnHome = new JButton("Home");
		btnHome.setBounds(6, 103, 91, 29);
		btnHome.setForeground(new Color(0, 0, 128));
		
		JButton btnReturn = new JButton("Returned");
		btnReturn.setBounds(136, 103, 100, 29);
		btnReturn.setForeground(new Color(0, 0, 128));
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				new BrowseGUI(librarySystem);
			}
		});
		btnBrowse.setBounds(270, 103, 88, 29);
		btnBrowse.setForeground(new Color(0, 0, 128));
		
		JButton btnReceive = new JButton("Received");
		btnReceive.setBounds(406, 103, 99, 29);
		btnReceive.setForeground(new Color(0, 0, 128));
		
		JButton btnOrder = new JButton("Order");
		btnOrder.setBounds(553, 103, 88, 29);
		btnOrder.setForeground(new Color(0, 0, 128));
		
		JButton btnAccount = new JButton("Account");
		btnAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				new ProfileGUI(librarySystem);
			}
		});
		btnAccount.setBounds(690, 103, 96, 29);
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
		
		JLabel lblPicture = new JLabel("");
		lblPicture.setIcon(new ImageIcon("/Users/zobiaasad/Desktop/pic1.png"));
		lblPicture.setBounds(16, 228, 474, 160);
		add(lblPicture);
		
		JLabel accountInfo = new JLabel("");
		User CurrentUser=librarySystem.getUserManager().getCurrentUser();
		String accountInfoTxt=CurrentUser.getUsername()+ " \r\n "+CurrentUser.getOverdueFee()+ " \r\n " + CurrentUser.getBorrowedMaterialString();
		accountInfo.setText(accountInfoTxt);
		accountInfo.setBounds(503, 281, 266, 107);
		add(accountInfo);
		
		JLabel lblAccountInfo = new JLabel("Account info here");
		lblAccountInfo.setBounds(502, 228, 239, 46);
		add(lblAccountInfo);
		//Panel panel = new Panel();
		//panel.setBounds(16, 228, 474, 160);
		//add(panel);

	}
}
