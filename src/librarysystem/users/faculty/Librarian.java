package librarysystem.users.faculty;

import librarysystem.materials.Material;
import librarysystem.users.UserType;

import java.util.List;

public class Librarian extends Faculty {
	
	public Librarian(String username, String email, String name, String password, int id) {
		super(username, email, name, password, id, UserType.LIBRARIAN);
	}
	
	public Librarian(String username, String email, String name, String password, int id, List<Material> borrowed, List<Material> onHold, double overdueFee, boolean blacklisted) {
		super(username, email, name, password, id, borrowed, onHold, overdueFee, blacklisted, UserType.LIBRARIAN);
	}
}
