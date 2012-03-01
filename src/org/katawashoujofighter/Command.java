package org.katawashoujofighter;

public class Command {

	public static final char IDLE 		= 0;
	public static final char RIGHT 		= 1 << 0;
	public static final char DOWN 		= 1 << 1;	
	public static final char LEFT 		= 1 << 2;		
	public static final char UP 		= 1 << 3;
	public static final char LOWWEAK 	= 1 << 4;
	public static final char LOWMEDIUM 	= 1 << 5;
	public static final char LOWSTRONG 	= 1 << 6;
	public static final char LOWBLOCK 	= 1 << 7;
	public static final char HIGHWEAK 	= 1 << 8;
	public static final char HIGHMEDIUM = 1 << 9;
	public static final char HIGHSTRONG = 1 << 10;
	public static final char HIGHBLOCK  = 1 << 11;
	public static final char NONE 		= 1 << 12;
	
	public static boolean is(char value, char command) {
		return ((value & command) > 0 );		
	}
	
	
	public static boolean isMotion(char command) {
		return 	   is(command, RIGHT) 
				|| is(command, LEFT) 
				|| is(command, UP)
				|| is(command, DOWN)
				;
	}
	
	
	
	

}
