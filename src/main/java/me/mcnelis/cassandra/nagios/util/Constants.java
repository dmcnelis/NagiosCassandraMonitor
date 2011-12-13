package me.mcnelis.cassandra.nagios.util;

import java.util.ResourceBundle;

public class Constants {
	//private static final String BUNDLE_NAME = "me.mcnelis.cassandra.nagios.util.constants"; //$NON-NLS-1$

	private static final String BUNDLE_NAME = "nagios"; //$NON-NLS-1$
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	public static String JMX_USERNAME = RESOURCE_BUNDLE.getString("jmx.username"); 
	public static String JMX_PASSWORD = RESOURCE_BUNDLE.getString("jmx.password");
	public static String JMX_PORT = RESOURCE_BUNDLE.getString("jmx.port");
	public static String STORAGE_SERVICE = RESOURCE_BUNDLE.getString("cassandra.storage.service");
	public static String STORAGE_PROXY = RESOURCE_BUNDLE.getString("cassandra.storage.proxy");
	public static String COMPACTION_MGR = RESOURCE_BUNDLE.getString("cassandra.compaction.manager");
	public static String STREAMING = RESOURCE_BUNDLE.getString("cassandra.streaming");
	public static String COMMIT_LOG = RESOURCE_BUNDLE.getString("cassandra.commit.log");
	
	public static Object get(String property) throws IllegalArgumentException, SecurityException, IllegalAccessException, NoSuchFieldException
	{
		return Constants.class.getDeclaredField(property).get(String.class);
	}
	
}
