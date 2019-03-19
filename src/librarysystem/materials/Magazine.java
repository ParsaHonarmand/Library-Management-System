package librarysystem.materials;

public class Magazine extends Material {
	
	public Magazine(String title, String author, String id, int edition, int barcode, MaterialStatus materialStatus) {
		super(title, author, id, edition, barcode, materialStatus, MaterialType.MAGAZINE);
	}
	
	public Magazine(String title, String author, String id, int edition, int barcode, MaterialStatus materialStatus, Long takeoutDate) {
		super(title, author, id, edition, barcode, materialStatus, MaterialType.MAGAZINE, takeoutDate);
	}
}
