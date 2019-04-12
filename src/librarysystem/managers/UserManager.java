package librarysystem.managers;

import librarysystem.LibrarySystem;
import librarysystem.database.TextDatabase;
import librarysystem.users.User;

import java.util.List;

/**
 * A manager for managing existing and new users
 * @author Rory Skipper
 */
public class UserManager {
	
	private final LibrarySystem librarySystem;
	
	private User currentUser;
	private List<User> users;
	
	public UserManager(LibrarySystem librarySystem) {
		this.librarySystem = librarySystem;
		this.users = TextDatabase.loadUsers(librarySystem.getMaterialManager());
	}
	
	/**
	 * Returns the current user logged into the system
	 * @return The current user logged into the system
	 */
	public User getCurrentUser() {
		return currentUser;
	}
	
	/**
	 * Returns the list of users loaded in the system
	 * @return The lsit of users loaded in the system
	 */
	public List<User> getUsers() {
		return users;
	}
	
	/**
	 * Sets the current user for the system
	 * @param currentUser The user to be set as the new current user
	 */
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
	/**
	 * Adds a new user to the system
	 * @param user The user being added to the system
	 */
	public void addUser(User user) {
		this.users.add(user);
		TextDatabase.addUser(user);
	}
	
	/**
	 * Removes a user from the system
	 * @param user The user being removed from the system
	 */
	public void removeUser(User user) {
		this.users.remove(user);
	}
	
	/**
	 * Attempts to login to the system using a username and password
	 * @param username The username to login with
	 * @param password The password to login with
	 * @return Returns the user upon successful login, or null otherwise
	 */
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
	
	/**
	 * Logs out the current user from the system
	 */
	public void logout() {
		this.setCurrentUser(null);
	}
}
