package searching;

import java.util.Comparator;

import librarysystem.materials.Material;

public class TitleComparator implements Comparator<Material> {
	
	@Override
	public int compare(Material material, Material material2) {
		return material.getTitle().compareTo(material2.getTitle());
	}

}
