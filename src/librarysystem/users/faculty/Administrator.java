package librarysystem.users.faculty;

import librarysystem.materials.Material;
import librarysystem.users.UserType;

import java.util.List;

public class Administrator extends Faculty {
	
	public Administrator(String username, String email, String password, int id) {
		super(username, email, password, id, UserType.ADMINISTRATOR);
	}
	
	public Administrator(String username, String email, String password, int id, List<Material> borrowed, List<Material> onHold, double overdueFee, boolean blacklisted) {
		super(username, email, password, id, borrowed, onHold, overdueFee, blacklisted, UserType.ADMINISTRATOR);
	}
}
