package me.mcnelis.cassandra.nagios;

import static org.junit.Assert.*;

import org.junit.Test;

public class CassNodeTest {

	
	@Test
	public void testCreateConnection() {
		CassNode c = new CassNode("192.168.80.146");
		assertTrue(c.createConnection());
	}

	@Test
	public void testCloseConnection() {
		CassNode c = new CassNode("192.168.80.146");
		c.createConnection();
		assertTrue(c.closeConnection());
	}
	
	@Test
	public void testSetObject() {
		CassNode c = new CassNode("192.168.80.146");
		c.createConnection();
		assertTrue(c.setObject(CassandraObjects.COMMIT_LOG.toString()));
	}

}
