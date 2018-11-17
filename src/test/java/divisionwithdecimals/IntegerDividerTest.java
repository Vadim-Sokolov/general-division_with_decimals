package divisionwithdecimals;

import static org.junit.Assert.*;
import org.junit.Test;

public class IntegerDividerTest {
	
	DivisionService divisionService = new DivisionService();

	@Test(expected = ArithmeticException.class)
	public void shouldThrowException_whenDivideZeroByZero() {
		divisionService.calculateDivisionResult(0, 0);
	}

	@Test(expected = ArithmeticException.class)
	public void shouldThrowException_whenDivideNegativeNumberByZero() {
		divisionService.calculateDivisionResult(-10, 0);
	}

	@Test
	public void shouldReturnCorrectResult_whenDivideZeroByNegativeNumber() {
		String expected = 
		"_0|-10\n" + 
		" 0|---\n" + 
		" -|0\n" + 
		" 0";
		assertEquals(expected, divisionService.calculateDivisionResult(0, -10));
	}

	@Test
	public void shouldReturnCorrectResult_whenDivideNegativeNumberByNegativeNumber() {
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
		assertEquals(expected, divisionService.calculateDivisionResult(-200200, -10));
	}

	@Test
	public void shouldReturnCorrectResult_whenDivideNegativeNumberByPositiveNumber() {
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
		assertEquals(expected, divisionService.calculateDivisionResult(-200200, 1000));
	}

	@Test
	public void shouldReturnCorrectResult_whenDividePositiveNumberByPositiveNumber() {
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
		assertEquals(expected, divisionService.calculateDivisionResult(78945, 4));
	}

	@Test
	public void shouldReturnCorrectResult_whenDividePositiveNumberByOne() {
		String expected = 
		"_10000000|1\n" + 
		" 1       |--------\n" + 
		" -       |10000000\n" + 
		"      _0\n" + 
		"       0\n" + 
		"       -\n" + 
		"        0";
		assertEquals(expected, divisionService.calculateDivisionResult(10000000, 1));
	}
	
	@Test
	public void shouldReturnCorrectResult_whenDivideOneByNegativeOne() {
		String expected = 
		"_1|-1\n" + 
		" 1|--\n" + 
		" -|-1\n" + 
		" 0";
		assertEquals(expected, divisionService.calculateDivisionResult(1, -1));
	}
	
	@Test
	public void shouldReturnCorrectResult_whenPositiveNumbersProduceRepeatingDecimal() {
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
		assertEquals(expected, divisionService.calculateDivisionResult(1000, 3));
	}
	
	@Test
	public void shouldReturnCorrectResult_whenPositiveAndNegativeNumberProduceRepeatingDecimal() {
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
		assertEquals(expected, divisionService.calculateDivisionResult(7777, -3));
	}
	
	@Test
	public void shouldReturnCorrectResult_whenDividendSmallerThanDivisorProducesRepeatingDecimal() {
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
		assertEquals(expected, divisionService.calculateDivisionResult(7, 12));
	}
	
	@Test
	public void shouldReturnCorrectResult_whenNegativeNumbersProduceRepeatingDecimal() {
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
		assertEquals(expected, divisionService.calculateDivisionResult(-25, -39));
	}
}
