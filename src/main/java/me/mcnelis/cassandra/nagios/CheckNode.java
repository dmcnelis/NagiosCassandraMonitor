package me.mcnelis.cassandra.nagios;

import me.mcnelis.cassandra.nagios.processor.ProcessTask;

/*
  useage: java CheckNode [hostname or IP]
*/

public class CheckNode
{

    public static void main(String[] args) throws Exception {
    	
    	if(args.length<2) {
    		System.out.println("Critical - Invalid argument list provided");
    	}
    	
    	ProcessTask t = new ProcessTask(args);
    	System.out.println(t.process());
    	
    }
}

