package pl.testng.jodatime;

import java.util.HashSet;
import java.util.Set;

import net.objectlab.kit.datecalc.common.DateCalculator;
import net.objectlab.kit.datecalc.common.DefaultHolidayCalendar;
import net.objectlab.kit.datecalc.common.HolidayCalendar;
import net.objectlab.kit.datecalc.common.HolidayHandlerType;
import net.objectlab.kit.datecalc.common.IMMDateCalculator;
import net.objectlab.kit.datecalc.common.Tenor;
import net.objectlab.kit.datecalc.common.TenorCode;
import net.objectlab.kit.datecalc.joda.LocalDateKitCalculatorsFactory;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.Months;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.testng.annotations.Test;


public class TimeTest {
	
	private DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyyMMdd");
	
	@Test
	public void testDateTime() {
		DateTime dt = new DateTime();

		
		System.out.println("Current yoda time yyyyMMdd is "+ dtf.print(dt));
		
		//DateTime dt2 = new DateTime(2012, 1, 31, 11, 00);
		
		
	}
	@Test
	public void testPeriodTime() {
		
		DateTime dt = new DateTime(2012, 1, 31, 11, 00);

		System.out.println("Set  yoda time yyyyMMdd is "+ dtf.print(dt));

		DateTime dt2 = dt.plus(Months.ONE);
		
		System.out.println("Set  yoda time + period yyyyMMdd is "+ dtf.print(dt2));
	}
	@Test
	public void testHolidayWithBD() {
		
		Set<LocalDate> holidays = new HashSet<>();
		
		holidays.add(new LocalDate(2012, 8, 15));
		
		HolidayCalendar<LocalDate> polishHolidayCalendar2012 = new DefaultHolidayCalendar<LocalDate>(holidays, new LocalDate(2012, 1, 1), new LocalDate(2012, 12, 30));
		
		LocalDateKitCalculatorsFactory.getDefaultInstance().registerHolidays("PL", polishHolidayCalendar2012);
		
		DateCalculator<LocalDate> dateCalculator = LocalDateKitCalculatorsFactory.getDefaultInstance().getDateCalculator("PL", HolidayHandlerType.FORWARD);
		
		dateCalculator.setStartDate(new LocalDate("2012-08-14")); 
		
		LocalDate endDate =  dateCalculator.moveByBusinessDays(3).getCurrentBusinessDate();
		
		System.out.println("End Date "+ endDate.toString());
	}
	@Test
	public void testHoliday() {
		
		Set<LocalDate> holidays = new HashSet<>();
		
		holidays.add(new LocalDate(2012, 8, 15));
		
		HolidayCalendar<LocalDate> polishHolidayCalendar2012 = new DefaultHolidayCalendar<LocalDate>(holidays, new LocalDate(2012, 1, 1), new LocalDate(2012, 12, 30));
		
		LocalDateKitCalculatorsFactory.getDefaultInstance().registerHolidays("PL", polishHolidayCalendar2012);
		
		DateCalculator<LocalDate> dateCalculator = LocalDateKitCalculatorsFactory.getDefaultInstance().getDateCalculator("PL", HolidayHandlerType.FORWARD);
		
		dateCalculator.setStartDate(new LocalDate("2012-08-14")); 
		
		DateCalculator<LocalDate> tenorDateCalculator =  dateCalculator.moveByTenor(new Tenor(1, TenorCode.DAY));
		
		System.out.println("End Date TenorDate "+ tenorDateCalculator.getCurrentBusinessDate().toString());
	}
	@Test
	public void testIMM() {
		
		IMMDateCalculator<LocalDate> immCalculator = LocalDateKitCalculatorsFactory.getDefaultInstance().getIMMDateCalculator();
		
		
		LocalDate localDate = immCalculator.getNextIMMDate(new LocalDate());
		System.out.println("Imm test: "+ localDate.toString());
		
		
	}
}
