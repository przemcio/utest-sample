package pl.testng.ch5;

import java.util.HashSet;
import java.util.Set;

public class BookingSystem2 {

	private ReservationLogger logger;

	private Set<ClassRoom> classRooms = new HashSet<ClassRoom>();
	private Set<ClassRoomReservation> reservations = new HashSet<ClassRoomReservation>();

	public BookingSystem2(ReservationLogger logger) {
		this.logger = logger;
	}

	public void addClassRoom(ClassRoom classRoom) {
		this.classRooms.add(classRoom);

	}

	public Set<ClassRoom> getAllClassRooms() {
		return classRooms;
	}

	public ClassRoomReservation book(String name, Day dayOfWeek,
			String startTime, String endTime) {

		ClassRoom classRoom = getClassRooms(name);
		if (classRoom == null) {
			throw new MissingClassRoomException();
		}

		ClassRoomReservation newReservation = new ClassRoomReservation(name,
				dayOfWeek, startTime, endTime);

		if (isInvalidReservations(newReservation)
				|| newReservation.isTimeCrossing(classRoom.getCleaningHours())) {
			throw new AlreadyReservedException();
		} else {
			addNewReservation(newReservation);
			return newReservation;
		}
	}

	private void addNewReservation(ClassRoomReservation newReservation) {
		reservations.add(newReservation);
		logger.log(newReservation.getClassRoomName(),
				newReservation.getStart(), newReservation.getEnd());

	}

	public ClassRoomReservation book(Day dayOfWeek, String startTime,
			String endTime, int atLeastMaxPeople, Equipment... equipments) {

		if (classRooms.size() == 0) {
			throw new MissingClassRoomException();
		}

		for (ClassRoom classRoom : classRooms) {

			if (!classRoom.isFullfillRequirements(atLeastMaxPeople,
					equipments)) {
				continue;
			}

			ClassRoomReservation toCheck = new ClassRoomReservation(
					classRoom.getName(), dayOfWeek, startTime, endTime);

			if (!isInvalidReservations(toCheck)
					&& !toCheck.isTimeCrossing(classRoom.getCleaningHours())) {
				addNewReservation(toCheck);
				return toCheck;
			}
		}

		throw new MissingClassRoomException();
	}

	private boolean isInvalidReservations(
			ClassRoomReservation reservationToCheck) {

		if (reservationToCheck != null) {

			for (ClassRoomReservation classRoomReservation : reservations) {

				if (classRoomReservation
						.isReservationCrossing(reservationToCheck)) {

					return true;
				}

			}
		}

		return false;
	}

	private ClassRoom getClassRooms(String name) {
		for (ClassRoom classRoom : classRooms) {
			if (classRoom.getName().equals(name)) {
				return classRoom;
			}
		}
		return null;
	}

	public Set<ClassRoom> getAvailableClassRooms(Day dayOfWeek,
			String startTime, String endTime) {
		Set<ClassRoom> result = new HashSet<ClassRoom>();

		for (ClassRoom classRoom : classRooms) {
			ClassRoomReservation toCheck = new ClassRoomReservation(
					classRoom.getName(), dayOfWeek, startTime, endTime);

			if (!isInvalidReservations(toCheck)) {
				result.add(classRoom);
			}
		}

		return result;
	}

	public Set<ClassRoomReservation> getAllReservations() {
		return reservations;
	}

}
