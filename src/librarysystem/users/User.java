package librarysystem.users;

import librarysystem.materials.Material;
import librarysystem.materials.MaterialStatus;

import java.util.ArrayList;
import java.util.List;

public abstract class User {
	
	private final String username, email;
	private final int id;
	private String password;
	private UserType userType;
	private List<Material> borrowed, onHold;
	private double overdueFee;
	private boolean blacklisted;
	
	public User(String username, String email, String password, int id, UserType userType) {
		this(username, email, password, id, new ArrayList<Material>(), new ArrayList<Material>(), 0, false, userType);
	}
	
	public User(String username, String email, String password, int id, List<Material> borrowed, List<Material> onHold, double overdueFee, boolean blacklisted, UserType userType) {
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
	
	public String getUsername() {
		return username;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getId() {
		return id;
	}
	
	public List<Material> getBorrowed() {
		return borrowed;
	}
	
	public void addBorrowedMaterial(Material borrowed) {
		this.onHold.remove(borrowed);
		this.borrowed.add(borrowed);
		borrowed.setMaterialStatus(MaterialStatus.BORROWED);
	}
	
	public void removeBorrowedMaterial(Material borrowed) {
		this.borrowed.remove(borrowed);
		borrowed.setMaterialStatus(MaterialStatus.RETURNED);
		if ((System.currentTimeMillis() - borrowed.getTakeoutDate())/1000L > 604800) {
			// TODO: Update overdueFee
		}
	}
	
	public List<Material> getOnHold() {
		return onHold;
	}
	
	public void addHold(Material hold) {
		this.onHold.add(hold);
		hold.setMaterialStatus(MaterialStatus.ON_HOLD);
	}
	
	public void removeHold(Material hold) {
		this.onHold.remove(hold);
		hold.setMaterialStatus(MaterialStatus.AVAILABLE);
	}
	
	public double getOverdueFee() {
		return overdueFee;
	}
	
	public void setOverdueFee(double overdueFee) {
		this.overdueFee = overdueFee;
	}
	
	public boolean isBlacklisted() {
		return blacklisted;
	}
	
	public void setBlacklisted(boolean blacklisted) {
		this.blacklisted = blacklisted;
	}
	
	public UserType getUserType() {
		return userType;
	}
	
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	
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
	
}
