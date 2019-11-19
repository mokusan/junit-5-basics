package com.example;

import static org.junit.jupiter.api.Assertions.*;

//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_METHOD) // PER_METHOD is a default value, that means, it is not necessary to put this annotation. And it means that the test instance is created before each method
class MathUtilsTest {                            // PER_CLASS means that there is only going to be one instance of MathUtils no matters how many methods you have
	
	MathUtils mathUtils;
	
	/*
	 * @BeforeAll //@BeforeAll runs even before the instance is created static void
	 * beforeAllInit() { // Methods with BeforeAll annotation need to be static!
	 * //unless you have the life cycle annotation with the flag PER_CLASS
	 * System.out.print("cleaning up..."); }
	 */
	
	@BeforeEach // execute this method before each other method 
	void init() {
		mathUtils = new MathUtils();
	}
	
	/*
	 * @AfterEach void cleanup() { System.out.print("cleaning up..."); }
	 */
	@Nested	
	@DisplayName("Testing add method") // allows to change the name that displays in the JUnit console, for improve readability
	@Tag("Math")
	class AddTest {
		
		@Test
		void testAddPositive() {			
			int expected = 2;
			int actual = mathUtils.add(1, 1);			
			assertEquals(expected, actual, () -> "should return sum " + expected + " but returned " + actual);
		}
		
		@Test
		void testAddNegative() {			
			int expected = -2;
			int actual = mathUtils.add(-1, -1);			
			assertEquals(expected, actual, () -> "should return sum " + expected + " but returned " + actual);
		}		
	}
	
	
	@Test
	@Tag("Math")
	void testDivide() {
		assertThrows(ArithmeticException.class, () -> mathUtils.divide(1, 0), "divide by zero should throw"); // The lambda is going to execute the thing that throws an exception
	}              
	
	@Test
	@Tag("Math")
	void testMultiply() {
		assertAll(
				() -> assertEquals(4, mathUtils.multiply(2,2)),
				() -> assertEquals(0, mathUtils.multiply(2,0)),
				() -> assertEquals(-2, mathUtils.multiply(2,-1))
				);
	}
	
	@RepeatedTest(3) //repeat this test 3 times 
	@Tag("Circle")
	void testComputeCircleArea(RepetitionInfo repetitionInfo) {
		int rep = repetitionInfo.getCurrentRepetition();
		switch (rep) {
		case 1:
			assertEquals(Math.PI * 100, mathUtils.computeCircleArea(10), "Should return right Circle area");
			break;
		case 2:
			assertEquals(Math.PI * 25, mathUtils.computeCircleArea(5), "Should return right Circle area");
			break;	
		case 3:
			assertEquals(Math.PI * 9, mathUtils.computeCircleArea(3), "Should return right Circle area");
			break;
		}		
	}
	
	/*
	 * @Test
	 * 
	 * @Disabled // disable a test
	 * 
	 * @DisplayName("Test Driven Development (TDD). Should not run") void
	 * testDisabled() { fail("this test should be disabled"); }
	 */
}
