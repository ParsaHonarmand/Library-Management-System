package librarysystem.materials;

public enum MaterialType {
	
	BOOK("Book"),
	CD("CD"),
	DVD("DVD"),
	EBOOK("eBook"),
	MAGAZINE("Magazine");
	
	private final String niceName;
	
	MaterialType(String niceName) {
		this.niceName = niceName;
	}
	
	public String getNiceName() {
		return niceName;
	}
}
