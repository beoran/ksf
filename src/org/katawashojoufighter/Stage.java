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
		_background = new Image(Whereis.background(name).getPath());
		_width 		= _background.getWidth();		
		_height 	= _background.getHeight();		
		_camera 	= new Camera(_width / 2 - 400, _height - 600, _width, _height);
		_things		= new Vector<Thing>();
	} 
	
	/** Call this when "entering" the stage, that is, starting play in it.
	 * The two fighters will be activated and put into the _things vector.  
	 **/
	void enter(Fighter fighter1, Fighter fighter2) {		
		_things		= new Vector<Thing>();
		_things.add(fighter1);
		_things.add(fighter2);	
		fighter1.set((_width / 2) - 100, _height - 300);
		fighter2.set(_width / 2 , _height - 300);		
	}
	
	void draw() {
		_background.draw(-_camera.x(), -_camera.y());
		for(Thing thing : _things) {
			thing.draw(_camera);
		}
	}

	public void update(long time_delta) {
		for(int index = 0; index < _things.size(); index++) {
			Thing thing = _things.get(index);
			thing.update(time_delta);
		}
	}
	
	
	

}
