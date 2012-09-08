package pl.testng.ch4;

import static org.testng.Assert.*;

import org.testng.annotations.DataProvider;

import org.testng.annotations.Test;



public class PasswordUtilTest {

	@Test(dataProvider="getValidTestData")
	public void shouldReturnValid(int minSize,int minNumberOfDigits,String password) {
		
		PasswordChecker passwordChecker = new PasswordChecker(minSize,minNumberOfDigits);
		
		assertTrue(passwordChecker.check(password));
		
	}
	@Test(dataProvider="getInValidTestData")
	public void shouldReturnFalse(int minSize,int minNumberOfDigits,String password) {
		
		PasswordChecker passwordChecker = new PasswordChecker(minSize,minNumberOfDigits);
		
		assertFalse(passwordChecker.check(password));
		
	}
	@Test(expectedExceptions=IllegalArgumentException.class)
	public void invalidInitialization() {
		
		@SuppressWarnings("unused")
		PasswordChecker passwordChecker = new PasswordChecker(5,6);
	}
	@Test(expectedExceptions=IllegalArgumentException.class)
	public void invalidInitialization2() {
		
		@SuppressWarnings("unused")
		PasswordChecker passwordChecker = new PasswordChecker(-1,-2);
	}
	@Test(expectedExceptions=IllegalArgumentException.class)
	public void invalidInitialization3() {
		
		@SuppressWarnings("unused")
		PasswordChecker passwordChecker = new PasswordChecker(4,-2);
	}
	
	@DataProvider
	private static final Object[][] getValidTestData(){
		return new Object[][] {
				new Object[]{Integer.valueOf(5),Integer.valueOf(1),"Tojesthaslo_#1"},
				new Object[]{Integer.valueOf(3),Integer.valueOf(1),"To_#1"},
				new Object[]{Integer.valueOf(3),Integer.valueOf(3),"23To_#1"},
				new Object[]{Integer.valueOf(3),Integer.valueOf(2),"_23#######To#"},
				new Object[]{Integer.valueOf(5),Integer.valueOf(2),"_23__2____To#"}
				};
		}
	@DataProvider
	private static final Object[][] getInValidTestData(){
		return new Object[][] {
				new Object[]{Integer.valueOf(5),Integer.valueOf(1),"T_#1"},
				new Object[]{Integer.valueOf(3),Integer.valueOf(1),"0000"},
				new Object[]{Integer.valueOf(3),Integer.valueOf(3),"23To_#"},
				new Object[]{Integer.valueOf(3),Integer.valueOf(2),"_23########"},
				new Object[]{Integer.valueOf(5),Integer.valueOf(2),"_23__2____"},
				new Object[]{Integer.valueOf(5),Integer.valueOf(2),"_______"},
				new Object[]{Integer.valueOf(5),Integer.valueOf(2),"samohaslo"},
				new Object[]{Integer.valueOf(5),Integer.valueOf(2),"duzehaslo"},
				new Object[]{Integer.valueOf(5),Integer.valueOf(2),null}
				};
		}
}
