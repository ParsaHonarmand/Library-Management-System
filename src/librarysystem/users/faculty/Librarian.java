package librarysystem.users.faculty;

import librarysystem.materials.Material;
import librarysystem.users.UserType;

import java.util.List;

public class Librarian extends Faculty {
	
	public Librarian(String username, String email, String password, int id) {
		super(username, email, password, id, UserType.LIBRARIAN);
	}
	
	public Librarian(String username, String email, String password, int id, List<Material> borrowed, List<Material> onHold, double overdueFee, boolean blacklisted) {
		super(username, email, password, id, borrowed, onHold, overdueFee, blacklisted, UserType.LIBRARIAN);
	}
}
