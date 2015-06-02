package me.mcnelis.cassandra.nagios.processor;

import me.mcnelis.cassandra.nagios.StatusChecker;
import me.mcnelis.cassandra.nagios.options.RangeHandler;

public class ProcessTask {
	protected StatusChecker checker;
	protected String checkType;
	protected RangeHandler warnThreshold = new RangeHandler();
	protected RangeHandler criticalThreshold = new RangeHandler();

	public ProcessTask(String...args) {
		this.checker = new StatusChecker(args[0]);
		this.checkType = args[1];
		if(args.length >= 3)
			this.initWarn(args[3]);
		if(args.length >= 4)
			this.initCritical(args[2]);
	}

	public String process() {
		StringBuffer output = new StringBuffer("");
		Object result = this.getResult();
		if (result instanceof Integer) {
			int checkNumber =(Integer) result;
			if(!this.warnThreshold.inRange(checkNumber)) {
				if(!this.criticalThreshold.inRange(checkNumber)) {
					output.append("Critical - ");
				} else {
					output.append("Warn - ");
				}
			} else {
				output.append("OK - ");
			}
		} else {
			output.append("OK - ");
		}
		output.append(this.checkType)
			.append("=")
			.append(result);
		
		return output.toString();
	}
	
	
	/**
	 * this is a crappy way to do this.  Need to 
	 * deal with this in a more elegant way
	 * @return
	 */
	protected Object getResult() {
		if(this.checkType.equalsIgnoreCase("numnodes"))
			return this.checker.getNumberOfLiveNodes();
		else if(this.checkType.equalsIgnoreCase("availableNodes"))
			return this.checker.getReachableNodes();
		else if(this.checkType.equalsIgnoreCase("deadNodes"))
			return this.checker.getNumberOfUnreachableNodes();
		else if(this.checkType.equalsIgnoreCase("deadNodeList"))
			return this.checker.getUnreachableNodes();
		else if(this.checkType.equalsIgnoreCase("status"))
			return this.checker.getStatus();
		else if(this.checkType.equalsIgnoreCase("token"))
			return this.checker.getToken();
		else if(this.checkType.equalsIgnoreCase("readlatency"))
			return this.checker.getRecentReadLatency();
		else if(this.checkType.equalsIgnoreCase("writelatency"))
			return this.checker.getRecentWriteLatency();
		else if(this.checkType.equalsIgnoreCase("heapUsage"))
			return this.checker.getHeapUsage();
		
		return null;
	}
	
	/**
	 * Warnings are -wARG
	 * @param command line argument for warnings
	 */
	protected void initCritical(String string) {
		this.warnThreshold.setRangeDefinition(string.substring(2));
		
	}

	/**
	 * Critical errors are -cARG
	 * @param command line argument for critical warnings
	 */
	protected void initWarn(String string) {
		this.criticalThreshold.setRangeDefinition(string.substring(2));
		
	}
}
