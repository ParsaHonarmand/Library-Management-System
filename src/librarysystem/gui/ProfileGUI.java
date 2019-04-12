package librarysystem.gui;

import librarysystem.LibrarySystem;
import librarysystem.managers.UserManager;
import librarysystem.users.UserType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileGUI extends JPanel {

	private LibrarySystem librarySystem;


	/**
	 * Create the panel.
	 */
	
	/**
	 * Providing user information about their own account eg: ID, Name, Username
	 * @param librarySystem The system to base the GUI on
	 */
	public ProfileGUI(LibrarySystem librarySystem) {
		
		this.librarySystem = librarySystem;
		this.setBounds(0, 0, librarySystem.WIDTH, librarySystem.HEIGHT);
		librarySystem.setTheme(this);
		
		JPanel panel = new JPanel();
		panel.setBounds(211, 270, 1054, 720);
		librarySystem.setTheme(panel);
		add(panel);
		panel.setLayout(null);
		
		UserManager user = librarySystem.getUserManager();
		String id = "" + user.getCurrentUser().getId();
		
		JLabel profilePic = new JLabel();
		profilePic.setBounds(50, 281, 150, 150);
		profilePic.setIcon(new ImageIcon("resources/profile.png"));
		setLayout(null);
		add(profilePic);
		
		
		JLabel lblBanner = new JLabel("");
		lblBanner.setBounds(15, 15, 1250, 200);
		lblBanner.setIcon(new ImageIcon("resources/banner_img.png"));
		setLayout(null);
		add(lblBanner);
		
		/**
		 * General buttons constant throughout all panels
		 */
		JButton btnHome = new JButton("Home");
		btnHome.setBounds(80, 225, 120, 30);
		btnHome.setForeground(new Color(0, 0, 128));
		add(btnHome);
		
		JButton btnReturned = new JButton("Returned");
		btnReturned.setBounds(280, 225, 120, 30);
		btnReturned.setForeground(new Color(0, 0, 128));
		add(btnReturned);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(480, 225, 120, 30);
		btnBrowse.setForeground(new Color(0, 0, 128));
		add(btnBrowse);
		
		JButton btnReceived = new JButton("Received");
		btnReceived.setBounds(1080, 225, 120, 30);
		btnReceived.setForeground(new Color(0, 0, 128));
		add(btnReceived);
		
		JButton btnOrder = new JButton("Order");
		btnOrder.setBounds(880, 225, 120, 30);
		btnOrder.setForeground(new Color(0, 0, 128));
		add(btnOrder);
		
		JButton btnAccount = new JButton("Account");
		btnAccount.setBounds(680, 225, 120, 30);
		btnAccount.setForeground(new Color(0, 0, 128));
		add(btnAccount);
		
		
		JButton btnChanPswrdLeft = new JButton("Change Password");
		btnChanPswrdLeft.setBounds(50, 450, 158, 60);
		add(btnChanPswrdLeft);
		
		JButton btnPayFees = new JButton("Pay Fees");
		btnPayFees.setBounds(50, 508, 158, 60);
		add(btnPayFees);
		
		JButton btnMaterials = new JButton("Materials");
		btnMaterials.setBounds(50, 564, 158, 60);
		add(btnMaterials);
		
		JButton btnReservations = new JButton("Reservations");
		btnReservations.setBounds(50, 621, 158, 60);
		if(librarySystem.getUserManager().getCurrentUser().getUserType()==UserType.INSTRUCTOR) {
			add(btnReservations);
		}
		
		JButton btnLogOut = new JButton("Logout");
		btnLogOut.setBounds(98, 685, 110, 43);
		add(btnLogOut);
		
		/**
		 * Adding action listeners to the buttons above such that they redirect the user to a different panel
		 */
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new HomeGUI(librarySystem);
			}
		});
		btnReturned.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ReturnGUI(librarySystem);
			}
		});
		
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BrowseGUI(librarySystem);
			}
		});
		
		btnReceived.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ReceiveGUI(librarySystem);
			}
		});
		
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new OrderGUI(librarySystem);
			}
		});
		
		btnAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ProfileGUI(librarySystem);
			}
		});
		
		btnPayFees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PayGUI(librarySystem);
			}
		});
		
		btnChanPswrdLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PasswordGUI(librarySystem);
			}
		});
		btnMaterials.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MaterialsGUI(librarySystem);
			}
		});
		
		btnReservations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ReservationsGUI(librarySystem);
			}
		});
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				librarySystem.getUserManager().logout();
				new LoginGUI(librarySystem);
			}
		});
		
		
		/**
		 * Setting JPanel characteristics 
		 */
		
		
		JLabel name = new JLabel("Your Name:");
		name.setForeground(new Color(255, 255, 255));
		name.setBounds(221, 130, 73, 16);
		panel.add(name);
		
		JLabel nameDisplay = new JLabel(user.getCurrentUser().getName());
		nameDisplay.setForeground(new Color(255, 255, 255));
		nameDisplay.setBounds(324, 124, 151, 29);
		panel.add(nameDisplay);
		
		JLabel ID = new JLabel("Your ID:");
		ID.setForeground(new Color(255, 255, 255));
		ID.setBounds(243, 246, 51, 16);
		panel.add(ID);
		
		JLabel username = new JLabel("Your Username:");
		username.setForeground(new Color(255, 255, 255));
		username.setBounds(201, 186, 99, 16);
		panel.add(username);
		
		JLabel usernameDisplay = new JLabel(user.getCurrentUser().getUsername());
		usernameDisplay.setForeground(new Color(255, 255, 255));
		usernameDisplay.setBounds(324, 179, 139, 30);
		panel.add(usernameDisplay);
		
		JLabel email = new JLabel("Your Email:");
		email.setForeground(new Color(255, 255, 255));
		email.setBounds(223, 300, 71, 16);
		panel.add(email);
		
		JLabel emailDisplay = new JLabel(user.getCurrentUser().getEmail());
		emailDisplay.setForeground(new Color(255, 255, 255));
		emailDisplay.setBounds(319, 293, 172, 30);
		panel.add(emailDisplay);
		
		JLabel IDDisplay = new JLabel(String.valueOf(user.getCurrentUser().getId()));
		IDDisplay.setForeground(new Color(255, 255, 255));
		IDDisplay.setBounds(324, 246, 92, 16);
		panel.add(IDDisplay);
		
		/*
		 * Make order and received functionality accessible to only the librarian 
		 */
		if (librarySystem.getUserManager().getCurrentUser().getUserType() == UserType.STUDENT || librarySystem.getUserManager().getCurrentUser().getUserType() == UserType.INSTRUCTOR ) {
			btnOrder.setVisible(false);
			btnReceived.setVisible(false);
			btnReturned.setVisible(false);
		}
		
		this.librarySystem.updateGUI(this);
	}
}

