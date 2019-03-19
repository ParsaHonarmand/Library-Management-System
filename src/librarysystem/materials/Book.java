package librarysystem.materials;

public class Book extends Material {
	
	public Book(String title, String author, String id, int edition, int barcode, MaterialStatus materialStatus) {
		super(title, author, id, edition, barcode, materialStatus, MaterialType.BOOK);
	}
	
	public Book(String title, String author, String id, int edition, int barcode, MaterialStatus materialStatus, Long takeoutDate) {
		super(title, author, id, edition, barcode, materialStatus, MaterialType.BOOK, takeoutDate);
	}
}
