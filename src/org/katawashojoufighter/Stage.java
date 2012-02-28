package org.katawashojoufighter;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * A Stage is one of the "level" of the game where the fight takes place.  
 */
public class Stage {
	Image 	background_;
	int  	width_;
	int 	height_;
	Camera  camera_;
	
	Stage(int width, int height, String backname) throws SlickException {
		width_ 		= width;
		height_ 	= height;
		background_ = new Image("data/image/background_test.png");
		camera_  	= new Camera(-background_.getWidth() / 4,  -background_.getHeight() / 2);
	} 
	
	void draw() {
		background_.draw(camera_.x(), camera_.y());
	}
	

}
