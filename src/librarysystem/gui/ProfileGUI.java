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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

	public ProfileGUI(LibrarySystem librarySystem) {
		setForeground(new Color(51, 102, 153));
		this.librarySystem = librarySystem;
		
		this.setBackground(new Color(255, 255, 255));
		this.setBounds(0, 0, 1281, 721);
		
		UserManager user = librarySystem.getUserManager();
		String id = "" + user.getCurrentUser().getId();
		
		JLabel ID = new JLabel("Your ID:");
		ID.setBounds(466, 282, 51, 16);
		
		JLabel email = new JLabel("Your Email:");
		email.setBounds(466, 465, 71, 16);
		
		JLabel emailDisplay = new JLabel(user.getCurrentUser().getEmail());
		emailDisplay.setBounds(612, 465, 73, 16);
		
		JLabel IDDisplay = new JLabel(id);
		IDDisplay.setBounds(612, 282, 8, 16);
		
		JLabel username = new JLabel("Your Username:");
		username.setBounds(466, 610, 99, 16);
		
		JLabel usernameDisplay = new JLabel(user.getCurrentUser().getUsername());
		usernameDisplay.setBounds(612, 610, 73, 16);
		
		JLabel profilePic = new JLabel();
		profilePic.setBounds(272, 130, 0, 0);
		profilePic.setIcon(new ImageIcon("C:\\Users\\Prit Patel\\git\\library-system"));
		
		//JButtons


		JButton btnHome = new JButton("Home");
		btnHome.setBounds(80 , BUTTONS_Y, BUTTONS_W, BUTTONS_H);
		btnHome.setForeground(new Color(0, 0, 128));
		add(btnHome);
		
		JButton btnReturned = new JButton("Returned");
		btnReturned.setBounds(80+BUTTONS_D, BUTTONS_Y, BUTTONS_W, BUTTONS_H);
		btnReturned.setForeground(new Color(0, 0, 128));
		add(btnReturned);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(80+BUTTONS_D*2, BUTTONS_Y, BUTTONS_W, BUTTONS_H);
		btnBrowse.setForeground(new Color(0, 0, 128));
		add(btnBrowse);
		
		JButton btnReceived = new JButton("Received");
		btnReceived.setBounds(80+BUTTONS_D*3, BUTTONS_Y, BUTTONS_W, BUTTONS_H);
		btnReceived.setForeground(new Color(0, 0, 128));
		add(btnReceived);
		
		JButton btnOrder = new JButton("Order");
		btnOrder.setBounds(80+BUTTONS_D*4, BUTTONS_Y, BUTTONS_W, BUTTONS_H);
		btnOrder.setForeground(new Color(0, 0, 128));
		add(btnOrder);
		
		JButton btnAccount = new JButton("Account");
		btnAccount.setBounds(80+BUTTONS_D*5, BUTTONS_Y, BUTTONS_W, BUTTONS_H);
		btnAccount.setForeground(new Color(0, 0, 128));
		add(btnAccount);
		
		JButton btnProfile = new JButton("Profile");
		btnProfile.setBounds(36, 139, 158, 60);
		add(btnProfile);
		
		JButton btnChanPswrdLeft = new JButton("Change Password");
		btnChanPswrdLeft.setBounds(36, 238, 158, 60);
		add(btnChanPswrdLeft);
		
		JButton btnPayFees = new JButton("Pay Fees");
		btnPayFees.setBounds(36, 336, 158, 60);
		add(btnPayFees);
		
		JButton btnMaterials = new JButton("Materials");
		btnMaterials.setBounds(36, 427, 158, 60);
		add(btnMaterials);
		
		JButton btnReservations = new JButton("Reservations");
		btnReservations.setBounds(36, 527, 158, 60);
		add(btnReservations);
		
		JButton btnLogOut = new JButton("Logout");
		btnLogOut.setBounds(97, 641, 110, 43);
		add(btnLogOut);
		
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				librarySystem.updateGUI(new HomeGUI(librarySystem));
			}
		});
		btnReturned.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//librarySystem.updateGUI(new ReturnedGUI(librarySystem));
			}
		});
		
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				librarySystem.updateGUI(new BrowseGUI(librarySystem));
			}
		});
		
		btnReceived.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//librarySystem.updateGUI(new ReceiveGUI(librarySystem));
			}
		});
		
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				librarySystem.updateGUI(new OrderGUI(librarySystem));
			}
		});
		
		btnAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccountGUI ac= new AccountGUI();
				librarySystem.updateGUI(ac);
			}
		});
		
		btnPayFees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				librarySystem.updateGUI(new PayGUI(librarySystem));
			}
		});
		
		btnChanPswrdLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				librarySystem.updateGUI(new PasswordGUI(librarySystem));
			}
		});
		btnMaterials.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				librarySystem.updateGUI(new MaterialsGUI(librarySystem));
			}
		});
		
		btnReservations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				librarySystem.updateGUI(new ReservationsGUI(librarySystem));
			}
		});
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//librarySystem.updateGUI(new LogOutGUI(librarySystem));
			}
		});

		this.librarySystem.updateGUI(this);
		setLayout(null);
		add(ID);
		add(email);
		add(username);
		add(emailDisplay);
		add(IDDisplay);
		add(usernameDisplay);
		add(profilePic);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 153, 204));
		panel.setBounds(252, 66, 1152, 781);
		add(panel);
		panel.setLayout(null);
		
		JLabel name = new JLabel("Your Name:");
		name.setBounds(237, 63, 73, 16);
		panel.add(name);
		
		JLabel nameDisplay = new JLabel(user.getCurrentUser().getName());
		nameDisplay.setBounds(361, 63, 73, 16);
		panel.add(nameDisplay);
	}
}
