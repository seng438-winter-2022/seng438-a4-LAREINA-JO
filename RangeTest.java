package org.jfree.data.test;

import static org.junit.Assert.*;
import org.jfree.data.Range;
import org.junit.*;

public class RangeTest {
    private Range exampleRange;
    private Range exampleRange2;
    private Range exampleRange3;
    private Range exampleRange4;
    private Range exampleRange5;
    private Range exampleRange6;
    private Range exampleRange7;
    private Range exampleRange8;
    private Range exampleRange9;
    private Range exampleRange10;
    private Range exampleRange11;
    private Range exampleRange12;
    
    //scale()
    private Range range;
	private Range range1;
    
//    @BeforeClass public static void setUpBeforeClass() throws Exception {
//    }

    @Before
    public void setUp() throws Exception { 
    	exampleRange = new Range(-1, 1);	// 0 central value
    	exampleRange2 = new Range(1, 2);	// 1.5 central value
    	exampleRange3 = new Range(-2, -1);	// -1.5 central value
    	exampleRange4 = new Range(-1000000000, 2000000000);	// 500000000 central value
    	exampleRange5 = new Range(Double.NaN, 1);
    	exampleRange6 = new Range(-1, Double.NaN);
    	exampleRange7 = new Range(Double.NaN, Double.NaN);
    	exampleRange8 = new Range(-1,5); // for contain()
    	
    	//ToString()
    	exampleRange9 = new Range(-5, 5); 
    	exampleRange10 = new Range(0, 0); 
    	exampleRange11 = new Range(5.0, 25.0); 
    	exampleRange12 = new Range(-25, -5); 
    	
    	range1=new Range(-5,5);
    }

    
    @Test
    public void upperBoundShouldBeOne() {
    	assertEquals("The upper bound value of -1 and 1 should be 1",
    	1, exampleRange.getUpperBound(), .000000001d);
    }
    
    @Test
    public void upperBoundShouldNotBeAboveOne() {
    	assertNotEquals("The upper bound value of -1 and 1 should not be above 1",
    	1.000001, exampleRange.getUpperBound(), .000000001d);
    }
    
    @Test
    public void upperBoundShouldNotBeBelowOne() {
    	assertNotEquals("The upper bound value of -1 and 1 should not be below 1",
    	0.999999, exampleRange.getUpperBound(), .000000001d);
    }

    @Test
    public void upperBoundShouldBeTwoBil() {
        assertEquals("The upper bound value of -1000000000 and 2000000000 should be 2000000000",
        2000000000, exampleRange4.getUpperBound(), .000000001d);
    }
    
    @Test
    public void lowerBoundShouldBeNegOne() {
    	assertEquals("The lower bound value of -1 and 1 should be -1",
    	-1, exampleRange.getLowerBound(), .000000001d);
    }
    
    @Test
    public void lowerBoundShouldNotBeAboveNegOne() {
    	assertNotEquals("The lower bound value of -1 and 1 should be above -1",
    	-0.999999, exampleRange.getLowerBound(), .000000001d);
    }
    
    @Test
    public void lowerBoundShouldNotBeBelowNegOne() {
    	assertNotEquals("The lower bound value of -1 and 1 should be below -1",
    	-1.000001, exampleRange.getLowerBound(), .000000001d);
    }
    
    @Test
    public void lowerBoundShouldBeNegOneBil() {
        assertEquals("The lower bound value of -1000000000 and 2000000000 should be -1000000000",
        -1000000000, exampleRange4.getLowerBound(), .000000001d);
    }
    
    @Test
    public void neitherBoudryIsNaN() {
        assertFalse("The range of -1 and 1 should should not be NaN",
        exampleRange.isNaNRange());
    }
    
    @Test
    public void lowerBoundryIsNotNaNUpperBoudryIsNaN() {
        assertFalse("The range of Double.NaN and 1 should should not be NaN",
        exampleRange5.isNaNRange());
    }
    
    @Test
    public void lowerBoundryIsNaNUpperBoudryIsNotNaN() {
        assertFalse("The range of -1 and Double.NaN should should not be NaN",
        exampleRange6.isNaNRange());
    }
    
    @Test
    public void rangeBoundriesAreNaN() {
        assertTrue("The range of Double.NaN and Double.NaN should should be NaN",
        exampleRange7.isNaNRange());
    }
    
