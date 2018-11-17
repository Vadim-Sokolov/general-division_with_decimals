package divisionwithdecimals;

import java.util.List;

public class IntegerDivider {
	
	private DivisionData divisionData;
	private List<DivisionStep> divisionSteps;
		
	public IntegerDivider(DivisionData divisionData) {
		this.divisionData = divisionData;
		this.divisionSteps = divisionData.getDivisionSteps();
	}

	public void performIntegerDivision() {
		int absoluteDividend = divisionData.getAbsoluteDividend();
		int absoluteDivisor = divisionData.getAbsoluteDivisor();
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
				index = divisionData.getCurrentIndex();
			} else {
				index++;
			}
			createStep(currentDividendNumber, divisor, divisionData.getCurrentIndex());
			currentDividendNumber = currentDividendNumber % divisor;
		}
		divisionData.setDivisionSteps(divisionSteps);
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
		divisionData.setCurrentIndex(index);
		return currentDividendNumber;
	}

	private int combineTwoIntegers(int first, int second) {
		return Integer.parseInt(Integer.toString(first) + Integer.toString(second));
	}
}
