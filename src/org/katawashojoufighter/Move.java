package org.katawashojoufighter;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.io.File;

/**
 * A move is any action, motion or position a Fighter can undertake, 
 * even lying down, etc.
 * */
public class Move {
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
	
	Move(Fighter fighter, String name) {
		_fighter 	= fighter;
		_name 		= name;		
	}	
	

}
