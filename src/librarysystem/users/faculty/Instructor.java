package librarysystem.users.faculty;

import librarysystem.materials.Material;
import librarysystem.reservations.Reservation;
import librarysystem.users.UserType;

import java.util.ArrayList;
import java.util.List;

public class Instructor extends Faculty {
	
	private List<Reservation> reservations = new ArrayList<>();
	
	public Instructor(String username, String email, String name, String password, int id) {
		super(username, email, name, password, id, UserType.INSTRUCTOR);
	}
	
	public Instructor(String username, String email, String name, String password, int id, List<Material> borrowed, List<Material> onHold, double overdueFee, boolean blacklisted, List<Reservation> reservations) {
		super(username, email, name, password, id, borrowed, onHold, overdueFee, blacklisted, UserType.INSTRUCTOR);
		this.reservations = reservations;
	}
	
	public List<Reservation> getReservations() {
		return reservations;
	}
	
	public void addReservation(Reservation reservation) {
		this.reservations.add(reservation);
	}
	
	public void removeReservation(Reservation reservation) {
		this.reservations.remove(reservation);
	}
	
	public String getReservationsString() {
		String reservations = "";
		for (Reservation reservation : this.reservations) {
			reservations += reservation.toString() + ",";
		}
		if (reservations.length() > 0) {
			return reservations.substring(0, reservations.length() - 1);
		}
		return "NONE";
	}
	
}
