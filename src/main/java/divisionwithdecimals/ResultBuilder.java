package divisionwithdecimals;

import java.util.Arrays;
import java.util.List;

public class ResultBuilder {

	protected StringBuilder result = new StringBuilder();
	protected final int DIVIDEND_SHIFT_SPACES = 2;
	protected final int SUBTRACTION_SHIFT_SPACES = 1;
	protected DivisionParameters divisionParameters;
	protected List<DivisionStep> divisionSteps;

	public ResultBuilder(DivisionParameters divisionParameters, List<DivisionStep> divisionSteps) {
		this.divisionParameters = divisionParameters;
		this.divisionSteps = divisionSteps;
	}

	public String convertDivisionResultToString() {
		int dividendLength = getIntegerLength(divisionParameters.getAbsoluteDividend());
		appendFirstLine(divisionParameters, dividendLength);
		appendSecondLine(divisionParameters, dividendLength);
		appendThirdLine(divisionParameters, dividendLength);
		appendDivisionSteps(divisionSteps);
		appendLastLine(divisionParameters, dividendLength);
		return result.toString();
	}

	private void appendFirstLine(DivisionParameters divisionParameters, int dividendLength) {
		if (divisionParameters.getDividend() >= 0) {
			result.append("_");
		}
		if (dividendLength <= getIntegerLength(divisionParameters.getAbsoluteDivisor())
				&& divisionParameters.getDividend() !=0
				&& divisionParameters.getAbsoluteDividend() != divisionParameters.getAbsoluteDivisor()) {
			result.append(divisionParameters.getDividend()).append(" ").append("|")
					.append(divisionParameters.getDivisor()).append("\n");
		} else {
			result.append(divisionParameters.getDividend()).append("|").append(divisionParameters.getDivisor())
					.append("\n");
		}
	}

	private void appendSecondLine(DivisionParameters divisionParameters, int dividendLength) {
		int index = 0;
		if (divisionParameters.getAbsoluteDividend() < divisionParameters.getAbsoluteDivisor()
				&& divisionParameters.getAbsoluteDividend() != 0) {
			index = 1;
		}
		result.append(" ").append(divisionSteps.get(index).getIntegerToSubtract()).append(
				createStringOfChars(dividendLength - getIntegerLength(divisionParameters.getAbsoluteDivisor()), ' '))
				.append("|");
		if (getIntegerLength(divisionParameters.getDivisor()) > getIntegerLength(divisionParameters.getQuotient())) {
			result.append(createStringOfChars(getIntegerLength(divisionParameters.getDivisor()), '-')).append("\n");
		} else {
			result.append(createStringOfChars(getIntegerLength(divisionParameters.getQuotient()), '-')).append("\n");
		}
	}

	private void appendThirdLine(DivisionParameters divisionParameters, int dividendLength) {
		int index = 0;
		if (divisionParameters.getAbsoluteDividend() < divisionParameters.getAbsoluteDivisor()
				&& divisionParameters.getAbsoluteDividend() != 0) {
			index = 1;
		}
		result.append(" ").append(createStringOfChars(getIntegerLength(divisionSteps.get(index).getIntegerToSubtract()), '-'));
		if (getIntegerLength(divisionParameters.getAbsoluteDividend()) 
				> getIntegerLength(divisionParameters.getDivisionSteps().get(index).getIntegerToSubtract())) {
			result.append(createStringOfChars(getIntegerLength(divisionParameters.getAbsoluteDividend()) 
					- getIntegerLength(divisionParameters.getDivisionSteps().get(index).getIntegerToSubtract()), ' '));
		}
		result.append("|").append(divisionParameters.getDecimalResult()).append("\n");
	}

	protected void appendDivisionSteps(List<DivisionStep> divisionSteps) {
		int index = 1;
		if (divisionParameters.getAbsoluteDividend() < divisionParameters.getAbsoluteDivisor()) {
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

	private void appendLastLine(DivisionParameters divisionParameters, int dividendLength) {
		if (divisionParameters.getRemainder() == 0) {
			result.append(createStringOfChars(dividendLength, ' ')).append("0");
		} else {
			DivisionStep target = divisionParameters.getDivisionSteps()
					.get(divisionParameters.getDivisionSteps().size() - 1);
			result.append(createStringOfChars(target.getSpaceShift(), ' '))
					.append(divisionParameters.getDecimalRemainder());
		}
	}

	protected int getIntegerLength(int input) {
		return Integer.toString(input).length();
	}

	protected String createStringOfChars(int length, char input) {
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

	public DivisionParameters getDivisionParameters() {
		return divisionParameters;
	}

	public void setDivisionParameters(DivisionParameters divisionParameters) {
		this.divisionParameters = divisionParameters;
	}
}
