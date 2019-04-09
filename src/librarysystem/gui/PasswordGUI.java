package librarysystem.gui;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import librarysystem.LibrarySystem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

public class PasswordGUI extends JPanel {
	private JTextField currPswrdTextField;
	private JTextField newPswrdTextField;
	private JTextField confirmPswrdTextField;
	private LibrarySystem librarySystem;
	/**
	 * Upper Tab Buttons Settings
	 */
			int BUTTONS_Y=20;
			int BUTTONS_W=120;
			int BUTTONS_H=30;
			int BUTTONS_D=BUTTONS_W+80;


	/**
	 * Create the panel.
	 */
	public PasswordGUI(LibrarySystem librarySystem) {
		this.librarySystem = librarySystem;
		
		setBackground(new Color(255, 255, 255));
		setForeground(Color.WHITE);;;;
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 153, 204));
		panel.setBounds(219, 66, 1185, 781);
		add(panel);
		panel.setLayout(null);

		
		/**
		 * Setting Textfeild characteristics 
		 */
		

		currPswrdTextField = new JPasswordField();
		currPswrdTextField.setBounds(560, 151, 187, 38);
		panel.add(currPswrdTextField);
		currPswrdTextField.setColumns(10);
		
		confirmPswrdTextField = new JPasswordField();
		confirmPswrdTextField.setBounds(560, 237, 187, 38);
		panel.add(confirmPswrdTextField);
		confirmPswrdTextField.setColumns(10);
		
		newPswrdTextField = new JPasswordField();
		newPswrdTextField.setBounds(560, 322, 187, 38);
		panel.add(newPswrdTextField);
		newPswrdTextField.setColumns(10);
		
		/**
		 * JLabels to display outcomes of actions performed or to direct user to input data as needed 
		 */
		
		JLabel lblNonMatchingPswrd = new JLabel("New password and confirm password do not match");
		lblNonMatchingPswrd.setBounds(564, 295, 371, 15);
		panel.add(lblNonMatchingPswrd);
		lblNonMatchingPswrd.setForeground(new Color(153, 0, 0));
		lblNonMatchingPswrd.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNonMatchingPswrd.setVisible(false);
		
		JLabel lblIncorrectPswrd = new JLabel("Incorrect Password");
		lblIncorrectPswrd.setBounds(560, 132, 158, 16);
		panel.add(lblIncorrectPswrd);
		lblIncorrectPswrd.setFont(new Font("Dialog", Font.BOLD, 12));
		lblIncorrectPswrd.setForeground(new Color(153, 0, 0));
		lblIncorrectPswrd.setVisible(false);
		
		JLabel btnChangePswrd = new JLabel("Current Password *");
		btnChangePswrd.setHorizontalAlignment(SwingConstants.RIGHT);
		btnChangePswrd.setBounds(305, 158, 158, 21);
		panel.add(btnChangePswrd);
		btnChangePswrd.setForeground(new Color(0, 0, 128));
		btnChangePswrd.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		
		
		JLabel lblNewPswrd = new JLabel("New Password*");
		lblNewPswrd.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewPswrd.setBounds(338, 245, 125, 21);
		panel.add(lblNewPswrd);
		lblNewPswrd.setForeground(new Color(0, 0, 128));
		lblNewPswrd.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		
		JLabel lblConfirmPswrd = new JLabel("Confirm Password*");
		lblConfirmPswrd.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConfirmPswrd.setBounds(305, 330, 157, 21);
		panel.add(lblConfirmPswrd);
		lblConfirmPswrd.setForeground(new Color(0, 0, 128));
		lblConfirmPswrd.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		
		JLabel lblMissingPswrd = new JLabel("Failed, please fill in all required fields*");
		lblMissingPswrd.setBounds(147, 511, 267, 30);
		panel.add(lblMissingPswrd);
		lblMissingPswrd.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		lblMissingPswrd.setForeground(new Color(153, 0, 0));
		lblMissingPswrd.setVisible(false);
		
		
		JLabel lblSucessChangePswrd = new JLabel("Your Password has been changed succcessfully");
		lblSucessChangePswrd.setForeground(new Color(204, 255, 204));
		lblSucessChangePswrd.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblSucessChangePswrd.setToolTipText("yug");
		lblSucessChangePswrd.setBounds(147, 453, 463, 50);
		panel.add(lblSucessChangePswrd);
		lblSucessChangePswrd.setVisible(false);
		
		JLabel lblUnchangedpswrd = new JLabel("Please enter a new password");
		lblUnchangedpswrd.setBounds(564, 108, 183, 31);
		panel.add(lblUnchangedpswrd);
		lblUnchangedpswrd.setForeground(new Color(153, 0, 0));
		lblUnchangedpswrd.setFont(new Font("Dialog", Font.BOLD, 12));
		lblUnchangedpswrd.setVisible(false);		
		
		/**
		 * JButtons that are consistent throughout all panels as well as specialized to this one
		 */

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
		
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.setBounds(713, 410, 167, 43);
		panel.add(btnChangePassword);
		btnChangePassword.setForeground(new Color(0, 0, 128));
		btnChangePassword.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		
		/**
		 * Button action handlers to implement utility 
		 */
		
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currPswrd=currPswrdTextField.getText();
				String newPswrd =newPswrdTextField.getText();
				String confirmPswrd =confirmPswrdTextField.getText();
				// reset/hide error Jlabels
				lblUnchangedpswrd.setVisible(false);
				lblSucessChangePswrd.setVisible(false);
				lblNonMatchingPswrd.setVisible(false);
				lblIncorrectPswrd.setVisible(false);
				if(!( currPswrd.length()==0 || newPswrd.length()==0 ||confirmPswrd.length()==0 )) {

					if(currPswrd.equals(librarySystem.getUserManager().getCurrentUser().getPassword())) {

						 if(newPswrd.equals(confirmPswrd)) {
							 if (newPswrd.equals(currPswrd)) 
									// new password and current password are the same 
									lblUnchangedpswrd.setVisible(true);
							 else {
									// successful password Change
									librarySystem.getUserManager().getCurrentUser().setPassword(newPswrd);
									btnChangePassword.setVisible(false);
									lblSucessChangePswrd.setVisible(true);
							 }
						 }else {
							// new and confirm does not match
							lblNonMatchingPswrd.setVisible(true);
						}
					}else {
						// incorrect current password
						lblIncorrectPswrd.setVisible(true);
					}
				}else {
					// missing Blank Field
					lblUnchangedpswrd.setVisible(true);
				}

			}
		});
		
		
		/**
		 * Actionlisteners for all the buttons to invoke panel calls as needed
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
		
		this.librarySystem.updateGUI(this);
	}
}
