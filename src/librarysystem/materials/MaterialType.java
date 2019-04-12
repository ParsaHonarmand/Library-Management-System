package librarysystem.materials;

public enum MaterialType {
	
	ALL("All"),
	BOOK("Book"),
	CD("CD"),
	DVD("DVD"),
	EBOOK("eBook"),
	MAGAZINE("Magazine"),
	TEXTBOOK("Textbook");
	
	private final String niceName;
	
	MaterialType(String niceName) {
		this.niceName = niceName;
	}
	
	public String getNiceName() {
		return niceName;
	}
}
