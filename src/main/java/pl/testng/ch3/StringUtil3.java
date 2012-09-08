package pl.testng.ch3;


public class StringUtil3 {
	public static String reverse(String s) {
		if(s == null) {
			return null;
		}
		
		StringBuilder sb = new StringBuilder(s.length());
		sb.append(s);

		sb.reverse();
		
		return sb.toString();
	}
}
