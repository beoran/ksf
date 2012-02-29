package org.katawashojoufighter;
import org.newdawn.slick.Image;


/** A frame is a single part of a motion that lasts a given duration,
 * which has an image to display it, and may have attacks or defenses 
 * associated with it.  
 * */
public class Frame {
	Thing  _thing;
	// Thing that this frame belongs to, if any. 	
	Image _image;
	// Image that is used to represent this frame.
	int	  _delay;
	// Duration of this frame.	
	
	/** Draws this frame. */
	public void draw(int x, int y) {
		_image.draw(x, y);
	}
	
	
}
