package librarysystem.gui;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
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
import javax.swing.SwingConstants;

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
import java.awt.Dimension;

public class MaterialsGUI extends JPanel {

	private final LibrarySystem librarySystem;
	
	private JTable tableMaterials;
	private JTable tableHold;
	private List<Material> tableContents = new ArrayList<>();
	private Material selectedMaterial = null;

	public MaterialsGUI(LibrarySystem librarySystem) {
		this.librarySystem = librarySystem;
		this.setBounds(0, 0, 1365, 730);
		
		JButton profile = new JButton("Profile");
		profile.setHorizontalAlignment(SwingConstants.LEFT);
		profile.setBounds(0,0,100,100);
		profile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				new ProfileGUI(librarySystem);
			}
		});
		
		JButton changePassword = new JButton("Change Password");
		changePassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				new PasswordGUI(librarySystem);
			}
		});
		
		JButton payFee = new JButton("Pay Fees");
		payFee.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				new PayGUI(librarySystem);
			}
		});
		
		JButton materials = new JButton("Materials");
		materials.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				new MaterialsGUI(librarySystem);
			}
		});
		
		JButton reservations = new JButton("Reservations");
		reservations.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});
		
		JButton logout = new JButton("Logout");
		logout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				new LoginGUI(librarySystem);
			}
		});
		
		JButton btnHome = new JButton("Home");
		btnHome.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnHome.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
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
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnBrowse.setBackground(Color.WHITE);
		btnBrowse.setBackground(SystemColor.activeCaption);
		btnBrowse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				new BrowseGUI(librarySystem);
			}
		});
		
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
				new AccountGUI(librarySystem);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});
		

		JComboBox stringSortBox = new JComboBox();
		stringSortBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		stringSortBox.setModel(new DefaultComboBoxModel(new String[] { "Title", "Author" }));

		JComboBox materialTypeSortBox = new JComboBox();
		materialTypeSortBox.setToolTipText("Select Material Type");
		materialTypeSortBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		materialTypeSortBox.setModel(new DefaultComboBoxModel(MaterialType.values()));		

		JLabel lblSortBy = new JLabel("Sort by:");
		lblSortBy.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JButton btnSort = new JButton("Sort");
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
		

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(profile)
						.addComponent(changePassword)
						.addComponent(payFee)
						.addComponent(materials)
						.addComponent(reservations)
						.addComponent(logout))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnHome, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnReturned, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnBrowse, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnOrder, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnReceived, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAccount, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE))
						.addComponent(profilePanel, GroupLayout.PREFERRED_SIZE, 1190, Short.MAX_VALUE))
					.addContainerGap(9, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnHome, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnReturned, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBrowse, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnOrder, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnReceived, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAccount, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(profilePanel, GroupLayout.PREFERRED_SIZE, 789, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(profile)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(changePassword)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(payFee)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(materials)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(reservations)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(logout)
							.addGap(165))))
		);

		



		
		
		
		GroupLayout gl_profilePanel = new GroupLayout(profilePanel);
		gl_profilePanel.setHorizontalGroup(
			gl_profilePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_profilePanel.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 342, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 387, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_profilePanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_profilePanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 191, Short.MAX_VALUE)
							.addComponent(lblSortBy)
							.addGap(194))
						.addGroup(gl_profilePanel.createSequentialGroup()
							.addGap(6)
							.addGroup(gl_profilePanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_profilePanel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_profilePanel.createParallelGroup(Alignment.LEADING)
										.addComponent(stringSortBox, GroupLayout.PREFERRED_SIZE, 404, GroupLayout.PREFERRED_SIZE)
										.addComponent(materialTypeSortBox, GroupLayout.PREFERRED_SIZE, 404, GroupLayout.PREFERRED_SIZE))
									.addContainerGap(21, Short.MAX_VALUE))
								.addGroup(gl_profilePanel.createSequentialGroup()
									.addGap(182)
									.addComponent(btnSort, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
									.addGap(176))))))
		);
		gl_profilePanel.setVerticalGroup(
			gl_profilePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_profilePanel.createSequentialGroup()
					.addGroup(gl_profilePanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_profilePanel.createSequentialGroup()
							.addGap(80)
							.addComponent(lblSortBy)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(stringSortBox, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(materialTypeSortBox, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnSort))
						.addGroup(gl_profilePanel.createSequentialGroup()
							.addGap(115)
							.addGroup(gl_profilePanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 545, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 545, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(129, Short.MAX_VALUE))
		);
		
		String[] columnMaterials = { "icon", "Materials" };
		DefaultTableModel model = new DefaultTableModel(new Object[][] {}, columnMaterials) {
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

		scrollPane.setColumnHeaderView(tableMaterials);
		tableMaterials = new JTable(model);
		scrollPane.setViewportView(tableMaterials);

		this.tableMaterials.setPreferredScrollableViewportSize(tableMaterials.getPreferredSize());
		this.tableMaterials.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.tableMaterials.getColumnModel().getColumn(0).setMaxWidth(100);
		//sort("Title", MaterialType.ALL);

		tableMaterials.addMouseListener(new MouseAdapter() {
		@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("button: " + e.getButton());
				if (e.getButton() == MouseEvent.BUTTON3) {
					int row = tableMaterials.rowAtPoint(e.getPoint());
					System.out.println("row: " + row);
					
					selectedMaterial = tableContents.get(row);
					
					JPopupMenu menu = new JPopupMenu();
					menu.add(new JMenuItem("Return"));
					menu.add(new JMenuItem("Renew"));
					menu.show(tableMaterials, e.getX(), e.getY());
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
		
		tableHold = new JTable (model1);
		tableHold.setPreferredScrollableViewportSize(new Dimension(150, 0));
		tableHold.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane_1.setViewportView(tableHold);
		
		//scrollPane_1.setColumnHeaderView(tableHold);
		this.tableHold.setPreferredScrollableViewportSize(tableHold.getPreferredSize());
		this.tableHold.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.tableHold.getColumnModel().getColumn(0).setMaxWidth(100);
		sort("Title", MaterialType.ALL);

		tableHold.addMouseListener(new MouseAdapter() {
		@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("button: " + e.getButton());
				if (e.getButton() == MouseEvent.BUTTON3) {
					int row = tableMaterials.rowAtPoint(e.getPoint());
					System.out.println("row: " + row);
					
					selectedMaterial = tableContents.get(row);
					
					JPopupMenu menu = new JPopupMenu();
					menu.add(new JMenuItem("Borrow"));
					menu.add(new JMenuItem("Cancel"));
					menu.show(tableHold, e.getX(), e.getY());
			}
		}
	});
		

		profilePanel.setLayout(gl_profilePanel);
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

	DefaultTableModel model = (DefaultTableModel) tableMaterials.getModel();
	for (int i = model.getRowCount() - 1; i >= 0; i--) {
		model.removeRow(i);
	}
	for (int i = 0; i < this.tableContents.size(); i++) {
		model.addRow(new Object[] { null, this.tableContents.get(i).getNiceName() });
	}
}
}

