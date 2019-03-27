package librarysystem.users.faculty;

import librarysystem.materials.Material;
import librarysystem.users.UserType;

import java.util.List;

public class TeachingAssistant extends Faculty {
	
	public TeachingAssistant(String username, String email, String name, String password, int id) {
		super(username, email, name, password, id, UserType.TEACHING_ASSISTANT);
	}
	
	public TeachingAssistant(String username, String email, String name, String password, int id, List<Material> borrowed, List<Material> onHold, double overdueFee, boolean blacklisted) {
		super(username, email, name, password, id, borrowed, onHold, overdueFee, blacklisted, UserType.TEACHING_ASSISTANT);
	}
}
