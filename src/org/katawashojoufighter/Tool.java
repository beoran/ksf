package org.katawashojoufighter;

/** Class with static tools such as limit, etc */
public class Tool {
	/** Limits value to max at most. */
	public static int limit(int value, int max) {
		if(value > max) return max;
		return value;
	}
	
	/** Clamps value between min and max. */
	public static long clamp(long value, long min, long max) {
		if(value > max) return max;
		if(value < min) return min;
		return value;
	}
	
	/** Clamps value between min and max. */
	public static int clamp(int value, int min, int max) {
		if(value > max) return max;
		if(value < min) return min;
		return value;
	}		
	
	
	/** If value is greater than max, return 0 instead.  Otherwise returns value*/
	public static int wraplimit(int value, int max) {
		if(value > max) return 0;
		return value;
	}
	
	

}
