package org.katawashojoufighter;

/** A Thing is any in-game object. It can currently only be a Fighter 
 * or a Projectile.  */
public class Thing extends Named {

	int _x;
	int _y;
	int _vx;
	int _vy;
	int _ax;
	int _ay;
	
	Thing(String name) {
		super(name);
	}

	
	public void update(long time_delta) {
		_vx += (_ax * time_delta) / 1000;
		_vy += (_ay * time_delta) / 1000;
		_x  += (_vx * time_delta) / 1000;
		_y  += (_vy * time_delta) / 1000;		
	}
}
