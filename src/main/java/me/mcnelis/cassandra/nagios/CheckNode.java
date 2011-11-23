package me.mcnelis.cassandra.nagios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import me.mcnelis.cassandra.nagios.util.Constants;

/*
  useage: java CheckNode [hostname or IP]
*/

public class CheckNode
{

    public static void main(String[] args) throws Exception {
    	
    	if(args.length<2) {
    		System.out.println("Critical - Invalid argument list provided");
    	}
    	
    	
    	
    }
}

