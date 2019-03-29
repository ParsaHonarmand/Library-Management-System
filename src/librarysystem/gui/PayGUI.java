package librarysystem.gui;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import librarysystem.LibrarySystem;
import librarysystem.users.User;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Panel;
import javax.swing.JToolBar;

public class PayGUI extends JPanel {

	private LibrarySystem librarySystem;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	
	private User currentUser = librarySystem.getUserManager().getCurrentUser();
	private double fee = currentUser.getOverdueFee();
	private String feeString = Double.toString(fee);
	private JLabel lblOutStandingFees = new JLabel(feeString);
	

	/**
	 * Create the panel.
	 */
	public PayGUI(LibrarySystem librarySystem) {
		this();
		
		this.librarySystem = librarySystem;
		librarySystem.updateGUI(this);

	}

	public PayGUI() {
		this.setBackground(new Color(204, 204, 204));
		this.setBounds(0, 0, 1075, 747);
		
		JButton profile = new JButton("Profile");
		profile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		profile.setHorizontalAlignment(SwingConstants.LEFT);
		profile.setBounds(0,0,100,100);
		
		JButton changePassword = new JButton("Change Password");
		
		JButton payFee = new JButton("Pay Fees");
		payFee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JButton materials = new JButton("Materials");
		materials.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton reservations = new JButton("Reservations");
		
		JButton logout = new JButton("Logout");
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JPanel profilePanel = new JPanel();
		profilePanel.setBackground(new Color(102, 153, 204));
		
		JButton btnNewButton = new JButton("Home"); //Fake button will be deleted later, show purpose 
		
		JButton btnNewButton_1 = new JButton("Browse"); //Fake button will be deleted later, show purpose
		
		JButton btnNewButton_2 = new JButton("Account"); //Fake button will be deleted later, show purpose
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(changePassword, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(logout)
								.addComponent(profile, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(payFee, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(materials, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(reservations, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(58)
							.addComponent(profilePanel, GroupLayout.DEFAULT_SIZE, 851, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 377, GroupLayout.PREFERRED_SIZE)
							.addGap(2)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 367, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_2))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(profile)
							.addGap(13)
							.addComponent(changePassword)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(payFee)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(materials)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(reservations)
							.addPreferredGap(ComponentPlacement.RELATED, 471, Short.MAX_VALUE)
							.addComponent(logout))
						.addComponent(profilePanel, GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		JLabel lblOutstandingFees = new JLabel("Outstanding Fees:");
		
		JLabel lblCard = new JLabel("Card #");
		
		JLabel lblExpiryDate = new JLabel("Expiry date;");
		
		JLabel lblCvc = new JLabel("CVC:");
		
		JLabel lblFirstName = new JLabel("First name:");
		
		JLabel lblLastName = new JLabel("Last name:");
		

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		
		
		JLabel lblError = new JLabel("Please enter all your info");
		lblError.setVisible(false);
		
		JButton btnPay = new JButton("Pay");
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(textField_1.getText() != "") && !(textField_2.getText() != "") && !(textField_3.getText() != "") && !(textField_4.getText() != "") && !(textField_5.getText() != "")) {
					currentUser.setOverdueFee(0.00);
					lblOutStandingFees.setText("0.00");
					lblError.setText("Thank you!");
					lblError.setVisible(true);
				}
				else if (currentUser.getOverdueFee() == 0.0) {
					lblError.setText("You have no outstanding Fees!");
					lblError.setVisible(true);
				}
				else {
					lblError.setText("Please enter all your info!");
					lblError.setVisible(true);
				}
			}
		});

		GroupLayout gl_profilePanel = new GroupLayout(profilePanel);
		gl_profilePanel.setHorizontalGroup(
			gl_profilePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_profilePanel.createSequentialGroup()
					.addGroup(gl_profilePanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_profilePanel.createSequentialGroup()
							.addGap(90)
							.addGroup(gl_profilePanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblLastName)
								.addComponent(lblFirstName)
								.addComponent(lblCvc)
								.addComponent(lblExpiryDate)
								.addComponent(lblCard)
								.addComponent(lblOutstandingFees))
							.addGap(76)
							.addGroup(gl_profilePanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblOutStandingFees)
								.addComponent(textField_1)
								.addComponent(textField_2)
								.addComponent(textField_3)
								.addComponent(textField_4)
								.addComponent(textField_5)
								.addComponent(lblError, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(gl_profilePanel.createSequentialGroup()
							.addGap(308)
							.addComponent(btnPay)))
					.addContainerGap(416, Short.MAX_VALUE))
		);
		gl_profilePanel.setVerticalGroup(
			gl_profilePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_profilePanel.createSequentialGroup()
					.addGap(64)
					.addGroup(gl_profilePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOutstandingFees)
						.addComponent(lblOutStandingFees))
					.addGap(18)
					.addGroup(gl_profilePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCard)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_profilePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblExpiryDate)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_profilePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCvc)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_profilePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFirstName)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_profilePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLastName)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnPay)
					.addGap(18)
					.addComponent(lblError)
					.addContainerGap(313, Short.MAX_VALUE))
		);
		profilePanel.setLayout(gl_profilePanel);
		setLayout(groupLayout);
		

	}
}
