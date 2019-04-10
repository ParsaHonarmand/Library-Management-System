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
		scrollPane.setBounds(10, 51, 818, 502);
		

		for (Material M : librarySystem.getUserManager().getCurrentUser().getBorrowed()) {
			model.addRow(new Object[] { null, M.getNiceName(), M.getId(), M.getBarcode(), false});
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
					if ((boolean) model.getValueAt(i, 4) == true){
						librarySystem.getMaterialManager().updateStatus(librarySystem.getMaterialManager().getMaterial((Integer) model.getValueAt(i, 3)), MaterialStatus.RETURNED);
						model.removeRow(i);
					}
				}
			}
		});
		
		
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
		
		
		JButton button_5 = new JButton("Return Selected Materials");
		button_5.setBounds(838, 54, 161, 29);
		
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
		add(button_5);
	}
}
