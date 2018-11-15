package divisionwithdecimals;

import static org.junit.Assert.*;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

public class IntegerDividerTest {

	@Test(expected = ArithmeticException.class)
	public void shouldThrowException_whenDivideZeroByZero() {
		DivisionParameters divisionParameters = new DivisionParameters(0, 0);
	}

	@Test(expected = ArithmeticException.class)
	public void shouldThrowException_whenDivideNegativeNumberByZero() {
		DivisionParameters divisionParameters = new DivisionParameters(-10, 0);
	}

	@Test
	public void shouldReturnCorrectResult_whenDivideZeroByNegativeNumber() {
		DivisionParameters divisionParameters = new DivisionParameters(0, -10);
		DecimalDivider decimalDivider = new DecimalDivider(divisionParameters);
		decimalDivider.performDivision();
		ResultBuilder resultBuilder = new ResultBuilder(divisionParameters, divisionParameters.getDivisionSteps());
		String expected = 
		"_0|-10\n" + 
		" 0|---\n" + 
		" -|0\n" + 
		" 0";
		assertEquals(expected, resultBuilder.convertDivisionResultToString());
	}

	@Test
	public void shouldReturnCorrectResult_whenDivideNegativeNumberByNegativeNumber() {
		DivisionParameters divisionParameters = new DivisionParameters(-200200, -10);
		DecimalDivider decimalDivider = new DecimalDivider(divisionParameters);
		decimalDivider.performDivision();
		ResultBuilder resultBuilder = new ResultBuilder(divisionParameters, divisionParameters.getDivisionSteps());
		String expected = 
		"-200200|-10\n" + 
		" 20    |-----\n" + 
		" --    |20020\n" + 
		"   _20\n" + 
		"    20\n" + 
		"    --\n" + 
		"    _0\n" + 
		"     0\n" + 
		"     -\n" + 
		"      0";
		assertEquals(expected, resultBuilder.convertDivisionResultToString());
	}

	@Test
	public void shouldReturnCorrectResult_whenDivideNegativeNumberByPositiveNumber() {
		DivisionParameters divisionParameters = new DivisionParameters(-200200, 1000);
		DecimalDivider decimalDivider = new DecimalDivider(divisionParameters);
		decimalDivider.performDivision();
		ResultBuilder resultBuilder = new ResultBuilder(divisionParameters, divisionParameters.getDivisionSteps());
		String expected = 
		"-200200|1000\n" + 
		" 2000  |----\n" + 
		" ----  |-200.2\n" + 
		"    _200\n" + 
		"     0\n" + 
		"     ---\n" + 
		"      _2000\n" + 
		"       2000\n" + 
		"       ----\n" + 
		"        0";
		assertEquals(expected, resultBuilder.convertDivisionResultToString());
	}

	@Test
	public void shouldReturnCorrectResult_whenDividePositiveNumberByPositiveNumber() {
		DivisionParameters divisionParameters = new DivisionParameters(78945, 4);
		DecimalDivider decimalDivider = new DecimalDivider(divisionParameters);
		decimalDivider.performDivision();
		ResultBuilder resultBuilder = new ResultBuilder(divisionParameters, divisionParameters.getDivisionSteps());
		String expected = 
		"_78945|4\n" + 
		" 4    |-----\n" + 
		" -    |19736.25\n" + 
		"_38\n" + 
		" 36\n" + 
		" --\n" + 
		" _29\n" + 
		"  28\n" + 
		"  --\n" + 
		"  _14\n" + 
		"   12\n" + 
		"   --\n" + 
		"   _25\n" + 
		"    24\n" + 
		"    --\n" + 
		"    _10\n" + 
		"     8\n" + 
		"     --\n" + 
		"     _20\n" + 
		"      20\n" + 
		"      --\n" + 
		"       0";
		assertEquals(expected, resultBuilder.convertDivisionResultToString());
	}

	@Test
	public void shouldReturnCorrectResult_whenDividePositiveNumberByOne() {
		DivisionParameters divisionParameters = new DivisionParameters(10000000, 1);
		DecimalDivider decimalDivider = new DecimalDivider(divisionParameters);
		decimalDivider.performDivision();
		ResultBuilder resultBuilder = new ResultBuilder(divisionParameters, divisionParameters.getDivisionSteps());
		String expected = 
		"_10000000|1\n" + 
		" 1       |--------\n" + 
		" -       |10000000\n" + 
		"      _0\n" + 
		"       0\n" + 
		"       -\n" + 
		"        0";
		assertEquals(expected, resultBuilder.convertDivisionResultToString());
	}
	
	@Test
	public void shouldReturnCorrectResult_whenDivideOneByNegativeOne() {
		DivisionParameters divisionParameters = new DivisionParameters(1, -1);
		DecimalDivider decimalDivider = new DecimalDivider(divisionParameters);
		decimalDivider.performDivision();
		ResultBuilder resultBuilder = new ResultBuilder(divisionParameters, divisionParameters.getDivisionSteps());
		String expected = 
		"_1|-1\n" + 
		" 1|--\n" + 
		" -|-1\n" + 
		" 0";
		assertEquals(expected, resultBuilder.convertDivisionResultToString());
	}
}
