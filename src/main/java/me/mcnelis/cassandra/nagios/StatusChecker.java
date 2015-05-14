package me.mcnelis.cassandra.nagios;

import java.util.ArrayList;
import java.util.List;

import javax.management.openmbean.CompositeData;

import org.apache.log4j.Logger;


import me.mcnelis.cassandra.nagios.interfaces.CassNodeInterface;

public class StatusChecker {
	private static Logger log = Logger.getLogger(StatusChecker.class);
	protected CassNodeInterface node;
	
	public StatusChecker(String hostName) {
		this.node = new CassNode(hostName);
		this.node.createConnection();
	}
	
	public StatusChecker(CassNodeInterface node) {
		this.node = node;
	}

	public long getHeapUsage() {
		this.node.setObject(CassandraObjects.JVM_MEM.toString());
		CompositeData memData = (CompositeData) this.node.getAttribute("HeapMemoryUsage");
		double used = (Long) memData.get("used");
		double max = (Long) memData.get("max");

		return Math.round(100.0 * used / max);
	}
	
	public int getNumberOfLiveNodes() {
		int size = this.getReachableNodes().size();
		log.debug("Live nodes: " + size);
		return size;
	}
	
	public boolean getIsAlive() {
		return true;
	}
	
	public String getStatus() {
		this.node.setObject(CassandraObjects.STORAGE_SERVICE.toString());
		String status = null;
		status = (String) this.node.getAttribute("OperationMode");
		log.debug("Operational mode: " + status);
		return status;
	}
	
	public String getToken() {
		this.node.setObject(CassandraObjects.STORAGE_SERVICE.toString());
		String token = null;
		token = (String) this.node.getAttribute("Token");
		log.debug("Token: " + token);
		return token;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getReachableNodes() {
		this.node.setObject(CassandraObjects.STORAGE_SERVICE.toString());
		ArrayList<String> nodes = null;
		nodes = (ArrayList<String>)this.node.getAttribute("LiveNodes");
		log.debug("Reachable nodes: " + nodes.toString());
		return nodes;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getUnreachableNodes() {
		this.node.setObject(CassandraObjects.STORAGE_SERVICE.toString());
		ArrayList<String> nodes = null;
		nodes = (ArrayList<String>)this.node.getAttribute("UnreachableNodes");
		log.debug("Unreachable nodes: " + nodes.toString());
		return nodes;
	}
	
	public int getNumberOfUnreachableNodes() {
		int size = this.getUnreachableNodes().size();
		log.debug("Live nodes: " + size);
		return size;
	}
	
	public Double getRecentReadLatency() {
		this.node.setObject(CassandraObjects.STORAGE_PROXY.toString());
		Double latency = null;
		latency = (Double) this.node.getAttribute("RecentReadLatencyMicros");
		log.debug("Read latency: " + latency);
		return latency;
	}
	
	public Double getRecentWriteLatency() {
		this.node.setObject(CassandraObjects.STORAGE_PROXY.toString());
		Double latency = null;
		latency = (Double) this.node.getAttribute("RecentWriteLatencyMicros");
		log.debug("Write latency: " + latency);
		return latency;
	}

	@Override
	protected void finalize() throws Throwable {
		if(this.node.getConnection() != null)
			this.node.closeConnection();
		super.finalize();
	}
	
}
