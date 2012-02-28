package org.katawashojoufighter;

/**
 * Camera specifies a view on the current stage. 
 **/
public class Camera {
	int _x, _y, _w, _h;
	
	Camera(int x, int y, int w, int h) {
		_x = x; 
		_y = y;
		_w = w;
		_h = h;
	}
	
	Camera(int x, int y) {
		this(x, y, 800, 600);
	}
	
	void move(int dx, int dy) {
		_x += dx;
		_y += dy;
		if (_x < 0) _x = 0;
		if (_y < 0) _y = 0;
		if (_x > (_w)) _x = _w;
		if (_y > (_h)) _y = _h;
	}

	public int x() { return _x;	}
	
	public int y() { return _y;	}
	
	public int w() { return _w; }
	
	public int h() { return _h; }

}
