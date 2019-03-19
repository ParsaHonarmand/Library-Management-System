package librarysystem.gui;

import librarysystem.LibrarySystem;
import librarysystem.materials.MaterialType;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

public class GUISearch {
	
	private final LibrarySystem librarySystem;
	
	private MaterialType materialType;
	private JFrame frame;
	private JTextField txtNameOfMaterial;
	
	/**
	 * Launch the application.
	 */
	public void open() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public GUISearch(LibrarySystem librarySystem, MaterialType materialType) {
		this.librarySystem = librarySystem;
		this.materialType = materialType;
		
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(192, 192, 192));
		frame.setTitle(materialType.getNiceName() + " Webpage");
		frame.setBounds(100, 100, 848, 518);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel Banner = new JLabel("");
		// TODO: Issue #1
		Banner.setIcon(new ImageIcon("/Users/zobiaasad/Desktop/mru.png"));
		Banner.setForeground(new Color(0, 0, 128));
		Banner.setBackground(new Color(0, 0, 128));
		
		JList list = new JList();
		
		JLabel lblSearch = new JLabel("Search");
		
		txtNameOfMaterial = new JTextField();
		txtNameOfMaterial.setText("Name of " + materialType.getNiceName());
		txtNameOfMaterial.setColumns(10);
		
		JButton btnNewButton = new JButton(">");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// check if .getText() = book title, author, book ID or any other book attributes
				// if not existent, dialog box pops up w/ error msg "not found"
				System.out.println(materialType.getNiceName() + " Not Found");
			}
		});
		
		JButton btnNewButton_1 = new JButton("<");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				StudentScreen student = new StudentScreen(librarySystem);
				student.StudentGUI(librarySystem);
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
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
														.addComponent(btnNewButton_1, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
														.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
																.addGap(9)
																.addComponent(lblSearch)))
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(txtNameOfMaterial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
												.addContainerGap(570, Short.MAX_VALUE))
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addGroup(groupLayout.createSequentialGroup()
														.addComponent(Banner, GroupLayout.DEFAULT_SIZE, 799, Short.MAX_VALUE)
														.addGap(17))
												.addGroup(groupLayout.createSequentialGroup()
														.addComponent(list, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
														.addGap(98)))))
		);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(Banner, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
								.addGap(42)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblSearch)
										.addComponent(txtNameOfMaterial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnNewButton))
								.addGap(32)
								.addComponent(list, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
								.addGap(191)
								.addComponent(btnNewButton_1)
								.addContainerGap(49, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
