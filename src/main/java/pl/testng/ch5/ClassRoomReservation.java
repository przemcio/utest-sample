package pl.testng.ch5;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import pl.testng.ch4.IllegalReservation;

public class ClassRoomReservation {


	private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(
			"HH:mm");

	private Date startTime;
	private Date endTime;
	private String classRoomName;
	private Day dayOfWeek;

	@SuppressWarnings("deprecation")
	public ClassRoomReservation(String classRoomName, Day dayOfWeek,
			String startTime, String endTime) {

		if (startTime == null || endTime == null) {
			throw new IllegalReservation();
		}

		try {
			this.startTime = DATE_FORMATTER.parse(startTime);
			this.endTime = DATE_FORMATTER.parse(endTime);
		} catch (ParseException e) {

			throw new IllegalReservation();
		}
		if (this.startTime.getMinutes() != 0 || this.endTime.getMinutes() != 0
				|| this.startTime.after(this.endTime)) {
			throw new IllegalReservation();
		}

		this.dayOfWeek = dayOfWeek;
		this.classRoomName = classRoomName;
	}

	public String getStart() {

		return DATE_FORMATTER.format(startTime);
	}

	public String getEnd() {

		return DATE_FORMATTER.format(endTime);
	}

	public boolean isReservationCrossing(
			ClassRoomReservation classRoomReservation) {
		if (StringUtils.equals(getClassRoomName(),
				classRoomReservation.getClassRoomName())
				&& isTimeCrossing(classRoomReservation)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isTimeCrossing(ClassRoomReservation reservation) {

		if (reservation!=null && (reservation.dayOfWeek == dayOfWeek || dayOfWeek == null || reservation.dayOfWeek == null)
				&& (((reservation.startTime.after(startTime) || reservation.startTime
						.equals(startTime)) && (reservation.endTime
						.before(endTime) || reservation.endTime.equals(endTime)))
						|| (reservation.startTime.before(startTime) || reservation.startTime
								.equals(startTime))
						&& reservation.endTime.after(startTime) || (reservation.endTime
						.after(endTime) || reservation.endTime.equals(endTime))
						&& reservation.startTime.before(endTime))) {
			return true;
		}
		return false;
	}

	public String getClassRoomName() {
		return classRoomName;
	}

}
