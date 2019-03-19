package librarysystem.materials;

public class Material {
	
	private MaterialStatus materialStatus;
	private final MaterialType materialType;
	private final String title, author, id;
	private final int edition, barcode;
	private Long takeoutDate;
	
	public Material(String title, String author, String id, int edition, int barcode, MaterialStatus materialStatus, MaterialType materialType) {
		this(title, author, id, edition, barcode, materialStatus, materialType, -1L);
	}
	
	public Material(String title, String author, String id, int edition, int barcode, MaterialStatus materialStatus, MaterialType materialType, Long takeoutDate) {
		this.title = title;
		this.author = author;
		this.id = id;
		this.edition = edition;
		this.barcode = barcode;
		this.materialStatus = materialStatus;
		this.materialType = materialType;
		this.takeoutDate = takeoutDate;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public String getId() {
		return id;
	}
	
	public int getEdition() {
		return edition;
	}
	
	public int getBarcode() {
		return barcode;
	}
	
	public MaterialStatus getMaterialStatus() {
		return materialStatus;
	}
	
	public void setMaterialStatus(MaterialStatus materialStatus) {
		this.materialStatus = materialStatus;
	}
	
	public Long getTakeoutDate() {
		return takeoutDate;
	}
	
	public void setTakeoutDate(Long takeoutDate) {
		this.takeoutDate = takeoutDate;
	}
	
	public MaterialType getMaterialType() {
		return materialType;
	}
	
	@Override
	public String toString() {
		return this.getTitle() + " - " + this.getAuthor() + " (" + this.getId() + ")";
	}
}
