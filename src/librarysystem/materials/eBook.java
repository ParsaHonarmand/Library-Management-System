package librarysystem.materials;

public class eBook extends Material {
	
	public eBook(String title, String author, String id, int edition, int barcode, MaterialStatus materialStatus) {
		super(title, author, id, edition, barcode, materialStatus, MaterialType.EBOOK);
	}
	
	public eBook(String title, String author, String id, int edition, int barcode, MaterialStatus materialStatus, Long takeoutDate, Long renewDate) {
		super(title, author, id, edition, barcode, materialStatus, MaterialType.EBOOK, takeoutDate, renewDate);
	}
}
