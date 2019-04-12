package librarysystem.materials;

public class Textbook extends Material {
	
	public Textbook(String title, String author, String id, int edition, int barcode, MaterialStatus materialStatus) {
		super(title, author, id, edition, barcode, materialStatus, MaterialType.TEXTBOOK);
	}
	
	public Textbook(String title, String author, String id, int edition, int barcode, MaterialStatus materialStatus, Long takeoutDate, Long renewDate) {
		super(title, author, id, edition, barcode, materialStatus, MaterialType.TEXTBOOK, takeoutDate, renewDate);
	}
}
