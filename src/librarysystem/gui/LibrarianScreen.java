package librarysystem.gui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import librarysystem.LibrarySystem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LibrarianScreen {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(LibrarySystem LS) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibrarianScreen window = new LibrarianScreen(LS);
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
	public LibrarianScreen(LibrarySystem LS) {
		initialize(LS);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(LibrarySystem LS) {
		frame = new JFrame();
		frame.setBounds(100, 100, 772, 619);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnAddMaterial = new JButton("Add Material");
		btnAddMaterial.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				AddMaterial.main(LS);
				frame.setVisible(false);
			}
		});
		
		JButton btnOrderMaterial = new JButton("Order Material");
		btnOrderMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderMaterial.main(LS);
			}
		});
		
		JButton btnRecieveMaterial = new JButton("Recieve Material");
		btnRecieveMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RecieveMaterialScreen.main(LS);
				frame.setVisible(false);
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(163)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnRecieveMaterial, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addComponent(btnOrderMaterial, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnAddMaterial, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(450, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(293, Short.MAX_VALUE)
					.addComponent(btnAddMaterial)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnOrderMaterial)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnRecieveMaterial)
					.addGap(196))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
