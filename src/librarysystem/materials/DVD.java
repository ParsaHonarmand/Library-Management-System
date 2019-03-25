package librarysystem.materials;

public class DVD extends Material {
	
	public DVD(String title, String author, String id, int edition, int barcode, MaterialStatus materialStatus) {
		super(title, author, id, edition, barcode, materialStatus, MaterialType.DVD);
	}
	
	public DVD(String title, String author, String id, int edition, int barcode, MaterialStatus materialStatus, Long takeoutDate, Long renewDate) {
		super(title, author, id, edition, barcode, materialStatus, MaterialType.DVD, takeoutDate, renewDate);
	}
}
