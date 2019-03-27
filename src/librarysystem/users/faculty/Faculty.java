package librarysystem.users.faculty;

import librarysystem.materials.Material;
import librarysystem.users.User;
import librarysystem.users.UserType;

import java.util.List;

public abstract class Faculty extends User {
	
	public Faculty(String username, String email, String name, String password, int id, UserType userType) {
		super(username, email, name, password, id, userType);
	}
	
	public Faculty(String username, String email, String name, String password, int id, List<Material> borrowed, List<Material> onHold, double overdueFee, boolean blacklisted, UserType userType) {
		super(username, email, name, password, id, borrowed, onHold, overdueFee, blacklisted, userType);
	}
}
