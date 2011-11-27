package me.mcnelis.cassandra.nagios.processor;

import static org.junit.Assert.*;

import me.mcnelis.cassandra.nagios.options.RangeTypes;

import org.junit.Test;

public class ProcessTaskTest {

	@Test
	public void testWarnProcess() {
		ProcessTask t = new ProcessTask("192.168.80.146", "numNodes", "-w6:", "-c5:");
		assertEquals("Critical - numNodes=4", t.process());
	}
	
	@Test
	public void testCriticalProcess() {
		ProcessTask t = new ProcessTask("192.168.80.146", "numNodes", "-w5:", "-c3:");
		assertEquals("Warn - numNodes=4", t.process());
	}
	
	@Test
	public void testOkProcess() {
		ProcessTask t = new ProcessTask("192.168.80.146", "numNodes", "-w4:", "-c3:");
		assertEquals("OK - numNodes=4", t.process());
	}

	@Test
	public void testInitCritical() {
		ProcessTask t = new ProcessTask("192.168.80.146", "numNodes", "-w5", "-c4");
		assertEquals(RangeTypes.ZEROTOLIMIT, t.criticalThreshold.getRangeType());
		assertEquals(4,t.criticalThreshold.getUpperBound(),.9);
	}

	@Test
	public void testInitWarn() {
		ProcessTask t = new ProcessTask("192.168.80.146", "numNodes", "-w5", "-c4");
		assertEquals(RangeTypes.ZEROTOLIMIT, t.warnThreshold.getRangeType());
		assertEquals(5,t.warnThreshold.getUpperBound(),.9);
	}

}
