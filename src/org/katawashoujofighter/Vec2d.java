package org.katawashoujofighter;

public class Vec2d {
	double _x; 
	double _y;
	
	/** Gets y coordinate. */
	public double x() { return _x; }
	/** Gets x coordinate. */
	public double y() { return _y; }
	/** Gets y coordinate rounded to int. */
	public int xi() { return (int) Math.round(_x); }
	/** Gets x coordinate rounded to int. */
	public int yi() { return (int) Math.round(_y); }	
	
	/** Constructs a gravity vector. */
	public Vec2d(double x, double y) {	_x = x; _y = y; 		 }
	/** Copy constructor. */
	public Vec2d(Vec2d vec) 		  {	this(vec.x(), vec.y());  }
	
	/** Constructs a vector. */
	public static Vec2d vec2d(double x, double y) { return new Vec2d(x, y);}
	/** Constructs a gravity vector. */
	public static Vec2d g(double y) 			  { return new Vec2d(0.0, y);}
	
	public Vec2d add(Vec2d vec) { return vec2d(x() + vec.x(), y() + vec.y()); }
	public Vec2d sub(Vec2d vec) { return vec2d(x() - vec.x(), y() - vec.y()); }
	public Vec2d mul(double f)  { return vec2d(x() * f, y() * f ); }
	public Vec2d div(double f)  { return vec2d(x() / f, y() / f ); }
	
	public String toString() { return "Vec2d(" + _x + "," + _y + ")"; }
		

}
