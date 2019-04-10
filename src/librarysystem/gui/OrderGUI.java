package librarysystem.gui;

import librarysystem.LibrarySystem;
import librarysystem.materials.Material;
import librarysystem.materials.MaterialStatus;
import librarysystem.materials.MaterialType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class OrderGUI extends JPanel {
	
	private LibrarySystem librarySystem;
	private JTextField txtIdWarning;

	/**
	 * Create the panel.
	 * @param librarySystem The system to base the GUI on
	 */
	public OrderGUI(LibrarySystem librarySystem) {
		this.librarySystem = librarySystem;
		
		
		JLabel lblBanner = new JLabel("");
		lblBanner.setBounds(15, 15, 1250, 200);
		lblBanner.setIcon(new ImageIcon("resources/banner_img.png"));
		setLayout(null);
		
		
		/**
		 * Buttons consistent throughout all panels 
		 */
		
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
		btnReceived.setBounds(680, 225, 120, 30);
		btnReceived.setForeground(new Color(0, 0, 128));
		
		JButton btnOrder = new JButton("Order");
		btnOrder.setBounds(880, 225, 120, 30);
		btnOrder.setForeground(new Color(0, 0, 128));
		
		JButton btnAccount = new JButton("Account");
		btnAccount.setBounds(1080, 225, 120, 30);
		btnAccount.setForeground(new Color(0, 0, 128));
		
		
		
		/**
		 * TextFeilds for data input to track orders and users responsible for them
		 */
		JTextPane txtpnId = new JTextPane();
		txtpnId.setBounds(201, 374, 223, 41);
		txtpnId.setText("ID");
		
		JTextPane txtpnAuthor = new JTextPane();
		txtpnAuthor.setBounds(201, 468, 223, 41);
		txtpnAuthor.setText("Author");
		
		JTextPane txtpnTitle = new JTextPane();
		txtpnTitle.setBounds(201, 283, 223, 37);
		txtpnTitle.setText("Title");
		
		JTextPane txtpnEdition = new JTextPane();
		txtpnEdition.setBounds(201, 563, 223, 41);
		txtpnEdition.setText("Edition");
		
		JComboBox<MaterialType> comboBox = new JComboBox<MaterialType>();
		comboBox.setBounds(730, 303, 223, 41);
		   comboBox.setModel(new DefaultComboBoxModel<MaterialType > (MaterialType.values()));
		
		JButton btnPlaceOrder = new JButton("Place Order");
		btnPlaceOrder.setBounds(838, 356, 115, 41);
		btnPlaceOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		/**
		 * Action listeners to invoke any panel called 
		 */
		
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				librarySystem.updateGUI(new HomeGUI(librarySystem));
			}
		});
		btnReturned.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				librarySystem.updateGUI(new ReturnGUI(librarySystem));
			}
		});
		
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BrowseGUI(librarySystem);
			}
		});
		
		btnReceived.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				librarySystem.updateGUI(new ReceiveGUI(librarySystem));
			}
		});
		
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				librarySystem.updateGUI(new OrderGUI(librarySystem));
			}
		});
		
		btnAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				librarySystem.updateGUI(new ProfileGUI(librarySystem));
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
		
	/**
	 * Setting panel characteristics 
	 */
		txtIdWarning = new JTextField();
		txtIdWarning.setBounds(477, 578, 225, 26);
		txtIdWarning.setBackground(Color.RED);
		txtIdWarning.setEnabled(false);
		txtIdWarning.setEditable(false);
		txtIdWarning.setVisible(false);
		txtIdWarning.setText("Warning: Edition must be a number");
		txtIdWarning.setColumns(10);
		
		this.librarySystem.updateGUI(this);
		setLayout(null);
		add(comboBox);
		add(btnPlaceOrder);
		add(txtpnTitle);
		add(txtpnAuthor);
		add(txtpnEdition);
		add(txtIdWarning);
		add(txtpnId);
		add(btnHome);
		add(btnReturned);
		add(btnBrowse);
		add(btnOrder);
		add(btnReceived);
		add(btnAccount);
		add(lblBanner);
	}
}