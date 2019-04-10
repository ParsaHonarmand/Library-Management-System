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
		this.setBounds(0, 0, 1365, 730);
		
		JButton profile = new JButton("Profile");
		profile.setHorizontalAlignment(SwingConstants.LEFT);
		profile.setBounds(6,51,84,29);
		profile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				new ProfileGUI(librarySystem);
			}
		});
		
		JButton changePassword = new JButton("Change Password");
		changePassword.setBounds(6, 92, 154, 29);
		changePassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				new PasswordGUI(librarySystem);
			}
		});
		
		JButton payFee = new JButton("Pay Fees");
		payFee.setBounds(6, 133, 97, 29);
		payFee.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				new PayGUI(librarySystem);
			}
		});
		
		JButton materials = new JButton("Materials");
		materials.setBounds(6, 174, 101, 29);
		materials.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				new MaterialsGUI(librarySystem);
			}
		});
		
		JButton reservations = new JButton("Reservations");
		reservations.setBounds(6, 215, 124, 29);
		reservations.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				new ReservationsGUI(librarySystem);
			}
		});
		
		JButton logout = new JButton("Logout");
		logout.setBounds(6, 652, 88, 29);
		logout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				new LoginGUI(librarySystem);
			}
		});
		
		JButton btnHome = new JButton("Home");
		btnHome.setBounds(166, 0, 174, 45);
		btnHome.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnHome.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				new HomeGUI(librarySystem);
			}
		});
		
		JButton btnReturned = new JButton("Returned");
		btnReturned.setBounds(346, 0, 170, 45);
		btnReturned.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnReturned.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				new ReturnGUI(librarySystem);
			}
		});
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(522, 0, 170, 45);
		btnBrowse.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnBrowse.setBackground(UIManager.getColor("Button.background"));
		btnBrowse.setBackground(SystemColor.activeCaption);
		btnBrowse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				new BrowseGUI(librarySystem);
			}
		});
		
		JButton btnOrder = new JButton("Order");
		btnOrder.setBounds(698, 0, 185, 45);
		btnOrder.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				new OrderGUI(librarySystem);
			}
		});
		
		JButton btnReceived = new JButton("Received");
		btnReceived.setBounds(889, 0, 222, 45);
		btnReceived.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnReceived.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				new ReceiveGUI(librarySystem);
			}
		});
		
		JButton btnAccount = new JButton("Account");
		btnAccount.setBounds(1117, 0, 174, 45);
		btnAccount.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				new ProfileGUI(librarySystem);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 115, 342, 545);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(360, 115, 387, 545);
		

		JComboBox stringSortBox = new JComboBox();
		stringSortBox.setBounds(771, 109, 404, 44);
		stringSortBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		stringSortBox.setModel(new DefaultComboBoxModel(new String[] { "Title", "Author" }));

		JComboBox materialTypeSortBox = new JComboBox();
		materialTypeSortBox.setBounds(771, 171, 404, 44);
		materialTypeSortBox.setToolTipText("Select Material Type");
		materialTypeSortBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		materialTypeSortBox.setModel(new DefaultComboBoxModel(MaterialType.values()));		

		JLabel lblSortBy = new JLabel("Sort by:");
		lblSortBy.setBounds(960, 80, 50, 17);
		lblSortBy.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblCurrentMaterials = new JLabel("Current items");		
		lblCurrentMaterials.setBounds(119, 81, 86, 16);
		JLabel lblItemsOnHold = new JLabel("Items on hold");
		lblItemsOnHold.setBounds(518, 81, 87, 16);

		JButton btnSort = new JButton("Sort");
		btnSort.setBounds(953, 233, 75, 29);
		btnSort.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSort.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				String stringSort = (String) stringSortBox.getSelectedItem();
				MaterialType materialType = (MaterialType) materialTypeSortBox.getSelectedItem();

				sort(stringSort, materialType);
			}
		});
		
		JPanel profilePanel = new JPanel();
		profilePanel.setBounds(166, 51, 1190, 789);

		
	
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
		setLayout(null);
		add(profile);
		add(changePassword);
		add(payFee);
		add(materials);
		add(reservations);
		add(logout);
		add(btnHome);
		add(btnReturned);
		add(btnBrowse);
		add(btnOrder);
		add(btnReceived);
		add(btnAccount);
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
				selectedRow = -1;
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

