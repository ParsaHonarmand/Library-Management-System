package librarysystem.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
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
		this();
		
		this.librarySystem = librarySystem;
		librarySystem.updateGUI(this);
		

	}

	public ProfileGUI() {
		UserManager user = new UserManager(librarySystem);
		String name = user.getCurrentUser().getName();
		String username = user.getCurrentUser().getUsername();
		String email = user.getCurrentUser().getEmail();
		int idInt = user.getCurrentUser().getId();
		String id = Integer.toString(idInt);
		
		JLabel nameLabel = new JLabel(name);
		
		JLabel nameText = new JLabel("Name:");
		
		JLabel emailText = new JLabel("Email:");
		
		JLabel emailLabel = new JLabel(email);
		
		JLabel idText = new JLabel("ID:");
		
		JLabel usernameText = new JLabel("Username:");
		
		JLabel IDLabel = new JLabel(id);
		
		JLabel usernameLabel = new JLabel(username);
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(309)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(nameText)
						.addComponent(idText))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(IDLabel)
						.addComponent(nameLabel, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
					.addGap(51)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(emailText)
							.addGap(18)
							.addComponent(emailLabel))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(usernameText)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(usernameLabel)))
					.addContainerGap(363, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(44)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(nameLabel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(nameText)
						.addComponent(emailText)
						.addComponent(emailLabel))
					.addGap(85)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(idText)
						.addComponent(usernameText)
						.addComponent(IDLabel)
						.addComponent(usernameLabel))
					.addContainerGap(558, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
	}
}
