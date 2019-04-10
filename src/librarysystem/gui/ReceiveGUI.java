package librarysystem.gui;

import librarysystem.LibrarySystem;
import librarysystem.materials.Material;
import librarysystem.materials.MaterialStatus;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;


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
	 * @param librarySystem - object that contains setter, getters, and managers
	 * navigation buttons 
	 * table displaying book information and check box
	 * button to receive selected material
	 *  
	 */
	public ReceiveGUI(LibrarySystem librarySystem) {
		this.librarySystem = librarySystem;
		this.setBounds(0, 0, librarySystem.WIDTH, librarySystem.HEIGHT);
		librarySystem.setTheme(this);
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
		
		JLabel lblBanner = new JLabel("");
		lblBanner.setBounds(15, 15, 1250, 200);
		lblBanner.setIcon(new ImageIcon("resources/banner_img.png"));
		setLayout(null);
		add(lblBanner);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(86, 304, 624, 325);
		
		for (Material M : librarySystem.getMaterialManager().getMaterials(MaterialStatus.ON_ORDER)) {
			model.addRow(new Object[] { null, M.getNiceName(), M.getId(), M.getBarcode(), false});
		}
		this.table = new JTable(model);
		this.table.setPreferredScrollableViewportSize(table.getPreferredSize());
		this.table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.table.getColumnModel().getColumn(0).setMaxWidth(100);
		
		scrollPane.setViewportView(this.table);
		
		JButton btnRecieveSelectedMaterials = new JButton("Recieve Selected Materials");
		btnRecieveSelectedMaterials.setBounds(83, 667, 208, 29);
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
		
		/**
		 * General buttons constant throughout all panels
		 */
		JButton btnHome = new JButton("Home");
		btnHome.setBounds(80, 225, 120, 30);
		btnHome.setForeground(new Color(0, 0, 128));
		
		JButton btnReturned = new JButton("Returned");
		btnReturned.setBounds(280, 225, 120, 30);
		btnReturned.setForeground(new Color(0, 0, 128));
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(480, 225, 120, 30);
		btnBrowse.setForeground(new Color(0, 0, 128));
		
		JButton btnReceived = new JButton("Received");
		btnReceived.setBounds(1080, 225, 120, 30);
		btnReceived.setForeground(new Color(0, 0, 128));
		
		JButton btnOrder = new JButton("Order");
		btnOrder.setBounds(880, 225, 120, 30);
		btnOrder.setForeground(new Color(0, 0, 128));
		
		JButton btnAccount = new JButton("Account");
		btnAccount.setBounds(680, 225, 120, 30);
		btnAccount.setForeground(new Color(0, 0, 128));
		
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new HomeGUI(librarySystem);
			}
		});
		btnReturned.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ReturnGUI(librarySystem);
			}
		});
		
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BrowseGUI(librarySystem);
			}
		});
		
		btnReceived.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ReceiveGUI(librarySystem);
			}
		});
		
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new OrderGUI(librarySystem);
			}
		});
		
		btnAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ProfileGUI(librarySystem);
			}
		});
		
		JScrollBar scrollBar = new JScrollBar();
		scrollPane.setRowHeaderView(scrollBar);
		
		
		this.librarySystem.updateGUI(this);
		setLayout(null);
		add(btnHome);
		add(btnReturned);
		add(btnBrowse);
		add(btnOrder);
		add(btnReceived);
		add(btnAccount);
		add(scrollPane);
		add(btnRecieveSelectedMaterials);
	}
}
