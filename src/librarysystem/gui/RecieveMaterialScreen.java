package librarysystem.gui;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;

import librarysystem.LibrarySystem;
import librarysystem.materials.Material;
import librarysystem.materials.MaterialType;
import librarysystem.materials.MaterialStatus;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RecieveMaterialScreen {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(LibrarySystem LS) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RecieveMaterialScreen window = new RecieveMaterialScreen(LS);
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
	public RecieveMaterialScreen(LibrarySystem LS) {
		initialize(LS);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(LibrarySystem LS) {
		frame = new JFrame();
		frame.setBounds(100, 100, 861, 601);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ArrayList<Material> materials = new ArrayList<Material>(LS.getMaterialManager().getMaterials(MaterialStatus.ON_ORDER));
		String[] materialNames =  new String[materials.size()];
		int i = 0;
		for (Material M : materials) {
			materialNames[i] = M.getTitle();
			i++;
		}
		//Material[] materialList = materials.toArray();
		JComboBox<String> comboBoxMaterial = new JComboBox<String>();
		comboBoxMaterial.setModel(new DefaultComboBoxModel(materialNames));
		
		
		JTextArea txtrItemRecieved = new JTextArea();
		txtrItemRecieved.setText("Item recieved");
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				comboBoxMaterial.getSelectedItem();
			}
		});
		
		
		
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(77)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtrItemRecieved, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE)
							.addGap(33)
							.addComponent(btnNewButton))
						.addComponent(comboBoxMaterial, GroupLayout.PREFERRED_SIZE, 518, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(266, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(104)
					.addComponent(comboBoxMaterial, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
					.addGap(70)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtrItemRecieved, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addContainerGap(73, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
