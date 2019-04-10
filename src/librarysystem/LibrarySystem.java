package librarysystem;

import librarysystem.gui.LoginGUI;
import librarysystem.managers.MaterialManager;
import librarysystem.managers.UserManager;

import javax.swing.*;

public class LibrarySystem {
	
	public final static int WIDTH = 1280, HEIGHT = 720;
	
	private MaterialManager materialManager;
	private UserManager userManager;
	private JFrame frame;
	
	public LibrarySystem() {
		this.materialManager = new MaterialManager(this);
		this.userManager = new UserManager(this);
		
		this.frame = new JFrame();
		this.frame.setBounds(100, 100, WIDTH, HEIGHT);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setVisible(false);
	}
	
	public static void main(String args[]) {
		LibrarySystem librarySystem = new LibrarySystem();

		new LoginGUI(librarySystem);
	}
	
	/**
	 * Updates the frame with a new JPanel
	 * @param panel The new panel to be displayed
	 */
	public void updateGUI(JPanel panel) {
		this.frame.setContentPane(panel);
		this.frame.setVisible(true);
	}
	
	/**
	 * Returns the userManager
	 * @return The userManager
	 */
	public UserManager getUserManager() {
		return userManager;
	}
	
	/**
	 * Returns the materialManager
	 * @return The materialManager
	 */
	public MaterialManager getMaterialManager() {
		return materialManager;
	}
	
	/**
	 * Returns the frame
	 * @return The frame
	 */
	public JFrame getFrame() {
		return this.frame;
	}
	
}
