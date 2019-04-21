package librarysystem.materials;

import javax.swing.*;
import java.io.File;

public enum MaterialType {
	
	/**
	 * Icons authors
	 * 
	 * Book icon: http://freeiconshop.com/icon/book-icon-flat/
	 * CD icon: https://www.shareicon.net/bluray-cd-dvd-disk-86786
	 * DVD icon: https://www.iconfinder.com/icons/905549/disk_dvd_multimedia_storage_icon
	 * eBook icon: https://www.iconfinder.com/icons/1666237/book_ebook_pdf_preview_icon_icon
	 * Magazine icon: https://www.flaticon.com/free-icon/magazine_1035857
	 * Textbook Icon: http://www.panindiagoodbooks.com/images/slider/img-1.png
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
