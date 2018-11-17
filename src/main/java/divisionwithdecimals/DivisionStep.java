package divisionwithdecimals;

public class DivisionStep {

	private int dividend;
	private int integerToSubtract;
	private int spaceShift;

	public DivisionStep(int dividend, int integerToSubtract, int spaceShift) {
		this.dividend = dividend;
		this.integerToSubtract = integerToSubtract;
		this.spaceShift = spaceShift;
	}

	@Override
	public String toString() {
		return "DivisionStep [dividend=" + dividend + ", integerToSubtract=" + integerToSubtract + ", spaceShift="
				+ spaceShift + "]";
	}

	public int getDividend() {
		return dividend;
	}

	public int getIntegerToSubtract() {
		return integerToSubtract;
	}
	
	public int getSpaceShift() {
		return spaceShift;
	}
}