    // combine
    @Test
    public void combineRangesFirstRangeIsNull() {
    	assertEquals("The resulting range by combining null and [1, 2] should be [1, 2]",
    	exampleRange2, Range.combine(null, exampleRange2));
    }
    
    @Test
    public void combineRangesSecondRangeIsNull() {
    	assertEquals("The resulting range by combining [1, 2] and null should be [1, 2]",
    	exampleRange2, Range.combine(exampleRange2, null));
    }
    
    @Test
    public void lowerBoundryOfCombinedRangesIsNegOne() {
    	assertEquals("The lower boundry of the resulting range by combining [-1, 1] and [1, 2] is -1",
    	-1, Range.combine(exampleRange, exampleRange2).getLowerBound(), .000000001d);
    }
    
    @Test
    public void upperBoundryOfCombinedRangesIsTwo() {
    	assertEquals("The upper boundry of the resulting range by combining [-1, 1] and [1, 2] is 2",
    	2, Range.combine(exampleRange, exampleRange2).getUpperBound(), .000000001d);
    }
    
    // combineIgnoringNaN
    @Test
    public void combineRangesIgnoringNaNFirstRangeIsNull() {
    	assertEquals("The resulting range by combining null and [1, 2] ignoring NaN should be [1, 2]",
    	exampleRange2, Range.combineIgnoringNaN(null, exampleRange2));
    }
    
    @Test
    public void combineRangesIgnoringNaNFirstRangeIsNullSecondRangeIsNaN() {
    	assertNull("The resulting range by combining null and NaN range ignoring NaN should be null",
    	Range.combineIgnoringNaN(null, exampleRange7));
    }
    
    @Test
    public void combineRangesIgnoringNaNSecondRangeIsNull() {
    	assertEquals("The resulting range by combining [1, 2] and null ignoring NaN should be [1, 2]",
    	exampleRange2, Range.combineIgnoringNaN(exampleRange2, null));
    }
    
    @Test
    public void combineRangesIgnoringNaNFirstRangeIsNaNSecondRangeIsNull() {
    	assertNull("The resulting range by combining NaN range and null ignoring NaN should be null",
    	Range.combineIgnoringNaN(exampleRange7, null));
    }
    
    @Test
    public void combineRangesIgnoringNaNBothRangesAreNaN() {
    	assertNull("The resulting range by combining NaN range and NaN range ignoring NaN should be null",
    	Range.combineIgnoringNaN(exampleRange7, exampleRange7));
    }
    
    @Test
    public void combineRangesIgnoringNaNBothRangesAreNull() {
    	assertNull("The resulting range by combining 2 null ranges ignoring NaN should be null",
    	Range.combineIgnoringNaN(null, null));
    }
    
    @Test
    public void lowerBoundryOfCombinedRangesIgnoringNaNIsNegOne() {
    	assertEquals("The lower boundry of the resulting range by combining [-1, 1] and [1, 2] ignoring NaN is -1",
    	-1, Range.combineIgnoringNaN(exampleRange, exampleRange2).getLowerBound(), .000000001d);
    }
    
    @Test
    public void upperBoundryOfCombinedRangesIgnoringNaNIsTwo() {
    	assertEquals("The upper boundry of the resulting range by combining [-1, 1] and [1, 2] ignoring NaN is 2",
    	2, Range.combineIgnoringNaN(exampleRange, exampleRange2).getUpperBound(), .000000001d);
    }
    
    @Test
    public void lowerBoundaryofCombineRangesIgnoringNaNAndBothLowerBoundariesAreNaN() {
    	assertEquals("The lower boundry of the resulting range by combining [NaN, 1] and [NaN, 1] ignoring NaN should be NaN",
    	Double.NaN, Range.combineIgnoringNaN(exampleRange5, exampleRange5).getLowerBound(), .000000001d);
    }
    
    //new mutant killing test cases
    @Test
    public void upperBoundaryofCombineRangesIgnoringNaNAndBothLowerBoundariesAreNaN() {
    	assertEquals("The lower boundry of the resulting range by combining [NaN, 1] and [NaN, 1] ignoring NaN should be NaN",
    	1, Range.combineIgnoringNaN(exampleRange5, exampleRange5).getUpperBound(), .000000001d);
    }
    
