package org.katawashoujofighter;

/**
 * Camera specifies a view on the current stage. 
 **/
public class Camera {
	int _x, _y, _vieww, _viewh, _stagew, _stageh;
	
	Camera(int x, int y, int stagew, int stageh, int vieww, int viewh) {
		_vieww 	= vieww;
		_viewh 	= viewh;
		_stagew = stagew;
		_stageh = stageh;
		set(x, y);
	}
	
	Camera(int x, int y, int stageh, int stagew) {
		this(x, y, stageh, stagew, 800, 600);
	}
	
	void move(int dx, int dy) {
		this.set(_x + dx, _y + dy);
	}
	
	void set(int x, int y) {
		_x = x;
		_y = y;
		if (_x < 0) _x = 0;
		if (_y < 0) _y = 0;
		if ((_x +_vieww) > (_stagew)) _x = _stagew - _vieww;
		if ((_y +_viewh) > (_stageh)) _x = _stageh - _viewh;
		if (_y > (_viewh)) _y = _viewh;
	}


	public int x() { return _x;	}
	
	public int y() { return _y;	}
	
	public int w() { return _vieww; }
	
	public int h() { return _viewh; }

}
