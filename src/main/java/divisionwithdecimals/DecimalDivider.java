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
	private DivisionData divisionData;
	private List<DivisionStep> divisionSteps;

	public void build(DivisionData divisionData) {
		this.divisionData = divisionData;
		this.absoluteDividend = divisionData.getAbsoluteDividend();
		this.absoluteDivisor = divisionData.getAbsoluteDivisor();
		this.negativeResult = divisionData.isNegativeResult();
		this.fractionDigits = new ArrayList<Integer>();
		this.repeatingAt = -1;
		this.remainders = new ArrayList<Integer>();
		this.divisionSteps = divisionData.getDivisionSteps();
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
				divisionData.setDecimalResult("-" + String.valueOf(wholePart));
			} else {
				divisionData.setDecimalResult(String.valueOf(wholePart));
			}
		}
	}

	private void setResultForRepeatingDecimal() {
		while (absoluteDividend > 0 && repeatingAt == -1) {
			remainders.add(absoluteDividend);
			if (absoluteDividend >= absoluteDivisor) {
				createStep(absoluteDividend, absoluteDivisor,
						remainders.size() + getIntegerLength(divisionData.getDividend()));
			}
			int integerDivisionValue = absoluteDividend / absoluteDivisor;
			absoluteDividend = (absoluteDividend % absoluteDivisor) * 10;
			fractionDigits.add(integerDivisionValue);
			repeatingAt = remainders.indexOf(absoluteDividend);
			setDecimalResult();
		}
	}

	private void performIntegerDivision() {
		IntegerDivider integerDivider = new IntegerDivider(divisionData);
		integerDivider.performIntegerDivision();
	}

	private void setDecimalResult() {
		StringBuilder result = new StringBuilder();
		appendHyphen(result);
		result.append(wholePart + ".");
		appendFractionDigits(result);
		appendBracket(result);
		divisionData.setDecimalResult(result.toString());
	}

	private StringBuilder appendHyphen(StringBuilder result) {
		if (negativeResult) {
			result.append("-");
		}
		return result;
	}

	private StringBuilder appendFractionDigits(StringBuilder result) {
		for (int i = 0; i < fractionDigits.size(); i++) {
			if (i == repeatingAt) {
				result.append("(");
			}
			result.append(fractionDigits.get(i));
		}
		return result;
	}

	private StringBuilder appendBracket(StringBuilder result) {
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
		divisionData.setDecimalRemainder(target.getDividend() - target.getIntegerToSubtract());
	}

	public List<Integer> getRemainders() {
		return remainders;
	}

	public void setRemainders(List<Integer> remainders) {
		this.remainders = remainders;
	}

	public DivisionData getDivisionParameters() {
		return divisionData;
	}

	public void setDivisionParameters(DivisionData divisionData) {
		this.divisionData = divisionData;
	}

	public List<DivisionStep> getDivisionSteps() {
		return divisionSteps;
	}
}
