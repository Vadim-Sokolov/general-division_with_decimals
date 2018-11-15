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
	private DivisionParameters divisionParameters;
	private List<DivisionStep> divisionSteps;

	public DecimalDivider(DivisionParameters divisionParameters) {
		this.divisionParameters = divisionParameters;
		this.absoluteDividend = divisionParameters.getAbsoluteDividend();
		this.absoluteDivisor = divisionParameters.getAbsoluteDivisor();
		this.negativeResult = divisionParameters.isNegativeResult();
		this.wholePart = absoluteDividend / absoluteDivisor;
		this.absoluteDividend = (absoluteDividend % absoluteDivisor) * 10;
		this.fractionDigits = new ArrayList<Integer>();
		this.repeatingAt = -1;
		this.remainders = new ArrayList<Integer>();
		this.divisionSteps = divisionParameters.getDivisionSteps();
	}

	public void performDivision() {
		performIntegerDivision();
		if (absoluteDividend == 0) {
			if (negativeResult) {
				divisionParameters.setDecimalResult("-" + String.valueOf(wholePart));
			} else {
				divisionParameters.setDecimalResult(String.valueOf(wholePart));
			}
		}
		while (absoluteDividend > 0 && repeatingAt == -1) {
			remainders.add(absoluteDividend);
			if (absoluteDividend >= absoluteDivisor) {
				createStep(absoluteDividend, absoluteDivisor,
						remainders.size() + getIntegerLength(divisionParameters.getDividend()));
			}
			int whole = absoluteDividend / absoluteDivisor;
			absoluteDividend = (absoluteDividend % absoluteDivisor) * 10;
			fractionDigits.add(whole);
			repeatingAt = remainders.indexOf(absoluteDividend);
			setDecimalResult();
		}
		setDecimalRemainder();
	}

	private void performIntegerDivision() {
		IntegerDivider integerDivider = new IntegerDivider(divisionParameters);
		integerDivider.performIntegerDivision();
	}

	private void setDecimalResult() {
		StringBuilder result = new StringBuilder();
		if (negativeResult) {
			result.append("-");
		}
		result.append(wholePart + ".");
		for (int i = 0; i < fractionDigits.size(); i++) {
			if (i == repeatingAt) {
				result.append("(");
			}
			result.append(fractionDigits.get(i));
		}
		if (repeatingAt >= 0) {
			result.append(")");
		}
		divisionParameters.setDecimalResult(result.toString());
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
		divisionParameters.setDecimalRemainder(target.getDividend() - target.getIntegerToSubtract());
	}

	public List<Integer> getRemainders() {
		return remainders;
	}

	public void setRemainders(List<Integer> remainders) {
		this.remainders = remainders;
	}

	public DivisionParameters getDivisionParameters() {
		return divisionParameters;
	}

	public void setDivisionParameters(DivisionParameters divisionParameters) {
		this.divisionParameters = divisionParameters;
	}

	public List<DivisionStep> getDivisionSteps() {
		return divisionSteps;
	}
}
