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
	
	/**
	 * Returns the list of materials being reserved
	 * @return The list of materials being reserved
	 */
	public List<Material> getMaterials() {
		return materials;
	}
	
	/**
	 * Returns the amount of materials being reserved
	 * @return The amount of materials being reserved
	 */
	public int getAmount() {
		return this.materials.size();
	}
	
	/**
	 * Adds a material to the list of reserved materials
	 * @param material The material to be reserved
	 */
	public void addMaterial(Material material) {
		this.materials.add(material);
	}
	
	/**
	 * Returns a boolean stating if the reservation has been approved
	 * @return A boolean stating if the reservation has been approved
	 */
	public boolean isApproved() {
		return approved;
	}
	
	/**
	 * Sets the reservation approval boolean
	 * @param approved The true/false value for approval
	 */
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	
	/**
	 * Returns a string representation of the reservation for the database
	 * @return A string representation of the reservation for the database
	 */
	@Override
	public String toString() {
		String materialsString = "";
		for (Material material : this.getMaterials()) {
			materialsString += material.getBarcode() + "-";
		}
		if (materialsString.length() > 0) {
			materialsString = materialsString.substring(0, materialsString.length() - 1);
		}
		return materialsString + "+" + this.isApproved();
	}
}
