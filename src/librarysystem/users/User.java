package librarysystem.users;

import librarysystem.database.TextDatabase;
import librarysystem.materials.Material;
import librarysystem.materials.MaterialStatus;

import java.util.ArrayList;
import java.util.List;

public abstract class User {

	private final String username, email;
	private final int id;
	private final String name;
	private String password;
	private UserType userType;
	private List<Material> borrowed, onHold;
	private double overdueFee;
	private boolean blacklisted;

	public User(String username, String email, String name, String password, int id, UserType userType) {
		this(username, email, name, password, id, new ArrayList<Material>(), new ArrayList<Material>(), 0, false, userType);
	}

	public User(String username, String email, String name, String password, int id, List<Material> borrowed, List<Material> onHold, double overdueFee, boolean blacklisted, UserType userType) {
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.id = id;
		this.borrowed = borrowed;
		this.onHold = onHold;
		this.overdueFee = overdueFee;
		this.blacklisted = blacklisted;
		this.userType = userType;
	}

	//Getters
	
	/**
	 * Returns the name of the user
	 * @return The name of the user
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the username of the user
	 * @return The username of the user
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Returns the email of the user
	 * @return The email of the user
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Returns the password of the user
	 * @return The password of the user
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Returns the ID of the user
	 * @return The ID of the user
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Returns the userType of the user
	 * @return The userType of the user
	 */
	public UserType getUserType() {
		return userType;
	}
	
	/**
	 * Returns the list of materials this user has on hold currently
	 * @return The list of materials this user has on hold currently
	 */
	public List<Material> getOnHold() {
		return onHold;
	}
	
	/**
	 * Returns the list of materials this user has taken out currently
	 * @return The list of materials this user has taken out currently
	 */
	public List<Material> getBorrowed() {
		return borrowed;
	}
	
	/**
	 * Returns the overdue fee this user has left to pay
	 * @return The overdue fee this user has left to pay
	 */
	public double getOverdueFee() {
		return overdueFee;
	}
	
	/**
	 * Returns the string representation of the borrowed materials this user has, for the database
	 * @return The string representation of the borrowed materials this user has, for the database
	 */
	public String getBorrowedMaterialString() {
		String borrowed = "";
		for (Material borrowedMat : this.borrowed) {
			borrowed += borrowedMat.getBarcode() + ",";
		}
		if (borrowed.length() > 0) {
			return borrowed.substring(0, borrowed.length() - 1);
		}
		return "NONE";
	}
	
	/**
	 * Returns the string representation of the held materials this user has, for the database
	 * @return The string representation of the held materials this user has, for the database
	 */
	public String getOnHoldMaterialString() {
		String onHold = "";
		for (Material onHoldMat : this.onHold) {
			onHold += onHoldMat.getBarcode() + ",";
		}
		if (onHold.length() > 0) {
			return onHold.substring(0, onHold.length() - 1);
		}
		return "NONE";
	}

	//Setters
	
	/**
	 * Updates the password of the user
	 * @param password The new password of the user
	 */
	public void setPassword(String password) {
		this.password = password;
		System.out.println(this.getUsername() + "'s Password is changed");
		TextDatabase.updateUser(this);
	}
	
	/**
	 * Updates the overdue fee of the user
	 * @param overdueFee The new overdue fee of the user
	 */
	public void setOverdueFee(double overdueFee) {
		System.out.println(this.getUsername() + "'s OverdueFee is updated");
		this.overdueFee = overdueFee;
		TextDatabase.updateUser(this);
	}
	
	/**
	 * Updates the blacklisted status of the user
	 * @param blacklisted The new blacklist status
	 */
	public void setBlacklisted(boolean blacklisted) {
		System.out.println(this.getUsername() + " is Blacklisted");
		this.blacklisted = blacklisted;
		TextDatabase.updateUser(this);
	}

	// Other Methods 
	
	/**
	 * Borrows a material under the user's account
	 * @param borrowed The material to be borrowed
	 */
	public void addBorrowedMaterial(Material borrowed) {
		this.onHold.remove(borrowed);
		this.borrowed.add(borrowed);
		borrowed.setMaterialStatus(MaterialStatus.BORROWED);
	}
	
	/**
	 * Removes the material under the user's account
	 * @param borrowed The material to be returned
	 */
	public void removeBorrowedMaterial(Material borrowed) {
		this.borrowed.remove(borrowed);
		borrowed.setMaterialStatus(MaterialStatus.RETURNED);
	}
	
	/**
	 * Puts a material on holder under the user's account
	 * @param hold The material to be put on hold
	 */
	public void addHold(Material hold) {
		this.onHold.add(hold);
		hold.setMaterialStatus(MaterialStatus.ON_HOLD);
	}
	
	/**
	 * Removes the hold under the user's account
	 * @param hold The material to be returned
	 */
	public void removeHold(Material hold) {
		this.onHold.remove(hold);
		hold.setMaterialStatus(MaterialStatus.AVAILABLE);
	}
	
	/**
	 * Returns the blacklisted status of the user
	 * @return The blacklisted status of the user
	 */
	public boolean isBlacklisted() {
		return blacklisted;
	}
	
	/**
	 * Returns a string representation of the user for the database
	 * @return A string representation of the user for the database
	 */
	public String toString() {
		return this.getUserType().name() + "|" + this.getId() + "|" + this.getUsername() + "|" + this.getEmail() + "|" + this.getName() + "|" + this.getPassword() + "|" + this.isBlacklisted() + "|"
				+ this.getOverdueFee() + "|" + this.getBorrowedMaterialString() + "|" + this.getOnHoldMaterialString();
	}

}
