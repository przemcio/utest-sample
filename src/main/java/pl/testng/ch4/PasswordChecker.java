package pl.testng.ch4;

public class PasswordChecker {

	private int minSize;
	private int minNumberOfDigits;

	private static final String HASH = "#";
	private static final String UNDERSCORE = "#";

	public PasswordChecker(int minSize, int minNumberOfDigits) {
		this.minSize = minSize;
		this.minNumberOfDigits = minNumberOfDigits;

		if (minNumberOfDigits > minSize || minNumberOfDigits < 0
				|| minSize <= 0) {
			throw new IllegalArgumentException();
		}

	}

	public boolean check(String password) {
		if (password == null || password.length() < minSize) {
			return false;
		}
		if (!password.contains(HASH)) {
			return false;
		}
		if (!password.contains(UNDERSCORE)) {
			return false;
		}
		int numberCounter = 0, upperCaseCounter = 0, lowerCaseCounter = 0;

		for (int i = 0; i < password.length(); i++) {
			if (Character.isLowerCase(password.charAt(i))) {
				lowerCaseCounter++;
			}
			if (Character.isUpperCase(password.charAt(i))) {
				upperCaseCounter++;
			}
			if (Character.isDigit(password.charAt(i))) {
				numberCounter++;
			}
		}

		if (numberCounter < minNumberOfDigits || lowerCaseCounter == 0
				|| upperCaseCounter == 0) {
			return false;
		}

		return true;
	}

}
