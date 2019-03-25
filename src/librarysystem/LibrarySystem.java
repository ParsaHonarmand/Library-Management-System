package librarysystem;

import librarysystem.database.TextDatabase;
import librarysystem.gui.HoldGUIScreen;
import librarysystem.gui.LoginScreen;
import librarysystem.gui.MenuGUI;
import librarysystem.gui.StudentScreen;
import librarysystem.managers.MaterialManager;
import librarysystem.managers.UserManager;
import librarysystem.materials.Book;
import librarysystem.materials.Material;
import librarysystem.materials.MaterialStatus;
import librarysystem.users.User;

import java.util.Scanner;

public class LibrarySystem {
	
	private MaterialManager materialManager;
	private static UserManager userManager;
	
	public static void main(String args[]) {
		LibrarySystem librarySystem = new LibrarySystem();
		
		librarySystem.materialManager = new MaterialManager(librarySystem);
		librarySystem.userManager = new UserManager(librarySystem);
		
		/*librarySystem.getMaterialManager().addMaterial(new Book("Astronomy 101 - 1st Edition", "Mark Davis", "ASTR-101", 1, librarySystem.getMaterialManager().getNextBarcode(), MaterialStatus.AVAILABLE));
		
		librarySystem.getMaterialManager().addMaterial(new Book("Economy 101 - 2nd Edition", "Liam Scott", "ECON-101", 2, librarySystem.getMaterialManager().getNextBarcode(), MaterialStatus.AVAILABLE));
		
		librarySystem.getMaterialManager().addMaterial(new Book("Java 101 - 3rd Edition", "Tyler Welty", "JAVA-101", 3, librarySystem.getMaterialManager().getNextBarcode(), MaterialStatus.AVAILABLE));
	*/
		
		new MenuGUI();
		
		System.out.println("printing users");
		for (User user : librarySystem.getUserManager().getUsers()) {
			System.out.println(user.getUserType());
		}
	}
	
	public UserManager getUserManager() {
		return userManager;
	}
	
	public MaterialManager getMaterialManager() {
		return materialManager;
	}
}