    @Test
    public void upperBoundaryofCombineRangesIgnoringNaNAndBothUpperBoundariesAreNaN() {
    	assertEquals("The upper boundry of the resulting range by combining [-1, NaN] and [-1, NaN] ignoring NaN should be NaN",
    	Double.NaN, Range.combineIgnoringNaN(exampleRange6, exampleRange6).getUpperBound(), .000000001d);
    }
    
    //new mutant killing test cases
    @Test
    public void lowerBoundaryofCombineRangesIgnoringNaNAndBothUpperBoundariesAreNaN() {
    	assertEquals("The upper boundry of the resulting range by combining [-1, NaN] and [-1, NaN] ignoring NaN should be NaN",
    	-1, Range.combineIgnoringNaN(exampleRange6, exampleRange6).getLowerBound(), .000000001d);
    }
    
    //new mutant killing test cases
    @Test
    public void lowerBoundaryofCombineRangesIgnoringNaNWithOneNaNAndOneValue() {
    	assertEquals("The upper boundry of the resulting range by combining [NaN, 1] and [-1, 1] ignoring NaN should be NaN",
    	-1, Range.combineIgnoringNaN(exampleRange, exampleRange5).getLowerBound(), .000000001d);
    }
    
    //new mutant killing test cases
    @Test
    public void upperBoundaryofCombineRangesIgnoringNaNWithOneNaNAndOneValue() {
    	assertEquals("The upper boundry of the resulting range by combining [-1, NaN] and [-1, 1] ignoring NaN should be NaN",
    	1, Range.combineIgnoringNaN(exampleRange, exampleRange6).getUpperBound(), .000000001d);
    }
    
    // equals
    @Test
    public void doubleNotEqualRange() {
    	assertFalse("A Double should not be equal to a Range",
    	exampleRange.equals((Double) 1.5d));
    }
    
    @Test
    public void lowerBoundaryMismatch() {
        assertFalse("The lower boundary of [-1, 1] does not match the range [NaN, 1]",
        exampleRange.equals(exampleRange5));
    }
    
    @Test
    public void upperBoundaryMismatch() {
        assertFalse("The upper boundary of [-1, 1] does not match the range [-1, NaN]",
        exampleRange.equals(exampleRange6));
    }
    
    @Test
    public void bothBoundariesMismatch() {
        assertFalse("The boundaries of [-1, 1] should not match the boundaries of the range [-1000000000, 2000000000]",
        exampleRange.equals(exampleRange4));
    }
    
    @Test
    public void rangeEqualsItself() {
    	assertTrue("The range [-1, 1] should equal the range [-1, 1]",
    	exampleRange.equals(new Range(-1, 1)));
    }
    
    //contain()
    @Test 
    public void containedPositivelValueShouldBeOne() { 
    assertTrue("The contained positive value should be 1", exampleRange8.contains(1)); 
    } 

    @Test 
    public void containedNegativelValueShouldBeNegativeOne() { 
    assertTrue("The contained negative value should be -1", exampleRange8.contains(-1)); 
    } 
    
    //new mutant killing test cases
    @Test 
    public void containedPostiveValueInSmallRange() { 
    	Range rangetemp = new Range (0.5, 1.0);
    	assertTrue("The contained positive value should be 0.75", rangetemp.contains(0.75)); 
    } 
    
    //new mutant killing test cases
    @Test 
    public void containedPostiveValueShouldBePositiveFive() { 
    assertTrue("The contained positive value should be 5", exampleRange8.contains(5)); 
    }

    @Test 
    public void containedALBBlValueShouldBeZero() { 
    assertTrue("The contained ALB value should be 0", exampleRange8.contains(0)); 
    }

    @Test 
    public void containedAUBValue() { 
    assertFalse("6 should not be contained", exampleRange8.contains(6)); 
    }

    @Test 
    public void containedBLBValue() { 
    assertFalse("-2 should not be contained", exampleRange8.contains(-2)); 
    }

    @Test 
    public void containedBUBlValueShouldBeFour() { 
    assertTrue("The contained BUB value should be 4", exampleRange8.contains(4)); 
    } 
    
