package librarysystem.reservations;

import librarysystem.materials.Material;

import java.util.List;

public class Reservation {
	
	private List<Material> materials;
	private boolean approved;
	
	public Reservation(List<Material> materials, boolean approved) {
		this.materials = materials;
		this.approved = approved;
	}
	
	public List<Material> getMaterials() {
		return materials;
	}
	
	public int getAmount() {
		return this.materials.size();
	}
	
	public void addMaterial(Material material) {
		this.materials.add(material);
	}
	
	public boolean isApproved() {
		return approved;
	}
	
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	
	@Override
	public String toString() {
		return this.getMaterials().get(0).toString() + "+" + this.getAmount() + "+" + this.isApproved();
	}
}
