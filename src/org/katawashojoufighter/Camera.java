package org.katawashojoufighter;

/**
 * Camera specifies a view on the current stage. 
 **/
public class Camera {
	int x_, y_, w_, h_;
	
	Camera(int x, int y, int w, int h) {
		x_ = x; 
		y_ = y;
		w_ = w;
		h_ = h;		
	}
	
	Camera(int x, int y) {
		this(x, y, 800, 600);
	}
	
	void move(int dx, int dy) {
		x_ += dx;
		y_ += dy;
		if (x_ < 0) x_ = 0;
		if (y_ < 0) y_ = 0;
		if (x_ > (w_)) x_ = w_;
		if (y_ > (h_)) y_ = h_;
	}

	public int x() { return x_;	}
	
	public int y() { return y_;	}
	
	public int w() { return w_; }
	
	public int h() { return h_; }

}
