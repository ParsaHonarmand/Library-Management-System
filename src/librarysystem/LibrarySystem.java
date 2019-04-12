package librarysystem;

import librarysystem.gui.LoginGUI;
import librarysystem.managers.MaterialManager;
import librarysystem.managers.UserManager;
import librarysystem.users.User;
import librarysystem.users.UserType;

import java.awt.Color;
import java.awt.Panel;

import javax.swing.*;

public class LibrarySystem {

	public final static int WIDTH = 1280, HEIGHT = 750;
	public final static Color studentThemeColor = new Color(0, 102, 153);
	public final static Color librarianThemeColor = new Color(0, 153, 153);
	public final static Color instructorThemeColor = new Color(204, 0, 153);

	private MaterialManager materialManager;
	private UserManager userManager;
	private JFrame frame;

	public LibrarySystem() {
		this.materialManager = new MaterialManager(this);
		this.userManager = new UserManager(this);

		this.frame = new JFrame();
		this.frame.setBounds(50, 50, WIDTH, HEIGHT);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setVisible(false);
	}

	public static void main(String args[]) {
		LibrarySystem librarySystem = new LibrarySystem();

		new LoginGUI(librarySystem);
	}

	/**
	 * Updates the frame with a new JPanel
	 * 
	 * @param panel
	 *            The new panel to be displayed
	 */
	public void updateGUI(JPanel panel) {
		this.frame.setContentPane(panel);
		this.frame.setVisible(true);
	}

	/**
	 * Returns the userManager
	 * 
	 * @return The userManager
	 */
	public UserManager getUserManager() {
		return userManager;
	}

	/**
	 * Returns the materialManager
	 * 
	 * @return The materialManager
	 */
	public MaterialManager getMaterialManager() {
		return materialManager;
	}

	/**
	 * Returns the frame
	 * 
	 * @return The frame
	 */
	public JFrame getFrame() {
		return this.frame;
	}

	/**
	 * Sets the colour of the panel based on the user type
	 * @param p the panel to set the colour on
	 */
	public void setTheme(JPanel p) {
		User currUser = this.getUserManager().getCurrentUser();
		if (currUser.getUserType() == UserType.STUDENT)
			p.setBackground(studentThemeColor);
		if (currUser.getUserType() == UserType.INSTRUCTOR)
			p.setBackground(instructorThemeColor);
		if (currUser.getUserType() == UserType.LIBRARIAN)
			p.setBackground(librarianThemeColor);

	}

}
