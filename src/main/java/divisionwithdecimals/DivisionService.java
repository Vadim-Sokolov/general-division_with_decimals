package divisionwithdecimals;

public class DivisionService {
	
	public String calculateDivisionResult(int dividend, int divisor) {
		DivisionData divisionData = createDivisionData(dividend, divisor);
		DecimalDivider decimalDivider = createDecimalDivider(divisionData);
		decimalDivider.performDivision();
		ResultBuilder resultBuilder = new ResultBuilder(divisionData, divisionData.getDivisionSteps());
		return resultBuilder.convertDivisionResultToString();
	}
	
	private DivisionData createDivisionData(int dividend, int divisor) {
		DivisionData divisionData = new DivisionData();
		divisionData.build(dividend, divisor);
		return divisionData;
	}
	
	private DecimalDivider createDecimalDivider(DivisionData divisionData) {
		DecimalDivider decimalDivider = new DecimalDivider();
		decimalDivider.build(divisionData);
		return decimalDivider;
	}
}
