package librarysystem.materials;

import java.io.File;

import javax.swing.ImageIcon;

public enum MaterialType {
	
	/**
	 * Icons authors
	 * 
	 * Book icon: http://freeiconshop.com/icon/book-icon-flat/
	 * CD icon: https://www.shareicon.net/bluray-cd-dvd-disk-86786
	 * DVD icon: https://www.iconfinder.com/icons/905549/disk_dvd_multimedia_storage_icon
	 * eBook icon: https://www.iconfinder.com/icons/1666237/book_ebook_pdf_preview_icon_icon
	 * Magazine icon: <div>Icons made by <a href="https://www.freepik.com/" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" 			    title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" 			    title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>
	 * Textbook Icon: http://www.panindiagoodbooks.com/
	 */
	
	ALL("All", null),
	BOOK("Book", new ImageIcon("resources" + File.separator + "book.png")),
	CD("CD", new ImageIcon("resources" + File.separator + "cd.png")),
	DVD("DVD", new ImageIcon("resources" + File.separator + "dvd.png")),
	EBOOK("eBook", new ImageIcon("resources" + File.separator + "ebook.png")),
	MAGAZINE("Magazine", new ImageIcon("resources" + File.separator + "magazine.png")),
	TEXTBOOK("Textbook", new ImageIcon("resources" + File.separator + "textbook.png"));
	
	private final String niceName;
	private final ImageIcon icon;
	
	MaterialType(String niceName, ImageIcon icon) {
		this.niceName = niceName;
		this.icon = icon;
	}
	
	public String getNiceName() {
		return niceName;
	}
	
	public ImageIcon getIcon() {
		return this.icon;
	}
}
