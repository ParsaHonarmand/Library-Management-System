package librarysystem.gui;

import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPopupMenu;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JToolBar;
import javax.swing.JMenu;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JComboBox;

import librarysystem.LibrarySystem;
import librarysystem.materials.Material;
import librarysystem.materials.MaterialStatus;
import librarysystem.materials.MaterialType;
import javax.swing.JTextField;
import java.awt.Color;


public class OrderGUI extends JPanel {
	
	private LibrarySystem librarySystem;
	private JTextField txtIdWarning;

	/**
	 * Create the panel.
	 */
	public OrderGUI(LibrarySystem librarySystem) {
		this.librarySystem = librarySystem;
		
		JButton btnHome = new JButton("Home");
		btnHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				new HomeGUI(librarySystem);
			}
		});
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ReturnGUI(librarySystem);
			}
		});
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				new BrowseGUI(librarySystem);
			}
		});
		
		JButton btnOrder = new JButton("Order");
		
		JButton btnAccount = new JButton("Account");
		btnAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				new ProfileGUI(librarySystem);
			}
		});
		
		JButton btnReceived = new JButton("Received");
		btnReceived.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				new ReceiveGUI(librarySystem);
			}
		});
		
		JTextPane txtpnId = new JTextPane();
		txtpnId.setText("ID");
		
		JTextPane txtpnAuthor = new JTextPane();
		txtpnAuthor.setText("Author");
		
		JTextPane txtpnTitle = new JTextPane();
		txtpnTitle.setText("Title");
		
		JTextPane txtpnEdition = new JTextPane();
		txtpnEdition.setText("Edition");
		
		JComboBox<MaterialType> comboBox = new JComboBox<MaterialType>();
		   comboBox.setModel(new DefaultComboBoxModel<MaterialType > (MaterialType.values()));
		
			JButton btnPlaceOrder = new JButton("Place Order");
			btnPlaceOrder.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnPlaceOrder.addMouseListener(new MouseAdapter() {
				@Override
				
				public void mousePressed(MouseEvent e) {
					int intEdition = 0;	
					try
					{
					    intEdition = Integer.parseInt(txtpnEdition.getText());
					    txtIdWarning.setVisible(false);

					}
					catch (NumberFormatException exept)
					{
						txtIdWarning.setVisible(true);
					    System.out.println("Edition must be a number");
					}
					
					
					Material M = new Material(txtpnTitle.getText(), txtpnAuthor.getText(), txtpnId.getText(), intEdition, librarySystem.getMaterialManager().getNextBarcode(), MaterialStatus.ON_ORDER, (MaterialType)comboBox.getSelectedItem(), -1L, -1L);
					librarySystem.getMaterialManager().addMaterial(M);
					System.out.println("item ordered");
				}
			});
		
		txtIdWarning = new JTextField();
		txtIdWarning.setBackground(Color.RED);
		txtIdWarning.setEnabled(false);
		txtIdWarning.setEditable(false);
		txtIdWarning.setVisible(false);
		txtIdWarning.setText("Warning: Edition must be a number");
		txtIdWarning.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(188)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(btnPlaceOrder))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(188)
							.addComponent(txtpnTitle, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(188)
							.addComponent(txtpnAuthor, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(188)
							.addComponent(txtpnEdition, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
							.addGap(51)
							.addComponent(txtIdWarning, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(188)
									.addComponent(txtpnId, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnHome, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(btnReturn, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(btnBrowse, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(btnOrder, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(btnReceived, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)))
							.addGap(6)
							.addComponent(btnAccount, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(45, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnHome)
						.addComponent(btnReturn)
						.addComponent(btnBrowse)
						.addComponent(btnOrder)
						.addComponent(btnReceived)
						.addComponent(btnAccount))
					.addGap(94)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPlaceOrder, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
					.addComponent(txtpnTitle, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(txtpnAuthor, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(txtpnId, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtpnEdition, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtIdWarning, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(292))
		);
		setLayout(groupLayout);
		
		this.librarySystem.updateGUI(this);
	}
}