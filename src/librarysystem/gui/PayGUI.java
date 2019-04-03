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
import librarysystem.managers.*;

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
	 * @param librarySystem creates an instance of librarySystem
	 */
	public PayGUI(LibrarySystem librarySystem) {
		this.librarySystem = librarySystem;
		
		this.setBackground(new Color(204, 204, 204));
		this.setBounds(0, 0, 1075, 747);

		JButton profile = new JButton("Profile");
		profile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				new ProfileGUI(librarySystem);
			}
		});
		profile.setHorizontalAlignment(SwingConstants.LEFT);
		profile.setBounds(6, 47, 154, 29);

		JButton changePassword = new JButton("Change Password");
		changePassword.setBounds(6, 89, 154, 29);
		changePassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				new PasswordGUI(librarySystem);
			}
		});

		JButton payFee = new JButton("Pay Fees");
		payFee.setBounds(6, 130, 154, 29);

		JButton materials = new JButton("Materials");
		materials.setBounds(6, 171, 154, 29);
		materials.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				new MaterialsGUI(librarySystem);
			}
		});

		JButton reservations = new JButton("Reservations");
		reservations.setBounds(6, 212, 154, 29);
		reservations.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				new ReservationsGUI(librarySystem);
			}
		});

		JButton logout = new JButton("Logout");
		logout.setBounds(6, 712, 88, 29);
		logout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				new LoginGUI(librarySystem);
			}
		});

		JPanel profilePanel = new JPanel();
		profilePanel.setBounds(218, 47, 851, 694);
		profilePanel.setBackground(new Color(102, 153, 204));

		JButton btnHome = new JButton("Home");
		btnHome.setBounds(6, 0, 377, 29);
		btnHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				new HomeGUI(librarySystem);
			}
		});

		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(385, 0, 367, 29);
		btnBrowse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				new BrowseGUI(librarySystem);
			}
		});

		JButton btnAccount = new JButton("Account");
		btnAccount.setBounds(758, 0, 311, 29);

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

		JLabel lblError = new JLabel("Please enter all your info");
		lblError.setBounds(280, 365, 195, 16);
		lblError.setVisible(false);
		
		JLabel lblFeesToBePaid = new JLabel("");
		lblFeesToBePaid.setBounds(338, 64, 150, 16);
		
		User currentUser = librarySystem.getUserManager().getCurrentUser();
		double fee = currentUser.getOverdueFee();
		String feeString = new Double(fee).toString();
		
		lblFeesToBePaid.setText(feeString);
			
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

		this.librarySystem.updateGUI(this);
		
		setLayout(null);
		add(changePassword);
		add(logout);
		add(profile);
		add(payFee);
		add(materials);
		add(reservations);
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
		add(btnHome);
		add(btnBrowse);
		add(btnAccount);
	}
}
