package librarysystem.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;

import librarysystem.LibrarySystem;
import librarysystem.users.User;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class overDueGIU extends JPanel {
	private JTextField cardNumberField;
	private JTextField expiryDateField;
	private JTextField cvcField;
	private JTextField firstNameField;
	private JTextField lastNameField;
	
	private LibrarySystem librarySystem;

	/**
	 * Create the panel.
	 */
	public overDueGIU(LibrarySystem librarySystem) {
		setLayout(null);
		
		JLabel lblYouHaveBeen = new JLabel("YOU HAVE BEEN BLACKLISTED!!! PLEASE PAY YOUR FEES TO CONTINUE USING THE LIBRARY SERVICES. ");
		lblYouHaveBeen.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblYouHaveBeen.setForeground(Color.RED);
		lblYouHaveBeen.setBounds(10, 0, 994, 159);
		add(lblYouHaveBeen);
		
		JLabel lblOutstandingFees = new JLabel("Outstanding Fees:");
		lblOutstandingFees.setBounds(302, 170, 114, 16);
		add(lblOutstandingFees);
		
		JLabel lblFeesToBePaid = new JLabel("0.0");
		lblFeesToBePaid.setBounds(553, 170, 150, 16);
		add(lblFeesToBePaid);
		
		JLabel lblCard = new JLabel("Card #");
		lblCard.setBounds(302, 197, 41, 16);
		add(lblCard);
		
		cardNumberField = new JTextField();
		cardNumberField.setColumns(10);
		cardNumberField.setBounds(482, 197, 155, 26);
		add(cardNumberField);
		
		expiryDateField = new JTextField();
		expiryDateField.setColumns(10);
		expiryDateField.setBounds(482, 234, 155, 26);
		add(expiryDateField);
		
		cvcField = new JTextField();
		cvcField.setColumns(10);
		cvcField.setBounds(482, 278, 155, 26);
		add(cvcField);
		
		firstNameField = new JTextField();
		firstNameField.setColumns(10);
		firstNameField.setBounds(482, 322, 155, 26);
		add(firstNameField);
		
		lastNameField = new JTextField();
		lastNameField.setColumns(10);
		lastNameField.setBounds(482, 366, 155, 26);
		add(lastNameField);
		
		User currentUser = librarySystem.getUserManager().getCurrentUser();
		double fee = currentUser.getOverdueFee();
		String feeString = new Double(fee).toString();
		
		lblFeesToBePaid.setText(feeString);
		
		JLabel lblError = new JLabel("New label");
		lblError.setBounds(535, 461, 46, 14);
		add(lblError);
		
		JButton btnPay = new JButton("Pay");
		btnPay.setBounds(522, 403, 75, 29);
		add(btnPay);
		btnPay.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
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
					librarySystem.updateGUI(new HomeGUI(librarySystem));
				}
			}
			
			
		});

		
		JLabel lblExpiryDate = new JLabel("Expiry date;");
		lblExpiryDate.setBounds(302, 239, 74, 16);
		add(lblExpiryDate);
		
		JLabel lblCvc = new JLabel("CVC:");
		lblCvc.setBounds(302, 283, 30, 16);
		add(lblCvc);
		
		JLabel lblFirstName = new JLabel("First name:");
		lblFirstName.setBounds(302, 328, 70, 16);
		add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last name:");
		lblLastName.setBounds(304, 372, 68, 16);
		add(lblLastName);
		


	}
}
