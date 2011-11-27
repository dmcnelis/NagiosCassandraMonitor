package me.mcnelis.cassandra.nagios.options;

import org.apache.log4j.Logger;

public class RangeHandler {
	
	private static Logger log = Logger.getLogger(RangeHandler.class);
	
	protected String rangeDefinition;
	protected RangeTypes rangeType;
	protected double lowerBound;
	protected double upperBound;
	
	public boolean inRange(double d) {
		
		this.getRangeType();
		log.debug(this.rangeType.toString());
		switch (this.rangeType) {
		case ZEROTOLIMIT:
			return this.inZeroToLimit(d);

		case NEGINFTOLIMIT:
			return this.inNegativeIninityToLimit(d);
		
		case LIMITTOINFINITY:
			return this.inLimitToInfinity(d);
		
		case INCLUSIVEBETWEEN:
			return this.inInclusiveBetween(d);
			
		case EXCLUSIVEBETWEEN:
			return this.inExclusiveBetween(d);
		default:
			return false;
		}
	}
	
	public boolean inRange(int i) {
		return this.inRange(Double.parseDouble(Integer.toString(i)));
	}
	
	protected boolean inZeroToLimit(Double d) {
		if(d >= 0d && d <= this.upperBound)
			return true;
		else
			return false;
	}
	
	protected boolean inLimitToInfinity(Double d) {
		if(d >= this.lowerBound)
			return true;
		else
			return false;
	}
	
	protected boolean inNegativeIninityToLimit(Double d) {
		if(this.upperBound >= d)
			return true;
		else
			return false;
	}
	
	protected boolean inExclusiveBetween(Double  d) {
		if(this.lowerBound < d && this.upperBound > d)
			return true;
		else
			return false;
	}
	
	protected boolean inInclusiveBetween(Double d) {
		
		double epsilon = .000001;
		if(
			((this.lowerBound <= d - epsilon) || (this.lowerBound <= d + epsilon))
			&&
			((this.upperBound >= d - epsilon) || (this.upperBound >= d + epsilon))
		)
			return true;
		else 
			return false;
		
	}
	
	/**
	 * Range definition formats
	 *	10	  |  RangeType.ZEROTOLIMIT
	 *	10:	  |  RangeType.LIMITTOINFINITY
	 *	~:10  |	 RangeType.NEGINFTOLIMIT
	 *	10:20 |  RangeType.EXCLUSIVEBETWEEN
	 *	@10:20|	 RangeType.INCLUSIVEBETWEEN
	 *
	 * Non-range tests are currently set to be inclusive.
	 * 
	 * Can change this later, but right now that makes the most sense to me.
	 *
	 *  Defined at: http://nagiosplug.sourceforge.net/developer-guidelines.html#AEN33
	 */
	
	public RangeTypes getRangeType() {
		int colonIdx = this.rangeDefinition.indexOf(':');
		if(colonIdx == -1) {
			this.rangeType = RangeTypes.ZEROTOLIMIT;
			this.upperBound = Double.parseDouble(this.rangeDefinition);
		} else {
			String[] vals = this.rangeDefinition.split(":");
			if (vals.length > 1) {
				
				this.rangeType = RangeTypes.EXCLUSIVEBETWEEN;
				
				if(vals[0].indexOf('@') != -1){					
					this.rangeType = RangeTypes.INCLUSIVEBETWEEN;
					this.lowerBound = Double.parseDouble(vals[0].substring(1));
				} else if(vals[0].equalsIgnoreCase("~")) {
					this.lowerBound = Double.NEGATIVE_INFINITY;
					this.rangeType = RangeTypes.NEGINFTOLIMIT;
				} else {
					this.lowerBound = Double.parseDouble(vals[0]);
				}
				
				if(vals[1].equalsIgnoreCase("~")) {
					this.upperBound = Double.POSITIVE_INFINITY;
					this.rangeType = RangeTypes.LIMITTOINFINITY;
				} else {
					this.upperBound = Double.parseDouble(vals[1]);
				}
					
			} else {
				this.rangeType = RangeTypes.LIMITTOINFINITY;
				this.lowerBound = Double.parseDouble(vals[0]);
			}
		}
		
		return this.rangeType;
	}
	
	public String getRangeDefinition() {
		return rangeDefinition;
	}

	public void setRangeDefinition(String rangeDefinition) {
		this.rangeDefinition = rangeDefinition;
	}

	public double getUpperBound() {
		return this.upperBound;
	}
	
	public double getLowerBound() {
		return this.lowerBound;
	}
}
