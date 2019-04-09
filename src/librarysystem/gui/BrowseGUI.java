package librarysystem.gui;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import librarysystem.LibrarySystem;
import librarysystem.managers.MaterialManager;
import librarysystem.materials.Material;
import librarysystem.materials.MaterialStatus;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import librarysystem.materials.MaterialType;
import librarysystem.searching.AuthorComparator;
import librarysystem.searching.TitleComparator;
import librarysystem.users.UserType;
import librarysystem.users.faculty.Instructor;

import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import javax.swing.JSpinner;
import java.awt.ComponentOrientation;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class BrowseGUI extends JPanel {

	private final LibrarySystem librarySystem;

	private JTable table;
	private List<Material> tableContents = new ArrayList<>();
	private int selectedRow = -1;
	private Material selectedMaterial = null;
	private JMenuItem holdMenuItem = new JMenuItem("Hold"), borrowMenuItem = new JMenuItem("Borrow"), orderMenuItem = new JMenuItem("Order"), reserveMenuItem = new JMenuItem("Reserve");
	private JPopupMenu menu;
	private JButton btnReserve, btnOrder_1;
	private JLabel reserveLabel, orderLabel, infoLabel;
	private JSpinner reservationSpinner, orderSpinner;

	/**
	 * Create the panel.
	 */
	public BrowseGUI(LibrarySystem librarySystem) {
		this.librarySystem = librarySystem;

		JButton home = new JButton("Home");
		home.setBounds(0, 0, 174, 45);
		home.setFont(new Font("Tahoma", Font.PLAIN, 17));
		home.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				new HomeGUI(librarySystem);
			}
		});

		JButton btnReturned = new JButton("Returned");
		btnReturned.setBounds(180, 0, 172, 45);
		btnReturned.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnReturned.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				new ReturnGUI(librarySystem);
			}
		});

		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(358, 0, 172, 45);
		btnBrowse.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnBrowse.setBackground(SystemColor.activeCaption);

		JButton btnOrder = new JButton("Order");
		btnOrder.setBounds(536, 0, 169, 45);
		btnOrder.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				new OrderGUI(librarySystem);
			}
		});

		JButton btnReceived = new JButton("Received");
		btnReceived.setBounds(723, 0, 181, 45);
		btnReceived.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnReceived.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				new ReceiveGUI(librarySystem);
			}
		});

		JButton btnAccount = new JButton("Account");
		btnAccount.setBounds(916, 0, 174, 45);
		btnAccount.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				new ProfileGUI(librarySystem);
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 51, 699, 658);

		JComboBox materialTypeSortBox = new JComboBox();
		materialTypeSortBox.setBounds(723, 178, 363, 44);
		materialTypeSortBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		materialTypeSortBox.setToolTipText("Select Material Type");
		materialTypeSortBox.setModel(new DefaultComboBoxModel(MaterialType.values()));

		JComboBox stringSortBox = new JComboBox();
		stringSortBox.setBounds(723, 103, 363, 44);
		stringSortBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		stringSortBox.setModel(new DefaultComboBoxModel(new String[] { "Title", "Author" }));

		JLabel lblSortBy = new JLabel("Sort by:");
		lblSortBy.setBounds(885, 68, 50, 17);
		lblSortBy.setFont(new Font("Tahoma", Font.PLAIN, 14));


		JButton btnSort = new JButton("Sort");
		btnSort.setBounds(868, 240, 75, 29);
		btnSort.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				String stringSort = (String) stringSortBox.getSelectedItem();
				MaterialType materialType = (MaterialType) materialTypeSortBox.getSelectedItem();

				sort(stringSort, materialType);
			}
		});
		btnSort.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		reservationSpinner = new JSpinner();
		reservationSpinner.setBounds(968, 445, 40, 26);
		reservationSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int maxAmount = librarySystem.getMaterialManager().getAmountAvailableForReservation(selectedMaterial);
				int value = (Integer) reservationSpinner.getValue();
				if (maxAmount < value) {
					infoLabel.setText("There are only " + maxAmount + " " + selectedMaterial.getMaterialType().getNiceName() + "s available to be reserved");
					reservationSpinner.setValue(maxAmount);
				} else if (value < 0) {
					reservationSpinner.setValue(0);
				}
			}
		});
		reservationSpinner.setVisible(false);
		
		reserveLabel = new JLabel("How many would you like to reserve?");
		reserveLabel.setBounds(733, 449, 229, 17);
		reserveLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		reserveLabel.setVisible(false);
		
		btnReserve = new JButton("Reserve");
		btnReserve.setBounds(832, 477, 92, 29);
		btnReserve.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				int amount = (Integer) reservationSpinner.getValue();
				librarySystem.getMaterialManager().reserveMaterial((Instructor) librarySystem.getUserManager().getCurrentUser(), selectedMaterial, amount);
				sort((String) stringSortBox.getSelectedItem(), (MaterialType) materialTypeSortBox.getSelectedItem());
				infoLabel.setText("Reserved " + amount + " " + selectedMaterial.getMaterialType().getNiceName() + "s successfully");
				reserveLabel.setVisible(false);
				btnReserve.setVisible(false);
				reservationSpinner.setVisible(false);
				reservationSpinner.setValue(0);
				selectedRow = -1;
				selectedMaterial = null;
			}
		});
		btnReserve.setVisible(false);
		
		orderLabel = new JLabel("How many would you like to order? ");
		orderLabel.setBounds(732, 568, 222, 17);
		orderLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		orderLabel.setVisible(false);
		
		orderSpinner = new JSpinner();
		orderSpinner.setBounds(960, 564, 40, 26);
		orderSpinner.setVisible(false);
		
		infoLabel = new JLabel("");
		infoLabel.setBounds(733, 305, 333, 122);
		infoLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		btnOrder_1 = new JButton("Order");
		btnOrder_1.setBounds(824, 596, 79, 29);
		btnOrder_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MaterialManager materialManager = librarySystem.getMaterialManager();
				for (int i = 0; i < (Integer) orderSpinner.getValue(); i++) {
					Material newMaterial = selectedMaterial.clone();
					newMaterial.setMaterialStatus(MaterialStatus.ON_ORDER);
					newMaterial.setBarcode(materialManager.getNextBarcode());
					materialManager.addMaterial(newMaterial);
				}
				btnOrder_1.setVisible(false);
				orderLabel.setVisible(false);
				orderSpinner.setVisible(false);
				infoLabel.setText("Ordered " + orderSpinner.getValue() + " " + selectedMaterial.getMaterialType().getNiceName() + "s successfully");
				selectedMaterial = null;
				selectedRow = -1;
				orderSpinner.setValue(0);
			}
		});
		btnOrder_1.setVisible(false);

		//Icon aboutIcon = new ImageIcon("about16.gif");

		String[] columnNames = { "Icon", "Material" };

		DefaultTableModel model = new DefaultTableModel(new Object[][] {}, columnNames) {
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

		this.table = new JTable(model);
		this.table.setPreferredScrollableViewportSize(table.getPreferredSize());
		this.table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.table.getColumnModel().getColumn(0).setMaxWidth(100);

		sort("Title", MaterialType.ALL);

		this.menu = new JPopupMenu();
		this.menu.add(this.holdMenuItem);
		this.menu.add(this.borrowMenuItem);
		UserType userType = this.librarySystem.getUserManager().getCurrentUser().getUserType();
		if (userType == UserType.INSTRUCTOR) {
			this.menu.add(this.reserveMenuItem);
		} else if (userType == UserType.LIBRARIAN) {
			this.menu.add(this.orderMenuItem);
		}

		this.setupPopupMenu();
		
		this.table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("button: " + e.getButton());
				if (e.getButton() == MouseEvent.BUTTON3) {
					selectedRow = table.rowAtPoint(e.getPoint());
					System.out.println("row: " + selectedRow);
					selectedMaterial = tableContents.get(selectedRow);
					menu = new JPopupMenu();
					menu.add(holdMenuItem);
					menu.add(borrowMenuItem);
					UserType userType = librarySystem.getUserManager().getCurrentUser().getUserType();
					if (userType == UserType.INSTRUCTOR) {
						menu.add(reserveMenuItem);
					} else if (userType == UserType.LIBRARIAN) {
						menu.add(orderMenuItem);
					}

					menu.show(table, e.getX(), e.getY());
				}
			}
		});

		scrollPane.setViewportView(this.table);

		this.librarySystem.updateGUI(this);
		setLayout(null);
		add(home);
		add(btnReturned);
		add(btnBrowse);
		add(btnOrder);
		add(scrollPane);
		add(btnReceived);
		add(btnAccount);
		add(stringSortBox);
		add(materialTypeSortBox);
		add(infoLabel);
		add(reserveLabel);
		add(reservationSpinner);
		add(lblSortBy);
		add(orderLabel);
		add(orderSpinner);
		add(btnSort);
		add(btnReserve);
		add(btnOrder_1);
	}
	
	public void setupPopupMenu() {
		this.holdMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				librarySystem.getMaterialManager().putOnHold(librarySystem.getUserManager().getCurrentUser(), selectedMaterial);
				((DefaultTableModel) table.getModel()).removeRow(selectedRow);
				selectedRow = -1;
				menu.hide();
				
			}});

		this.borrowMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				librarySystem.getMaterialManager().borrowMaterial(librarySystem.getUserManager().getCurrentUser(), selectedMaterial);
				((DefaultTableModel) table.getModel()).removeRow(selectedRow);
				selectedRow = -1;
				menu.hide();
			}
		});

		this.orderMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				orderLabel.setVisible(true);
				btnOrder_1.setVisible(true);
				orderSpinner.setVisible(true);
				
			}
		});

		this.reserveMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reserveLabel.setVisible(true);
				btnReserve.setVisible(true);
				reservationSpinner.setVisible(true);
			}
		});
		}

	public void sort(String stringSort, MaterialType materialType) {
		this.tableContents.clear();
		if (materialType == MaterialType.ALL) {
			this.tableContents = librarySystem.getMaterialManager().getUniqueMaterials(MaterialStatus.AVAILABLE);
		} else {
			this.tableContents = librarySystem.getMaterialManager().getUniqueMaterials(MaterialStatus.AVAILABLE, materialType);
		}

		if (stringSort.equalsIgnoreCase("Title")) {
			this.tableContents.sort(new TitleComparator());
		} else if (stringSort.equalsIgnoreCase("Author")) {
			this.tableContents.sort(new AuthorComparator());
		}

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		for (int i = model.getRowCount() - 1; i >= 0; i--) {
			model.removeRow(i);
		}
		for (int i = 0; i < this.tableContents.size(); i++) {
			model.addRow(new Object[] { null, this.tableContents.get(i).getNiceName() });
		}

	}
}

