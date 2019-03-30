package librarysystem.gui;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;

import librarysystem.LibrarySystem;
import librarysystem.materials.Material;
import librarysystem.materials.MaterialStatus;

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

public class ReturnGUI extends JPanel {
	private JTable table;
	private LibrarySystem librarySystem;

	/**
	 * Create the panel.
	 */
	public ReturnGUI(LibrarySystem librarySystem) {
		this.librarySystem = librarySystem;
		
		String[] columnNames = { "Icon", "Material","ID", "Return" };
		
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
		

		for (Material M : librarySystem.getUserManager().getCurrentUser().getBorrowed()) {
			model.addRow(new Object[] { null, M.getNiceName(), M.getId(), false});
		}
		this.table = new JTable(model);
		this.table.setPreferredScrollableViewportSize(table.getPreferredSize());
		this.table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.table.getColumnModel().getColumn(0).setMaxWidth(100);

		
		scrollPane.setViewportView(this.table);
		
		this.librarySystem.updateGUI(this);
		
		JButton btnRecieveSelectedMaterials = new JButton("Return Selected Materials");
		btnRecieveSelectedMaterials.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				for (int i = model.getRowCount() - 1; i >= 0; i--) {
					if ((boolean) model.getValueAt(i, 3) == true){
						librarySystem.getMaterialManager().updateStatus(librarySystem.getMaterialManager().getMaterial((String) model.getValueAt(i, 2)), MaterialStatus.RETURNED);
						model.removeRow(i);
					}
				}
			}
		});
		
		
		JButton btnHome = new JButton("Home");
		btnHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				librarySystem.updateGUI(new HomeGUI(librarySystem));
			}
		});
		btnHome.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				librarySystem.updateGUI(new ReturnGUI(librarySystem));
			}
		});
		btnReturn.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new BrowseGUI(librarySystem);
			}
		});
		btnBrowse.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnBrowse.setBackground(SystemColor.menu);
		
		
		JButton button_5 = new JButton("Return Selected Materials");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnHome, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(btnReturn, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(btnBrowse, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 818, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(button_5, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(47, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnHome, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnReturn, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBrowse, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 502, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(button_5)))
					.addContainerGap(290, Short.MAX_VALUE))
		);
		
		table = new JTable();
		scrollPane.setColumnHeaderView(table);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollPane.setRowHeaderView(scrollBar);
		setLayout(groupLayout);

	}
}
