package librarysystem.managers;

import librarysystem.LibrarySystem;
import librarysystem.database.TextDatabase;
import librarysystem.materials.Material;
import librarysystem.materials.MaterialStatus;
import librarysystem.users.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MaterialManager {
	
	private HashMap<MaterialStatus, List<Material>> materialLists = new HashMap<>();
	
	public MaterialManager(LibrarySystem librarySystem) {
		this.materialLists = TextDatabase.loadMaterials();
	}
	
	public void loadMaterials() {
		this.materialLists.clear();
		
		for (MaterialStatus materialStatus : MaterialStatus.values()) {
			this.materialLists.put(materialStatus, new ArrayList<Material>());
		}
	}
	
	public List<Material> getMaterials(MaterialStatus materialStatus) {
		return this.materialLists.get(materialStatus);
	}
	
	public void putOnHold(User user, Material material) {
		if (material.getMaterialStatus() == MaterialStatus.AVAILABLE) {
			user.addHold(material);
			this.getMaterials(MaterialStatus.AVAILABLE).remove(material);
		}
		this.getMaterials(MaterialStatus.ON_HOLD).add(material);
	}
	
	public void borrowMaterial(User user, Material material) {
		if (material.getMaterialStatus() == MaterialStatus.AVAILABLE) {
			user.addBorrowedMaterial(material);
			this.getMaterials(MaterialStatus.AVAILABLE).remove(material);
		}
		this.getMaterials(MaterialStatus.BORROWED).add(material);
		
	}
	
	public void returnMaterial(User user, Material material) {
		if (material.getMaterialStatus() == MaterialStatus.BORROWED) {
			user.removeBorrowedMaterial(material);
			this.getMaterials(MaterialStatus.BORROWED).remove(material);
		} else if (material.getMaterialStatus() == MaterialStatus.ON_HOLD) {
			user.removeHold(material);
			this.getMaterials(MaterialStatus.ON_HOLD).remove(material);
		}
		
		this.getMaterials(material.getMaterialStatus()).add(material);
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
	
	public ArrayList<Material> getMaterial(MaterialStatus materialStatus) {
		ArrayList<Material> mat = new ArrayList<Material>();
		for (Material material : this.getMaterials(materialStatus)) {
				mat.add(material);
		}
		return mat;
	}
	
	
	public HashMap<MaterialStatus, List<Material>> getMaterialLists() {
		return materialLists;
	}
	
	public void addMaterial(Material material) {
		this.getMaterials(material.getMaterialStatus()).add(material);
		TextDatabase.addMaterial(material);
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
