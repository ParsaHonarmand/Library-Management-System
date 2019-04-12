package librarysystem.managers;

import librarysystem.LibrarySystem;
import librarysystem.database.TextDatabase;
import librarysystem.materials.Material;
import librarysystem.materials.MaterialStatus;
import librarysystem.materials.MaterialType;
import librarysystem.reservations.Reservation;
import librarysystem.users.User;
import librarysystem.users.faculty.Instructor;

//import javax.xml.soap.Text;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MaterialManager {
	
	private LibrarySystem librarySystem;
	
	private HashMap<MaterialStatus, List<Material>> materialLists;
	
	public MaterialManager(LibrarySystem librarySystem) {
		this.librarySystem = librarySystem;
		
		this.materialLists = TextDatabase.loadMaterials();
	}
	
	/**
	 * Updates the state of the material, you should not change the status (Material#setMaterialStatus) before using this method otherwise it wont work
	 *
	 * @param material  The material to update the status of
	 * @param newStatus The new status for the material
	 */
	public void updateStatus(Material material, MaterialStatus newStatus) {
		this.getMaterials(material.getMaterialStatus()).remove(material);
		this.getMaterials(newStatus).add(material);
		material.setMaterialStatus(newStatus);
		TextDatabase.updateMaterial(material);
	}
	
	/**
	 * Returns the list of materials that have a certain status
	 *
	 * @param materialStatus The status of materials you want
	 * @return The list of materials that have a certain status
	 */
	public List<Material> getMaterials(MaterialStatus materialStatus) {
		return this.materialLists.get(materialStatus);
	}
	
	/**
	 * Returns the list of unique materials of a specific status
	 *
	 * @param materialStatus The status of materials you want
	 * @return The list of unique materials of a specific status
	 */
	public List<Material> getUniqueMaterials(MaterialStatus materialStatus) {
		List<Material> materials = new ArrayList<Material>(), loopMaterials = this.getMaterials(materialStatus);
		for (Material material : loopMaterials) {
			String id = material.getId();
			boolean addMaterial = true;
			for (Material loopMaterial : materials) {
				if (loopMaterial.getId().equals(id)) {
					addMaterial = false;
					break;
				}
			}
			if (addMaterial) {
				materials.add(material);
			}
		}
		
		return materials;
	}
	
	/**
	 * Returns the list of unique materials of a specific status and type
	 *
	 * @param materialStatus The status of materials you want
	 * @param materialType   The type of materials you want
	 * @return The list of unique materials of a specific status and type
	 */
	public List<Material> getUniqueMaterials(MaterialStatus materialStatus, MaterialType materialType) {
		List<Material> materials = new ArrayList<Material>(), loopMaterials = this.getMaterials(materialStatus);
		for (Material material : loopMaterials) {
			if (material.getMaterialType() == materialType) {
				String id = material.getId();
				boolean addMaterial = true;
				for (Material loopMaterial : materials) {
					if (loopMaterial.getId().equals(id)) {
						addMaterial = false;
						break;
					}
				}
				if (addMaterial) {
					materials.add(material);
				}
			}
		}
		
		return materials;
	}
	
	/**
	 * Puts a specific material on hold for a user (don't update the status of the material, this method will do it for you)
	 *
	 * @param user     The user putting the material on hold
	 * @param material The material that is being put on hold
	 */
	public void putOnHold(User user, Material material) {
		this.updateStatus(material, MaterialStatus.ON_HOLD);
		user.addHold(material);
		TextDatabase.updateUser(user);
	}
	
	/**
	 * Borrows a specific material for a user (don't update the status of the material, this method will do it for you)
	 *
	 * @param user     The user taking out the material
	 * @param material The material that is being borrowed
	 */
	public void borrowMaterial(User user, Material material) {
		material.setTakeoutDate(System.currentTimeMillis());
		this.updateStatus(material, MaterialStatus.BORROWED);
		user.addBorrowedMaterial(material);
		TextDatabase.updateUser(user);
	}
	
	/**
	 * Returns a specific material for a user (don't update the status of the material, this method will do it for you)
	 *
	 * @param user     The user returning the material
	 * @param material The material that is being returned
	 */
	public void returnMaterial(User user, Material material) {
		Long timeTakenOut = System.currentTimeMillis() - material.getTakeoutDate();
		if (material.hasBeenRenewed()) {
			timeTakenOut = System.currentTimeMillis() - material.getRenewDate();
		}
		if (timeTakenOut / 1000L > 60 * 60 * 24 * 7) {
			double fee = (timeTakenOut / 1000L - (60 * 60 * 24 * 7)) / (60 * 60 * 24) * 5;
			user.setOverdueFee(user.getOverdueFee() + fee);
		}
		material.setTakeoutDate(-1L);
		material.setRenewDate(-1L);
		this.updateStatus(material, MaterialStatus.RETURNED);
		user.removeBorrowedMaterial(material);
		user.removeHold(material);
		TextDatabase.updateUser(user);
	}
	
	/**
	 * Reserve a certain material
	 *
	 * @param user     The instructor that is reserving the material
	 * @param material The material being reserved
	 * @param amount   The amount of copies being reserved
	 */
	public void reserveMaterial(Instructor user, Material material, int amount) {
		String id = material.getId();
		Reservation reservation = null;
		for (Reservation loopReservation : user.getReservations()) {
			if (loopReservation.getMaterials().get(0).getId().equals(id)) {
				reservation = loopReservation;
				break;
			}
		}
		
		int loopAmount = 0;
		List<Material> toBeReserved = new ArrayList<Material>();
		for (Material availableMaterial : this.getMaterials(MaterialStatus.AVAILABLE)) {
			if (availableMaterial.getId().equals(id)) {
				toBeReserved.add(availableMaterial);
				if (++loopAmount == amount) {
					break;
				}
			}
		}
		
		if (reservation == null) {
			user.addReservation(new Reservation(toBeReserved, true));
		}
		
		for (Material reservationMaterial : toBeReserved) {
			if (reservation != null) {
				reservation.addMaterial(material);
			}
			updateStatus(reservationMaterial, MaterialStatus.RESERVED);
		}
		
		System.out.println("update user");
		TextDatabase.updateUser(user);
	}
	
	/**
	 * Returns a material of a specific ID
	 *
	 * @param id The id of the material being searched for
	 * @return The material matching the given id
	 */
	public Material getMaterial(String id) {
		for (MaterialStatus materialStatus : MaterialStatus.values()) {
			Material material = this.getMaterial(materialStatus, id);
			if (material != null) {
				return material;
			}
		}
		return null;
	}
	
	/**
	 * Returns a material of a certain status and id
	 *
	 * @param materialStatus The status of the material
	 * @param id             The id of the material
	 * @return A material of a certain status and id
	 */
	public Material getMaterial(MaterialStatus materialStatus, String id) {
		for (Material material : this.getMaterials(materialStatus)) {
			if (material.getId().equals(id)) {
				return material;
			}
		}
		return null;
	}

	/**
	 * Returns a list of materials of a certain status and id
	 *
	 * @param materialStatus The status of the material
	 * @param id             The id of the material
	 * @return A list of materials of a certain status and id
	 */
	public List<Material> getMaterials(MaterialStatus materialStatus, String id) {
		List<Material> materials = new ArrayList<>();
		for (Material material : this.getMaterials(materialStatus)) {
			if (material.getId().equals(id)) {
				materials.add(material);
			}
		}
		return materials;
	}
	
	/**
	 * Returns the material of a specific barcode
	 *
	 * @param barcode The barcode of the material you''re looking for
	 * @return Returns the material of a specific barcode
	 */
	public Material getMaterial(int barcode) {
		for (MaterialStatus materialStatus : MaterialStatus.values()) {
			for (Material material : this.getMaterials(materialStatus)) {
				if (material.getBarcode() == barcode) {
					return material;
				}
			}
		}
		return null;
	}
	
	
	/**
	 * Returns the amount of of material available for reservation
	 * @param material The material being reserved
	 * @return Returns the amount of specific material available for reservation
	 */
	public int getAmountAvailableForReservation(Material material) {
		int amount = 0;
		String id = material.getId();
		for (Material availableMaterial : this.getMaterials(MaterialStatus.AVAILABLE)) {
			if (availableMaterial.getId().equals(id)) {
				amount++;
			}
		}
		return amount;
	}
	
	/**
	 * Adds a material to the system
	 * @param material material to be added
	 */
	public void addMaterial(Material material) {
		this.getMaterials(material.getMaterialStatus()).add(material);
		TextDatabase.addMaterial(material);
	}
	
	/**
	 * Reshelves all material
	 */
	public void reshelveMaterial() {
		for (Material material : this.getMaterials(MaterialStatus.RETURNED)) {
			this.reshelveMaterial(material);
		}
	}
	
	/**
	 * Reshelves a specific material
	 * @param material The material to be reshelved
	 */
	public void reshelveMaterial(Material material) {
		this.updateStatus(material, MaterialStatus.AVAILABLE);
	}
	
	/**
	 * Receives all material thats on order
	 */
	public void receiveMaterial() {
		for (Material material : this.getMaterials(MaterialStatus.ON_ORDER)) {
			this.receiveMaterial(material);
		}
	}
	
	/**
	 * Receives a certain material thats on order
	 * @param material The material being received
	 */
	public void receiveMaterial(Material material) {
		this.updateStatus(material, MaterialStatus.AVAILABLE);
	}
	
	/**
	 * Returns the next available barcode for the new material
	 * @return Returns the next available barcode for the new material
	 */
	public int getNextBarcode() {
		int barcode = 0;
		for (List<Material> materials : this.materialLists.values()) {
			barcode += materials.size();
		}
		Material material;
		do {
			material = this.getMaterial(++barcode);
		} while (material != null);
		return barcode;
	}
	
	/**
	 * Searches through material
	 * @param searchStr The string to search with
	 * @param materialStatus The status of the material you're looking for
	 * @return The list of materials matching the searchStr and materialStatus
	 */
	public List<Material> search(String searchStr, MaterialStatus materialStatus) {
		// TODO: Refine searching, right now search engine is weak
		searchStr = searchStr.toLowerCase().trim();
		List<Material> materials = new ArrayList<>();
		for (Material material : this.getMaterials(materialStatus)) {
			String author = material.getAuthor().toLowerCase(), title = material.getTitle().toLowerCase();
			if (author.contains(searchStr) || searchStr.contains(author) || title.contains(searchStr) || searchStr.contains(title)) {
				materials.add(material);
			}
		}
		return materials;
	}
	
}
