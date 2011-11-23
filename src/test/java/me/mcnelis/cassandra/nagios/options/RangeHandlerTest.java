package me.mcnelis.cassandra.nagios.options;

import static org.junit.Assert.*;

import org.junit.Test;

public class RangeHandlerTest {

	@Test
	public void testInRangeDouble() {
		RangeHandler r = new RangeHandler();
		r.setRangeDefinition("10:20");
		assertTrue(r.inRange(12d));
	}

	@Test
	public void testInRangeInt() {
		RangeHandler r = new RangeHandler();
		r.setRangeDefinition("10:20");
		assertTrue(r.inRange(12));
	}

	@Test
	public void testInZeroToLimit() {
		RangeHandler r = new RangeHandler();
		r.upperBound = 20;
		assertTrue(r.inZeroToLimit(19d));
	}
	
	@Test
	public void testNotInZeroToLimit() {
		RangeHandler r = new RangeHandler();
		r.upperBound = 20;
		assertFalse(r.inZeroToLimit(22d));
		assertFalse(r.inZeroToLimit(-1d));
	}


	@Test
	public void testInLimitToInfinity() {
		RangeHandler r = new RangeHandler();
		r.lowerBound = 500;
		assertTrue(r.inLimitToInfinity(1200d));
		assertTrue(r.inLimitToInfinity(Double.POSITIVE_INFINITY));
	}
	
	@Test
	public void testNotInLimitToInfinity() {
		RangeHandler r = new RangeHandler();
		r.lowerBound = 500;
		assertFalse(r.inLimitToInfinity(499d));
	}

	@Test
	public void testInNegativeIninityToLimit() {
		RangeHandler r = new RangeHandler();
		r.upperBound = 20;
		assertTrue(r.inNegativeIninityToLimit(Double.NEGATIVE_INFINITY));
		assertTrue(r.inNegativeIninityToLimit(2d));
	}

	@Test
	public void testNotInNegativeIninityToLimit() {
		RangeHandler r = new RangeHandler();
		r.upperBound = 20;
		assertFalse(r.inNegativeIninityToLimit(22d));
	}
	
	@Test
	public void testInExclusiveBetween() {
		RangeHandler r = new RangeHandler();
		r.upperBound = 20;
		r.lowerBound = 10;
		assertTrue(r.inExclusiveBetween(11d));
	}
	
	@Test
	public void testNotInExclusiveBetween() {
		RangeHandler r = new RangeHandler();
		r.upperBound = 20;
		r.lowerBound = 10;
		assertFalse(r.inExclusiveBetween(10d));
		assertFalse(r.inExclusiveBetween(20d));
		assertFalse(r.inExclusiveBetween(4d));
		assertFalse(r.inExclusiveBetween(21d));
	}

	@Test
	public void testInInclusiveBetween() {
		RangeHandler r = new RangeHandler();
		r.upperBound = 20;
		r.lowerBound = 10;
		
		assertTrue(r.inInclusiveBetween(11d));
		
		assertTrue(r.inInclusiveBetween(20d));
		
		assertTrue(r.inInclusiveBetween(10d));
	}

	@Test
	public void testNotInclusiveBetween() {
		RangeHandler r = new RangeHandler();
		r.upperBound = 20;
		r.lowerBound = 10;
		assertFalse(r.inInclusiveBetween(4d));
		assertFalse(r.inInclusiveBetween(21d));
	}
	
	@Test
	public void testGetRangeTypeZeroLimit() {
		RangeHandler r = new RangeHandler();
		r.setRangeDefinition("10");
		assertEquals(RangeTypes.ZEROTOLIMIT, r.getRangeType());
	}
	
	@Test
	public void testGetRangeTypeInfLimit() {
		RangeHandler r = new RangeHandler();
		r.setRangeDefinition("~:10");
		assertEquals(RangeTypes.NEGINFTOLIMIT, r.getRangeType());
	}
	
	@Test
	public void testGetRangeTypeLimitInf() {
		RangeHandler r = new RangeHandler();
		r.setRangeDefinition("10:~");
		assertEquals(RangeTypes.LIMITTOINFINITY, r.getRangeType());
	}
	
	@Test
	public void testGetRangeTypeInclusive() {
		RangeHandler r = new RangeHandler();
		r.setRangeDefinition("@10:20");
		assertEquals(RangeTypes.INCLUSIVEBETWEEN, r.getRangeType());
	}
	
	@Test
	public void testGetRangeTypeExclusive() {
		RangeHandler r = new RangeHandler();
		r.setRangeDefinition("10:20");
		assertEquals(RangeTypes.EXCLUSIVEBETWEEN, r.getRangeType());
	}

}
