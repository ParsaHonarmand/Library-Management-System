package librarysystem.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import librarysystem.LibrarySystem;
import librarysystem.managers.MaterialManager;
import librarysystem.materials.Material;
import librarysystem.materials.MaterialStatus;

public class HoldGUIScreen {

	private JFrame frame;
	private JTextField textField;
	private LibrarySystem librarySystem;
	JList list;
	JList listHold;

	/**
	 * Launch the application.
	 */
	public static void HoldScreen(LibrarySystem librarySystem) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HoldGUIScreen window = new HoldGUIScreen(librarySystem);
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
	public HoldGUIScreen(LibrarySystem librarySystem) {
		this.librarySystem = librarySystem;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(192, 192, 192));
		frame.setTitle("Hold book Webpage");
		frame.setBounds(100, 100, 1192, 688);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel Banner = new JLabel("");
		Banner.setIcon(new ImageIcon("/Users/zobiaasad/Desktop/mru.png"));
		Banner.setForeground(new Color(0, 0, 128));
		Banner.setBackground(new Color(0, 0, 128));

		// setLayout(new FlowLayout());
		// JList list = new JList();
		// search(textField.getText());

		JLabel lblSearch = new JLabel("Search");
		textField = new JTextField();
		list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listHold = new JList();
		textField.setColumns(10);
		
		DefaultListModel dlm = new DefaultListModel();
		
		JLabel lblAvailableBooks = new JLabel("Available books");

		
		
		
		JButton btnNewButton = new JButton("Enter");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				list.setListData(searchString(textField.getText()).toArray());
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
		JButton btnPutOnHold = new JButton("Put on hold list");
		btnPutOnHold.addMouseListener(new MouseAdapter() {
			
			public void mousePressed(MouseEvent e) {
				dlm.addElement(list.getSelectedValue());
				listHold.setModel(dlm);
			}
		});

		
		//JScrollPane scrollPane = new JScrollPane(list);
		// list.setVisible(searchString(textField.getText()));
		// list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//scrollPane.setViewportView(list);
		// .add(new JScrollPane(list));
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				System.out.println("Book added to hold");

			}
		});
		
		JLabel lblHoldList = new JLabel("Hold list");
		
		JButton button = new JButton("New button");
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(422)
					.addComponent(lblAvailableBooks)
					.addPreferredGap(ComponentPlacement.RELATED, 407, Short.MAX_VALUE)
					.addComponent(lblHoldList)
					.addGap(215))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblSearch)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 357, Short.MAX_VALUE)
							.addComponent(btnPutOnHold)
							.addGap(281)
							.addComponent(button))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(41)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addGap(187)
							.addComponent(list, GroupLayout.PREFERRED_SIZE, 432, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(Banner, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(listHold, GroupLayout.PREFERRED_SIZE, 431, GroupLayout.PREFERRED_SIZE)))
					.addGap(45))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSearch)
								.addComponent(btnNewButton))
							.addGap(42))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(button)
								.addComponent(btnPutOnHold))
							.addGap(18)))
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(list, GroupLayout.PREFERRED_SIZE, 389, GroupLayout.PREFERRED_SIZE)
								.addComponent(listHold, GroupLayout.PREFERRED_SIZE, 389, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblAvailableBooks)
								.addComponent(lblHoldList))
							.addGap(126))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(Banner, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
							.addGap(292)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))))
		);
		frame.getContentPane().setLayout(groupLayout);
	}

	// Returns the list of strings found from the search (used for the JList)
	private List<Material> searchString(String inputSearch) {
		System.out.println("input search: " + inputSearch);
		MaterialManager materialManager = librarySystem.getMaterialManager();
		List<Material> listMaterial = materialManager.search(inputSearch, MaterialStatus.AVAILABLE);
		for (Material material : listMaterial) {
			System.out.println("Found: " + material.toString());
		}
		return listMaterial;
	}
}
