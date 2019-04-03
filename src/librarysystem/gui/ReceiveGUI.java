package librarysystem.gui;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;


import librarysystem.LibrarySystem;
import librarysystem.materials.Material;
import librarysystem.materials.MaterialStatus;
import librarysystem.users.User;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.awt.Dimension;
import javax.swing.JScrollBar;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.UIManager;

public class ReceiveGUI extends JPanel {
	private final LibrarySystem librarySystem;

	private List<Material> tableContents = new ArrayList<>();
	private Material selectedMaterial = null;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public ReceiveGUI(LibrarySystem librarySystem) {
		this.librarySystem = librarySystem;
		
		String[] columnNames = { "Icon", "Material","ID", "Make Available" };
		
		DefaultTableModel model = new DefaultTableModel(new Object[][] {}, columnNames) {
			//  Returning the Class of each column will allow different
			//  renderers to be used based on Class
			public Class getColumnClass(int column) {
				if (column == 0)
					return Icon.class;
				if (column == 1)
					return String.class;
				if (column == 2)
					return String.class;
				if (column == 3)
					return Boolean.class;
				return String.class;
			}

			public boolean isCellEditable(int row, int column) {
				return column == 3;
			}
			
		};
		
		JScrollPane scrollPane = new JScrollPane();
		
		for (Material M : librarySystem.getMaterialManager().getMaterials(MaterialStatus.ON_ORDER)) {
			model.addRow(new Object[] { null, M.getNiceName(), M.getId(), false});
		}
		this.table = new JTable(model);
		this.table.setPreferredScrollableViewportSize(table.getPreferredSize());
		this.table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.table.getColumnModel().getColumn(0).setMaxWidth(100);
		
		scrollPane.setViewportView(this.table);
		
		JButton btnRecieveSelectedMaterials = new JButton("Recieve Selected Materials");
		btnRecieveSelectedMaterials.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				for (int i = model.getRowCount() - 1; i >= 0; i--) {
					if ((boolean) model.getValueAt(i, 3) == true){
						System.out.println("status changed");
						librarySystem.getMaterialManager().updateStatus(librarySystem.getMaterialManager().getMaterial((String)
								model.getValueAt(i, 2)), MaterialStatus.AVAILABLE);					
						System.out.println(librarySystem.getMaterialManager().getMaterials(MaterialStatus.ON_ORDER).toString());
						model.removeRow(i);
					}
				}
				
			}
		});
		
		JButton btnHome = new JButton("Home");
		btnHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new HomeGUI(librarySystem);
			}
		});
		btnHome.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JButton btnReturned = new JButton("Returned");
		btnReturned.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new ReturnGUI(librarySystem);
			}
		});
		btnReturned.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new BrowseGUI(librarySystem);
			}
		});
		btnBrowse.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnBrowse.setBackground(UIManager.getColor("Button.background"));
		
		JButton btnOrder = new JButton("Order");
		btnOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new OrderGUI(librarySystem);
			}
		});
		btnOrder.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JButton btnReceive = new JButton("Received");
		btnReceive.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JButton btnAccount = new JButton("Account");
		btnAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new ProfileGUI(librarySystem);
			}
		});
		btnAccount.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnHome, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(btnReturned, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(btnBrowse, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(btnOrder, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnReceive, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(btnAccount, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 818, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnRecieveSelectedMaterials)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnHome, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnReturned, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBrowse, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnOrder, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnReceive, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAccount, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 502, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRecieveSelectedMaterials))
					.addContainerGap(331, Short.MAX_VALUE))
		);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollPane.setRowHeaderView(scrollBar);
		setLayout(groupLayout);
		
		this.librarySystem.updateGUI(this);
	}
}
