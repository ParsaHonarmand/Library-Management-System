package librarysystem.gui;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
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
import java.awt.Color;
import javax.swing.SwingConstants;

public class LoginGUI extends JPanel {
	
	private final LibrarySystem librarySystem;
	
	private JTextField usernameField;
	private JPasswordField passwordField;

	/**
	 * Create the panel.
	 */
	public LoginGUI(LibrarySystem librarySystem) {
		this.librarySystem = librarySystem;
		Dimension dimension = new Dimension(541, 40);

		this.librarySystem.updateGUI(this);
		setLayout(null);
		
		/**
		 * Setting panel characteristics 
		 */
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 245, 245));
		panel.setBounds(259, 230, 724, 315);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblBanner = new JLabel("");
		lblBanner.setBounds(15, 0, 1250, 200);
		lblBanner.setIcon(new ImageIcon("resources/banner_img.png"));
		setLayout(null); 
		this.add(lblBanner);
		
		/**
		 * Labels used to display information to user in order to notify of actions performed or data input requirments 
		 */
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(110, 80, 105, 29);
		panel.add(lblUsername);
		lblUsername.setForeground(new Color(0, 102, 204));
		lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(116, 147, 110, 44);
		panel.add(lblPassword);
		lblPassword.setForeground(new Color(0, 102, 204));
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		
		JLabel lblRegister = new JLabel("No account ? Register Now !");
		lblRegister.setBounds(141, 226, 267, 32);
		panel.add(lblRegister);
		lblRegister.setForeground(new Color(51, 102, 102));
		lblRegister.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		
		/**
		 * Textfeilds that allow for input from user
		 */
		usernameField = new JTextField();
		usernameField.setHorizontalAlignment(SwingConstants.CENTER);
		usernameField.setForeground(new Color(51, 102, 102));
		usernameField.setBounds(238, 73, 378, 40);
		panel.add(usernameField);
		usernameField.setMinimumSize(dimension);
		usernameField.setMaximumSize(dimension);
		usernameField.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setForeground(new Color(51, 102, 102));
		passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		passwordField.setBounds(238, 152, 378, 40);
		panel.add(passwordField);
		passwordField.setMinimumSize(dimension);
		passwordField.setMaximumSize(dimension);
		
		/**
		 * Used to login in to the system and verification validity of user
		 */
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(423, 220, 175, 46);
		panel.add(btnLogin);
		btnLogin.setForeground(new Color(0, 102, 204));
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				User user = librarySystem.getUserManager().login(usernameField.getText(), passwordField.getText());
				if (user == null) {
					JOptionPane.showMessageDialog(librarySystem.getFrame(), "Username and password are not valid. Try again.");
				} if (user.getOverdueFee()>50) {
					librarySystem.updateGUI(new overDueGIU(librarySystem));
				} else {
					librarySystem.updateGUI(new HomeGUI(librarySystem));
				}
			}
		});
		btnLogin.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		/**
		 * Used to insert image on to panel
		 */
		JLabel bckgGifImg = new JLabel("");
		bckgGifImg.setBounds(0, 0, librarySystem.WIDTH, librarySystem.HEIGHT);
		add(bckgGifImg);
		bckgGifImg.setIcon(new ImageIcon("resources/libwall.jpg"));
		

		this.librarySystem.updateGUI(this);
	}
}
