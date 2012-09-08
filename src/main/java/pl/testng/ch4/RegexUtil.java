package pl.testng.ch4;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

	private static final Pattern PATTERN = Pattern.compile("[\\d]{3}");

	public String[] getNumbers(String inputString) {

		List<String> found = new ArrayList<String>();

		Matcher matcher = PATTERN.matcher(inputString);

		while (matcher.find()) {
			found.add(matcher.group());
		}
		if (found.size() == 0) {
			return null;
		} else {
			return found.toArray(new String[found.size()]);
		}
	}

}
