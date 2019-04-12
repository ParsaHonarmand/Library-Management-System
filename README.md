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