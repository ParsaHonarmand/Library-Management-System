package librarysystem.gui;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;

import librarysystem.LibrarySystem;
import librarysystem.materials.Material;
import librarysystem.materials.MaterialStatus;
import librarysystem.users.UserType;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.UIManager;

public class ReturnGUI extends JPanel {
	private JTable table;
	private LibrarySystem librarySystem;

	/**
	 * Create the panel.
	 */
	public ReturnGUI(LibrarySystem librarySystem) {
		this.librarySystem = librarySystem;
		
		String[] columnNames = { "Icon", "Material","ID", "Barcode", "Return" };
		
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

		scrollPane.setBounds(10, 51, 818, 466);
		

		for (Material M : librarySystem.getUserManager().getCurrentUser().getBorrowed()) {
			model.addRow(new Object[] { null, M.getNiceName(), M.getId(), M.getBarcode(), false});
		}
		this.table = new JTable(model);
		this.table.setPreferredScrollableViewportSize(table.getPreferredSize());
		this.table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.table.getColumnModel().getColumn(0).setMaxWidth(100);

		
		scrollPane.setViewportView(this.table);
		
		JButton btnReturnSelectedMaterials = new JButton("Return Selected Materials");
		btnReturnSelectedMaterials.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				for (int i = model.getRowCount() - 1; i >= 0; i--) {
					if ((boolean) model.getValueAt(i, 4) == true){
						librarySystem.getMaterialManager().returnMaterial		
							(librarySystem.getUserManager().getCurrentUser(), librarySystem.getMaterialManager().getMaterial(
								(Integer) model.getValueAt(i, 3)));
						model.removeRow(i);		
						System.out.println("returning material");
					}
				}
			}
		});
		
		this.librarySystem.updateGUI(this);
				
		JButton btnHome = new JButton("Home");
		btnHome.setBounds(0, 0, 174, 45);
		btnHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				librarySystem.updateGUI(new HomeGUI(librarySystem));
			}
		});
		btnHome.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JButton btnReturn = new JButton("Return");
		btnReturn.setBounds(180, 0, 173, 45);
		btnReturn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				librarySystem.updateGUI(new ReturnGUI(librarySystem));
			}
		});
		btnReturn.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(359, 0, 173, 45);
		btnBrowse.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new BrowseGUI(librarySystem);
			}
		});
		btnBrowse.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnBrowse.setBackground(SystemColor.menu);
		
		btnBrowse.setBackground(UIManager.getColor("Button.background"));
				
		table = new JTable();
		scrollPane.setColumnHeaderView(table);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollPane.setRowHeaderView(scrollBar);

		this. librarySystem.updateGUI(this);
		setLayout(null);
		add(btnHome);
		add(btnReturn);
		add(btnBrowse);
		add(scrollPane);
		

		btnReturnSelectedMaterials.setBounds(30, 544, 253, 25);
		add(btnReturnSelectedMaterials);
		

		
		JButton btnAccount = new JButton("Account");
		btnAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new ProfileGUI(librarySystem);
			}
		});
		btnAccount.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnAccount.setBounds(910, 0, 174, 45);
		add(btnAccount);
		
		JButton btnReceive = new JButton("Received");
		btnReceive.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new ReceiveGUI(librarySystem);
			}
		});
		btnReceive.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnReceive.setBounds(732, 0, 168, 45);
		add(btnReceive);
		
		JButton btnOrder = new JButton("Order");
		btnOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			new OrderGUI(librarySystem);
			}
		});
		/*
		 * copy and paste me into your class and your button will be invisible too!!!!!!!
		 */
		if (librarySystem.getUserManager().getCurrentUser().getUserType() == UserType.STUDENT || librarySystem.getUserManager().getCurrentUser().getUserType() == UserType.INSTRUCTOR ) {
			btnAccount.setVisible(false);
			btnOrder.setVisible(false);
			btnReceive.setVisible(false);
		}
		
		btnOrder.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnOrder.setBounds(542, 0, 172, 45);
		add(btnOrder);
	}
}
