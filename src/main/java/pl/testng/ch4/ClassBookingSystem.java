package pl.testng.ch4;

import java.util.ArrayList;
import java.util.List;

public class ClassBookingSystem {

	List<Reservation> reservations = new ArrayList<Reservation>();
	
	public void addReservation(String startTime, String endTime) {
		
		Reservation newReservation = new Reservation(startTime, endTime);
		
		for (Reservation reservation : reservations) {
			if(newReservation.isCrossing(reservation)) {
				throw new IllegalReservation();
			}
		}
		
		reservations.add(new Reservation(startTime, endTime));
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

}
