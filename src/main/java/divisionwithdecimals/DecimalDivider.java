package divisionwithdecimals;

import java.util.*;

public class DecimalDivider {

	private int wholePart;
	private int repeatingAt;
	private List<Integer> fractionDigits;
	private int absoluteDividend;
	private int absoluteDivisor;
	private boolean negativeResult;
	private List<Integer> remainders;
	private DivisionInformation divisionInformation;
	private List<DivisionStep> divisionSteps;

	public DecimalDivider(DivisionInformation divisionInformation) {
		this.divisionInformation = divisionInformation;
		this.absoluteDividend = divisionInformation.getAbsoluteDividend();
		this.absoluteDivisor = divisionInformation.getAbsoluteDivisor();
		this.negativeResult = divisionInformation.isNegativeResult();
		this.fractionDigits = new ArrayList<Integer>();
		this.repeatingAt = -1;
		this.remainders = new ArrayList<Integer>();
		this.divisionSteps = divisionInformation.getDivisionSteps();
		setVariables();
	}
	
	private void setVariables() {
		this.wholePart = absoluteDividend / absoluteDivisor;
		this.absoluteDividend = (absoluteDividend % absoluteDivisor) * 10;
	}

	public void performDivision() {
		performIntegerDivision();
		setResultForTerminatingDecimal();
		setResultForRepeatingDecimal();
		setDecimalRemainder();
	}
	
	private void setResultForTerminatingDecimal() {
		if (absoluteDividend == 0) {
			if (negativeResult) {
				divisionInformation.setDecimalResult("-" + String.valueOf(wholePart));
			} else {
				divisionInformation.setDecimalResult(String.valueOf(wholePart));
			}
		}
	}
	
	private void setResultForRepeatingDecimal() {
		while (absoluteDividend > 0 && repeatingAt == -1) {
			remainders.add(absoluteDividend);
			if (absoluteDividend >= absoluteDivisor) {
				createStep(absoluteDividend, absoluteDivisor,
						remainders.size() + getIntegerLength(divisionInformation.getDividend()));
			}
			int whole = absoluteDividend / absoluteDivisor;
			absoluteDividend = (absoluteDividend % absoluteDivisor) * 10;
			fractionDigits.add(whole);
			repeatingAt = remainders.indexOf(absoluteDividend);
			setDecimalResult();
		}
	}

	private void performIntegerDivision() {
		IntegerDivider integerDivider = new IntegerDivider(divisionInformation);
		integerDivider.performIntegerDivision();
	}

	private void setDecimalResult() {
		StringBuilder result = new StringBuilder();
		appendHyphen(result);
		result.append(wholePart + ".");
		appendFractionDigits(result);
		appendBracket(result);
		divisionInformation.setDecimalResult(result.toString());
	}
	
	private StringBuilder appendHyphen(StringBuilder stringBuilder) {
		StringBuilder result = stringBuilder;
		if (negativeResult) {
			result.append("-");
		}
		return result;
	}
	
	private StringBuilder appendFractionDigits(StringBuilder stringBuilder) {
		StringBuilder result = stringBuilder;
		for (int i = 0; i < fractionDigits.size(); i++) {
			if (i == repeatingAt) {
				result.append("(");
			}
			result.append(fractionDigits.get(i));
		}
		return result;
	}
	
	private StringBuilder appendBracket(StringBuilder stringBuilder) {
		StringBuilder result = stringBuilder;
		if (repeatingAt >= 0) {
			result.append(")");
		}
		return result;
	}

	private void createStep(int currentDividend, int divisor, int spaceShift) {
		int currentQuotient = currentDividend / divisor;
		int integerToSubtract = divisor * currentQuotient;
		divisionSteps.add(new DivisionStep(currentDividend, integerToSubtract, spaceShift));
	}

	private int getIntegerLength(int input) {
		return Integer.toString(input).length();
	}

	private void setDecimalRemainder() {
		DivisionStep target = divisionSteps.get(divisionSteps.size() - 1);
		divisionInformation.setDecimalRemainder(target.getDividend() - target.getIntegerToSubtract());
	}

	public List<Integer> getRemainders() {
		return remainders;
	}

	public void setRemainders(List<Integer> remainders) {
		this.remainders = remainders;
	}

	public DivisionInformation getDivisionParameters() {
		return divisionInformation;
	}

	public void setDivisionParameters(DivisionInformation divisionInformation) {
		this.divisionInformation = divisionInformation;
	}

	public List<DivisionStep> getDivisionSteps() {
		return divisionSteps;
	}
}
