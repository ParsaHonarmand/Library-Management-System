package librarysystem.gui;
import librarysystem.LibrarySystem;
import librarysystem.materials.MaterialType;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import java.awt.TextField;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JScrollBar;
import javax.swing.JToolBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentScreen {

	private JFrame frame;
	private LibrarySystem librarySystem;

	public static void StudentGUI(LibrarySystem librarySystem) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentScreen window = new StudentScreen(librarySystem);
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
	public StudentScreen(LibrarySystem librarySystem) {
		this.librarySystem = librarySystem;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(192, 192, 192));
		frame.setTitle("Student Webpage");
		frame.setBounds(100, 100, 848, 518);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel Banner = new JLabel("");
		Banner.setIcon(new ImageIcon("/Users/zobiaasad/Desktop/mru.png"));
		Banner.setForeground(new Color(0, 0, 128));
		Banner.setBackground(new Color(0, 0, 128));
		
		JLabel QuickLink = new JLabel("Quick Links ");
		QuickLink.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		
		JList list = new JList();
		
		JButton Hold = new JButton("Hold Items");
		Hold.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				HoldGUIScreen holdGUI = new HoldGUIScreen(librarySystem);
				holdGUI.HoldScreen(librarySystem);
				frame.setVisible(false);
				// go to screen where items are placed on hold 
			}
		});
		
		JButton Balance = new JButton("Check Balance");
		
		JButton Availability = new JButton("Check Availability");
		
		JButton btnNewButton_3 = new JButton("New button");
		
		JLabel Contact = new JLabel("Contact Us ");
		Contact.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		Contact.setForeground(new Color(0, 0, 0));
		Contact.setBackground(new Color(0, 0, 0));
		
		JLabel InfoNum = new JLabel("General Information   403.234.4567");
		
		JLabel ServiceNum = new JLabel("Library Service Desk   403.222.3333");
		
		JLabel EmailAdd = new JLabel("Email Address     library@mtroyal.ca");
		
		JLabel lblNewLabel = new JLabel("Choose Type to Search ");
		
		JButton btnNewButton = new JButton("Books");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				GUISearch guiSearch = new GUISearch(librarySystem, MaterialType.BOOK);
				guiSearch.open();
				frame.setVisible(false);
			}
		});
		
		JButton btnNewButton_1 = new JButton("Magazines");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				GUISearch guiSearch = new GUISearch(librarySystem, MaterialType.MAGAZINE);
				guiSearch.open();
				frame.setVisible(false);
			}
		});
		
		JButton btnNewButton_2 = new JButton("CDs");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				GUISearch guiSearch = new GUISearch(librarySystem, MaterialType.CD);
				guiSearch.open();
				frame.setVisible(false);
			}
		});
		
		JButton btnNewButton_4 = new JButton("DVDs");
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				GUISearch guiSearch = new GUISearch(librarySystem, MaterialType.DVD);
				guiSearch.open();
				frame.setVisible(false);
			}
		});
		
		JButton btnNewButton_5 = new JButton("eBooks");
		btnNewButton_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				GUISearch guiSearch = new GUISearch(librarySystem, MaterialType.EBOOK);
				guiSearch.open();
				frame.setVisible(false);
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(EmailAdd, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(InfoNum, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(Contact, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(Banner, GroupLayout.DEFAULT_SIZE, 799, Short.MAX_VALUE)
										.addGap(17))
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 463, Short.MAX_VALUE)
										.addComponent(QuickLink, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
										.addGap(58))
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(ServiceNum, GroupLayout.PREFERRED_SIZE, 810, Short.MAX_VALUE)
										.addContainerGap())
									.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
											.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(btnNewButton_4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(btnNewButton_5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addPreferredGap(ComponentPlacement.RELATED, 432, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
											.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
													.addComponent(Balance, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
													.addComponent(Availability, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
													.addComponent(btnNewButton_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
												.addGap(116))
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(Hold, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addGap(18)
												.addComponent(list, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
												.addGap(98)))))))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(Banner, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(QuickLink, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(Hold)
								.addComponent(list, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(Balance)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(Availability)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_3))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNewButton_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_4)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_5)))
					.addGap(8)
					.addComponent(Contact, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(InfoNum)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ServiceNum)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(EmailAdd)
					.addContainerGap(47, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
