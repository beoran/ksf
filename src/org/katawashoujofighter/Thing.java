package org.katawashoujofighter;

/** A Thing is any in-game object. It can currently only be a Fighter 
 * or a Projectile. Any thing is part of a Frame. */
public class Thing extends Named {
	// NOTE: This sort of simulates the physics. Also look into using phys2d and see if it is better?
	// it seems like in pphys2d, unlike chipmunk, every body
	// can only have 1 shape. This isn't good since the shape
	// of the fighter can and does change.  

	// position, speed, accelleration and gravity.
	Vec2d _p, _v, _a, _g; 
	boolean _air; // true if the thing is up in the air or falling, false if it's standing
	
	
	Thing(String name) {
		super(name);
		set(0, 0);
		setspeed(0,0);
		setaccel(0,0);
		g(10); 
		stop();	_air = false;
	}
	
	/** Coordinate, seed and accelerations getters and setters */
	int x() 			{ return _p.xi(); }
	int y() 			{ return _p.yi(); }
	int vx() 			{ return _v.xi(); }
	int vy() 			{ return _v.yi(); }
	int ax() 			{ return _a.xi(); }
	int ay() 			{ return _a.yi(); }
	Vec2d g() 			{ return _g; 	  }
	Vec2d g(int newg) 	{ return _g  = Vec2d.g(newg); }
	boolean air(boolean newair) { 
		_air  = newair;   
		return _air;
	}
	boolean air() 		{ return _air; }
	
	// override these two:
	int height() { return 0; } 
	int width()  { return 0; }
	
	void move(Vec2d dp)		  		{ _p = _p.add(dp);				     }
	void move(int dx, int dy) 		{ this.move(Vec2d.vec2d(dx, dy));    }	
	void p(Vec2d p)           		{ _p = p;							 }
    void set(int x, int y)    		{ this.p(Vec2d.vec2d(x, y));         }
    void v(Vec2d v) 		  		{ _v = v;
    	Main.printf("setspeed: p: %S, v:%s, a:%s\n", _p, _v, _a);
    }    
	void setspeed(int vx, int vy) 	{ this.v(Vec2d.vec2d(vx, vy)); 		 }
	void a(Vec2d a) 		  	  	{ _a = a;							 }    
	void setaccel(int vx, int vy) 	{ this.a(Vec2d.vec2d(vx, vy)); 		 }
	
	
	// sets speed an accelerations to 0
	void stop() {
		setspeed(0,0); setaccel(0,0);
	}
	
	public void update(long time_delta) {
		_v = _v.add(_a.mul(time_delta).div(1000));
		if (_air) {
			_v = _v.add(_g.mul(time_delta).div(1000));
		}
		_p = _p.add(_v.mul(time_delta).div(1000));
		Main.printf("Thing: p: %S, v:%s, a:%s\n", _p, _v, _a);
	}
	
	/** Override this */
	public void draw(Camera camera) {
		
	}
	
	
	
}
