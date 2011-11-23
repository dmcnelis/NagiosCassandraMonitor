package me.mcnelis.cassandra.nagios.interfaces;

import javax.management.InstanceNotFoundException;
import javax.management.MBeanServerConnection;

/**
 * 
 * @author David McNelis
 *
 */
public interface CassNodeInterface {
	/**
	 * Create a JMX Connection to a particular node
	 * on the cassandra cluster
	 * 
	 * @return success or failure of creating connection
	 */
	public boolean createConnection();
	
	/**
	 * Close the JMX Connection to a particular node
	 * 
	 * @return success or failure on closing a connection
	 */
	public boolean closeConnection();
	
	/** 
	 * Need to specify a specfic object that we are interacting
	 * with at a particular moment in time
	 * 
	 * @return success or failure in setting the object.
	 * 
	 * @throws 
	 */
	public boolean setObject(String object);
	
	
	public Object getAttribute(String attrName);
	
	public String getHostname();

	public void setHostname(String hostname);

	public String getPort();

	public void setPort(String port);

	public String getUsername();

	public void setUsername(String username);

	public String getPassword();

	public void setPassword(String password);

	public MBeanServerConnection getConnection();
	
}
