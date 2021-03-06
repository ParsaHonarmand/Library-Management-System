package librarysystem.gui;
import javax.swing.JPanel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import librarysystem.LibrarySystem;
import librarysystem.materials.Material;
import librarysystem.materials.MaterialStatus;
import librarysystem.users.UserType;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ReturnGUI extends JPanel {
	private JTable table;
	private LibrarySystem librarySystem;

	/**
	 * Create the panel.
	 * @param librarySystem The system to base the GUI on
	 */
	public ReturnGUI(LibrarySystem librarySystem) {
		this.librarySystem = librarySystem;
		this.setBounds(0, 0, librarySystem.WIDTH, librarySystem.HEIGHT);
		librarySystem.setTheme(this);
		

		String[] columnNames = { "Icon", "Material","ID", "Barcode", "Reshelve" };

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

		scrollPane.setBounds(32, 289, 671, 336);
		JLabel lblBanner = new JLabel("");
		lblBanner.setBounds(15, 15, 1250, 200);
		lblBanner.setIcon(new ImageIcon("resources/banner_img.png"));
		setLayout(null);
		add(lblBanner);

		for (Material M : librarySystem.getMaterialManager().getMaterials(MaterialStatus.RETURNED)) {
			model.addRow(new Object[] { M.getMaterialType().getIcon(), M.getNiceName(), M.getId(), M.getBarcode(), false});
		}
		this.table = new JTable(model);
		this.table.setPreferredScrollableViewportSize(table.getPreferredSize());
		this.table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.table.getColumnModel().getColumn(0).setMaxWidth(50);
		this.table.getColumnModel().getColumn(2).setMaxWidth(100);
		this.table.getColumnModel().getColumn(3).setMaxWidth(55);
		this.table.getColumnModel().getColumn(4).setMaxWidth(55);
		this.table.setRowHeight(50);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		this.table.setDefaultRenderer(String.class, centerRenderer);


		scrollPane.setViewportView(this.table);

		JButton btnReturnSelectedMaterials = new JButton("Reshelve Selected Materials");
		btnReturnSelectedMaterials.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				for (int i = model.getRowCount() - 1; i >= 0; i--) {
					if ((boolean) model.getValueAt(i, 4) == true){
						librarySystem.getMaterialManager().reshelveMaterial(librarySystem.getMaterialManager().getMaterial(
								(Integer) model.getValueAt(i, 3)));
						model.removeRow(i);
						System.out.println("reshelving material");
					}
				}
			}
		});
		btnReturnSelectedMaterials.setBounds(32, 647, 253, 25);
		add(btnReturnSelectedMaterials);

		this.librarySystem.updateGUI(this);

		JButton btnHome = new JButton("Home");
		btnHome.setBounds(80, 225, 120, 30);
		btnHome.setForeground(new Color(0, 0, 128));
		btnHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				librarySystem.updateGUI(new HomeGUI(librarySystem));
			}
		});

		JButton btnReturned = new JButton("Returned");
		btnReturned.setBounds(280, 225, 120, 30);
		btnReturned.setForeground(new Color(0, 0, 128));
		btnReturned.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				librarySystem.updateGUI(new ReturnGUI(librarySystem));
			}
		});

		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(480, 225, 120, 30);
		btnBrowse.setForeground(new Color(0, 0, 128));
		btnBrowse.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new BrowseGUI(librarySystem);
			}
		});


		JButton btnAccount = new JButton("Account");
		btnAccount.setBounds(680, 225, 120, 30);
		btnAccount.setForeground(new Color(0, 0, 128));
		add(btnAccount);
		btnAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new ProfileGUI(librarySystem);
			}
		});


		JButton btnReceived = new JButton("Received");
		btnReceived.setBounds(1080, 225, 120, 30);
		btnReceived.setForeground(new Color(0, 0, 128));
		add(btnReceived);
		btnReceived.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new ReceiveGUI(librarySystem);
			}
		});


		JButton btnOrder = new JButton("Order");
		btnOrder.setBounds(880, 225, 120, 30);
		btnOrder.setForeground(new Color(0, 0, 128));
		add(btnOrder);
		btnOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new OrderGUI(librarySystem);
			}
		});

		table = new JTable();
		scrollPane.setColumnHeaderView(table);

		JScrollBar scrollBar = new JScrollBar();
		scrollPane.setRowHeaderView(scrollBar);

		this. librarySystem.updateGUI(this);
		setLayout(null);
		add(btnHome);
		add(btnReturned);
		add(btnBrowse);
		add(scrollPane);
		
		
		/*
		 * Make order and received functionality accessible to only the librarian 
		 */
		if (librarySystem.getUserManager().getCurrentUser().getUserType() == UserType.STUDENT || librarySystem.getUserManager().getCurrentUser().getUserType() == UserType.INSTRUCTOR ) {
			btnOrder.setVisible(false);
			btnReceived.setVisible(false);
		}
	}
}
