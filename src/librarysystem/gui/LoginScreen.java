package librarysystem.gui;

import java.awt.EventQueue;
//import javax.persistence.InheritanceType;
//import librarysystem.managers;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import java.awt.TextField;
import javax.swing.JTextField;

import librarysystem.LibrarySystem;
import librarysystem.users.User;
import librarysystem.users.UserType;

import javax.swing.JTextArea;
import java.awt.Button;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;
import javax.swing.JPasswordField;

public class LoginScreen {
	
	private JFrame frame;
	private JTextField userName;
	private JPasswordField pass;
	
	/**
	 * Launch the application.
	 */
	public static void main(LibrarySystem LS) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen window = new LoginScreen(LS);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public LoginScreen(LibrarySystem LS) {
		
		initialize(LS);
	}
	
	/**
	 * Initialize the contents of the frame.
	 *
	 * @param
	 */
	private void initialize(LibrarySystem LS) {
		
		//LibrarySystem librarySystem = LS;
		frame = new JFrame();
		frame.setBounds(100, 100, 742, 606);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		userName = new JTextField();
		userName.setColumns(10);
		
		JLabel lblUsername = new JLabel("username");
		
		pass = new JPasswordField();
		pass.setColumns(10);
		
		JLabel lblPassword = new JLabel("password");
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				User user = LS.getUserManager().login(userName.getText(), pass.getText());
				if (user == null) {
					JOptionPane.showMessageDialog(frame,
							"Username and password are not valid. Try again.");
				} else if (user.getUserType() == UserType.LIBRARIAN) {
					System.out.println("librarian");
					LibrarianScreen.main(LS);
					frame.setVisible(false);
				} else if (user.getUserType() == UserType.STUDENT) {
					StudentScreen.StudentGUI(LS);
					frame.setVisible(false);
				} else if (user.getUserType() == UserType.ADMINISTRATOR) {
					//open administratorScreenSwing
				} else if (user.getUserType() == UserType.CLERK) {
					//open clerkScreenSwing
				} else if (user.getUserType() == UserType.FACULTY) {
					//open clerkScreenSwing
				} else if (user.getUserType() == UserType.INSTRUCTOR) {
					//open clerkScreenSwing
				} else if (user.getUserType() == UserType.TEACHING_ASSISTANT) {
					//open clerkScreenSwing
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
												.addGap(93)
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
														.addComponent(lblUsername)
														.addComponent(lblPassword))
												.addGap(18)
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
														.addComponent(pass, Alignment.LEADING)
														.addComponent(userName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)))
										.addGroup(groupLayout.createSequentialGroup()
												.addGap(211)
												.addComponent(btnLogin)))
								.addContainerGap(389, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGap(68)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(userName, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblUsername))
								.addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(pass, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblPassword))
								.addGap(18)
								.addComponent(btnLogin)
								.addContainerGap(368, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
	}
}
