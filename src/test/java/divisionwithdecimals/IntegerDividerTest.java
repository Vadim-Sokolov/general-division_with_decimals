package divisionwithdecimals;

import static org.junit.Assert.*;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

public class IntegerDividerTest {

	@Test(expected = ArithmeticException.class)
	public void shouldThrowException_whenDivideZeroByZero() {
		DivisionInformation divisionInformation = new DivisionInformation(0, 0);
	}

	@Test(expected = ArithmeticException.class)
	public void shouldThrowException_whenDivideNegativeNumberByZero() {
		DivisionInformation divisionInformation = new DivisionInformation(-10, 0);
	}

	@Test
	public void shouldReturnCorrectResult_whenDivideZeroByNegativeNumber() {
		DivisionInformation divisionInformation = new DivisionInformation(0, -10);
		DecimalDivider decimalDivider = new DecimalDivider(divisionInformation);
		decimalDivider.performDivision();
		ResultBuilder resultBuilder = new ResultBuilder(divisionInformation, divisionInformation.getDivisionSteps());
		String expected = 
		"_0|-10\n" + 
		" 0|---\n" + 
		" -|0\n" + 
		" 0";
		assertEquals(expected, resultBuilder.convertDivisionResultToString());
	}

	@Test
	public void shouldReturnCorrectResult_whenDivideNegativeNumberByNegativeNumber() {
		DivisionInformation divisionInformation = new DivisionInformation(-200200, -10);
		DecimalDivider decimalDivider = new DecimalDivider(divisionInformation);
		decimalDivider.performDivision();
		ResultBuilder resultBuilder = new ResultBuilder(divisionInformation, divisionInformation.getDivisionSteps());
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
		DivisionInformation divisionInformation = new DivisionInformation(-200200, 1000);
		DecimalDivider decimalDivider = new DecimalDivider(divisionInformation);
		decimalDivider.performDivision();
		ResultBuilder resultBuilder = new ResultBuilder(divisionInformation, divisionInformation.getDivisionSteps());
		String expected = 
		"-200200|1000\n" + 
		" 2000  |------\n" + 
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
		DivisionInformation divisionInformation = new DivisionInformation(78945, 4);
		DecimalDivider decimalDivider = new DecimalDivider(divisionInformation);
		decimalDivider.performDivision();
		ResultBuilder resultBuilder = new ResultBuilder(divisionInformation, divisionInformation.getDivisionSteps());
		String expected = 
		"_78945|4\n" + 
		" 4    |--------\n" + 
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
		DivisionInformation divisionInformation = new DivisionInformation(10000000, 1);
		DecimalDivider decimalDivider = new DecimalDivider(divisionInformation);
		decimalDivider.performDivision();
		ResultBuilder resultBuilder = new ResultBuilder(divisionInformation, divisionInformation.getDivisionSteps());
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
		DivisionInformation divisionInformation = new DivisionInformation(1, -1);
		DecimalDivider decimalDivider = new DecimalDivider(divisionInformation);
		decimalDivider.performDivision();
		ResultBuilder resultBuilder = new ResultBuilder(divisionInformation, divisionInformation.getDivisionSteps());
		String expected = 
		"_1|-1\n" + 
		" 1|--\n" + 
		" -|-1\n" + 
		" 0";
		assertEquals(expected, resultBuilder.convertDivisionResultToString());
	}
	
	@Test
	public void shouldReturnCorrectResult_whenPositiveNumbersProduceRepeatingDecimal() {
		DivisionInformation divisionInformation = new DivisionInformation(1000, 3);
		DecimalDivider decimalDivider = new DecimalDivider(divisionInformation);
		decimalDivider.performDivision();
		ResultBuilder resultBuilder = new ResultBuilder(divisionInformation, divisionInformation.getDivisionSteps());
		String expected = 
		"_1000|3\n" + 
		" 9   |-------\n" + 
		" -   |333.(3)\n" + 
		" _10\n" + 
		"  9\n" + 
		"  --\n" + 
		"  _10\n" + 
		"   9\n" + 
		"   --\n" + 
		"   _10\n" + 
		"    9\n" + 
		"    --\n" + 
		"     1";
		assertEquals(expected, resultBuilder.convertDivisionResultToString());
	}
	
	@Test
	public void shouldReturnCorrectResult_whenPositiveAndNegativeNumberProduceRepeatingDecimal() {
		DivisionInformation divisionInformation = new DivisionInformation(7777, -3);
		DecimalDivider decimalDivider = new DecimalDivider(divisionInformation);
		decimalDivider.performDivision();
		ResultBuilder resultBuilder = new ResultBuilder(divisionInformation, divisionInformation.getDivisionSteps());
		String expected = 
		"_7777|-3\n" + 
		" 6   |---------\n" + 
		" -   |-2592.(3)\n" + 
		"_17\n" + 
		" 15\n" + 
		" --\n" + 
		" _27\n" + 
		"  27\n" + 
		"  --\n" + 
		"  _7\n" + 
		"   6\n" + 
		"   -\n" + 
		"   _10\n" + 
		"    9\n" + 
		"    --\n" + 
		"     1";
		assertEquals(expected, resultBuilder.convertDivisionResultToString());
	}
	
	@Test
	public void shouldReturnCorrectResult_whenDividendSmallerThanDivisorProducesRepeatingDecimal() {
		DivisionInformation divisionInformation = new DivisionInformation(7, 12);
		DecimalDivider decimalDivider = new DecimalDivider(divisionInformation);
		decimalDivider.performDivision();
		ResultBuilder resultBuilder = new ResultBuilder(divisionInformation, divisionInformation.getDivisionSteps());
		String expected = 
		"_7 |12\n" + 
		" 60|-------\n" + 
		" --|0.58(3)\n" + 
		" _100\n" + 
		"  96\n" + 
		"  ---\n" + 
		"  _40\n" + 
		"   36\n" + 
		"   --\n" + 
		"    4";
		assertEquals(expected, resultBuilder.convertDivisionResultToString());
	}
	
	@Test
	public void shouldReturnCorrectResult_whenNegativeNumbersProduceRepeatingDecimal() {
		DivisionInformation divisionInformation = new DivisionInformation(-25, -39);
		DecimalDivider decimalDivider = new DecimalDivider(divisionInformation);
		decimalDivider.performDivision();
		ResultBuilder resultBuilder = new ResultBuilder(divisionInformation, divisionInformation.getDivisionSteps());
		String expected = 
		"-25 |-39\n" + 
		" 234|----------\n" + 
		" ---|0.(641025)\n" + 
		"   _160\n" + 
		"    156\n" + 
		"    ---\n" + 
		"    _40\n" + 
		"     39\n" + 
		"     --\n" + 
		"      _100\n" + 
		"       78\n" + 
		"       ---\n" + 
		"       _220\n" + 
		"        195\n" + 
		"        ---\n" + 
		"         25";
		assertEquals(expected, resultBuilder.convertDivisionResultToString());
	}
}
