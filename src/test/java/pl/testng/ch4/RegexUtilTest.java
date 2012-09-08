package pl.testng.ch4;

import static org.testng.Assert.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class RegexUtilTest {

	@Test(dataProvider="getTestData")
	public void shouldReturnValue(String inputString, String[] outputTable){
		RegexUtil util = new RegexUtil();
		
		String[] toCheck = util.getNumbers(inputString);
		
		assertEquals(toCheck, outputTable);
		
	}
	@DataProvider
	private static final Object[][] getTestData() {
		return new Object[][] {
			new Object[] { "abc12", null},
			new Object[] { "cdefg 345 12bb23",new String[]{"345"}},
			new Object[] { "cdefg 345 12bbb23 678tt",new String[]{"345","678"}},
			new Object[] { "345412",new String[]{"345","412"}},
			new Object[] { "3452111",new String[]{"345","211"}},
				
		};
	}
}
