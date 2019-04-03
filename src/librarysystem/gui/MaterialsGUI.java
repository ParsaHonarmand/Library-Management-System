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

import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import librarysystem.LibrarySystem;

public class MaterialsGUI extends JPanel {
	
	private LibrarySystem librarySystem;

	/**
	 * Create the panel.
	 */
	public MaterialsGUI() {
		this.setBackground(Color.WHITE);
		this.setBounds(0, 0, 1075, 747);
		
		JButton profile = new JButton("Profile");
		profile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		profile.setHorizontalAlignment(SwingConstants.LEFT);
		profile.setBounds(0,0,100,100);
		
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
		
		JPanel profilePanel = new JPanel();
		
		JButton btnNewButton = new JButton("Home"); //Fake button will be deleted later, show purpose 
		
		JButton btnNewButton_1 = new JButton("Browse"); //Fake button will be deleted later, show purpose
		
		JButton btnNewButton_2 = new JButton("Account"); //Fake button will be deleted later, show purpose
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(changePassword, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(logout)
								.addComponent(profile, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(payFee, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(materials, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(reservations, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(58)
							.addComponent(profilePanel, GroupLayout.DEFAULT_SIZE, 851, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 377, GroupLayout.PREFERRED_SIZE)
							.addGap(2)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 367, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_2))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(profile)
							.addGap(13)
							.addComponent(changePassword)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(payFee)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(materials)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(reservations)
							.addPreferredGap(ComponentPlacement.RELATED, 471, Short.MAX_VALUE)
							.addComponent(logout))
						.addComponent(profilePanel, GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		JLabel lblNewLabel = new JLabel("Current Materials");
		
		JLabel lblOnHold = new JLabel("On Hold");
		GroupLayout gl_profilePanel = new GroupLayout(profilePanel);
		gl_profilePanel.setHorizontalGroup(
			gl_profilePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_profilePanel.createSequentialGroup()
					.addGap(156)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED, 270, Short.MAX_VALUE)
					.addComponent(lblOnHold)
					.addGap(256))
		);
		gl_profilePanel.setVerticalGroup(
			gl_profilePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_profilePanel.createSequentialGroup()
					.addGap(61)
					.addGroup(gl_profilePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblOnHold))
					.addContainerGap(617, Short.MAX_VALUE))
		);
		profilePanel.setLayout(gl_profilePanel);
		setLayout(groupLayout);
	}

	
	public MaterialsGUI(LibrarySystem librarySystem) {
		this();
		
		this.librarySystem = librarySystem;
		librarySystem.updateGUI(this);
		
	}
}

