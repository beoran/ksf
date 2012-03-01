package org.katawashoujofighter;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.io.File;

/**
 * A move is any action, motion or position a Fighter can undertake, 
 * even lying down, etc.
 * */
public class Move extends Named {
	// The fighter this move belongs to. 
	Fighter   _fighter;
	String 	  _name;	
	// Animation with the frames of this move.
	Animation _animation;
	
	/** Loads an image for a move. Returns nil if could not load. */
	Image load_image(String fightername, String movename) {		
		File movepng    = Whereis.movepng(fightername, movename);
		File movegif    = Whereis.movegif(fightername, movename);
		File moveok	    = null;
		// loads either a png or a gif file with the moves in it.
		if(movepng.exists()) { moveok = movepng; }
		if(movegif.exists()) { moveok = movegif; }
		if(moveok == null) 	 { return null;		 }		
		try {
			return new Image(moveok.getPath());
		} catch (SlickException e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	
	Move(String name, Fighter fighter) {
		super(name);
		_fighter 	= fighter;		
		loadAnimation();
	}	
	
	void loadAnimation() {
		Main.printf("Loading animation %s:\n", this.name());
		_animation	= new Animation();
		File[] framefiles = Whereis.movefiles(_fighter.name(), this.name());
		// sort the frame files so the're correctly ordered (at least they should be!)
		java.util.Arrays.sort(framefiles);
		for(int index = 0; index < framefiles.length; index++) {
			File file 	= framefiles[index];
			Frame frame = null;			
			// Main.printf("Loading frame %s:\n", file.getPath());
			try {
				frame = new Frame(file);
				_animation.add(frame);
			} catch (SlickException e) {
				frame = null;
				Main.perrorf("Could not load frame %s\n!", file.getPath());
				e.printStackTrace();
			}			
		}				
	}
	
	void draw(int x, int y) {
		_animation.draw(x, y, false);
	}


	public void update(long ms) {
		_animation.update(ms);
	}


	public int height() { return _animation.height();	}
	
	

}
