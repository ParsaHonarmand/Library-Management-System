package librarysystem.users;

import librarysystem.materials.Material;

import java.util.List;

public class Student extends User {
	
	public Student(String username, String email, String name, String password, int id) {
		super(username, email, name, password, id, UserType.STUDENT);
	}
	
	public Student(String username, String email, String name, String password, int id, List<Material> borrowed, List<Material> onHold, double overdueFee, boolean blacklisted) {
		super(username, email, name, password, id, borrowed, onHold, overdueFee, blacklisted, UserType.STUDENT);
	}
}
