package librarysystem.gui;

import librarysystem.LibrarySystem;
import librarysystem.managers.MaterialManager;
import librarysystem.managers.UserManager;
import librarysystem.materials.Material;
import librarysystem.materials.MaterialStatus;
import librarysystem.materials.MaterialType;
import librarysystem.searching.AuthorComparator;
import librarysystem.searching.TitleComparator;
import librarysystem.users.User;
import librarysystem.users.UserType;
import librarysystem.users.faculty.Instructor;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Rory Skipper
 *
 */
public class BrowseGUI extends JPanel {

	private final LibrarySystem librarySystem;
	private final MaterialManager materialManager;
	private final UserManager userManager;

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
	 * @param librarySystem The system to base the GUI on
	 */
	public BrowseGUI(LibrarySystem librarySystem) {
	
		User currUser=librarySystem.getUserManager().getCurrentUser();
		
		this.librarySystem = librarySystem;
		this.materialManager = librarySystem.getMaterialManager();
		this.userManager = librarySystem.getUserManager();
		this.setBounds(0, 0, librarySystem.WIDTH, librarySystem.HEIGHT);
		librarySystem.updateGUI(this);

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
				new ReturnGUI(librarySystem);
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
				new ReceiveGUI(librarySystem);
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
		lblSortBy.setForeground(Color.WHITE);
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
				int maxAmount = materialManager.getAmountAvailableForReservation(selectedMaterial);
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
		reserveLabel.setBackground(new Color(255, 255, 255));
		reserveLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		reserveLabel.setVisible(false);

		btnReserve = new JButton("Reserve");
		btnReserve.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				int amount = (Integer) reservationSpinner.getValue();
				librarySystem.getMaterialManager().reserveMaterial((Instructor) currUser, selectedMaterial, amount);
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
		orderLabel.setForeground(Color.WHITE);
		orderLabel.setBackground(new Color(255, 255, 255));
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

		/*
		 * Make order and received functionality accessible to only the librarian 
		 */
		if (currUser.getUserType() == UserType.STUDENT || currUser.getUserType() == UserType.INSTRUCTOR ) {
			btnOrder.setVisible(false);
			btnReceived.setVisible(false);
		}
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(0)
							.addComponent(home, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnReturned, GroupLayout.PREFERRED_SIZE, 12, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnBrowse, GroupLayout.PREFERRED_SIZE, 52, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnOrder, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
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
											.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
												.addComponent(btnReceived, GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
												.addGap(12)
												.addComponent(btnAccount, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
												.addGap(2))
											.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
													.addComponent(stringSortBox, Alignment.LEADING, 0, 455, Short.MAX_VALUE)
													.addComponent(materialTypeSortBox, 0, 455, Short.MAX_VALUE))
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
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(home, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnReturned, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
							.addComponent(btnBrowse, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnOrder, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnAccount, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnReceived, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
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

		UserType userType = currUser.getUserType();
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
					UserType userType = userManager.getCurrentUser().getUserType();
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
		setLayout(groupLayout);

		this.librarySystem.updateGUI(this);
	}

	public void setupPopupMenu() {
		this.holdMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				materialManager.putOnHold(userManager.getCurrentUser(), selectedMaterial);
				((DefaultTableModel) table.getModel()).removeRow(selectedRow);
				tableContents.remove(selectedRow);

				Material replacementMaterial = materialManager.getMaterial(MaterialStatus.AVAILABLE, selectedMaterial.getId());
				if (replacementMaterial != null) {
					((DefaultTableModel) table.getModel()).insertRow(selectedRow, new Object[] { null, replacementMaterial.getNiceName() });
					tableContents.add(selectedRow, replacementMaterial);
				}

				selectedRow = -1;
				menu.hide();

			}
		});

		this.borrowMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int borrowedSize = librarySystem.getUserManager().getCurrentUser().getBorrowed().size();
				if (borrowedSize >= 5) {
					infoLabel.setVisible(true);
					infoLabel.setText("You already have " + borrowedSize + " materials taken out!");
				} else {
					infoLabel.setVisible(true);
					infoLabel.setText(selectedMaterial.getMaterialType().getNiceName() + " taken out successfully");
					materialManager.borrowMaterial(userManager.getCurrentUser(), selectedMaterial);
					((DefaultTableModel) table.getModel()).removeRow(selectedRow);
					tableContents.remove(selectedRow);

					Material replacementMaterial = materialManager.getMaterial(MaterialStatus.AVAILABLE, selectedMaterial.getId());
					if (replacementMaterial != null) {
						((DefaultTableModel) table.getModel()).insertRow(selectedRow, new Object[] { null, replacementMaterial.getNiceName() });
						tableContents.add(selectedRow, replacementMaterial);
					}
				}
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

	/**
	 * Sorts the table by a material type and by author or title
	 * @param stringSort A string representing title or author
	 * @param materialType Then type of materials to look for
	 */
	public void sort(String stringSort, MaterialType materialType) {
		this.tableContents.clear();
		if (materialType == MaterialType.ALL) {
			this.tableContents = materialManager.getUniqueMaterials(MaterialStatus.AVAILABLE);
		} else {
			this.tableContents = materialManager.getUniqueMaterials(MaterialStatus.AVAILABLE, materialType);
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
