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
import javax.swing.JToolBar;

public class AccountGUI extends JPanel {
	
	private LibrarySystem librarySystem;

	/**
	 * Create the panel.
	 */
	public AccountGUI(LibrarySystem librarySystem) {
		this();
		
		this.librarySystem = librarySystem;
		librarySystem.updateGUI(this);

	}
	
	public AccountGUI()
	{
		this.setBackground(Color.WHITE);
		this.setBounds(0, 0, 1075, 747);
		
		JButton profile = new JButton("Profile");
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
		
		JToolBar toolBar = new JToolBar();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(changePassword)
								.addComponent(payFee)
								.addComponent(materials)
								.addComponent(logout)
								.addComponent(reservations)
								.addComponent(profile))
							.addGap(58)
							.addComponent(profilePanel, GroupLayout.DEFAULT_SIZE, 858, Short.MAX_VALUE))
						.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(profilePanel, GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
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
							.addPreferredGap(ComponentPlacement.RELATED, 482, Short.MAX_VALUE)
							.addComponent(logout)))
					.addContainerGap())
		);
		setLayout(groupLayout);
		
		
	}
}
