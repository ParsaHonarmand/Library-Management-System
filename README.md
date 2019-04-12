1. To put the project together, Create a new Java Project in eclipse called library-system, then etract these files into the new project you created.
2. For the JUnit tests there will be an error, hover over the error and add JUnit 4 to your build path.
3. Run the main method in LibrarySystem class under the librarysystem package
4. Login to any of the following accounts:
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

5. Some features are only available for certain types of users, for example:
	- Only librarians can see books that Students have returned under the "Returned" tab, and can reshelve them
	- Only instructors can reserve materials (Right click in browse menu and click Reserve)
	- Only librarians can access the order tab to order new books
	- Only librarians can access the received tab to shelve ordered books