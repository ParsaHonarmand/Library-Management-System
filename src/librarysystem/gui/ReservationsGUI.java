package librarysystem.gui;

import librarysystem.LibrarySystem;
import librarysystem.gui.JSpinnerInTables.SpinnerEditor;
import librarysystem.materials.Material;
import librarysystem.reservations.Reservation;
import librarysystem.users.UserType;
import librarysystem.users.faculty.Instructor;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

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
	 * @param librarySystem The system to base the GUI on
	 */
	public ReservationsGUI(LibrarySystem librarySystem) {
		this.librarySystem = librarySystem;
		this.setBounds(0, 0, librarySystem.WIDTH, librarySystem.HEIGHT);
		
		
		setBackground(new Color(255, 255, 255));
		setForeground(Color.WHITE);;;;
		setLayout(null);
		
		JPanel panel = new JPanel();
		librarySystem.setTheme(panel);
		panel.setBounds(211, 270, 1054, 720);
		add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(85, 61, 570, 375);
		panel.add(scrollPane);
		JLabel profilePic = new JLabel();
		profilePic.setBounds(50, 281, 150, 150);
		profilePic.setIcon(new ImageIcon("resources/profile.png"));
		setLayout(null);
		add(profilePic);
		
		
		JLabel lblBanner = new JLabel("");
		lblBanner.setBounds(15, 15, 1250, 200);
		lblBanner.setIcon(new ImageIcon("resources/banner_img.png"));
		setLayout(null);
		add(lblBanner);
		
		/**
		 * General buttons constant throughout all panels
		 */
		JButton btnHome = new JButton("Home");
		btnHome.setBounds(80, 225, 120, 30);
		btnHome.setForeground(new Color(0, 0, 128));
		add(btnHome);
		
		JButton btnReturned = new JButton("Returned");
		btnReturned.setBounds(280, 225, 120, 30);
		btnReturned.setForeground(new Color(0, 0, 128));
		add(btnReturned);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(480, 225, 120, 30);
		btnBrowse.setForeground(new Color(0, 0, 128));
		add(btnBrowse);
		
		JButton btnReceived = new JButton("Received");
		btnReceived.setBounds(1080, 225, 120, 30);
		btnReceived.setForeground(new Color(0, 0, 128));
		add(btnReceived);
		
		JButton btnOrder = new JButton("Order");
		btnOrder.setBounds(880, 225, 120, 30);
		btnOrder.setForeground(new Color(0, 0, 128));
		add(btnOrder);
		
		JButton btnAccount = new JButton("Account");
		btnAccount.setBounds(680, 225, 120, 30);
		btnAccount.setForeground(new Color(0, 0, 128));
		add(btnAccount);
		
		
		JButton btnChanPswrdLeft = new JButton("Change Password");
		btnChanPswrdLeft.setBounds(50, 450, 158, 60);
		add(btnChanPswrdLeft);
		
		JButton btnPayFees = new JButton("Pay Fees");
		btnPayFees.setBounds(50, 508, 158, 60);
		add(btnPayFees);
		
		JButton btnMaterials = new JButton("Materials");
		btnMaterials.setBounds(50, 564, 158, 60);
		add(btnMaterials);
		
		JButton btnReservations = new JButton("Reservations");
		btnReservations.setBounds(50, 621, 158, 60);
		if(librarySystem.getUserManager().getCurrentUser().getUserType()==UserType.INSTRUCTOR) {
			add(btnReservations);
		}
		
		JButton btnLogOut = new JButton("Logout");
		btnLogOut.setBounds(98, 685, 110, 43);
		add(btnLogOut);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(715, 405, 117, 29);
		panel.add(btnCancel);

		
		// action listeners
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new HomeGUI(librarySystem);
			}
		});
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
		
		
		btnPayFees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PayGUI(librarySystem);
			}
		});
		
		btnAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				librarySystem.updateGUI(new ProfileGUI(librarySystem));
			}
		});
		
		btnChanPswrdLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PasswordGUI(librarySystem);
			}
		});
		btnMaterials.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MaterialsGUI(librarySystem);
			}
		});
		
		btnReservations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ReservationsGUI(librarySystem);
			}
		});
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				librarySystem.getUserManager().logout();
				new LoginGUI(librarySystem);
			}
		});

		if (librarySystem.getUserManager().getCurrentUser().getUserType() == UserType.STUDENT || librarySystem.getUserManager().getCurrentUser().getUserType() == UserType.INSTRUCTOR ) {
			btnOrder.setVisible(false);
			btnReceived.setVisible(false);
		}
		String[] columnNames = { "Icon", "Material","Amount"};

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
			data[row] = new Object[] {bookImg, materials.get(row).getNiceName(), materials.size()};
			row++;
			tableReservations.add(reservation);
		}

        this.table = new JTable(data, columnNames);
        TableColumnModel tcm = table.getColumnModel();
        TableColumn tc = tcm.getColumn(2);
        tc.setCellEditor(new SpinnerEditor());
        tcm.getColumn(0).setCellRenderer(new IconRenderer());
		
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
		
		this.librarySystem.updateGUI(this);
	}
	
	/*
	 * Credits to 'bsm' + 'mKorbel': https://stackoverflow.com/questions/5614875/how-to-set-icon-in-a-column-of-jtable
	 * */
	public class IconRenderer extends DefaultTableCellRenderer{
	    public Component getTableCellRendererComponent(JTable table,Object obj,boolean isSelected,boolean hasFocus,int row,int column){
	        if(obj instanceof ImageIcon)
	            setIcon(new ImageIcon("resources/book.png"));
	        setBorder(UIManager.getBorder("TableHeader.cellBorder"));
	        setHorizontalAlignment(JLabel.CENTER);
	        return this;
	    }
	}

}
