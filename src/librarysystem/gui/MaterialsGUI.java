package librarysystem.gui;

import librarysystem.LibrarySystem;
import librarysystem.materials.Material;
import librarysystem.materials.MaterialType;
import librarysystem.searching.AuthorComparator;
import librarysystem.searching.TitleComparator;
import librarysystem.users.UserType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * GUI class for users to manage their materials (renew, cancel, borrow, return)
 * @author Parsa Honarmand
 * @author Rory Skipper
 */
public class MaterialsGUI extends JPanel {
	
	//fields
	private final LibrarySystem librarySystem;
	
	private JTable tableMaterials;
	private JTable tableHold;
	private List<Material> tableContents = new ArrayList<>();
	private List<Material> holdContents = new ArrayList<>();
	private Material selectedMaterial = null;
	private JMenuItem returnMenuItem = new JMenuItem("Return"), borrowMenuItem = new JMenuItem("Borrow"), renewMenuItem = new JMenuItem("Renew"), cancelMenuItem = new JMenuItem("Cancel");
	private JPopupMenu materialsMenu;
	private JPopupMenu holdsMenu;
	private int selectedRow = -1;

	/**
	 * Constructor that creates the GUI including buttons, Jtables, Jmenus, etc
	 * @param librarySystem The system to base the GUI on
	 * 
	 */
	public MaterialsGUI(LibrarySystem librarySystem) {
		this.librarySystem = librarySystem;
		this.setBounds(0, 0, librarySystem.WIDTH, librarySystem.HEIGHT);
		
		JPanel profilePanel = new JPanel();
		profilePanel.setBounds(211, 270, 1054, 720);
		librarySystem.setTheme(profilePanel);

		JLabel profilePic = new JLabel();
		profilePic.setBounds(50, 281, 150, 150);
		profilePic.setIcon(new ImageIcon("resources/profile.png"));
		setLayout(null);
		add(profilePic);
		
		
		JLabel lblBanner = new JLabel("");
		lblBanner.setBounds(15, 15, 1250, 200);
		lblBanner.setIcon(new ImageIcon("resources/banner_img.png"));
		setLayout(null);
		add(lblBanner);
		/**
		 * General buttons constant throughout all panels
		 */
		JButton btnHome = new JButton("Home");
		btnHome.setBounds(80, 225, 120, 30);
		btnHome.setForeground(new Color(0, 0, 128));
		add(btnHome);
		
		JButton btnReturned = new JButton("Returned");
		btnReturned.setBounds(280, 225, 120, 30);
		btnReturned.setForeground(new Color(0, 0, 128));
		add(btnReturned);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(480, 225, 120, 30);
		btnBrowse.setForeground(new Color(0, 0, 128));
		add(btnBrowse);
		
		JButton btnReceived = new JButton("Received");
		btnReceived.setBounds(1080, 225, 120, 30);
		btnReceived.setForeground(new Color(0, 0, 128));
		add(btnReceived);
		
		JButton btnOrder = new JButton("Order");
		btnOrder.setBounds(880, 225, 120, 30);
		btnOrder.setForeground(new Color(0, 0, 128));
		add(btnOrder);
		
		JButton btnAccount = new JButton("Account");
		btnAccount.setBounds(680, 225, 120, 30);
		btnAccount.setForeground(new Color(0, 0, 128));
		add(btnAccount);
		
		
		JButton btnChanPswrdLeft = new JButton("Change Password");
		btnChanPswrdLeft.setBounds(50, 450, 158, 60);
		add(btnChanPswrdLeft);
		
		JButton btnPayFees = new JButton("Pay Fees");
		btnPayFees.setBounds(50, 508, 158, 60);
		add(btnPayFees);
		
		JButton btnMaterials = new JButton("Materials");
		btnMaterials.setBounds(50, 564, 158, 60);
		add(btnMaterials);
		
		JButton btnReservations = new JButton("Reservations");
		btnReservations.setBounds(50, 621, 158, 60);
		if(librarySystem.getUserManager().getCurrentUser().getUserType()==UserType.INSTRUCTOR) {
			add(btnReservations);
		}
		
		JButton btnLogOut = new JButton("Logout");
		btnLogOut.setBounds(98, 685, 110, 43);
		add(btnLogOut);
		
		/**
		 * Adding action listeners to the buttons above such that they redirect the user to a different panel
		 */
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new HomeGUI(librarySystem);
			}
		});
		btnReturned.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ReturnGUI(librarySystem);
			}
		});
		
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BrowseGUI(librarySystem);
			}
		});
		
		btnReceived.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ReceiveGUI(librarySystem);
			}
		});
		
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new OrderGUI(librarySystem);
			}
		});
		
		btnAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ProfileGUI(librarySystem);
			}
		});
		
		btnPayFees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PayGUI(librarySystem);
			}
		});
		
		btnChanPswrdLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PasswordGUI(librarySystem);
			}
		});
		btnMaterials.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MaterialsGUI(librarySystem);
			}
		});
		
		btnReservations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ReservationsGUI(librarySystem);
			}
		});
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				librarySystem.getUserManager().logout();
				new LoginGUI(librarySystem);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 42, 342, 395);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(380, 42, 387, 395);
		

		JComboBox stringSortBox = new JComboBox();
		stringSortBox.setBounds(779, 30, 262, 44);
		stringSortBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		stringSortBox.setModel(new DefaultComboBoxModel(new String[] { "Title", "Author" }));

		JComboBox materialTypeSortBox = new JComboBox();
		materialTypeSortBox.setBounds(779, 88, 262, 44);
		materialTypeSortBox.setToolTipText("Select Material Type");
		materialTypeSortBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		materialTypeSortBox.setModel(new DefaultComboBoxModel(MaterialType.values()));		

		JLabel lblSortBy = new JLabel("Sort by:");
		lblSortBy.setBounds(786, 5, 50, 17);
		lblSortBy.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblCurrentMaterials = new JLabel("Current items");		
		lblCurrentMaterials.setBounds(26, 6, 86, 16);
		JLabel lblItemsOnHold = new JLabel("Items on hold");
		lblItemsOnHold.setBounds(393, 6, 87, 16);

		JButton btnSort = new JButton("Sort");
		btnSort.setBounds(964, 154, 75, 29);
		btnSort.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSort.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				String stringSort = (String) stringSortBox.getSelectedItem();
				MaterialType materialType = (MaterialType) materialTypeSortBox.getSelectedItem();

				sort(stringSort, materialType);
			}
		});
		
		
		
	
		String[] columnMaterials = { "icon", "Materials" };
		DefaultTableModel model = new DefaultTableModel(new Object[][] {}, columnMaterials) {
			public Class getColumnClass(int column) {
				if (column == 0)
					return Icon.class;
				if (column == 1)
					return String.class;
				return String.class;
			}

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		scrollPane.setColumnHeaderView(tableMaterials);
		tableMaterials = new JTable(model);
		scrollPane.setViewportView(tableMaterials);

		this.tableMaterials.setPreferredScrollableViewportSize(tableMaterials.getPreferredSize());
		this.tableMaterials.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.tableMaterials.getColumnModel().getColumn(0).setMaxWidth(100);
		

		this.tableMaterials.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("button: " + e.getButton());
				if (e.getButton() == MouseEvent.BUTTON3) {
					selectedRow = tableMaterials.rowAtPoint(e.getPoint());
					System.out.println("row: " + selectedRow);
					selectedMaterial = tableContents.get(selectedRow);
					materialsMenu.show(tableMaterials, e.getX(), e.getY());
				}
			}
		});
		
		
		
		
		String[] columnHolds = { "icon", "On Hold" };
		DefaultTableModel model1 = new DefaultTableModel(new Object[][] {}, columnHolds) {
			//  Returning the Class of each column will allow different
			//  renderers to be used based on Class
			public Class getColumnClass(int column) {
				if (column == 0)
					return Icon.class;
				if (column == 1)
					return String.class;
				return String.class;
			}

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		this.tableHold = new JTable (model1);
		this.tableHold.setPreferredScrollableViewportSize(new Dimension(150, 0));
		this.tableHold.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane_1.setViewportView(tableHold);
		
		this.tableHold.getColumnModel().getColumn(0).setMaxWidth(100);

		this.tableHold.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("button: " + e.getButton());
				if (e.getButton() == MouseEvent.BUTTON3) {
					selectedRow = tableHold.rowAtPoint(e.getPoint());
					System.out.println("row: " + selectedRow);
					selectedMaterial = holdContents.get(selectedRow);
					holdsMenu.show(tableHold, e.getX(), e.getY());
				}
			}
		});
		
		
		this.materialsMenu = new JPopupMenu();
		this.materialsMenu.add(this.returnMenuItem);
		this.materialsMenu.add(this.renewMenuItem);
		
		this.holdsMenu = new JPopupMenu();
		this.holdsMenu.add(this.borrowMenuItem);
		this.holdsMenu.add(this.cancelMenuItem);
		
		this.setupPopupMenu();

		
		
		profilePanel.setLayout(null);
		profilePanel.add(scrollPane);
		
		JScrollBar contentScroll = new JScrollBar();
		scrollPane.setRowHeaderView(contentScroll);
		profilePanel.add(scrollPane_1);
		
		JScrollBar holdScroll = new JScrollBar();
		scrollPane_1.setRowHeaderView(holdScroll);
		if (librarySystem.getUserManager().getCurrentUser().getUserType() == UserType.STUDENT || librarySystem.getUserManager().getCurrentUser().getUserType() == UserType.INSTRUCTOR ) {
			btnOrder.setVisible(false);
			btnReceived.setVisible(false);
		}
		
		profilePanel.add(lblCurrentMaterials);
		profilePanel.add(lblItemsOnHold);
		profilePanel.add(lblSortBy);
		profilePanel.add(stringSortBox);
		profilePanel.add(materialTypeSortBox);
		profilePanel.add(btnSort);
		/*
		 * Make order and received functionality accessible to only the librarian 
		 */
		if (librarySystem.getUserManager().getCurrentUser().getUserType() == UserType.STUDENT || librarySystem.getUserManager().getCurrentUser().getUserType() == UserType.INSTRUCTOR ) {
			btnOrder.setVisible(false);
			btnReceived.setVisible(false);
		}
		
		setLayout(null);


		add(profilePanel);
		sort("Title", MaterialType.ALL);
		this.librarySystem.updateGUI(this);
	}
	
	/**
	 * Method to create all the options when a material is right-clicked
	 */
	public void setupPopupMenu() {
		
		this.returnMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				librarySystem.getMaterialManager().returnMaterial(librarySystem.getUserManager().getCurrentUser(), selectedMaterial);
				((DefaultTableModel) tableMaterials.getModel()).removeRow(selectedRow);
				selectedRow = -1;
				materialsMenu.hide();
			}});

		this.borrowMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				librarySystem.getMaterialManager().borrowMaterial(librarySystem.getUserManager().getCurrentUser(), selectedMaterial);
				((DefaultTableModel) tableHold.getModel()).removeRow(selectedRow);
				selectedRow = -1;
				holdsMenu.hide();
			}
		});
		
		this.renewMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				librarySystem.getMaterialManager().putOnHold(librarySystem.getUserManager().getCurrentUser(), selectedMaterial);
				((DefaultTableModel) tableMaterials.getModel()).removeRow(selectedRow);
				//selectedRow = -1;
				materialsMenu.hide();
			}
		});
		
		this.cancelMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				librarySystem.getMaterialManager().reshelveMaterial(selectedMaterial);
				((DefaultTableModel) tableHold.getModel()).removeRow(selectedRow);
				selectedRow = -1;
				holdsMenu.hide();
			}
		});
		
	}

	
	/**
	 * method to fill the Jtables and sort the materials based on title/author and material type
	 * @param stringSort The string to sort with
	 * @param materialType The type of materials to filter for
	 */
	public void sort(String stringSort, MaterialType materialType) {
		this.tableContents.clear();
		this.holdContents.clear();
	
		//List<Material> userBorrowed = this.librarySystem.getUserManager().getCurrentUser().getOnHold();
		//this.tableContents = userBorrowed;
	
		if (materialType == MaterialType.ALL) {
			this.tableContents = this.librarySystem.getUserManager().getCurrentUser().getBorrowed();
			this.holdContents = this.librarySystem.getUserManager().getCurrentUser().getOnHold();
		} 
		//else {
		//	this.tableContents = librarySystem.getUserManager().getCurrentUser().getUniqueMaterials(MaterialStatus.AVAILABLE, materialType);
		//}
	
		if (stringSort.equalsIgnoreCase("Title")) {
			this.tableContents.sort(new TitleComparator());
			this.holdContents.sort(new TitleComparator());
		} 
		else if (stringSort.equalsIgnoreCase("Author")) {
			this.tableContents.sort(new AuthorComparator());
			this.holdContents.sort(new AuthorComparator());
		}

		DefaultTableModel model = (DefaultTableModel) tableMaterials.getModel();
		for (int i = model.getRowCount() - 1; i >= 0; i--) {
			model.removeRow(i);
		}
		for (int i = 0; i < this.tableContents.size(); i++) {
			model.addRow(new Object[] { null, this.tableContents.get(i).getNiceName() });
		}

		DefaultTableModel model1 = (DefaultTableModel) tableHold.getModel();
		for (int i = model1.getRowCount() - 1; i >= 0; i--) {
			model1.removeRow(i);
		}
		for (int i = 0; i < this.holdContents.size(); i++) {
			model1.addRow(new Object[] { null, this.holdContents.get(i).getNiceName() });
		}
		
	}
}

