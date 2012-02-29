package org.katawashojoufighter;

/** A Thing is any in-game object. It can currently only be a Fighter 
 * or a Projectile. Any thing is part of a Frame. */
public class Thing extends Named {
	// NOTE: This sort of simulates the physics. Also look into using phys2d and see if it is better?
	// it seems like in pphys2d, unlike chipmunk, every body
	// can only have 1 shape. This isn't good since the shape
	// of the fighter can and does change.  

	int _x;
	int _y;
	int _vx;
	int _vy;
	int _ax;
	int _ay;
	int  _g;	// gravity XXX: implement this.
	boolean _air; // true if the thing is up in the air or falling, false if it's standing
	
	
	Thing(String name) {
		super(name);
		x(0); y(0); g(10); stop();	_air = false;
	}
	
	/** Coordinate, seed and accelerations getters and setters */
	int x() 			{ return _x; }
	int y() 			{ return _y; }
	int x(int newx) 	{ return _x = newx; }
	int y(int newy) 	{ return _y = newy; }
	int vx() 			{ return _vx; }
	int vy() 			{ return _vy; }
	int vx(int newx) 	{ return _vx = newx; }
	int vy(int newy) 	{ return _vy = newy; }
	int ax() 			{ return _ax; }
	int ay() 			{ return _ay; }
	int ax(int newx) 	{ return _ax = newx; }
	int ay(int newy) 	{ return _ay = newy; }
	int g() 			{ return _g; }
	int g(int newg) 	{ return _g  = newg; }
	boolean air(boolean newair) { _air  = newair; if(_air) { vy(0); ay(0); }  return _air;}
	boolean air() 		{ return _air; }
	
	// override these two:
	int height() { return 0; } 
	int width()  { return 0; }
	
	
	void move(int dx, int dy) {
		this.set(_x + dx, _y + dy);
	}
	
	void set(int x, int y) {
		x(x); y(y);
	}
	
	void setspeed(int vx, int vy) {
		vx(vx); vy(vy);
	}
	
	void setaccel(int ax, int ay) {
		ax(ax); ay(ay);
	}
	
	// sets speed an accelerations to 0
	void stop() {
		vx(0); vy(0); ax(0); ay(0);
	}
	
	public void update(long time_delta) {
		_vx += (_ax * time_delta) / 1000;
		_vy += (_ay * time_delta) / 1000;
		if (_air) {
			_vy += (_g * time_delta) / 1000;
		}
		_x  += (_vx * time_delta) / 1000;
		_y  += (_vy * time_delta) / 1000;		
	}
	
	/** Override this */
	public void draw(Camera camera) {
		
	}
	
	
	
}