    //toString
    @Test 
    public void RangetoString() { 
    assertEquals("Range[-5.0,5.0]", exampleRange9.toString()); 
    } 

    @Test 
    public void RangeZerotoString() { 
    assertEquals("Range[0.0,0.0]", exampleRange10.toString()); 
    } 

    @Test 
    public void PositiveRangetoString() { 
    assertEquals("Range[5.0,25.0]", exampleRange11.toString()); 
    } 

    @Test 
    public void NegativeRangetoString() { 
    assertEquals("Range[-25.0,-5.0]", exampleRange12.toString()); 
    }
    
    //constrain()
    @Test 
    public void ContainedValue() { 
    assertEquals(3.0, exampleRange9.constrain(3.0), .000000001d); 
    } 

    @Test 
    public void ValueGreaterThanUpper() { 
    assertEquals(5.0, exampleRange9.constrain(7.0), .000000001d); 
    } 

    @Test 
    public void ValueLessThanLower() { 
    assertEquals(-5.0, exampleRange9.constrain(-7.0), .000000001d); 
    } 

    @Test 
    public void ValueLessThanUpperAndGreaterThanLower() { 
    assertEquals(0.0, exampleRange9.constrain(0.0), .000000001d); 
    }
    
    //new mutant killing test cases
    @Test 
    public void ValueLightlyLessThanUpper() { 
    assertEquals(4.5, exampleRange9.constrain(4.5), .000000001d); 
    }
    //new mutant killing test cases
    @Test 
    public void ValueLightlyGreaterThanLower() { 
    assertEquals(-4.5, exampleRange9.constrain(-4.5), .000000001d); 
    }
    //new mutant killing test cases
    @Test 
    public void ValueLightlyGreaterThanUpper() { 
    assertEquals(5.0, exampleRange9.constrain(5.5), .000000001d); 
    }
    //new mutant killing test cases
    @Test 
    public void ValueLightlyLessThanLower() { 
    assertEquals(-5.0, exampleRange9.constrain(-5.5), .000000001d); 
    } 

    
    //Scale()
  //Test the null value
    @Test(expected = IllegalArgumentException.class)
    public void ScaleNull() {
    	Range result=Range.scale(range,2);
    }
    
    //Test the negative factor
    @Test(expected = IllegalArgumentException.class)
    public void ScaleWithNegativeFactor() {
    	Range result=Range.scale(range1,-2);
    }
    
    //Test the Zero factor
    @Test
    public void ScaleWithZeroFactor() {
    	Range result=Range.scale(range1,0);
    	Range correct=new Range(0,0);
    	assertEquals(correct, result);
    }
    

    //Test the Positive factor
    @Test
    public void ScaleWithPositiveFactor() {
    	Range result=Range.scale(range1,2);
    	Range correct=new Range(-10,10);
    	assertEquals(correct, result);
    }
    
    //Test Expand with positive value
    @Test
    public void ExpandPositiveValue() {
    	Range actual = new Range(-10, 10);
    	actual = Range.expand(actual, 0.5, 0.5);
    	Range expected = new Range (-20 , 20);
    	assertEquals(expected, actual);
    }
    
    //Test Expand with zero value
    @Test
    public void ExpandZeroValue() {
    	Range actual = new Range(-10, 10);
    	actual = Range.expand(actual, 0, 0);
    	Range expected = new Range (-10 , 10);
    	assertEquals(expected, actual);
    }
    
    //Test Expand with negative value
    @Test
    public void ExpandNegativeValue() {
    	Range actual = new Range(-10, 10);
    	actual = Range.expand(actual, -1, -1);
    	Range expected = new Range (0 , 0);
    	assertEquals(expected, actual);
    }
    
    //Test Expand with null value
    @Test(expected = IllegalArgumentException.class)
    public void ExpandNullValue() {
    	Range actual = new Range(-10, 10);
    	actual = Range.expand(null, 0.5, 0.5);
    }
    
    //Test ExpandToInluclude with larger value than upperbound
    @Test
    public void ExpandToIncludeLargerValue() {
    	Range actual = new Range(-10, 10);
    	actual = Range.expandToInclude(actual, 20);
    	Range expected = new Range (-10 , 20);
    	assertEquals(expected, actual);
    }
    
