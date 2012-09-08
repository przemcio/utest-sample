package pl.testng.ch5;

import static org.testng.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Set;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class BookingSystem2Test {

	private BookingSystem2 bookingSystem;
	private ReservationLogger logger;
	@BeforeMethod
	public void setUp() {
		logger = mock(ReservationLogger.class);
		bookingSystem = new BookingSystem2(logger);
		
	}

	public void shouldReturnAllExistingClassRooms() {

		ClassRoom classRoom = new ClassRoom("");

		bookingSystem.addClassRoom(classRoom);

		Set<ClassRoom> classRooms = bookingSystem.getAllClassRooms();

		assertEquals(classRooms.size(), 1);
		assertEquals(classRoom, classRooms.iterator().next());
	}

	public void shouldNotAllowToAddTwoTheSameClassRooms() {

		ClassRoom classRoom = new ClassRoom("");

		bookingSystem.addClassRoom(classRoom);
		bookingSystem.addClassRoom(classRoom);

		Set<ClassRoom> classRooms = bookingSystem.getAllClassRooms();

		assertEquals(classRooms.size(), 1);

	}

	@Test(dataProvider = "getAvailableClassRoomsData")
	public void shouldAllowToBookClassRoom(String startTime, String endTime) {
		ClassRoom classRoomA1 = new ClassRoom("A1");
		bookingSystem.addClassRoom(classRoomA1);
		bookingSystem.book("A1", Day.WEDNESDAY, "10:00", "13:00");
		bookingSystem.book("A1", Day.MONDAY, startTime, endTime);

	}

	public void shooulReturnsAllReservations() {

		ClassRoom classRoomA = new ClassRoom("classRoomA");			
		
		bookingSystem.addClassRoom(classRoomA);

		bookingSystem.book("classRoomA", Day.MONDAY, "10:00", "11:00");

		Set<ClassRoomReservation> reservations = bookingSystem
				.getAllReservations();

		assertEquals(reservations.size(), 1);

		assertEquals(reservations.iterator().next().getClassRoomName(),
				"classRoomA");
	}

	@Test(expectedExceptions = MissingClassRoomException.class)
	public void shouldThrowExceptionCasueMissingClassRooms() {

		bookingSystem.book("classRoomA", Day.MONDAY, "10:00", "11:00");

	}

	public void shouldReturnAvailableClassRooms() {

		ClassRoom classRoom = new ClassRoom("");
		bookingSystem.addClassRoom(classRoom);
		Set<ClassRoom> classRooms = bookingSystem.getAvailableClassRooms(
				Day.MONDAY, "10:00", "11:00");

		assertEquals(classRooms.size(), 1);
		assertEquals(classRoom, classRooms.iterator().next());
	}

	@Test(dataProvider = "getAvailableClassRoomsData")
	public void shouldNOTReturnAvailableClassRooms(String startTime,
			String endTime) {

		ClassRoom classRoomA1 = new ClassRoom("A1");
		bookingSystem.addClassRoom(classRoomA1);
		bookingSystem.book("A1", Day.MONDAY, "10:00", "13:00");
		Set<ClassRoom> classRooms = bookingSystem.getAvailableClassRooms(
				Day.MONDAY, startTime, endTime);

		assertEquals(classRooms.size(), 0);

	}

	@DataProvider
	public static Object[][] getAvailableClassRoomsData() {
		return new Object[][] { new Object[] { "11:00", "12:00" },
				new Object[] { "09:00", "12:00" },
				new Object[] { "11:00", "14:00" } };

	}

	@Test(expectedExceptions = AlreadyReservedException.class, dataProvider = "getAvailableClassRoomsData")
	public void shouldNotAllowedToAddWrongReservation(String startDate,
			String endDate) throws AlreadyReservedException {

		ClassRoom classRoomA1 = new ClassRoom("A1");
		bookingSystem.addClassRoom(classRoomA1);
		bookingSystem.book("A1", Day.MONDAY, "10:00", "13:00");
		bookingSystem.book("A1", Day.MONDAY, startDate, endDate);

	}
	public void shoudlReservedClassRoomWithEquipment() {
		
		ClassRoom classRoomA1 = new ClassRoom("A1",20);

		
		ClassRoom classRoomB1 = new ClassRoom("B1",30);
		classRoomB1.addEquipment(Equipment.PROJECTOR);
		
		bookingSystem.addClassRoom(classRoomA1);
		bookingSystem.addClassRoom(classRoomB1);
		
		ClassRoomReservation reservation =  bookingSystem.book(Day.MONDAY, "10:00", "11:00", 25, Equipment.PROJECTOR);
		assertNotNull(reservation);
		assertEquals(reservation.getClassRoomName(), "B1");
	}
	@Test(expectedExceptions=MissingClassRoomException.class)
	public void shouldNotMadeAreservationDueToCleaningHours() {
		ClassRoom classRoomA1 = new ClassRoom("A1",10);
		classRoomA1.setCleaningHour("11:00","12:00");
		bookingSystem.addClassRoom(classRoomA1);
		bookingSystem.book(Day.MONDAY, "10:00", "11:00", 25, Equipment.PROJECTOR);
		
	}
	@Test(expectedExceptions=AlreadyReservedException.class)
	public void shouldNotMadeAreservationDueToCleaningHours2() {
		ClassRoom classRoomA1 = new ClassRoom("A1",20);
		classRoomA1.setCleaningHour("11:00","12:00");
		bookingSystem.addClassRoom(classRoomA1);
		
		bookingSystem.book("A1",Day.MONDAY, "10:00", "12:00");
		
	}
	public void newReservationShouldBeLogged() {
		ClassRoom classRoomA1 = new ClassRoom("A1");
		bookingSystem.addClassRoom(classRoomA1);
		bookingSystem.book("A1", Day.MONDAY, "10:00", "13:00");
		
		verify(logger).log("A1","10:00","13:00");
		
	}
	
}
