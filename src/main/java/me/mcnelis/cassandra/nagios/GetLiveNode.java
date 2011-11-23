package me.mcnelis.cassandra.nagios;
import java.util.HashMap;
import java.util.Map;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

/*
  useage: java GetLiveNode [hostname]
*/

public class GetLiveNode
{

    public static void main(String[] args) throws Exception
    {
        String user = "cassandra";
        String pass = "";
        String JMXURL = "service:jmx:rmi:///jndi/rmi://" + args[0] + ":8080/jmxrmi";
        Map hm = new HashMap();
        hm.put(JMXConnector.CREDENTIALS, new String[]{user, pass});
        JMXConnector connector = JMXConnectorFactory.connect(new JMXServiceURL(JMXURL), hm);
        MBeanServerConnection connection = connector.getMBeanServerConnection();

        ObjectName on = new ObjectName("org.apache.cassandra.service:type=StorageService");
        System.out.println(connection.getAttribute(on, "LiveNodes"));
        connector.close();
    }

}

