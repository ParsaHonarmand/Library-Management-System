package librarysystem.managers;

import librarysystem.LibrarySystem;
import librarysystem.database.TextDatabase;
import librarysystem.materials.Material;
import librarysystem.materials.MaterialStatus;
import librarysystem.materials.MaterialType;
import librarysystem.users.User;

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
	
	public void updateStatus(Material material, MaterialStatus newStatus) {
		this.getMaterials(material.getMaterialStatus()).remove(material);
		this.getMaterials(newStatus).add(material);
		material.setMaterialStatus(newStatus);
		TextDatabase.updateMaterial(material);
	}
	
	public List<Material> getMaterials(MaterialStatus materialStatus) {
		return this.materialLists.get(materialStatus);
	}
	
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
	
	public void putOnHold(User user, Material material) {
		this.updateStatus(material, MaterialStatus.ON_HOLD);
		user.addHold(material);
	}
	
	public void borrowMaterial(User user, Material material) {
		this.updateStatus(material, MaterialStatus.BORROWED);
		user.addBorrowedMaterial(material);
	}
	
	public void returnMaterial(User user, Material material) {
		user.removeBorrowedMaterial(material);
		user.removeHold(material);
		this.updateStatus(material, MaterialStatus.RETURNED);
	}
	
	public Material getMaterial(String id) {
		for (MaterialStatus materialStatus : MaterialStatus.values()) {
			Material material = this.getMaterial(materialStatus, id);
			if (material != null) {
				return material;
			}
		}
		return null;
	}
	
	public Material getMaterial(MaterialStatus materialStatus, String id) {
		for (Material material : this.getMaterials(materialStatus)) {
			if (material.getId().equals(id)) {
				return material;
			}
		}
		return null;
	}
	
	public Material getMaterial(int barcode) {
		for (MaterialStatus materialStatus : MaterialStatus.values()) {
			Material material = this.getMaterial(materialStatus, barcode);
			if (material != null) {
				return material;
			}
		}
		return null;
	}
	
	public Material getMaterial(MaterialStatus materialStatus, int barcode) {
		for (Material material : this.getMaterials(materialStatus)) {
			if (material.getBarcode() == barcode) {
				return material;
			}
		}
		return null;
	}
	
	
	public HashMap<MaterialStatus, List<Material>> getMaterialLists() {
		return materialLists;
	}
	
	public void addMaterial(Material material) {
		this.getMaterials(material.getMaterialStatus()).add(material);
		TextDatabase.addMaterial(material);
	}
	
	public void reshelveMaterial() {
		for (Material material : this.getMaterials(MaterialStatus.RETURNED)) {
			this.reshelveMaterial(material);
		}
	}
	
	public void reshelveMaterial(Material material) {
		this.updateStatus(material, MaterialStatus.AVAILABLE);
	}
	
	public void receiveMaterial() {
		for (Material material : this.getMaterials(MaterialStatus.ON_ORDER)) {
			this.receiveMaterial(material);
		}
	}
	
	public void receiveMaterial(Material material) {
		this.updateStatus(material, MaterialStatus.AVAILABLE);
	}
	
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
