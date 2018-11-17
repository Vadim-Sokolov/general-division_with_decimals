package divisionwithdecimals;

public class DividerRunner {

	public static void main(String[] args) {
		DivisionInformation divisionInformation = createDivisionData();
		DecimalDivider decimalDivider = createDecimalDivider(divisionInformation);
		decimalDivider.performDivision();
		ResultBuilder resultBuilder = new ResultBuilder(divisionInformation, divisionInformation.getDivisionSteps());
		String result = resultBuilder.convertDivisionResultToString();
		System.out.println(result);
	}
	
	private static DivisionInformation createDivisionData() {
		DivisionInformation divisionInformation = new DivisionInformation();
		divisionInformation.build(25, 39);
		return divisionInformation;
	}
	
	private static DecimalDivider createDecimalDivider(DivisionInformation divisionInformation) {
		DecimalDivider decimalDivider = new DecimalDivider();
		decimalDivider.build(divisionInformation);
		return decimalDivider;
	}
}
