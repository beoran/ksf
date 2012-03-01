package org.katawashoujofighter;

/** This is an axis-aligned bounds box. It is modeled as two vectors, 
 * one for the position and one for the size. 
 **/
public class AABB {
	Vec2d _p;
	Vec2d _size;
	
	public AABB(int x, int y, int w, int h) { 
		_p 		= new Vec2d(x, y);
		_size	= new Vec2d(w, h);
	}
	
	int x() 	 { return _p.xi(); } 
	int y() 	 { return _p.yi(); }	
	int width()  { return _size.xi(); } 
	int height() { return _size.yi(); }
	
	 
	
	
	
	
	
	
}
