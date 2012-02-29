package org.katawashojoufighter;

/** A Thing is any in-game object. It can currently only be a Fighter 
 * or a Projectile. Any thing is part of a Frame. */
public class Thing extends Named {

	int _x;
	int _y;
	int _vx;
	int _vy;
	int _ax;
	int _ay;
	
	Thing(String name) {
		super(name);
		x(0);
		y(0);
	}
	
	/** Coordinate getters and setters */
	int x() { return _x; }
	int y() { return _y; }
	int x(int newx) { return _x = newx; }
	int y(int newy) { return _y = newy; }
	
	void move(int dx, int dy) {
		this.set(_x + dx, _y + dy);
	}
	
	void set(int x, int y) {
		x(x); y(y);
	}

	
	public void update(long time_delta) {
		_vx += (_ax * time_delta) / 1000;
		_vy += (_ay * time_delta) / 1000;
		_x  += (_vx * time_delta) / 1000;
		_y  += (_vy * time_delta) / 1000;		
	}
	
	/** Override this */
	public void draw(Camera camera) {
		
	}
	
	
	
}
