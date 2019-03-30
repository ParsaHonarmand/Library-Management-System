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

import java.awt.Color;
import java.awt.Font;

public class BrowseGUI extends JPanel {

	private final LibrarySystem librarySystem;

	private JTable table;
	private List<Material> tableContents = new ArrayList<>();
	private Material selectedMaterial = null;
	//Upper Tab Buttons Settings
	int BUTTONS_Y=20;
	int BUTTONS_W=120;
	int BUTTONS_H=30;
	int BUTTONS_D=BUTTONS_W+80;

	/**
	 * Create the panel.
	 */
	public BrowseGUI(LibrarySystem librarySystem) {
		this.librarySystem = librarySystem;

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(94, 90, 697, 552);
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});

		JComboBox materialTypeSortBox = new JComboBox();
		materialTypeSortBox.setBounds(882, 208, 363, 44);
		materialTypeSortBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		materialTypeSortBox.setToolTipText("Select Material Type");
		materialTypeSortBox.setModel(new DefaultComboBoxModel(MaterialType.values()));

		JComboBox stringSortBox = new JComboBox();
		stringSortBox.setBounds(882, 152, 363, 44);
		stringSortBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		stringSortBox.setModel(new DefaultComboBoxModel(new String[] { "Title", "Author" }));

		JLabel lblSortBy = new JLabel("Sort by:");
		lblSortBy.setBounds(1050, 91, 50, 17);
		lblSortBy.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JButton btnSort = new JButton("Sort");
		btnSort.setBounds(1022, 264, 75, 29);

		//JButtons


		JButton btnHome = new JButton("Home");
		btnHome.setBounds(80 , BUTTONS_Y, BUTTONS_W, BUTTONS_H);
		btnHome.setForeground(new Color(0, 0, 128));
		add(btnHome);

		JButton btnReturned = new JButton("Returned");
		btnReturned.setBounds(80+BUTTONS_D, BUTTONS_Y, BUTTONS_W, BUTTONS_H);
		btnReturned.setForeground(new Color(0, 0, 128));
		add(btnReturned);

		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(80+BUTTONS_D*2, BUTTONS_Y, BUTTONS_W, BUTTONS_H);
		btnBrowse.setForeground(new Color(0, 0, 128));
		add(btnBrowse);

		JButton btnReceived = new JButton("Received");
		btnReceived.setBounds(80+BUTTONS_D*3, BUTTONS_Y, BUTTONS_W, BUTTONS_H);
		btnReceived.setForeground(new Color(0, 0, 128));
		add(btnReceived);

		JButton btnOrder = new JButton("Order");
		btnOrder.setBounds(80+BUTTONS_D*4, BUTTONS_Y, BUTTONS_W, BUTTONS_H);
		btnOrder.setForeground(new Color(0, 0, 128));
		add(btnOrder);

		JButton btnAccount = new JButton("Account");
		btnAccount.setBounds(80+BUTTONS_D*5, BUTTONS_Y, BUTTONS_W, BUTTONS_H);
		btnAccount.setForeground(new Color(0, 0, 128));
		add(btnAccount);
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				librarySystem.updateGUI(new HomeGUI(librarySystem));
			}
		});
		btnReturned.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//librarySystem.updateGUI(new ReturnedGUI(librarySystem));
			}
		});

		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				librarySystem.updateGUI(new BrowseGUI(librarySystem));
			}
		});

		btnReceived.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//librarySystem.updateGUI(new ReceiveGUI(librarySystem));
			}
		});

		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				librarySystem.updateGUI(new OrderGUI(librarySystem));
			}
		});

		btnAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccountGUI ac= new AccountGUI();
				librarySystem.updateGUI(ac);
			}
		});	

		btnSort.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				String stringSort = (String) stringSortBox.getSelectedItem();
				MaterialType materialType = (MaterialType) materialTypeSortBox.getSelectedItem();

				sort(stringSort, materialType);
			}
		});
		btnSort.setFont(new Font("Tahoma", Font.PLAIN, 14));

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

		this.librarySystem.updateGUI(this);
		setLayout(null);
		add(scrollPane);
		add(btnSort);
		add(stringSortBox);
		add(materialTypeSortBox);
		add(lblSortBy);
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
