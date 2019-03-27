package librarysystem.gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import librarysystem.LibrarySystem;

public class ExampleGUI extends JPanel {
	
	private LibrarySystem librarySystem;

	/**
	 * Create the panel.
	 */
	public ExampleGUI() {
		
		JButton btnNewButton = new JButton("s");
		add(btnNewButton);

	}
	
	public ExampleGUI(LibrarySystem librarySystem) {
		this();
		
		this.librarySystem = librarySystem;
		librarySystem.updateGUI(this);
		
	}

}
