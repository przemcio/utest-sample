package pl.testng.ch6;

import java.util.Calendar;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.mockito.Mockito.*;

public class HelpDeskTest {

	private HelpDesk helpDesk;
	private Issue defaultIssue = new Issue();

	@BeforeMethod
	public void setUp() {
		helpDesk = new HelpDesk();

	}

	@Test(dataProvider = "getData")
	public void shouldHandleIssue(Integer dayOfWeek, Integer hourOfDay,
			Boolean expectedResult) {

		TimeProvider timeProvider = mock(TimeProvider.class);

		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.DAY_OF_WEEK, dayOfWeek);
		cal.set(Calendar.HOUR_OF_DAY, hourOfDay);

		when(timeProvider.getTime()).thenReturn(cal);
		helpDesk.setTimeProvider(timeProvider);

		Assert.assertEquals(helpDesk.willHandleIssue(defaultIssue),
				expectedResult.booleanValue());
	}

	@DataProvider
	public Object[][] getData() {
		return new Object[][] { new Object[] { Calendar.THURSDAY, 15, true },
				new Object[] { Calendar.THURSDAY, 18, true },
				new Object[] { Calendar.FRIDAY, 15, true },
				new Object[] { Calendar.FRIDAY, 18, false },
				new Object[] { Calendar.SATURDAY, 18, false },
				new Object[] { Calendar.SATURDAY, 6, false },
				new Object[] { Calendar.SUNDAY, 12, false },
				new Object[] { Calendar.SUNDAY, 18, false } };
	}

}
