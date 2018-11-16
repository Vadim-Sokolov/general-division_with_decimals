package divisionwithdecimals;

public class DividerRunner {

	public static void main(String[] args) {
		DivisionInformation divisionInformation = new DivisionInformation(-25, -39);
		DecimalDivider decimalDivider = new DecimalDivider(divisionInformation);
		decimalDivider.performDivision();
		ResultBuilder resultBuilder = new ResultBuilder(divisionInformation, divisionInformation.getDivisionSteps());
		String result = resultBuilder.convertDivisionResultToString();
		System.out.println(result);
	}
}
