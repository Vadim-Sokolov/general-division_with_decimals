package divisionwithdecimals;

public class DividerRunner {

	public static void main(String[] args) {
		DivisionParameters divisionParameters = new DivisionParameters(0,-10);
		DecimalDivider decimalDivider = new DecimalDivider(divisionParameters);
		decimalDivider.performDivision();
		ResultBuilder resultBuilder = new ResultBuilder(divisionParameters, divisionParameters.getDivisionSteps());
		String result = resultBuilder.convertDivisionResultToString();
		System.out.println(result);
	}
}
