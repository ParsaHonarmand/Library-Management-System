package librarysystem.gui;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.LayoutStyle.ComponentPlacement;

import librarysystem.LibrarySystem;
import librarysystem.users.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginGUI extends JPanel {
	
	private final LibrarySystem librarySystem;
	
	private JTextField usernameField;
	private JPasswordField passwordField;

	/**
	 * Create the panel.
	 */
	public LoginGUI(LibrarySystem librarySystem) {
		this.librarySystem = librarySystem;
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		usernameField = new JTextField();
		Dimension dimension = new Dimension(541, 40);
		usernameField.setMinimumSize(dimension);
		usernameField.setMaximumSize(dimension);
		usernameField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		usernameField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		passwordField = new JPasswordField();
		passwordField.setMinimumSize(dimension);
		passwordField.setMaximumSize(dimension);
		
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				User user = librarySystem.getUserManager().login(usernameField.getText(), passwordField.getText());
				if (user == null) {
					JOptionPane.showMessageDialog(librarySystem.getFrame(), "Username and password are not valid. Try again.");
				} else {
					librarySystem.updateGUI(new HomeGUI(librarySystem));
				}
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(138, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblUsername)
								.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
							.addGap(36)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(usernameField, GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(260)
							.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
							.addGap(49)
							.addComponent(btnRegister, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)))
					.addGap(178))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(234)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(usernameField, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
					.addGap(57)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
					.addGap(55)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRegister, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(219, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

		this.librarySystem.updateGUI(this);
	}
}
