package org.katawashoujofighter;


import java.util.Vector;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import net.phys2d.raw.World;
import net.phys2d.math.Vector2f;

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
	int 			_ground; // y coordinate of the ground.
	World			_world; // phys2d simulation world.
	static Vector2f _gravity = new Vector2f(0.0f, 10.0f);
	static int	 	iterations = 1000;
	
	/* Creates a new Stage by loading all it's resources from the data 
	 * directory. 
	 **/
	Stage(String name) throws SlickException {
		super(name);
		_world		= new World(_gravity, iterations);
		_background = new Image(Whereis.background(name).getPath());
		_width 		= _background.getWidth();		
		_height 	= _background.getHeight();		
		_ground		= _height - 200; 
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
		fighter1.set((_width / 2) - 100, _ground - fighter1.height());
		// hide fighter2 for now. 
		fighter2.set(_width , _ground - fighter2.height());		
	}
	
	/** Checks if a thing is grounded (not up in the air). */
	boolean grounded(Thing thing) {
		return ((thing.x() + thing.height()) >= _ground); 
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
			thing.air(grounded(thing)); // decide if the thing is in the air or not.
			thing.update(time_delta);			
		}
	}
	
	
	

}
