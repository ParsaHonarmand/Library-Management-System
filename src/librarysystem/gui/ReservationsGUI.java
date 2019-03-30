package librarysystem.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import librarysystem.LibrarySystem;
import librarysystem.gui.JSpinnerInTables.SpinnerEditor;
import librarysystem.materials.Material;
import librarysystem.materials.MaterialStatus;
import librarysystem.materials.MaterialType;
import librarysystem.reservations.Reservation;
import librarysystem.users.UserType;
import librarysystem.users.faculty.Instructor;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.JSpinner;

public class ReservationsGUI extends JPanel {
	private LibrarySystem librarySystem;
	private JTable table;
	private List<Reservation> tableReservations = new ArrayList<>();
	private List<JSpinner> tableSpinners = new ArrayList<>();
	private Reservation selectedReservation = null;
	private JSpinner selectedSpinner = null;
	private int selectedRow = -1;
	//Upper Tab Buttons Settings
			int BUTTONS_Y=20;
			int BUTTONS_W=120;
			int BUTTONS_H=30;
			int BUTTONS_D=BUTTONS_W+80;


	/**
	 * Create the panel.
	 */
	public ReservationsGUI(LibrarySystem librarySystem) {
		this.librarySystem = librarySystem;
		
		setBackground(new Color(255, 255, 255));
		setForeground(Color.WHITE);;;;
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 153, 204));
		panel.setBounds(219, 66, 1185, 781);
		add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(131, 109, 570, 375);
		panel.add(scrollPane);
		//JButtons


		JButton btnHome = new JButton("Home");
		btnHome.setBounds(80 , BUTTONS_Y, BUTTONS_W, BUTTONS_H);
		btnHome.setForeground(new Color(0, 0, 128));
		add(btnHome);
		
		JButton btnReturned = new JButton("Returned");
		btnReturned.setBounds(80+BUTTONS_D, BUTTONS_Y, BUTTONS_W, BUTTONS_H);
		btnReturned.setForeground(new Color(0, 0, 128));
		add(btnReturned);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(80+BUTTONS_D*2, BUTTONS_Y, BUTTONS_W, BUTTONS_H);
		btnBrowse.setForeground(new Color(0, 0, 128));
		add(btnBrowse);
		
		JButton btnReceived = new JButton("Received");
		btnReceived.setBounds(80+BUTTONS_D*3, BUTTONS_Y, BUTTONS_W, BUTTONS_H);
		btnReceived.setForeground(new Color(0, 0, 128));
		add(btnReceived);
		
		JButton btnOrder = new JButton("Order");
		btnOrder.setBounds(80+BUTTONS_D*4, BUTTONS_Y, BUTTONS_W, BUTTONS_H);
		btnOrder.setForeground(new Color(0, 0, 128));
		add(btnOrder);
		
		JButton btnAccount = new JButton("Account");
		btnAccount.setBounds(80+BUTTONS_D*5, BUTTONS_Y, BUTTONS_W, BUTTONS_H);
		btnAccount.setForeground(new Color(0, 0, 128));
		add(btnAccount);
		
		JButton btnProfile = new JButton("Profile");
		btnProfile.setBounds(36, 139, 158, 60);
		add(btnProfile);
		
		JButton btnChanPswrdLeft = new JButton("Change Password");
		btnChanPswrdLeft.setBounds(36, 238, 158, 60);
		add(btnChanPswrdLeft);
		
		JButton btnPayFees = new JButton("Pay Fees");
		btnPayFees.setBounds(36, 336, 158, 60);
		add(btnPayFees);
		
		JButton btnMaterials = new JButton("Materials");
		btnMaterials.setBounds(36, 427, 158, 60);
		add(btnMaterials);
		
		JButton btnReservations = new JButton("Reservations");
		btnReservations.setBounds(36, 527, 158, 60);
		add(btnReservations);
		
		JButton btnLogOut = new JButton("Logout");
		btnLogOut.setBounds(97, 641, 110, 43);
		add(btnLogOut);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(584, 544, 117, 29);
		panel.add(btnCancel);

		
		// action listeners
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				librarySystem.updateGUI(new HomeGUI(librarySystem));
			}
		});
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				librarySystem.updateGUI(new HomeGUI(librarySystem));
			}
		});
		btnReturned.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//librarySystem.updateGUI(new ReturnedGUI(librarySystem));
			}
		});
		
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				librarySystem.updateGUI(new BrowseGUI(librarySystem));
			}
		});
		
		btnReceived.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//librarySystem.updateGUI(new ReceiveGUI(librarySystem));
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
		
		btnProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				librarySystem.updateGUI(new ProfileGUI(librarySystem));
			}
		}); 
		btnPayFees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				librarySystem.updateGUI(new PayGUI(librarySystem));
			}
		});
		
		btnChanPswrdLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				librarySystem.updateGUI(new PasswordGUI(librarySystem));
			}
		});
		btnMaterials.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				librarySystem.updateGUI(new MaterialsGUI(librarySystem));
			}
		});
		
		btnReservations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				librarySystem.updateGUI(new ReservationsGUI(librarySystem));
			}
		});
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//librarySystem.updateGUI(new LogOutGUI(librarySystem));
			}
		});

		
		String[] columnNames = { "Icon", "Material","Amount" };

		DefaultTableModel model = new DefaultTableModel(new Object[][][] {}, columnNames) {
		};

		JLabel bookImg = new JLabel("");
		bookImg.setIcon(new ImageIcon("resources/book.png"));

		Instructor instructor = (Instructor) librarySystem.getUserManager().getCurrentUser();
		List<Reservation> reservations = instructor.getReservations();
		Object[][] data = new Object[reservations.size()][3];
		int row = 0;
		for (Reservation reservation : instructor.getReservations()) {
			System.out.println("reservation: " + reservation);
			List<Material> materials = reservation.getMaterials();
			data[row++] = new Object[] {bookImg, materials.get(0).getNiceName(), materials.size()};
			tableReservations.add(reservation);
		}

        this.table = new JTable(data, columnNames);
        TableColumnModel tcm = table.getColumnModel();
        TableColumn tc = tcm.getColumn(2);
        tc.setCellEditor(new SpinnerEditor());
        tcm.getColumn(0).setCellRenderer(new IconRenderer());
		
		

		//String

        //table.setSurrendersFocusOnKeystroke(true);
		//this.table = new JTable(model);
		this.table.setPreferredScrollableViewportSize(table.getPreferredSize());
		this.table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.table.getColumnModel().getColumn(0).setMaxWidth(100);
		this.table.getColumnModel().getColumn(2).setMaxWidth(200);


		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("button: " + e.getButton());
				if (e.getButton() == MouseEvent.BUTTON3) {
					int row = table.rowAtPoint(e.getPoint());
					System.out.println("row: " + row);
					selectedReservation = tableReservations.get(row);
					JPopupMenu menu = new JPopupMenu();
					menu.add(new JMenuItem("Cancel"));
					menu.show(table, e.getX(), e.getY());
				}
			}
		});

		scrollPane.setViewportView(this.table);
	}
	
	/*
	 * Credits to 'bsm' + 'mKorbel': https://stackoverflow.com/questions/5614875/how-to-set-icon-in-a-column-of-jtable
	 * */
	public class IconRenderer extends DefaultTableCellRenderer{
	    public Component getTableCellRendererComponent(JTable table,Object obj,boolean isSelected,boolean hasFocus,int row,int column){
	        if(obj instanceof ImageIcon)
	            setIcon((ImageIcon) obj);
	        setBorder(UIManager.getBorder("TableHeader.cellBorder"));
	        setHorizontalAlignment(JLabel.CENTER);
	        return this;
	    }
	}

}
