package librarysystem.gui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;

import librarysystem.LibrarySystem;
import librarysystem.materials.Material;
import librarysystem.materials.MaterialStatus;
import librarysystem.materials.MaterialType;

import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddMaterial {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(LibrarySystem LS) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMaterial window = new AddMaterial(LS);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddMaterial(LibrarySystem LS) {
		initialize(LS);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(LibrarySystem LS) {
		frame = new JFrame();
		frame.setBounds(100, 100, 856, 423);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//JComboBox<MaterialType> materialType= new JComboBox<MaterialType>(MaterialType.values());
		
		
		// comboBoxTraceModeSelection = new JComboBox<TraceMode>(TraceMode.values());
		JComboBox<MaterialType> comboBoxMaterialType = new JComboBox<MaterialType>();
		   comboBoxMaterialType.setModel(new DefaultComboBoxModel<MaterialType > (MaterialType.values()));
		
		JTextPane Title = new JTextPane();
		Title.setText("Title");
		
		JTextPane Auther = new JTextPane();
		Auther.setText("Author");
		
		JTextPane Id = new JTextPane();
		Id.setText("ID");
		
		JTextPane Edition = new JTextPane();
		Edition.setText("Edition");
		
		
		
		JButton btnUpdateDataBase = new JButton("Add New Material");
		btnUpdateDataBase.addMouseListener(new MouseAdapter() {
			@Override
			
			public void mousePressed(MouseEvent e) {
				int intEdition = 0;	
				try
				{
				    intEdition = Integer.parseInt(Edition.getText());

				}
				catch (NumberFormatException exept)
				{
				    System.out.println("Edition must be a number");
				}
				
				
				Material M = new Material(Title.getText(), Auther.getText(), Id.getText(), intEdition, LS.getMaterialManager().getNextBarcode(), MaterialStatus.AVAILABLE, (MaterialType)comboBoxMaterialType.getSelectedItem(), -1L);
				LS.getMaterialManager().addMaterial(M);
				System.out.println("database updated");
			}
		});
		
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(46)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(Id, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(Auther, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(Title, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxMaterialType, Alignment.LEADING, 0, 223, Short.MAX_VALUE))
							.addGap(81)
							.addComponent(btnUpdateDataBase))
						.addComponent(Edition, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(347, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(51)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBoxMaterialType, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnUpdateDataBase))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(Title, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(Auther, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(Id, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(Edition, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(110, Short.MAX_VALUE))
		);

		frame.getContentPane().setLayout(groupLayout);
		
	}
}
