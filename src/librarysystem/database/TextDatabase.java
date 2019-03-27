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
				String username = userInfo[2], email = userInfo[3], password = userInfo[4];
				boolean blacklisted = Boolean.valueOf(userInfo[5]);
				double overdueFee = Double.valueOf(userInfo[6]);
				List<Material> borrowedMaterials = new ArrayList<>(), onHoldMaterials = new ArrayList<>();
				String[] borrowedInfo = userInfo[7].split(",");
				if (!borrowedInfo[0].equals("NONE")) {
					for (int i = 0; i < borrowedInfo.length; i++) {
						Material material = materialManager.getMaterial(Integer.valueOf(borrowedInfo[i]));
						if (material != null) {
							borrowedMaterials.add(material);
						}
					}
				}
				
				String[] onHoldInfo = userInfo[8].split(",");
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
					String[] reservationInfo = userInfo[9].split("\\,");
					if (!reservationInfo[0].equals("NONE")) {
						for (int i = 0; i < onHoldInfo.length; i++) {
							String[] reservationValues = reservationInfo[i].split("\\+");
							List<Material> materials = new ArrayList<>();
							for (String barcode : reservationValues[0].split(",")) {
								materials.add(materialManager.getMaterial(MaterialStatus.RESERVED, Integer.valueOf(barcode)));
							}
							Reservation reservation = new Reservation(materials, Boolean.valueOf(reservationValues[1]));
							reservations.add(reservation);
						}
					}
					
					user = new Instructor(username, email, password, id, borrowedMaterials, onHoldMaterials, overdueFee, blacklisted, reservations);
				} else if (userType == UserType.STUDENT) {
					user = new Student(username, email, password, id, borrowedMaterials, onHoldMaterials, overdueFee, blacklisted);
				} else if (userType == UserType.ADMINISTRATOR) {
					user = new Administrator(username, email, password, id, borrowedMaterials, onHoldMaterials, overdueFee, blacklisted);
				} else if (userType == UserType.CLERK) {
					user = new Clerk(username, email, password, id, borrowedMaterials, onHoldMaterials, overdueFee, blacklisted);
				} else if (userType == UserType.LIBRARIAN) {
					user = new Librarian(username, email, password, id, borrowedMaterials, onHoldMaterials, overdueFee, blacklisted);
				} else if (userType == UserType.TEACHING_ASSISTANT) {
					user = new TeachingAssistant(username, email, password, id, borrowedMaterials, onHoldMaterials, overdueFee, blacklisted);
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
	
	public static void saveUsers(List<User> users) {
		TextDatabase.checkFileExistence(TextDatabase.getUsersFile());
		try {
			FileWriter fileWriter = new FileWriter(TextDatabase.usersFile);
			for (User user : users) {
				String userInfo = user.getUserType().name() + "|"
						+ user.getId() + "|"
						+ user.getUsername() + "|"
						+ user.getEmail() + "|"
						+ user.getPassword() + "|"
						+ user.isBlacklisted() + "|"
						+ user.getOverdueFee() + "|"
						+ user.getBorrowedMaterialString() + "|"
						+ user.getOnHoldMaterialString();
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
	
	public static File getUsersFile() {
		return TextDatabase.usersFile;
	}
	
	public static File getMaterialsFile() {
		return TextDatabase.materialsFile;
	}
	
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
