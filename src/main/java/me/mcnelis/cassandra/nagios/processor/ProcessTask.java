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
			this.initWarn(args[2]);
		if(args.length >= 4)
			this.initCritical(args[3]);
	}

	public String process() {
		StringBuffer output = new StringBuffer("");
		int checkNumber = this.checker.getNumberOfLiveNodes();
		if(this.warnThreshold.inRange(checkNumber)) {
			if(!this.criticalThreshold.inRange(checkNumber)) {
				output.append("Critical - ");
			} else {
				output.append("Warn - ");
			}
		} else {
			output.append("OK - ");
		}
		output.append(this.checkType)
			.append("=")
			.append(checkNumber);
		
		return output.toString();
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
