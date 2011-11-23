package me.mcnelis.cassandra.nagios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

public class StatusCheckerTest {

	StatusChecker s = new StatusChecker("192.168.80.146");
	
	@Test
	public void testGetNumberOfLiveNodes() {
		assertEquals(4,s.getNumberOfLiveNodes());
	}

	@Test
	public void testGetIsAlive() {
		assertTrue(s.getIsAlive());
	}

	@Test
	public void testGetStatus() {
		assertNotNull(s.getStatus());
	}

	@Test
	public void testGetToken() {
		assertNotNull(s.getToken());
	}

	@Test
	public void testGetReachableNodes() {
		assertTrue(s.getReachableNodes() instanceof ArrayList);
	}

	@Test
	public void testGetUnreachableNodes() {
		assertTrue(s.getUnreachableNodes() instanceof ArrayList);
	}

	@Test
	public void testGetNumberOfUnreachableNodes() {
		assertEquals(0, s.getNumberOfUnreachableNodes());
	}

	@Test
	public void testGetRecentReadLatency() {
		assertTrue(s.getRecentReadLatency() > 0d || Double.isNaN(s.getRecentReadLatency()));
	}

	@Test
	public void testGetRecentWriteLatency() {
		assertTrue(s.getRecentWriteLatency() > 0d);
	}

}
