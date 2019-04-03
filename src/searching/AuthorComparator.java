package searching;

import java.util.Comparator;

import librarysystem.materials.Material;

public class AuthorComparator implements Comparator<Material> {
	
	@Override
	public int compare(Material material, Material material2) {
		return material.getAuthor().compareTo(material2.getAuthor());
	}
	

}
