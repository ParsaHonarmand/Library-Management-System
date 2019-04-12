package librarysystem.materials;

import librarysystem.util.TimeUtil;

public class Material implements Cloneable {
	
	private MaterialStatus materialStatus;
	private final MaterialType materialType;
	private final String title, author, id;
	private final int edition;
	private int barcode;
	private Long takeoutDate, renewDate;
	
	public Material(String title, String author, String id, int edition, int barcode, MaterialStatus materialStatus, MaterialType materialType) {
		this(title, author, id, edition, barcode, materialStatus, materialType, -1L, -1L);
	}
	
	public Material(String title, String author, String id, int edition, int barcode, MaterialStatus materialStatus, MaterialType materialType, Long takeoutDate, Long renewDate) {
		this.title = title;
		this.author = author;
		this.id = id;
		this.edition = edition;
		this.barcode = barcode;
		this.materialStatus = materialStatus;
		this.materialType = materialType;
		this.takeoutDate = takeoutDate;
		this.renewDate = renewDate;
	}
	
	/**
	 * Returns the title of this material
	 * @return The title of this material
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Returns the author of this material
	 * @return The author of this material
	 */
	public String getAuthor() {
		return author;
	}
	
	/**
	 * Returns the ID of this material
	 * @return The ID of this material
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Returns the edition of this book
	 * @return The edition of this book
	 */
	public int getEdition() {
		return edition;
	}
	
	/**
	 * Returns the barcode of this material
	 * @return The barcode of this material
	 */
	public int getBarcode() {
		return barcode;
	}
	
	/**
	 * Sets the barcode of this material
	 * @param barcode The new barcode
	 */
	public void setBarcode(int barcode) {
		this.barcode = barcode;
	}
	
	/**
	 * Returns the status of this material
	 * @return The status of this material
	 */
	public MaterialStatus getMaterialStatus() {
		return materialStatus;
	}
	
	/**
	 * Sets the status of this material
	 * @param materialStatus the new status of the material
	 */
	public void setMaterialStatus(MaterialStatus materialStatus) {
		this.materialStatus = materialStatus;
	}
	
	/**
	 * Returns the date the material was taken out
	 * @return The date the material was taken out
	 */
	public Long getTakeoutDate() {
		return takeoutDate;
	}
	
	/**
	 * Sets the takeout date of the material
	 * @param takeoutDate The new takeout date
	 */
	public void setTakeoutDate(Long takeoutDate) {
		this.takeoutDate = takeoutDate;
	}
	
	/**
	 * Returns the date the material was renewed
	 * @return The date the material was renewed
	 */
	public Long getRenewDate() {
		return renewDate;
	}
	
	/**
	 * Sets the renew date of the material
	 * @param renewDate The new renew date
	 */
	public void setRenewDate(Long renewDate) {
		this.renewDate = renewDate;
	}
	
	/**
	 * Returns the type of material
	 * @return The type of material
	 */
	public MaterialType getMaterialType() {
		return materialType;
	}
	
	/**
	 * Returns a boolean stating of the material has been renewed before or not
	 * @return A boolean stating of the material has been renewed before or not
	 */
	public boolean hasBeenRenewed() {
		return this.renewDate > -1L;
	}
	
	/**
	 * Renews the material
	 */
	public void renew() {
		this.setRenewDate(System.currentTimeMillis());
	}
	
	/**
	 * Returns a string representation of the material for the database
	 * @return A string representation of the material for the database
	 */
	@Override
	public String toString() {
		return this.getMaterialStatus().name() + "|"
				+ this.getMaterialType().name() + "|"
				+ this.getBarcode() + "|"
				+ this.getId() + "|"
				+ this.getTitle() + "|"
				+ this.getAuthor() + "|"
				+ this.getEdition() + "|"
				+ this.getTakeoutDate() + "|"
				+ this.getRenewDate();
	}
	/**
	 * Calculates the time left the user has to return this material and converts it to a string
	 */
	public String getTimeLeftToReturn() {
		Long timeTakenOut = System.currentTimeMillis() - this.getTakeoutDate();
		if (this.hasBeenRenewed()) {
			timeTakenOut = System.currentTimeMillis() - this.getRenewDate();
		}
		timeTakenOut = timeTakenOut/1000L;
		System.out.println("timeTakenOut: " + timeTakenOut);
		
		Long week = 60 * 60 * 24 * 7L;
		Long timeLeft = Math.max(0, week - timeTakenOut);
		System.out.println("timeLeft: " + timeLeft);
		
		return TimeUtil.getNiceTime(timeLeft);
	}
	
	/**
	 * Returns a string representation of the material to be displayed to a user
	 * @return A string representation of the material to be displayed to a user
	 */
	public String getNiceName() {
		return this.getTitle() + " - " + this.getAuthor() + " (" + this.getId() + ")";
	}
	
	/**
	 * Clones the material
	 * @return A cloned object of this material
	 */
	@Override
	public Material clone() {
		try {
			return (Material) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
