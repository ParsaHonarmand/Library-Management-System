package librarysystem.database;

import librarysystem.managers.MaterialManager;
import librarysystem.materials.*;
import librarysystem.reservations.Reservation;
import librarysystem.users.Student;
import librarysystem.users.User;
import librarysystem.users.UserType;
import librarysystem.users.faculty.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TextDatabase {

	private static File usersFile = new File("users.txt"), materialsFile = new File("materials.txt");
	
	/**
	 * Returns a list of all users from the users.txt file
	 * @param materialManager The material manager to get material information from
	 * @return A list of all users from the users.txt file
	 */
	public static List<User> loadUsers(MaterialManager materialManager) {
		List<User> users = new ArrayList<User>();
		TextDatabase.checkFileExistence(TextDatabase.getUsersFile());
		System.out.println("loading users");
		try {
			FileReader fileReader = new FileReader(TextDatabase.usersFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null && !line.equals("")) {
				System.out.println("line: " + line);
				String[] userInfo = line.split("\\|");
				UserType userType = UserType.valueOf(userInfo[0]);
				int id = Integer.valueOf(userInfo[1]);
				String username = userInfo[2], email = userInfo[3], name = userInfo[4], password = userInfo[5];
				boolean blacklisted = Boolean.valueOf(userInfo[6]);
				double overdueFee = Double.valueOf(userInfo[7]);
				List<Material> borrowedMaterials = new ArrayList<>(), onHoldMaterials = new ArrayList<>();
				String[] borrowedInfo = userInfo[8].split(",");
				if (!borrowedInfo[0].equals("NONE")) {
					for (int i = 0; i < borrowedInfo.length; i++) {
						Material material = materialManager.getMaterial(Integer.valueOf(borrowedInfo[i]));
						if (material != null) {
							borrowedMaterials.add(material);
						}
					}
				}

				String[] onHoldInfo = userInfo[9].split(",");
				if (!onHoldInfo[0].equals("NONE")) {
					for (int i = 0; i < onHoldInfo.length; i++) {
						Material material = materialManager.getMaterial(Integer.valueOf(onHoldInfo[i]));
						if (material != null) {
							onHoldMaterials.add(material);
						}
					}
				}

				User user = null;
				if (userType == UserType.INSTRUCTOR) {
					List<Reservation> reservations = new ArrayList<>();
					String[] reservationInfo = userInfo[10].split("\\,");
					System.out.println("reservation info: " + userInfo[10]);
					if (!reservationInfo[0].equals("NONE")) {
						for (int i = 0; i < onHoldInfo.length; i++) {
							String[] reservationValues = reservationInfo[i].split("\\+");
							List<Material> materials = new ArrayList<>();
							for (String barcode : reservationValues[0].split(",")) {
								materials.add(materialManager.getMaterial(Integer.valueOf(barcode)));
							}
							Reservation reservation = new Reservation(materials, Boolean.valueOf(reservationValues[1]));
							reservations.add(reservation);
						}
					}

					user = new Instructor(username, email, name, password, id, borrowedMaterials, onHoldMaterials, overdueFee, blacklisted, reservations);
				} else if (userType == UserType.STUDENT) {
					user = new Student(username, email, name, password, id, borrowedMaterials, onHoldMaterials, overdueFee, blacklisted);
				} else if (userType == UserType.ADMINISTRATOR) {
					user = new Administrator(username, email, name, password, id, borrowedMaterials, onHoldMaterials, overdueFee, blacklisted);
				} else if (userType == UserType.CLERK) {
					user = new Clerk(username, email, name, password, id, borrowedMaterials, onHoldMaterials, overdueFee, blacklisted);
				} else if (userType == UserType.LIBRARIAN) {
					user = new Librarian(username, email, name, password, id, borrowedMaterials, onHoldMaterials, overdueFee, blacklisted);
				} else if (userType == UserType.TEACHING_ASSISTANT) {
					user = new TeachingAssistant(username, email, name, password, id, borrowedMaterials, onHoldMaterials, overdueFee, blacklisted);
				}

				if (user != null) {
					users.add(user);
				}
			}
			bufferedReader.close();
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	/**
	 * Saves all the users to the users.txt file
	 * @param users The list of users to be saved
	 */
	public static void saveUsers(List<User> users) {
		TextDatabase.checkFileExistence(TextDatabase.getUsersFile());
		try {
			FileWriter fileWriter = new FileWriter(TextDatabase.usersFile);
			for (User user : users) {
				String userInfo = user.toString();
				UserType userType = user.getUserType();
				if (userType == UserType.INSTRUCTOR) {
					Instructor instructor = (Instructor) user;
					userInfo += "|" + instructor.getReservationsString();
				}
				userInfo += "\n";
				fileWriter.write(userInfo);
			}
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds a user to the users.txt file
	 * @param user The user to be saved
	 */
	public static void addUser(User user) {
		TextDatabase.checkFileExistence(TextDatabase.getUsersFile());
		try {
			FileWriter fileWriter = new FileWriter(TextDatabase.usersFile, true);
			String userInfo = user.toString();
			UserType userType = user.getUserType();
			if (userType == UserType.INSTRUCTOR) {
				Instructor instructor = (Instructor) user;
				userInfo += "|" + instructor.getReservationsString();
			}
			userInfo += "\n";
			fileWriter.write(userInfo);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Loads all the materials from the materials.txt file
	 * @return The list of all materials loaded from the materials.txt file
	 */
	public static HashMap<MaterialStatus, List<Material>> loadMaterials() {
		HashMap<MaterialStatus, List<Material>> materialMap = new HashMap<>();
		for (MaterialStatus materialStatus : MaterialStatus.values()) {
			materialMap.put(materialStatus, new ArrayList<>());
		}

		TextDatabase.checkFileExistence(TextDatabase.getMaterialsFile());

		try {
			FileReader fileReader = new FileReader(TextDatabase.getMaterialsFile());
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null && !line.equals("")) {
				System.out.println("line: " + line);
				String[] materialInfo = line.split("\\|");
				MaterialStatus materialStatus = MaterialStatus.valueOf(materialInfo[0]);
				MaterialType materialType = MaterialType.valueOf(materialInfo[1]);
				int barcode = Integer.valueOf(materialInfo[2]), edition = Integer.valueOf(materialInfo[6]);
				String id = materialInfo[3], title = materialInfo[4], author = materialInfo[5];
				Long takeoutDate = Long.valueOf(materialInfo[7]);
				Long renewDate = (long) -1; //Long.valueOf(materialInfo[8]);

				Material material = null;
				if (materialType == MaterialType.BOOK)
					material = new Book(title, author, id, edition, barcode, materialStatus, takeoutDate, renewDate);
				else if (materialType == MaterialType.CD)
					material = new CD(title, author, id, edition, barcode, materialStatus, takeoutDate, renewDate);
				else if (materialType == MaterialType.DVD)
					material = new DVD(title, author, id, edition, barcode, materialStatus, takeoutDate, renewDate);
				else if (materialType == MaterialType.EBOOK)
					material = new eBook(title, author, id, edition, barcode, materialStatus, takeoutDate, renewDate);
				else if (materialType == MaterialType.MAGAZINE)
					material = new Magazine(title, author, id, edition, barcode, materialStatus, takeoutDate, renewDate);

				if (material != null) {
					materialMap.get(materialStatus).add(material);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return materialMap;
	}
	
	/**
	 * Save all materials to the materials.txt file
	 * @param materialMap The materials to be saved to the materials.txt file
	 */
	public static void saveMaterials(HashMap<MaterialStatus, List<Material>> materialMap) {
		TextDatabase.checkFileExistence(TextDatabase.getMaterialsFile());

		try {
			FileWriter fileWriter = new FileWriter(TextDatabase.getMaterialsFile());

			for (MaterialStatus materialStatus : materialMap.keySet()) {
				List<Material> materials = materialMap.get(materialStatus);
				for (Material material : materials) {
					fileWriter.write(material.toString() + "\n");
				}
			}
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds a material to the materials.txt file
	 * @param material The material to be added
	 */
	public static void addMaterial(Material material) {
		TextDatabase.checkFileExistence(TextDatabase.getMaterialsFile());
		System.out.println("adding material:" + material.toString());
		try {
			FileWriter fileWriter = new FileWriter(TextDatabase.getMaterialsFile(), true);
			fileWriter.write(material.toString() + "\n");
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void updateMaterial(Material material) {
		int barcode = material.getBarcode();

		TextDatabase.checkFileExistence(TextDatabase.getMaterialsFile());
		try {
			FileReader fileReader = new FileReader(TextDatabase.getMaterialsFile());
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line, fileText = "";
			while ((line = bufferedReader.readLine()) != null && !line.equals("")) {
				String[] materialInfo = line.split("\\|");
				if (Integer.valueOf(materialInfo[2]) == barcode) {
					fileText += material.toString() + "\n";
				} else {
					fileText += line + "\n";
				}
			}
			bufferedReader.close();
			fileReader.close();

			FileWriter fileWriter = new FileWriter(TextDatabase.getMaterialsFile());
			fileWriter.write(fileText);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Saves the user information to the users.txt file - replacing old info if already existing
	 * @param user The user to be updated
	 */
	public static void updateUser(User user) {
		String username = user.getUsername();
		TextDatabase.checkFileExistence(TextDatabase.getUsersFile());
		try {
			FileReader fileReader = new FileReader(TextDatabase.getUsersFile());
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line, fileText = "";
			while ((line = bufferedReader.readLine()) != null && !line.equals("")) {
				String[] userInfo = line.split("\\|");
				if (userInfo[2].equals(username)) {
					System.out.println("found old user data");
					fileText += user.toString() + "\n";
				} else {
					fileText += line + "\n";
				}

			}
			bufferedReader.close();
			fileReader.close();
			FileWriter fileWriter = new FileWriter(TextDatabase.getUsersFile());
			fileWriter.write(fileText);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the user file
	 * @return The user file
	 */
	public static File getUsersFile() {
		return TextDatabase.usersFile;
	}
	
	/**
	 * Returns the materials file
	 * @return The materials file
	 */
	public static File getMaterialsFile() {
		return TextDatabase.materialsFile;
	}
	
	/**
	 * Checks if a certain file object exists, and if not, it creates it
	 * @param file The file to check for existence
	 */
	private static void checkFileExistence(File file) {
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
