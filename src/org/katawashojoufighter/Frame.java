package org.katawashojoufighter;
import java.io.File;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Color;

/** A frame is a single part of a motion that lasts a given duration,
 * which has an image to display it, and may have attacks or defenses 
 * associated with it.  
 * */
public class Frame extends Named {
	Thing  _thing;
	// Thing that this frame belongs to, if any. 		
	Image _image;
	// Image that is used to represent this frame.
	Image _fimage;
	// This image is the flipped image, for opposite facing.
	int	  _delay;
	// Duration of this frame.
	Color _trans;
	
	
	/** Draws this frame, flipped or not so. */
	public void draw(int x, int y, boolean flip) {
		if(flip) {
			_fimage.draw(x, y);
		} else { 
			_image.draw(x, y);
		}
	}
	
	
	/** Initializes a frame by loading it's image from the given file name.
	 * The name of the frame is also set accordingly. 
	 * @throws SlickException */
	Frame(File file) throws SlickException {		
		super(Whereis.nameof(file)); 
		_image 	= new Image(file.getPath());
		_trans = _image.getColor(0, 0); //top left color is transparent.
		 // load again, transparantly (what a waste of memory! :p) 
		_image 	= new Image(file.getPath(), _trans);
		_fimage	= _image.getFlippedCopy(true, false);
		
	}
}
