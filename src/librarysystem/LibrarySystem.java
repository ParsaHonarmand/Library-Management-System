package librarysystem;

import librarysystem.database.TextDatabase;
import librarysystem.gui.HoldGUIScreen;
import librarysystem.gui.LoginScreen;
import librarysystem.gui.MenuGUI;
import librarysystem.gui.StudentScreen;
import librarysystem.gui.MenuGUI;
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
		
		MenuGUI menuGUI = new MenuGUI();
		LoginScreen.main(librarySystem);

	}
	
	public void updateGUI(JPanel panel) {
		this.frame.setVisible(true);
		this.frame.setContentPane(panel);
	}
	
	public UserManager getUserManager() {
		return userManager;
	}
	
	public MaterialManager getMaterialManager() {
		return materialManager;
	}
	
}
