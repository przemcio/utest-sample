package pl.testng.ch3;


import static org.testng.Assert.*;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;


/**
 * Unit test for simple App.
 */


public class StringUtilTest {

	//@Test(dataProvider="getTestData")
    public void shouldReverseString(String in, String expected)
    {
        String reversed = StringUtil.reverse(in);
        
        assertEquals(reversed, expected);
        
    }
	@Test(dataProvider="getTestData")
    public void shouldReverseString2(String in, String expected)
    {
        String reversed = StringUtil2.reverse(in);
        
        assertEquals(reversed, expected);
        
    }
	@Test(dataProvider="getTestData")
    public void shouldReverseString3(String in, String expected)
    {
        String reversed = StringUtil3.reverse(in);
        
        assertEquals(reversed, expected);
        
    }
    @DataProvider
    private static final String[][] getTestData() {
    	return new String[][] {
    			new String []{"abba","abba"},
    			new String []{"12345","54321"},
    			new String []{"",""},
    			new String []{"@#$%","%$#@"},
    			new String []{null,null}
    	};
    }
}
