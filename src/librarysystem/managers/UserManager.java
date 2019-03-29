package librarysystem.managers;

import librarysystem.LibrarySystem;
import librarysystem.database.TextDatabase;
import librarysystem.users.User;

import java.util.List;

public class UserManager {
	
	private final LibrarySystem librarySystem;
	
	private User currentUser;
	private List<User> users;
	
	public UserManager(LibrarySystem librarySystem) {
		this.librarySystem = librarySystem;
		this.users = TextDatabase.loadUsers(librarySystem.getMaterialManager());
	}
	
	public User getCurrentUser() {
		return currentUser;
	}
	
	public List<User> getUsers() {
		return users;
	}
	
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}	
	
	public void addUser(User user) {
		this.users.add(user);
	}
	
	public void removeUser(User user) {
		this.users.remove(user);
	}
	
	public  User login(String username, String password) {
		for (User user : this.users) {
			if ((user.getUsername().equalsIgnoreCase(username) || user.getEmail().equalsIgnoreCase(username)) && user.getPassword().equals(password)) {
				this.setCurrentUser(user);
				System.out.println("login successful");
				return user;
			}
		}
		return null;
	}
	
	public void logout() {
		this.setCurrentUser(null);
	}
}