    //Test ExpandToInluclude with middle value between bounds
    @Test
    public void ExpandToIncludeMiddleValue() {
    	Range actual = new Range(-10, 10);
    	actual = Range.expandToInclude(actual, 0);
    	Range expected = new Range (-10 , 10);
    	assertEquals(expected, actual);
    }
    
    //Test ExpandToInluclude with smaller value than lowerbound
    @Test
    public void ExpandToIncludeSmallerValue() {
    	Range actual = new Range(-10, 10);
    	actual = Range.expandToInclude(actual, -20);
    	Range expected = new Range (-20 , 10);
    	assertEquals(expected, actual);
    }
    
    //Test ExpandToInluclude with null range object
    @Test
    public void ExpandToIncludeNull() {
    	Range actual = new Range(-10, 10);
    	actual = Range.expandToInclude(null, 0);
    	Range expected = new Range (0 , 0);
    	assertEquals(expected, actual);
    }
    
    //Test ShiftNoZeroCrossing with small value that unable to cross zero
	@Test
    public void ShiftNoZeroCrossingWithSmallDeltaValue() {
		Range actual = new Range(-10, 10);
    	actual = Range.shift(actual, 5);
    	Range expected = new Range (-5 , 15);
    	assertEquals(expected, actual);
	}
	
    //new mutant killing test cases
    //Test ShiftNoZeroCrossing with close zero value that unable to cross zero
	@Test
    public void ShiftNoZeroCrossingWithCloseUpperZeroValue() {
		Range actual = new Range(0.5, 10);
    	actual = Range.shift(actual, -5);
    	Range expected = new Range (0 , 5);
    	assertEquals(expected, actual);
	}
	
    //new mutant killing test cases
    //Test ShiftNoZeroCrossing with close zero value that unable to cross zero
	@Test
    public void ShiftNoZeroCrossingWithCloseLowerZeroValue() {
		Range actual = new Range(-0.5, 10);
    	actual = Range.shift(actual, 5);
    	Range expected = new Range (0 , 15);
    	assertEquals(expected, actual);
	}
	
	//Test ShiftNoZeroCrossing with large value that able to cross zero
	@Test
    public void ShiftNoZeroCrossingWithLargeDeltaValue() {
		Range actual = new Range(-10, 10);
    	actual = Range.shift(actual, 15);
    	Range expected = new Range (0 , 25);
    	assertEquals(expected, actual);
	}
	
	//Test ShiftNoZeroCrossing (range with 0 initially) with large value that able to cross zero
	@Test
    public void ShiftZeroNoZeroCrossingWithLargeDeltaValue() {
		Range actual = new Range(0,10);
    	actual = Range.shift(actual, 15);
    	Range expected = new Range (15 , 25);
    	assertEquals(expected, actual);
	}
	
	//Test ShiftZeroCrossing with small value that unable to cross zero
	@Test
    public void ShiftWithZeroCrossingWithSmallDeltaValue() {
		Range actual = new Range(-10, 10);
    	actual = Range.shift(actual, 5, true);
    	Range expected = new Range (-5 , 15);
    	assertEquals(expected, actual);
	}
	
	//Test ShiftZeroCrossing with large value that able to cross zero
	@Test
    public void ShiftWithZeroCrossingWithLargeDeltaValue() {
		Range actual = new Range(-10, 10);
    	actual = Range.shift(actual, 15, true);
    	Range expected = new Range (5 , 25);
    	assertEquals(expected, actual);
	}
	
    //new mutant killing test cases
    //Test the null factor
    @Test(expected = IllegalArgumentException.class)
    public void ShiftWithZeroCrossingWithRangeNull() {
    	Range.shift(null, 15, true);
    }
	
