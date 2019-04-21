1. To put the project together, Create a new Java Project in eclipse called library-system
2. NOTE: You must use Java 8+ SO pick JavaSE-1.8 for execution environment JRE
	- If you absolutely must use Java 7, email me back I can create a Java 7 version for you quickly enough
3. Navigate to the project folder on your computer
4. Extract the javadocs, resources and src folders and the materials.txt + users.txt files
5. IMPORTANT: Do not extract the .project and .classpath files, keep your own files
6. Refresh your project in eclipse (Select and press F5)
7. For the JUnit tests there will be an error, hover over the error and add JUnit 4 to your build path.
	- Error will be on line 22, if you hover over that "@Test" eclipse should suggest to add JUnit 4 to your path for you
8. Run the main method in LibrarySystem class under the librarysystem package
9. Login to any of the following accounts:
	- Student:
		username: johnsmith1
		password: password
	- Librarian:
		username: johnsmith2
		password: password
	- Instructor:
		username: sohaib
		password: password
	- Student (With blacklist already set):
		username: jen
		password: j

10. Some features are only available for certain types of users, for example:
	- Only librarians can see books that Students have returned under the "Returned" tab, and can reshelve them
	- Only librarians can access the order tab to order new books
	- Only librarians can access the received tab to shelve ordered books
	- Only instructors can reserve materials (Right click in browse menu and click Reserve)
	
Git repository: https://gitlab.com/seng-300/library-system

Image references:
banner_img:
pic1.png: Photoshop by Zobia including image https://www.ubishops.ca/wp-content/uploads/job-search-workshops.png
picHours.png (Made by Zobia)
profile.png: https://www.flaticon.com/free-icon/man-user_74472#term=avatar&page=1&position=3
mru.png: https://www.mtroyal.ca/AboutMountRoyal/MarketingCommunications/OurLogo/index.htm
libwall.jpg: http://www.myconfinedspace.com/2017/08/23/swimming-pool-library/swimming-pool-library-jpg/
library.jpg: https://unsplash.com/photos/McX3XuJRsUM
book.png: http://freeiconshop.com/icon/book-icon-flat/
cd.png: https://www.shareicon.net/bluray-cd-dvd-disk-86786
dvd.png: https://www.iconfinder.com/icons/905549/disk_dvd_multimedia_storage_icon
ebook.png: https://www.iconfinder.com/icons/1666237/book_ebook_pdf_preview_icon_icon
magazine.png: https://www.flaticon.com/free-icon/magazine_1035857
textbook.png: http://www.panindiagoodbooks.com/images/slider/img-1.png