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
import librarysystem.materials.Material;
import librarysystem.materials.MaterialStatus;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
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
import searching.AuthorComparator;
import searching.TitleComparator;

import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;

public class BrowseGUI extends JPanel {

	private final LibrarySystem librarySystem;

	private JTable table;
	private List<Material> tableContents = new ArrayList<>();
	private Material selectedMaterial = null;

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
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});

		JComboBox materialTypeSortBox = new JComboBox();
		materialTypeSortBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		materialTypeSortBox.setToolTipText("Select Material Type");
		materialTypeSortBox.setModel(new DefaultComboBoxModel(MaterialType.values()));

		JComboBox stringSortBox = new JComboBox();
		stringSortBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		stringSortBox.setModel(new DefaultComboBoxModel(new String[] { "Title", "Author" }));

		JLabel lblSortBy = new JLabel("Sort by:");
		lblSortBy.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JButton btnNewButton = new JButton("Sort");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				String stringSort = (String) stringSortBox.getSelectedItem();
				MaterialType materialType = (MaterialType) materialTypeSortBox.getSelectedItem();

				sort(stringSort, materialType);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblTes = new JLabel("");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(
						groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
								groupLayout
										.createSequentialGroup().addGap(
												0)
										.addGroup(
												groupLayout.createParallelGroup(Alignment.TRAILING)
														.addGroup(groupLayout.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE).addComponent(scrollPane,
																GroupLayout.PREFERRED_SIZE, 699, GroupLayout.PREFERRED_SIZE))
														.addGroup(
																groupLayout.createSequentialGroup().addComponent(home, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnReturned, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
																		.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnBrowse, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(btnOrder, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)))
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout.createSequentialGroup().addGap(163).addComponent(btnNewButton).addGap(149))
												.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup().addGap(18).addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
														.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
																.addGroup(groupLayout.createSequentialGroup().addComponent(btnReceived, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
																		.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnAccount, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
																		.addGap(2))
																.addGroup(groupLayout.createSequentialGroup()
																		.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addComponent(stringSortBox, Alignment.LEADING, 0, 343, Short.MAX_VALUE)
																				.addComponent(materialTypeSortBox, 0, 343, Short.MAX_VALUE))
																		.addContainerGap())
																.addGroup(Alignment.LEADING,
																		groupLayout.createSequentialGroup().addComponent(lblTes, GroupLayout.PREFERRED_SIZE, 343, GroupLayout.PREFERRED_SIZE).addContainerGap()))
														.addGroup(groupLayout.createSequentialGroup().addComponent(lblSortBy).addGap(157)))))));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(home, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE).addComponent(btnReturned,
										GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnBrowse, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE).addComponent(btnOrder, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnReceived, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE).addComponent(btnAccount, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED).addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 658, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup().addGap(23).addComponent(lblSortBy).addGap(18).addComponent(stringSortBox, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
										.addGap(31).addComponent(materialTypeSortBox, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE).addGap(18).addComponent(btnNewButton).addGap(36).addComponent(lblTes,
												GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap()));

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

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("button: " + e.getButton());
				if (e.getButton() == MouseEvent.BUTTON3) {
					int row = table.rowAtPoint(e.getPoint());
					System.out.println("row: " + row);
					selectedMaterial = tableContents.get(row);
					JPopupMenu menu = new JPopupMenu();
					menu.add(new JMenuItem("Hold"));
					menu.add(new JMenuItem("Borrow"));
					UserType userType = librarySystem.getUserManager().getCurrentUser().getUserType();
					if (userType == UserType.INSTRUCTOR) {
						menu.add(new JMenuItem("Reserve"));
					} else if (userType == UserType.LIBRARIAN) {
						menu.add(new JMenuItem("Order"));
					}
					menu.show(table, e.getX(), e.getY());
				}
			}
		});

		scrollPane.setViewportView(this.table);
		setLayout(groupLayout);

		this.librarySystem.updateGUI(this);
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
