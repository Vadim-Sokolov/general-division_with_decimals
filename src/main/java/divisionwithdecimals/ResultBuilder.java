package divisionwithdecimals;

import java.util.Arrays;
import java.util.List;

public class ResultBuilder {

	private StringBuilder result = new StringBuilder();
	private static final int DIVIDEND_SHIFT_SPACES = 2;
	private static final int SUBTRACTION_SHIFT_SPACES = 1;
	private DivisionData divisionData;
	private List<DivisionStep> divisionSteps;

	public ResultBuilder(DivisionData divisionData, List<DivisionStep> divisionSteps) {
		this.divisionData = divisionData;
		this.divisionSteps = divisionSteps;
	}

	public String convertDivisionResultToString() {
		int dividendLength = getIntegerLength(divisionData.getAbsoluteDividend());
		appendFirstLine(divisionData, dividendLength);
		appendSecondLine(divisionData, dividendLength);
		appendThirdLine(divisionData);
		appendDivisionSteps(divisionSteps);
		appendLastLine(divisionData, dividendLength);
		return result.toString();
	}

	private void appendFirstLine(DivisionData divisionData, int dividendLength) {
		if (divisionData.getDividend() >= 0) {
			result.append("_");
		}
		if (dividendLength <= getIntegerLength(divisionData.getAbsoluteDivisor())
				&& divisionData.getDividend() !=0
				&& divisionData.getAbsoluteDividend() != divisionData.getAbsoluteDivisor()) {
			result.append(divisionData.getDividend()).append(" ").append("|")
					.append(divisionData.getDivisor()).append("\n");
		} else {
			result.append(divisionData.getDividend()).append("|").append(divisionData.getDivisor())
					.append("\n");
		}
	}

	private void appendSecondLine(DivisionData divisionData, int dividendLength) {
		int index = 0;
		if (divisionData.getAbsoluteDividend() < divisionData.getAbsoluteDivisor()
				&& divisionData.getAbsoluteDividend() != 0) {
			index = 1;
		}
		result.append(" ").append(divisionSteps.get(index).getIntegerToSubtract()).append(
				createStringOfChars(dividendLength - getIntegerLength(divisionData.getAbsoluteDivisor()), ' '))
				.append("|");
		if (getIntegerLength(divisionData.getDivisor()) > divisionData.getDecimalResult().length()) {
			result.append(createStringOfChars(getIntegerLength(divisionData.getDivisor()), '-')).append("\n");
		} else {
			result.append(createStringOfChars(divisionData.getDecimalResult().length(), '-')).append("\n");
		}
	}

	private void appendThirdLine(DivisionData divisionData) {
		int index = 0;
		if (divisionData.getAbsoluteDividend() < divisionData.getAbsoluteDivisor()
				&& divisionData.getAbsoluteDividend() != 0) {
			index = 1;
		}
		result.append(" ").append(createStringOfChars(getIntegerLength(divisionSteps.get(index).getIntegerToSubtract()), '-'));
		if (getIntegerLength(divisionData.getAbsoluteDividend()) 
				> getIntegerLength(divisionData.getDivisionSteps().get(index).getIntegerToSubtract())) {
			result.append(createStringOfChars(getIntegerLength(divisionData.getAbsoluteDividend()) 
					- getIntegerLength(divisionData.getDivisionSteps().get(index).getIntegerToSubtract()), ' '));
		}
		result.append("|").append(divisionData.getDecimalResult()).append("\n");
	}

	private void appendDivisionSteps(List<DivisionStep> divisionSteps) {
		int index = 1;
		if (divisionData.getAbsoluteDividend() < divisionData.getAbsoluteDivisor()) {
			index = 2;
		}
		for (int i = index; i < divisionSteps.size(); i++) {
			String dividendSpaces = createStringOfChars(divisionSteps.get(i).getSpaceShift() - DIVIDEND_SHIFT_SPACES,
					' ');
			String integerToSubtractSpaces = createStringOfChars(
					divisionSteps.get(i).getSpaceShift() - SUBTRACTION_SHIFT_SPACES, ' ');
			appendLineToResult(result, dividendSpaces, integerToSubtractSpaces, divisionSteps, i);
		}
	}

	private void appendLastLine(DivisionData divisionData, int dividendLength) {
		if (divisionData.getRemainder() == 0) {
			result.append(createStringOfChars(dividendLength, ' ')).append("0");
		} else {
			DivisionStep target = divisionData.getDivisionSteps()
					.get(divisionData.getDivisionSteps().size() - 1);
			result.append(createStringOfChars(target.getSpaceShift(), ' '))
					.append(divisionData.getDecimalRemainder());
		}
	}

	private int getIntegerLength(int input) {
		return Integer.toString(input).length();
	}

	private String createStringOfChars(int length, char input) {
		String result = "";
		if (length > 0) {
			char[] array = new char[length];
			Arrays.fill(array, input);
			result = new String(array);
		}
		return result;
	}

	private void appendLineToResult(StringBuilder result, String dividendSpaces, String integerToSubtractSpaces,
			List<DivisionStep> divisionSteps, int index) {
		result.append(dividendSpaces).append("_").append(divisionSteps.get(index).getDividend()).append("\n")
				.append(integerToSubtractSpaces).append(divisionSteps.get(index).getIntegerToSubtract()).append("\n")
				.append(integerToSubtractSpaces)
				.append(createStringOfChars(getIntegerLength(divisionSteps.get(index).getDividend()), '-'))
				.append("\n");
	}

	public StringBuilder getResult() {
		return result;
	}

	public void setResult(StringBuilder result) {
		this.result = result;
	}

	public DivisionData getDivisionParameters() {
		return divisionData;
	}

	public void setDivisionParameters(DivisionData divisionData) {
		this.divisionData = divisionData;
	}
}
