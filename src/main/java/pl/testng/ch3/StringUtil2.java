package pl.testng.ch3;

import java.util.ArrayList;
import java.util.List;

public class StringUtil2 {
	public static String reverse(String s) {
		if(s == null) {
			return null;
		}
		List<String> tempArray = new ArrayList<String>(s.length());
		for(int i=0 ;i<s.length(); i++) {
			tempArray.add(s.substring(i, i+1));
		}
		StringBuilder reversedString = new StringBuilder(s.length());
		
		for(int i = tempArray.size(); i > 0 ; i--) {
			reversedString.append(tempArray.get(i-1));
		}
		
		return reversedString.toString();
	}
}
