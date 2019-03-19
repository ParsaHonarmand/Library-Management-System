package librarysystem.reservations;

import librarysystem.materials.Material;

public class Reservation {
	
	private final Material material;
	private int amount;
	private boolean approved;
	
	public Reservation(Material material, int amount, boolean approved) {
		this.material = material;
		this.amount = amount;
		this.approved = approved;
	}
	
	public Material getMaterial() {
		return material;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public boolean isApproved() {
		return approved;
	}
	
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	
	@Override
	public String toString() {
		return this.getMaterial().getId() + "+" + this.getAmount() + "+" + this.isApproved();
	}
}
