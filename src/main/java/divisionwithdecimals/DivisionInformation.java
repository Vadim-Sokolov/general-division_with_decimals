package divisionwithdecimals;

import java.util.ArrayList;
import java.util.List;

public class DivisionInformation {

	private int dividend;
	private int divisor;
	private int quotient;
	private int remainder;
	private int currentIndex;
	private int absoluteDividend;
	private int absoluteDivisor;
	private boolean negativeResult;
	private String decimalResult;
	private List<DivisionStep> divisionSteps;
	private int decimalRemainder;
	
	public void build(int dividend, int divisor) {
		if (divisor == 0) {
			throw new ArithmeticException();
		}
		this.dividend = dividend;
		this.divisor = divisor;
		this.quotient = dividend / divisor;
		this.remainder = dividend % divisor;
		this.absoluteDividend = Math.abs(dividend);
		this.absoluteDivisor = Math.abs(divisor);
		this.negativeResult = (double) dividend / divisor < 0;
		this.divisionSteps = new ArrayList<>();
	}

	public int getDividend() {
		return dividend;
	}

	public void setDividend(int dividend) {
		this.dividend = dividend;
	}

	public int getDivisor() {
		return divisor;
	}

	public void setDivisor(int divisor) {
		this.divisor = divisor;
	}

	public int getQuotient() {
		return quotient;
	}

	public void setQuotient(int quotient) {
		this.quotient = quotient;
	}

	public int getRemainder() {
		return remainder;
	}

	public void setRemainder(int remainder) {
		this.remainder = remainder;
	}

	public int getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}

	public int getAbsoluteDividend() {
		return absoluteDividend;
	}

	public void setAbsoluteDividend(int absoluteDividend) {
		this.absoluteDividend = absoluteDividend;
	}

	public int getAbsoluteDivisor() {
		return absoluteDivisor;
	}

	public void setAbsoluteDivisor(int absoluteDivisor) {
		this.absoluteDivisor = absoluteDivisor;
	}

	public boolean isNegativeResult() {
		return negativeResult;
	}

	public void setNegativeResult(boolean negativeResult) {
		this.negativeResult = negativeResult;
	}

	public String getDecimalResult() {
		return decimalResult;
	}

	public void setDecimalResult(String decimalResult) {
		this.decimalResult = decimalResult;
	}

	public List<DivisionStep> getDivisionSteps() {
		return divisionSteps;
	}

	public void setDivisionSteps(List<DivisionStep> divisionSteps) {
		this.divisionSteps = divisionSteps;
	}

	public int getDecimalRemainder() {
		return decimalRemainder;
	}

	public void setDecimalRemainder(int decimalRemainder) {
		this.decimalRemainder = decimalRemainder;
	}
}
