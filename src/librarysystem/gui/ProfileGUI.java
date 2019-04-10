package librarysystem.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;

import librarysystem.LibrarySystem;
import librarysystem.managers.UserManager;
import librarysystem.users.UserType;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JLabel;

public class ProfileGUI extends JPanel {

	private LibrarySystem librarySystem;
	//Upper Tab Buttons Settings
	int BUTTONS_Y=20;
	int BUTTONS_W=120;
	int BUTTONS_H=30;
	int BUTTONS_D=BUTTONS_W+80;

	/**
	 * Create the panel.
	 */
	
	/**
	 * Providing user information about their own account eg: ID, Name, Username
	 * @param librarySystem
	 */
	public ProfileGUI(LibrarySystem librarySystem) {
		setForeground(new Color(51, 102, 153));
		this.librarySystem = librarySystem;
		
		this.setBackground(new Color(255, 255, 255));
		this.setBounds(0, 0, 1281, 721);
		
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
		btnReceived.setBounds(680, 225, 120, 30);
		btnReceived.setForeground(new Color(0, 0, 128));
		add(btnReceived);
		JButton btnOrder = new JButton("Order");
		btnOrder.setBounds(880, 225, 120, 30);
		btnOrder.setForeground(new Color(0, 0, 128));
		add(btnOrder);
		JButton btnAccount = new JButton("Account");
		btnAccount.setBounds(1080, 225, 120, 30);
		btnAccount.setForeground(new Color(0, 0, 128));
		add(btnAccount);
		
		JButton btnProfile = new JButton("Profile");
		btnProfile.setBounds(49, 429, 158, 60);
		add(btnProfile);
		
		JButton btnChanPswrdLeft = new JButton("Change Password");
		btnChanPswrdLeft.setBounds(49, 487, 158, 60);
		add(btnChanPswrdLeft);
		
		JButton btnPayFees = new JButton("Pay Fees");
		btnPayFees.setBounds(49, 544, 158, 60);
		add(btnPayFees);
		
		JButton btnMaterials = new JButton("Materials");
		btnMaterials.setBounds(49, 600, 158, 60);
		add(btnMaterials);
		
		JButton btnReservations = new JButton("Reservations");
		btnReservations.setBounds(36, 527, 158, 60);
		//System.out.println(librarySystem.getUserManager().getCurrentUser().getUserType());
		if(librarySystem.getUserManager().getCurrentUser().getUserType()==UserType.INSTRUCTOR) {
			add(btnReservations);
		}
		
		JButton btnLogOut = new JButton("Logout");
		btnLogOut.setBounds(97, 658, 110, 43);
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
		add(profilePic);
		
		/**
		 * Setting JPanel characteristics 
		 */
		JPanel panel = new JPanel();
		panel.setBounds(243, 281, 957, 406);
		panel.setBackground(new Color(102, 153, 204));
		add(panel);
		panel.setLayout(null);
		
		JLabel name = new JLabel("Your Name:");
		name.setBounds(221, 130, 73, 16);
		panel.add(name);
		
		JLabel nameDisplay = new JLabel(user.getCurrentUser().getName());
		nameDisplay.setBounds(331, 124, 151, 29);
		panel.add(nameDisplay);
		
		JLabel ID = new JLabel("Your ID:");
		ID.setBounds(237, 246, 51, 16);
		panel.add(ID);
		
		JLabel username = new JLabel("Your Username:");
		username.setBounds(201, 186, 99, 16);
		panel.add(username);
		
		JLabel usernameDisplay = new JLabel(user.getCurrentUser().getUsername());
		usernameDisplay.setBounds(324, 179, 139, 30);
		panel.add(usernameDisplay);
		
		JLabel email = new JLabel("Your Email:");
		email.setBounds(223, 300, 71, 16);
		panel.add(email);
		
		JLabel emailDisplay = new JLabel(user.getCurrentUser().getEmail());
		emailDisplay.setBounds(331, 293, 172, 30);
		panel.add(emailDisplay);
		
		JLabel IDDisplay = new JLabel(id);
		IDDisplay.setBounds(331, 246, 92, 16);
		panel.add(IDDisplay);
		
		if (librarySystem.getUserManager().getCurrentUser().getUserType() == UserType.STUDENT || librarySystem.getUserManager().getCurrentUser().getUserType() == UserType.INSTRUCTOR ) {
			btnOrder.setVisible(false);
			btnReceived.setVisible(false);
		}
		
		this.librarySystem.updateGUI(this);
	}
}

