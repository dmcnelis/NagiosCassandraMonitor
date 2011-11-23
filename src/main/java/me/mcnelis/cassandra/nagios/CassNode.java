package me.mcnelis.cassandra.nagios;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.apache.log4j.Logger;

import me.mcnelis.cassandra.nagios.interfaces.CassNodeInterface;
import me.mcnelis.cassandra.nagios.util.Constants;

public class CassNode implements CassNodeInterface {

	private static Logger log = Logger.getLogger(CassNode.class);
	
	protected String hostname;
	protected String port = Constants.JMX_PORT;
	protected String username = Constants.JMX_PORT;
	protected String password = Constants.JMX_PORT;
	
	protected JMXConnector connector;
	protected MBeanServerConnection connection;
	
	public CassNode() { }
	
	public CassNode(String hostname) {
		this.hostname = hostname;
	}
	
	public MBeanServerConnection getConnection() {
		return connection;
	}

	protected ObjectName jmxObject;
	
	@Override
	public boolean createConnection() {
	
		boolean success = true;
		StringBuffer JMXURL = new StringBuffer();
		JMXURL.append("service:jmx:rmi:///jndi/rmi://")
				.append(this.hostname)
				.append(":")
				.append(this.port)
				.append("/jmxrmi");
		
		Map<String,String[]> hm = new HashMap<String,String[]>();
        hm.put(JMXConnector.CREDENTIALS, new String[]{this.username, this.password});
        
        try {
			this.connector = JMXConnectorFactory.connect(new JMXServiceURL(JMXURL.toString()), hm);
			this.connection = this.connector.getMBeanServerConnection();
		} catch (MalformedURLException e) {
			log.error("Malformed URL: " + JMXURL.toString());
			success = false;
		} catch (IOException e) {
			log.error("IOException" + e.getMessage());
			success = false;
		}    	
		
		return success;
	}

	@Override
	public boolean closeConnection() {
		try {
			this.connector.close();
			return true;
		} catch (IOException e) {
			log.error("Unable to close the connection");
			return false;
		}
	}

	@Override
	public boolean setObject(String object) {
		try {
			this.jmxObject = new ObjectName(object);
			return true;
		} catch (MalformedObjectNameException e) {
			log.error("Unable to load object, malformed URL");
			return false;
		} catch (NullPointerException e) {
			log.error("Object was null");
			return false;
		}
	}

	@Override
	public Object getAttribute(String attrName) {
		try {
			Object attribute = (Object) this.connection.getAttribute(this.jmxObject, attrName);
			return attribute;
		} catch (AttributeNotFoundException e) {
			log.error("This attribute was note found: " + attrName);
		} catch (InstanceNotFoundException e) {
			log.error("The object was not found: " + this.jmxObject);
		} catch (MBeanException e) {
			log.error(e.getCause());
		} catch (ReflectionException e) {
			log.error(e.getCause());
		} catch (IOException e) {
			log.error(e.getCause());
		}
		return null;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
