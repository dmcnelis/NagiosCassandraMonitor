package me.mcnelis.cassandra.nagios.processor;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProcessTaskTest {

	@Test
	public void testWarnProcess() {
		ProcessTask t = new ProcessTask("192.168.80.146", "numNodes", "-w@4:5", "-c0:2");
		assertEquals("Warn - numNodes=4", t.process());
	}
	
	@Test
	public void testCriticalProcess() {
		ProcessTask t = new ProcessTask("192.168.80.146", "numNodes", "-w5:", "-c4:");
		assertEquals("Warn - numNodes=4", t.process());
	}

	@Test
	public void testInitCritical() {
		fail("Not yet implemented");
	}

	@Test
	public void testInitWarn() {
		fail("Not yet implemented");
	}

}
