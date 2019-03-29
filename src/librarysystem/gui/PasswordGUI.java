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

public class PasswordGUI extends JPanel {
	private JTextField currPswrdTextField;
	private JTextField newPswrdTextField;
	private JTextField confirmPswrdTextField;
	private LibrarySystem librarySystem;


	/**
	 * Create the panel.
	 */
	public PasswordGUI() {
		// JPasswordFields
		

		currPswrdTextField = new JPasswordField();;
		currPswrdTextField.setColumns(10);
		
		newPswrdTextField = new JPasswordField();;
		newPswrdTextField.setColumns(10);
		
		confirmPswrdTextField = new JPasswordField();;
		confirmPswrdTextField.setColumns(10);
		
		// Jlabels
		JLabel lblCurrentPassword = new JLabel("Current Password *");
		JLabel lblNewPassword = new JLabel("New Password*");
		JLabel lblConfirmPassword = new JLabel("Confirm Password*");
		
		JLabel lblIncorrectPassword = new JLabel("Incorrect Password");
		lblIncorrectPassword.setFont(new Font("Dialog", Font.BOLD, 10));
		lblIncorrectPassword.setForeground(Color.RED);
		lblIncorrectPassword.setVisible(false);
		
		JLabel lblNonMatchingPswrd = new JLabel("The confirm password does not match ");
		lblNonMatchingPswrd.setForeground(Color.RED);
		lblNonMatchingPswrd.setFont(new Font("Dialog", Font.BOLD, 10));
		lblNonMatchingPswrd.setVisible(false);
		
		// Changing Password 
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currPswrd=currPswrdTextField.getText();
				if(currPswrd.equals(librarySystem.getUserManager().getCurrentUser().getPassword())) {
					String newPswrd =newPswrdTextField.getText();
					String confirmPswrd =confirmPswrdTextField.getText();
					if (newPswrd.equals(confirmPswrd)) {
						librarySystem.getUserManager().getCurrentUser().setPassword(newPswrd);
					}
					else {
						// new and confirm does not match
						lblNonMatchingPswrd.setVisible(true);
						
					}
				}
				else {
					// incorrect current password
					lblIncorrectPassword.setVisible(true);
				}
				
			}
		});
		
		

		
		
		// positioning JPanel components 
		btnChangePassword.setHorizontalAlignment(SwingConstants.LEFT);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(262, Short.MAX_VALUE)
					.addComponent(btnChangePassword)
					.addGap(26))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(69)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblCurrentPassword)
						.addComponent(lblNewPassword)
						.addComponent(lblConfirmPassword))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNonMatchingPswrd)
						.addComponent(lblIncorrectPassword)
						.addComponent(confirmPswrdTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(newPswrdTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(currPswrdTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(127, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(66)
					.addComponent(lblIncorrectPassword)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(currPswrdTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCurrentPassword))
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(newPswrdTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewPassword))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNonMatchingPswrd)
					.addGap(2)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(confirmPswrdTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblConfirmPassword))
					.addGap(33)
					.addComponent(btnChangePassword)
					.addContainerGap(41, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
	public PasswordGUI(LibrarySystem librarySystem) {
		this();
		this.librarySystem = librarySystem;
		librarySystem.updateGUI(this);
		
	}

}
