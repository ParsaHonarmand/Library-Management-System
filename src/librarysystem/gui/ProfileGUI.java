package librarysystem.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;

import librarysystem.LibrarySystem;
import librarysystem.managers.UserManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

public class ProfileGUI extends JPanel {

	private LibrarySystem librarySystem;

	/**
	 * Create the panel.
	 */

	public ProfileGUI(LibrarySystem librarySystem) {
		setForeground(new Color(51, 102, 153));
		this.librarySystem = librarySystem;
		
		this.setBackground(new Color(102, 153, 204));
		this.setBounds(0, 0, 1280, 720);
		
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
		
		UserManager user = librarySystem.getUserManager();
		String id = "" + user.getCurrentUser().getId();
		
		JButton home = new JButton("Home"); //Fake button will be deleted later, show purpose 
		
		JButton browse = new JButton("Browse"); //Fake button will be deleted later, show purpose
		
		JButton account = new JButton("Account");
		
		JButton profile = new JButton("Profile");
		
		JLabel name = new JLabel("Your Name:");
		
		JLabel nameDisplay = new JLabel(user.getCurrentUser().getName());
		
		JLabel ID = new JLabel("Your ID:");
		
		JLabel email = new JLabel("Your Email:");
		
		JLabel emailDisplay = new JLabel(user.getCurrentUser().getEmail());
		
		JLabel IDDisplay = new JLabel(id);
		
		JLabel username = new JLabel("Your Username:");
		
		JLabel usernameDisplay = new JLabel(user.getCurrentUser().getUsername());
		
		JLabel profilePic = new JLabel();
		profilePic.setIcon(new ImageIcon("C:\\Users\\Prit Patel\\git\\library-system"));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(home, GroupLayout.PREFERRED_SIZE, 377, GroupLayout.PREFERRED_SIZE)
							.addGap(2)
							.addComponent(browse, GroupLayout.PREFERRED_SIZE, 367, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(account, GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(profile, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(changePassword, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(logout, Alignment.LEADING)
								.addComponent(payFee, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(materials, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(reservations, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(306)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(name)
										.addComponent(ID)
										.addComponent(email)
										.addComponent(username))
									.addGap(47)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(emailDisplay)
										.addComponent(nameDisplay)
										.addComponent(IDDisplay)
										.addComponent(usernameDisplay)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(112)
									.addComponent(profilePic)))
							.addGap(469)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(home)
						.addComponent(browse)
						.addComponent(account))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(profile)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(name)
							.addComponent(nameDisplay)))
					.addGap(13)
					.addComponent(changePassword)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(payFee)
						.addComponent(profilePic))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(materials)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(reservations)
					.addGap(41)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(ID)
						.addComponent(IDDisplay))
					.addGap(167)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(emailDisplay)
						.addComponent(email))
					.addPreferredGap(ComponentPlacement.RELATED, 174, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(username)
						.addComponent(usernameDisplay))
					.addGap(59)
					.addComponent(logout)
					.addContainerGap())
		);
		setLayout(groupLayout);

		this.librarySystem.updateGUI(this);
	}
}
