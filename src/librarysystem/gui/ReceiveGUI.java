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


/**
 * This class displays information about books that are on order
 * and allows the operator to select those books to update the status to available
 * @author jadon
 *
 */
public class ReceiveGUI extends JPanel {
	private final LibrarySystem librarySystem;

	private List<Material> tableContents = new ArrayList<>();
	private Material selectedMaterial = null;
	private JTable table;

	/**
	 * Constructor that creates jpanel and elements including:
	 * @param LibrarySystem - object that contains setter, getters, and managers
	 * navigation buttons 
	 * table displaying book information and check box
	 * button to receive selected material
	 *  
	 */
	public ReceiveGUI(LibrarySystem librarySystem) {
		this.librarySystem = librarySystem;
		
		String[] columnNames = { "Icon", "Material","ID","Barcode", "Make Available" };
		
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
					return String.class;
				if (column == 4)
					return Boolean.class;
				return String.class;
			}

			public boolean isCellEditable(int row, int column) {
				return column == 4;
			}
			
		};
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 55, 818, 502);
		
		for (Material M : librarySystem.getMaterialManager().getMaterials(MaterialStatus.ON_ORDER)) {
			model.addRow(new Object[] { null, M.getNiceName(), M.getId(), M.getBarcode(), false});
		}
		this.table = new JTable(model);
		this.table.setPreferredScrollableViewportSize(table.getPreferredSize());
		this.table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.table.getColumnModel().getColumn(0).setMaxWidth(100);
		
		scrollPane.setViewportView(this.table);
		
		JButton btnRecieveSelectedMaterials = new JButton("Recieve Selected Materials");
		btnRecieveSelectedMaterials.setBounds(836, 51, 208, 29);
		btnRecieveSelectedMaterials.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				System.out.println(model.getRowCount());
				for (int i = model.getRowCount() - 1; i >= 0; i--) {
					if ((boolean) model.getValueAt(i, 4) == true){
						System.out.println("status changed");
						librarySystem.getMaterialManager().updateStatus(librarySystem.getMaterialManager().getMaterial((Integer)
								model.getValueAt(i, 3)), MaterialStatus.AVAILABLE);					
						System.out.println(librarySystem.getMaterialManager().getMaterials(MaterialStatus.ON_ORDER).toString());
						model.removeRow(i);
					}
				}
				
			}
		});
		
		JButton btnHome = new JButton("Home");
		btnHome.setBounds(0, 0, 174, 45);
		btnHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new HomeGUI(librarySystem);
			}
		});
		btnHome.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JButton btnReturned = new JButton("Returned");
		btnReturned.setBounds(180, 0, 173, 45);
		btnReturned.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new ReturnGUI(librarySystem);
			}
		});
		btnReturned.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(359, 0, 173, 45);
		btnBrowse.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new BrowseGUI(librarySystem);
			}
		});
		btnBrowse.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnBrowse.setBackground(UIManager.getColor("Button.background"));
		
		JButton btnOrder = new JButton("Order");
		btnOrder.setBounds(538, 0, 172, 45);
		btnOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new OrderGUI(librarySystem);
			}
		});
		btnOrder.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JButton btnReceive = new JButton("Received");
		btnReceive.setBounds(728, 0, 168, 45);
		btnReceive.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JButton btnAccount = new JButton("Account");
		btnAccount.setBounds(906, 0, 174, 45);
		btnAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new ProfileGUI(librarySystem);
			}
		});
		btnAccount.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JScrollBar scrollBar = new JScrollBar();
		scrollPane.setRowHeaderView(scrollBar);
		
		
		this.librarySystem.updateGUI(this);
		setLayout(null);
		add(btnHome);
		add(btnReturned);
		add(btnBrowse);
		add(btnOrder);
		add(btnReceive);
		add(btnAccount);
		add(scrollPane);
		add(btnRecieveSelectedMaterials);
	}
}
