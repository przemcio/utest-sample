package pl.testng.ch4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation {

	
	private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("HH:mm");
	
	private Date startTime;
	private Date endTime;

	@SuppressWarnings("deprecation")
	public Reservation(String startTime, String endTime) {
		
		if(startTime == null || endTime == null) {
			throw new IllegalReservation();
		}
		
		try {
			this.startTime = DATE_FORMATTER.parse(startTime);
			this.endTime = DATE_FORMATTER.parse(endTime);
		} catch (ParseException e) {

			throw new IllegalReservation();
		}
		if(this.startTime.getMinutes() != 0 || this.endTime.getMinutes() != 0 || this.startTime.after(this.endTime)) {
			throw new IllegalReservation();
		}
	}

	public String getStart() {

		return DATE_FORMATTER.format(startTime);
	}

	public String getEnd() {

		return DATE_FORMATTER.format(endTime);
	}
	public boolean isCrossing(Reservation reservation) {

		
		if(((reservation.startTime.after(startTime) ||reservation.startTime.equals(startTime) )
				&& (reservation.endTime.before(endTime)||reservation.endTime.equals(endTime) ))
				|| (reservation.startTime.before(startTime) ||reservation.startTime.equals(startTime) )&& reservation.endTime.after(startTime) 
				|| (reservation.endTime.after(endTime) || reservation.endTime.equals(endTime) )&& reservation.startTime.before(endTime)) {
			return true;
		}
		return false;
	}
	
}
