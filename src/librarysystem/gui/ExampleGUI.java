package librarysystem.gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import librarysystem.LibrarySystem;

public class ExampleGUI extends JPanel {
	
	private LibrarySystem librarySystem;

	/**
	 * Create the panel.
	 */
	
	// Simply add the librarySystem as a parameter to the already generated constructor that winder builder makes for you
	public ExampleGUI(LibrarySystem librarySystem) {
		this.librarySystem = librarySystem;
		
		JButton btnNewButton = new JButton("s");
		add(btnNewButton);

		// update gui after setup
		this.librarySystem.updateGUI(this);
	}

}
