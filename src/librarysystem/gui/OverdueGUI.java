package librarysystem.gui;

import librarysystem.LibrarySystem;
import librarysystem.users.User;
import librarysystem.users.UserType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OverdueGUI extends JPanel {
	private JTextField cardNumberField;
	private JTextField expiryDateField;
	private JTextField cvcField;
	private JTextField firstNameField;
	private JTextField lastNameField;
	
	private LibrarySystem librarySystem;

	/**
	 * Create the panel.
	 * @param librarySystem The system to base the GUI on
	 */
	public OverdueGUI(LibrarySystem librarySystem) {
		setLayout(null);
		this.setBounds(0, 0, librarySystem.WIDTH, librarySystem.HEIGHT);
		
		JLabel lblYouHaveBeen = new JLabel(" YOU HAVE BEEN BLACKLISTED !!!!!!!!! PLEASE PAY YOUR FEES TO CONTINUE USING THE LIBRARY SERVICES. ");
		lblYouHaveBeen.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblYouHaveBeen.setForeground(new Color(255, 255, 255));
		lblYouHaveBeen.setBounds(84, 593, 1114, 55);
		add(lblYouHaveBeen);
		
		User currentUser = librarySystem.getUserManager().getCurrentUser();
		double fee = currentUser.getOverdueFee();
		String feeString = new Double(fee).toString();
		
		this.librarySystem = librarySystem;
		this.setBounds(0, 0, librarySystem.WIDTH, librarySystem.HEIGHT);
		this.setBackground(Color.WHITE);
		
		
		this.setBackground(new Color(0, 102, 153));
		librarySystem.setTheme(this);
		

		

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
					librarySystem.updateGUI(new HomeGUI(librarySystem));
				}
			}
		});
		
		
		
		JButton btnLogOut = new JButton("Logout");
		btnLogOut.setBounds(98, 685, 110, 43);
		add(btnLogOut);
		
		/**
		 * Adding action listeners to the buttons above such that they redirect the user to a different panel
		 */
		
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				librarySystem.getUserManager().logout();
				new LoginGUI(librarySystem);
			}
		});

		
		this.librarySystem.updateGUI(this);

		this.setLayout(null);
		this.add(lblLastName);
		this.add(lblFirstName);
		this.add(lblCvc);
		this.add(lblExpiryDate);
		this.add(lblCard);
		this.add(lblOutstandingFees);
		this.add(cardNumberField);
		this.add(expiryDateField);
		this.add(cvcField);
		this.add(firstNameField);
		this.add(lastNameField);
		this.add(lblError);
		this.add(btnPay);
		this.add(lblFeesToBePaid);
	
	}
}
