package org.katawashojoufighter;


import java.util.Vector;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * A Stage is one of the "level" of the game where the fight takes place.  
 */
public class Stage extends Named {
	String			_id;
	Image 		    _background;
	int  			_width;
	int 		    _height;
	Camera  		_camera;
	Vector<Thing>	_things; 	
	
	/* Creates a new Stage by loading all it's resources from the data 
	 * directory. 
	 **/
	Stage(String name) throws SlickException {
		super(name);
		_width 		= 800;
		_height 	= 600;
		_background = new Image(Whereis.background(name).getPath());
		_camera 	= new Camera(-_background.getWidth() / 4,  -_background.getHeight() / 2);
		_things		= new Vector<Thing>();
	} 
	
	void draw() {
		_background.draw(_camera.x(), _camera.y());
	}

	public void update(long time_delta) {
		for(int index = 0; index < _things.size(); index++) {
			Thing thing = _things.get(index);
			thing.update(time_delta);
		}
	}
	
	
	

}
