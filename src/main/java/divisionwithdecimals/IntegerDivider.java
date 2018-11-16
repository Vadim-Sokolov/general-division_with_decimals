package divisionwithdecimals;

import java.util.ArrayList;
import java.util.List;

public class IntegerDivider {
	
	private DivisionInformation divisionInformation;
	private List<DivisionStep> divisionSteps;
		
	public IntegerDivider(DivisionInformation divisionInformation) {
		this.divisionInformation = divisionInformation;
		this.divisionSteps = divisionInformation.getDivisionSteps();
	}

	public void performIntegerDivision() {
		int absoluteDividend = divisionInformation.getAbsoluteDividend();
		int absoluteDivisor = divisionInformation.getAbsoluteDivisor();
		int[] digitsOfDividend = convertIntegerToDigits(absoluteDividend);
		divide(absoluteDivisor, digitsOfDividend);
	}

	private int[] convertIntegerToDigits(int input) {
		String integerToString = Integer.toString(input);
		int[] digits = new int[integerToString.length()];
		for (int i = 0; i < integerToString.length(); i++) {
			digits[i] = Character.getNumericValue(integerToString.charAt(i));
		}
		return digits;
	}

	private void divide(int divisor, int[] digitsOfDividend) {
		int currentDividendNumber = digitsOfDividend[0];
		int index = 0;
		while (index < digitsOfDividend.length) {
			if (currentDividendNumber < divisor) {
				if (index == 0) {
					index++;
				}
				currentDividendNumber = getNextDividend(currentDividendNumber, digitsOfDividend, index, divisor);
				index = divisionInformation.getCurrentIndex();
			} else {
				index++;
			}
			createStep(currentDividendNumber, divisor, divisionInformation.getCurrentIndex());
			currentDividendNumber = currentDividendNumber % divisor;
		}
		divisionInformation.setDivisionSteps(divisionSteps);
	}
	
	private void createStep(int currentDividendNumber, int divisor, int spaceShift) {
		int currentQuotient = currentDividendNumber / divisor;
		int integerToSubtract = divisor * currentQuotient;
		divisionSteps.add(new DivisionStep(currentDividendNumber, integerToSubtract, spaceShift));
	}

	private int getNextDividend(int currentDividendNumber, int[] digits, int index, int divisor) {
		while (currentDividendNumber < divisor) {
			if (index < digits.length) {
				currentDividendNumber = combineTwoIntegers(currentDividendNumber, digits[index++]);
			} else {
				break;
			}
		}
		divisionInformation.setCurrentIndex(index);
		return currentDividendNumber;
	}

	private int combineTwoIntegers(int first, int second) {
		return Integer.parseInt(Integer.toString(first) + Integer.toString(second));
	}
}
