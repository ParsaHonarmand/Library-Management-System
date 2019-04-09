package librarysystem.gui;

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

public class AccountGUI extends JPanel {

	private LibrarySystem librarySystem;

	/**
	 * Create the panel.
	 */
	public AccountGUI(LibrarySystem librarySystem) {
		this.librarySystem = librarySystem;
		
		this.setBackground(Color.WHITE);
		this.setBounds(0, 0, 1075, 747);

		/**
		 * Setting buttons for further implementation
		 */
		JButton profile = new JButton("Profile");
		profile.setHorizontalAlignment(SwingConstants.LEFT);
		profile.setBounds(6, 50, 84, 29);

		JButton changePassword = new JButton("Change Password");
		changePassword.setBounds(6, 91, 154, 29);
		changePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				librarySystem.updateGUI(new PasswordGUI(librarySystem));
			}
		});
		JButton payFee = new JButton("Pay Fees");
		payFee.setBounds(6, 132, 97, 29);
		payFee.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				new PayGUI(librarySystem);
			}
		});

		JButton materials = new JButton("Materials");
		materials.setBounds(6, 173, 101, 29);
		materials.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				new MaterialsGUI(librarySystem);
			}
		});

		JButton reservations = new JButton("Reservations");
		reservations.setBounds(6, 214, 124, 29);

		JButton logout = new JButton("Logout");
		logout.setBounds(6, 712, 88, 29);
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		/**
		 * Setting panel characteristics via groupLayout
		 */
		JPanel profilePanel = new JPanel();
		profilePanel.setBounds(218, 50, 851, 691);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout
								.createSequentialGroup().addContainerGap().addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(profile).addComponent(changePassword).addComponent(payFee)
										.addComponent(materials).addComponent(reservations).addComponent(logout))
								.addGap(58).addComponent(profilePanel, GroupLayout.DEFAULT_SIZE, 858, Short.MAX_VALUE).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addGap(50)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(profilePanel, GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup().addComponent(profile).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(changePassword).addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(payFee).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(materials).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(reservations)
								.addPreferredGap(ComponentPlacement.RELATED, 482, Short.MAX_VALUE).addComponent(logout)))
				.addContainerGap()));

		setLayout(groupLayout);
		


		this.librarySystem.updateGUI(this);
		setLayout(null);
		add(profile);
		add(changePassword);
		add(payFee);
		add(materials);
		add(reservations);
		add(logout);
		add(profilePanel);
		profilePanel.setLayout(null);
	}
}
