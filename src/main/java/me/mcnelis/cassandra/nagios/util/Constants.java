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
	
	public static Object get(String property) throws IllegalArgumentException, SecurityException, IllegalAccessException, NoSuchFieldException
	{
		return Constants.class.getDeclaredField(property).get(String.class);
	}
	
}
