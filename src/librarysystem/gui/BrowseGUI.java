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
import librarysystem.users.UserType;
import librarysystem.users.faculty.Instructor;
import searching.AuthorComparator;
import searching.TitleComparator;

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
		home.setFont(new Font("Tahoma", Font.PLAIN, 17));
		home.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				new HomeGUI(librarySystem);
			}
		});

		JButton btnReturned = new JButton("Returned");
		btnReturned.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnReturned.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});
		btnReturned.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnBrowse.setBackground(SystemColor.activeCaption);

		JButton btnOrder = new JButton("Order");
		btnOrder.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				new OrderGUI(librarySystem);
			}
		});

		JButton btnReceived = new JButton("Received");
		btnReceived.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnReceived.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});

		JButton btnAccount = new JButton("Account");
		btnAccount.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				new ProfileGUI(librarySystem);
			}
		});

		JScrollPane scrollPane = new JScrollPane();

		JComboBox materialTypeSortBox = new JComboBox();
		materialTypeSortBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		materialTypeSortBox.setToolTipText("Select Material Type");
		materialTypeSortBox.setModel(new DefaultComboBoxModel(MaterialType.values()));

		JComboBox stringSortBox = new JComboBox();
		stringSortBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		stringSortBox.setModel(new DefaultComboBoxModel(new String[] { "Title", "Author" }));

		JLabel lblSortBy = new JLabel("Sort by:");
		lblSortBy.setFont(new Font("Tahoma", Font.PLAIN, 14));


		JButton btnSort = new JButton("Sort");
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
		reserveLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		reserveLabel.setVisible(false);
		
		btnReserve = new JButton("Reserve");
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
		orderLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		orderLabel.setVisible(false);
		
		orderSpinner = new JSpinner();
		orderSpinner.setVisible(false);
		
		infoLabel = new JLabel("");
		infoLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		btnOrder_1 = new JButton("Order");
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
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(0)
							.addComponent(home, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnReturned, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnBrowse, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnOrder, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 699, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(btnReceived, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(btnAccount, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
												.addGap(2))
											.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
													.addComponent(stringSortBox, Alignment.LEADING, 0, 343, Short.MAX_VALUE)
													.addComponent(materialTypeSortBox, 0, 343, Short.MAX_VALUE))
												.addContainerGap())
											.addGroup(groupLayout.createSequentialGroup()
												.addGap(10)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
													.addComponent(infoLabel, GroupLayout.PREFERRED_SIZE, 333, GroupLayout.PREFERRED_SIZE)
													.addGroup(groupLayout.createSequentialGroup()
														.addComponent(reserveLabel)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(reservationSpinner, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
												.addContainerGap()))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblSortBy)
											.addGap(157))))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(27)
									.addComponent(orderLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(orderSpinner, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(163)
									.addComponent(btnSort)
									.addGap(149)))
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(127)
								.addComponent(btnReserve)
								.addContainerGap()))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(119)
							.addComponent(btnOrder_1)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(home, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnReturned, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnBrowse, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnOrder, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnReceived, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAccount, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(23)
							.addComponent(lblSortBy)
							.addGap(18)
							.addComponent(stringSortBox, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addGap(31)
							.addComponent(materialTypeSortBox, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnSort)
							.addGap(36)
							.addComponent(infoLabel, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(reserveLabel)
								.addComponent(reservationSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnReserve)
							.addGap(58)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(orderLabel)
								.addComponent(orderSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnOrder_1))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 658, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);

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
					menu.show(table, e.getX(), e.getY());
				}
			}
		});

		scrollPane.setViewportView(this.table);
		setLayout(groupLayout);

		this.librarySystem.updateGUI(this);
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

