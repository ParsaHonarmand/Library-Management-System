package librarysystem.gui;

import librarysystem.LibrarySystem;
import librarysystem.users.User;

import javax.swing.JPanel;

public class NameGUI extends JPanel {

	private LibrarySystem librarySystem;
	
	/**
	 * Create the panel.
	 */
	public NameGUI(LibrarySystem librarySystem) {
		this.librarySystem = librarySystem;
		
		this.librarySystem.updateGUI(this);
	}
	
	public void actionPerformed() {
		User user = this.librarySystem.getUserManager().getCurrentUser();
		String oldPassword = "123"; // oldPasswordField.getText();
		String newPassword = "12345";
		String confirmPassword = "12345";
		if (!oldPassword.equals(user.getPassword())) {
		
		}
		
		user.setPassword(newPassword);
		// textSuccessLabel.setText("Password changed successfully!")
	}

}
