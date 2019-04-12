package librarysystem.gui;

import librarysystem.LibrarySystem;
import librarysystem.users.User;
import librarysystem.users.UserType;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


/**
 * GUI class for users to view their outstanding fees and pay them off
 * @author Parsa Honarmand
 */
public class PayGUI extends JPanel {
	

	private LibrarySystem librarySystem;
	private JTextField cardNumberField;
	private JTextField expiryDateField;
	private JTextField cvcField;
	private JTextField firstNameField;
	private JTextField lastNameField;


	/**
	 * Constructor creates the panel, buttons, textfields, etc. 
	 * @param librarySystem The system to base the GUI on
	 */
	public PayGUI(LibrarySystem librarySystem) {
		User currentUser = librarySystem.getUserManager().getCurrentUser();
		double fee = currentUser.getOverdueFee();
		String feeString = new Double(fee).toString();
		
		this.librarySystem = librarySystem;
		this.setBounds(0, 0, librarySystem.WIDTH, librarySystem.HEIGHT);
		this.setBackground(Color.WHITE);
		
		JPanel profilePanel = new JPanel();
		profilePanel.setBounds(211, 270, 1054, 720);
		profilePanel.setBackground(new Color(0, 102, 153));
		librarySystem.setTheme(profilePanel);
		
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
		JLabel lblOutstandingFees = new JLabel("Outstanding Fees:");
		lblOutstandingFees.setBounds(90, 64, 114, 16);

		JLabel lblCard = new JLabel("Card #");
		lblCard.setBounds(90, 103, 41, 16);

		JLabel lblExpiryDate = new JLabel("Expiry date;");
		lblExpiryDate.setBounds(90, 147, 74, 16);

		JLabel lblCvc = new JLabel("CVC:");
		lblCvc.setBounds(90, 191, 30, 16);

		JLabel lblFirstName = new JLabel("First name:");
		lblFirstName.setBounds(90, 235, 70, 16);

		JLabel lblLastName = new JLabel("Last name:");
		lblLastName.setBounds(90, 279, 68, 16);
		
		JLabel lblError = new JLabel("Please enter all your info");
		lblError.setBounds(280, 365, 195, 16);
		lblError.setVisible(false);
		
		JLabel lblFeesToBePaid = new JLabel(String.valueOf(librarySystem.getUserManager().getCurrentUser().getOverdueFee()));
		lblFeesToBePaid.setBounds(338, 64, 150, 16);
		lblFeesToBePaid.setText(feeString);
		
		cardNumberField = new JTextField();
		cardNumberField.setBounds(280, 98, 155, 26);
		cardNumberField.setColumns(10);

		expiryDateField = new JTextField();
		expiryDateField.setBounds(280, 142, 155, 26);
		expiryDateField.setColumns(10);

		cvcField = new JTextField();
		cvcField.setBounds(280, 186, 155, 26);
		cvcField.setColumns(10);

		firstNameField = new JTextField();
		firstNameField.setBounds(280, 230, 155, 26);
		firstNameField.setColumns(10);

		lastNameField = new JTextField();
		lastNameField.setBounds(280, 274, 155, 26);
		lastNameField.setColumns(10);

		
		
		JButton btnPay = new JButton("Pay");
		btnPay.setBounds(316, 312, 75, 29);
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String text1 = cardNumberField.getText(), text2 = expiryDateField.getText(), text3 = cvcField.getText(), text4 = firstNameField.getText(), text5 = lastNameField.getText();
				
				if (fee == 0) {
					lblError.setText("You have no outstanding Fees!");
					lblError.setVisible(true);
				} 
				else if (text1 == null || text1.equals("") || text2 == null || text2.equals("") || text3 == null || text3.equals("") || text4 == null || text4.equals("") || text5 == null || text5.equals("")) {
					lblError.setText("Please enter all your info!");
					lblError.setVisible(true);
				} 
				else {
					currentUser.setOverdueFee(0.00);
					lblFeesToBePaid.setText("0.00");
					lblError.setText("Thank you!");
					lblError.setVisible(true);
				}
			}
		});
		
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

		if (librarySystem.getUserManager().getCurrentUser().getUserType() == UserType.STUDENT || librarySystem.getUserManager().getCurrentUser().getUserType() == UserType.INSTRUCTOR ) {
			btnOrder.setVisible(false);
			btnReceived.setVisible(false);
			btnReturned.setVisible(false);
		}
		this.librarySystem.updateGUI(this);
		
		setLayout(null);
		add(btnHome);
		add(btnReturned);
		add(btnBrowse);
		add(btnReceived);
		add(btnOrder);
		add(btnAccount);
		add(profilePanel);
		profilePanel.setLayout(null);
		profilePanel.add(lblLastName);
		profilePanel.add(lblFirstName);
		profilePanel.add(lblCvc);
		profilePanel.add(lblExpiryDate);
		profilePanel.add(lblCard);
		profilePanel.add(lblOutstandingFees);
		profilePanel.add(cardNumberField);
		profilePanel.add(expiryDateField);
		profilePanel.add(cvcField);
		profilePanel.add(firstNameField);
		profilePanel.add(lastNameField);
		profilePanel.add(lblError);
		profilePanel.add(btnPay);
		profilePanel.add(lblFeesToBePaid);
	
	}
}
