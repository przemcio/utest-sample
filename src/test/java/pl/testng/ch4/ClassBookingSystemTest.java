package pl.testng.ch4;

import static org.testng.Assert.*;

import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class ClassBookingSystemTest {

	ClassBookingSystem bookingSystem;

	@BeforeMethod
	public void prepareSystem() {
		bookingSystem = new ClassBookingSystem();
	}

	@Test
	public void shouldReturnReservation() {

		bookingSystem.addReservation("10:00", "11:00");

		List<Reservation> reservations = bookingSystem.getReservations();

		assertEquals(reservations.size(), 1);
		assertEquals(reservations.get(0).getStart(), "10:00");
		assertEquals(reservations.get(0).getEnd(), "11:00");
	}
	@Test(dataProvider="getValidReservation")
	public void shouldReturnReservations(String starTime1,String endTime1,String startTime2,String endTime2) {

		bookingSystem.addReservation(starTime1, endTime1);
		bookingSystem.addReservation(startTime2, endTime2);

		List<Reservation> reservations = bookingSystem.getReservations();

		assertEquals(reservations.size(), 2);
		assertEquals(reservations.get(0).getStart(), starTime1);
		assertEquals(reservations.get(0).getEnd(), endTime1);
		assertEquals(reservations.get(1).getStart(), startTime2);
		assertEquals(reservations.get(1).getEnd(), endTime2);
	}
	@DataProvider
	private static final Object[][] getValidReservation() {

		return new Object[][] {
				new Object[] { "11:00", "12:00", "12:00", "14:00" },
				new Object[] { "12:00", "14:00", "11:00", "12:00" },
				new Object[] { "09:00", "12:00", "12:00", "13:00" }};

	}
	

	@Test(expectedExceptions = IllegalReservation.class)
	public void wrongDatePattern() {

		bookingSystem.addReservation("10:30", "11:00");

	}

	@Test(expectedExceptions = IllegalReservation.class, dataProvider = "getDoubleBookingData")
	public void doubleBooking(String starTime1, String endTime1,
			String starTime2, String endTime2) {

		bookingSystem.addReservation(starTime1, endTime1);
		bookingSystem.addReservation(starTime2, endTime2);

	}
	@Test
	public void testInitializationReservation() {
		Reservation reservation = new Reservation("10:00","11:00");
		assertEquals(reservation.getStart(),"10:00");
		assertEquals(reservation.getEnd(),"11:00");
	}
	@Test(expectedExceptions=IllegalReservation.class,dataProvider="getWrongReservationsData")
	public void wrongInitializationReservation(String startTime,String endTime) {
		Reservation reservation = null; 
		reservation = new Reservation(startTime,endTime);
		
		assertNull(reservation);
		
	}

	@DataProvider
	private static final Object[][] getWrongReservationsData() {

		return new Object[][] {
				new Object[] { "11:30", "12:00"},
				new Object[] { "11:00", "12:30"},
				new Object[] { "11:00", "12:05"},
				new Object[] { "11:05", "12:00"},
				new Object[] { "11:00", "aaaa"},
				new Object[] { "vvvvv", "aaaa"},
				new Object[] { "vvvvv", "10:00"},
				new Object[] { null, null},
				new Object[] { "13:00", "11:00"}};
	}
	
	
	@DataProvider
	private static final Object[][] getDoubleBookingData() {

		return new Object[][] {
				new Object[] { "11:00", "12:00", "11:00", "12:00" },
				new Object[] { "11:00", "12:00", "10:00", "13:00" },
				new Object[] { "11:00", "12:00", "11:00", "13:00" },
				new Object[] { "11:00", "12:00", "10:00", "12:00" } };

	}

}
