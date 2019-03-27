package librarysystem.materials;

public class CD extends Material {
	
	public CD(String title, String author, String id, int edition, int barcode, MaterialStatus materialStatus) {
		super(title, author, id, edition, barcode, materialStatus, MaterialType.CD);
	}
	
	public CD(String title, String author, String id, int edition, int barcode, MaterialStatus materialStatus, Long takeoutDate, Long renewDate) {
		super(title, author, id, edition, barcode, materialStatus, MaterialType.CD, takeoutDate, renewDate);
	}
}