	private Range exampleRangeintersectsDouble;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	
	@Before
	public void intersectsDoublesetUp() throws Exception {
		exampleRangeintersectsDouble = new Range(1.1, 5.5);
	}
	
	
	@Test
	public void LowerBoundNotCrossingTest() {
		assertFalse("The two ranges do not cross", exampleRangeintersectsDouble.intersects(0.0, 1.0));
	}
	
	
	@Test
	public void UpperBoundNotCrossingTest() {
		assertFalse("The two ranges do not cross", exampleRangeintersectsDouble.intersects(11.11, 15.15));
	}
	
	
	@Test
	public void LowerBoundCrossingTest() {
		assertTrue("The two ranges cross", exampleRangeintersectsDouble.intersects(0.0, 2.2));
	}
	
	
	@Test
	public void UpperBoundCrossingTest() {
		assertTrue("The two ranges cross", exampleRangeintersectsDouble.intersects(3.3, 12.12));
	}
	
	
	@Test
	public void AllRangeCrossingTest() {
		assertTrue("The two ranges cross", exampleRangeintersectsDouble.intersects(1.1, 5.5));
	}
	
	
	@Test
	public void RangeCrossingInBetweenTest() {
		assertTrue("The two ranges cross", exampleRangeintersectsDouble.intersects(3.3, 4.4));
	}
	
    //new mutant killing test cases
	@Test
	public void RangeUpperBoundTest() {
		assertTrue("The two ranges cross", exampleRangeintersectsDouble.intersects(5.4, 6.3));
	}
    //new mutant killing test cases
	@Test
	public void RangeLowerBoundTest() {
		assertTrue("The two ranges cross", exampleRangeintersectsDouble.intersects(1.1, 1.2));
	}
	
	
	
	private Range exampleRangeintersectsRange;
	
	
	
	@Before
	public void intersectsRangesetUp() throws Exception {
		exampleRangeintersectsRange = new Range(1.1, 5.5);
	}
	
	
	@Test
	public void lowerBoundNotCrossingTest() {
		Range inputRange = new Range(0.0, 1.0);
		assertFalse("The two ranges cross", exampleRangeintersectsRange.intersects(inputRange));
	}
	
	
	@Test
	public void upperBoundNotCrossingTest() {
		Range inputRange = new Range(11.11, 15.15);
		assertFalse("The two ranges cross", exampleRangeintersectsRange.intersects(inputRange));
	}
	
	
	@Test
	public void lowerBoundCrossingTest() {
		Range inputRange = new Range(0.0, 2.2);
		assertTrue("The two ranges do not cross", exampleRangeintersectsRange.intersects(inputRange));
	}
	
	
	@Test
	public void upperBoundCrossingTest() {
		Range inputRange = new Range(3.3, 12.12);
		assertTrue("The two ranges do not cross", exampleRangeintersectsRange.intersects(inputRange));
	}
	
	
	@Test
	public void allRangeCrossingTest() {
		Range inputRange = new Range(1.1, 5.5);
		assertTrue("The two ranges do not cross", exampleRangeintersectsRange.intersects(inputRange));
	}
	
	
	@Test
	public void rangeCrossingInBetweenTest() {
		Range inputRange = new Range(3.3, 4.4);
		assertTrue("The two ranges do not cross", exampleRangeintersectsRange.intersects(inputRange));
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	private Range range11, range22;
	
	
	@Test
	public void hashcodeTwoSameRangesTest() {
		range11 = new Range(1, 10);
		range22 = new Range(1, 10);
		assertEquals(range11.hashCode(), range22.hashCode());
	}
	
    //new mutant killing test cases
	@Test
	public void hashcodePostiveBoundTest() {
		range11 = new Range(1, 10);
		assertEquals(2119434240, range11.hashCode());
	}
	
	
	@Test
	public void hashcodeTwoDifferRangestest() {
		range11 = new Range(1, 10);
		range22 = new Range(5, 15);
		assertFalse(range11.hashCode() == range22.hashCode());
	}
	
	@Test
	public void getCentralValuePositiveBoundTest() {
		assertEquals("Central value should be 6.000", 6.000,  new Range(1, 11).getCentralValue(), .000000001d);
	}
	
	
	@Test
	public void getCentralValueNegativeBoundTest() {
		assertEquals("Central value should be -6.000", -6.000, new Range(-11, -1).getCentralValue(), .000000001d);
	}
	
	
	@Test
	public void getCentralValueMixedBoundTest() {
		assertEquals("Central value should be 4.000", 4.000, new Range(-1, 9).getCentralValue(), .000000001d);
	}
	
	
	@Test
	public void getCentralValueSameBoundTest() {
		assertEquals("Central value should be 1.000", 1.000, new Range(1, 1).getCentralValue(), .000000001d);
	}
    
}
