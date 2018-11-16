package divisionwithdecimals;

import java.util.Arrays;
import java.util.List;

public class ResultBuilder {

	private StringBuilder result = new StringBuilder();
	private final int DIVIDEND_SHIFT_SPACES = 2;
	private final int SUBTRACTION_SHIFT_SPACES = 1;
	private DivisionInformation divisionInformation;
	private List<DivisionStep> divisionSteps;

	public ResultBuilder(DivisionInformation divisionInformation, List<DivisionStep> divisionSteps) {
		this.divisionInformation = divisionInformation;
		this.divisionSteps = divisionSteps;
	}

	public String convertDivisionResultToString() {
		int dividendLength = getIntegerLength(divisionInformation.getAbsoluteDividend());
		appendFirstLine(divisionInformation, dividendLength);
		appendSecondLine(divisionInformation, dividendLength);
		appendThirdLine(divisionInformation, dividendLength);
		appendDivisionSteps(divisionSteps);
		appendLastLine(divisionInformation, dividendLength);
		return result.toString();
	}

	private void appendFirstLine(DivisionInformation divisionInformation, int dividendLength) {
		if (divisionInformation.getDividend() >= 0) {
			result.append("_");
		}
		if (dividendLength <= getIntegerLength(divisionInformation.getAbsoluteDivisor())
				&& divisionInformation.getDividend() !=0
				&& divisionInformation.getAbsoluteDividend() != divisionInformation.getAbsoluteDivisor()) {
			result.append(divisionInformation.getDividend()).append(" ").append("|")
					.append(divisionInformation.getDivisor()).append("\n");
		} else {
			result.append(divisionInformation.getDividend()).append("|").append(divisionInformation.getDivisor())
					.append("\n");
		}
	}

	private void appendSecondLine(DivisionInformation divisionInformation, int dividendLength) {
		int index = 0;
		if (divisionInformation.getAbsoluteDividend() < divisionInformation.getAbsoluteDivisor()
				&& divisionInformation.getAbsoluteDividend() != 0) {
			index = 1;
		}
		result.append(" ").append(divisionSteps.get(index).getIntegerToSubtract()).append(
				createStringOfChars(dividendLength - getIntegerLength(divisionInformation.getAbsoluteDivisor()), ' '))
				.append("|");
		if (getIntegerLength(divisionInformation.getDivisor()) > divisionInformation.getDecimalResult().length()) {
			result.append(createStringOfChars(getIntegerLength(divisionInformation.getDivisor()), '-')).append("\n");
		} else {
			result.append(createStringOfChars(divisionInformation.getDecimalResult().length(), '-')).append("\n");
		}
	}

	private void appendThirdLine(DivisionInformation divisionInformation, int dividendLength) {
		int index = 0;
		if (divisionInformation.getAbsoluteDividend() < divisionInformation.getAbsoluteDivisor()
				&& divisionInformation.getAbsoluteDividend() != 0) {
			index = 1;
		}
		result.append(" ").append(createStringOfChars(getIntegerLength(divisionSteps.get(index).getIntegerToSubtract()), '-'));
		if (getIntegerLength(divisionInformation.getAbsoluteDividend()) 
				> getIntegerLength(divisionInformation.getDivisionSteps().get(index).getIntegerToSubtract())) {
			result.append(createStringOfChars(getIntegerLength(divisionInformation.getAbsoluteDividend()) 
					- getIntegerLength(divisionInformation.getDivisionSteps().get(index).getIntegerToSubtract()), ' '));
		}
		result.append("|").append(divisionInformation.getDecimalResult()).append("\n");
	}

	private void appendDivisionSteps(List<DivisionStep> divisionSteps) {
		int index = 1;
		if (divisionInformation.getAbsoluteDividend() < divisionInformation.getAbsoluteDivisor()) {
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

	private void appendLastLine(DivisionInformation divisionInformation, int dividendLength) {
		if (divisionInformation.getRemainder() == 0) {
			result.append(createStringOfChars(dividendLength, ' ')).append("0");
		} else {
			DivisionStep target = divisionInformation.getDivisionSteps()
					.get(divisionInformation.getDivisionSteps().size() - 1);
			result.append(createStringOfChars(target.getSpaceShift(), ' '))
					.append(divisionInformation.getDecimalRemainder());
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

	public DivisionInformation getDivisionParameters() {
		return divisionInformation;
	}

	public void setDivisionParameters(DivisionInformation divisionInformation) {
		this.divisionInformation = divisionInformation;
	}
}
