package org.katawashojoufighter;

import java.io.File;
import java.util.HashMap;

/**
 * A Fighter is any of the characters that can be played or can be fought against. 
 * A Fighter has different "moves" and different parameters such as maximum health, etc.    
 * */
public class Fighter extends Thing {
	final static int DEFAULT_HEALTH 	= 10000;
	final static int DEFAULT_RAGE   	= 10000;
	final static int DEFAULT_STRENGTH   = 100;
	final static int DEFAULT_TOUGHNESS  = 100;
	final static int DEFAULT_AGILITY   	= 100;
	 
	int _health;
	int _health_max;
	int _rage;
	int _rage_max;
	
	int _strength;
	int _toughness;
	int _agility;
	int _perception;
	int _wisdom;
	int _intelligence;
	int _charm;
	// currently active move if any. 
	Move _now; 
	
	HashMap<String, Move> _moves;
	
	/* Creates a new Fighter by loading all it's resources from the data 
	 * directory. 
	 * */	
	Fighter(String name) {
		super(name);
		_health 	= _health_max = DEFAULT_HEALTH;
		_rage_max 	= DEFAULT_RAGE;
		_strength   = DEFAULT_STRENGTH;
		_toughness  = DEFAULT_TOUGHNESS;
		_agility    = DEFAULT_AGILITY;
		loadMoves();
		// Use idle move to start.
		_now		= getMove("idle");
		if(_now == null) {
			Main.perrorf("Could not find idle move?\n");
		}
	}
	
	Move loadMove(String movename) {
		Main.printf("Loading move %s:\n", movename);
		return new Move(movename, this);
	}
	
	void loadMoves() {
		_moves = new HashMap<String, Move>();
		Main.printf("Loading moves for %s:\n", _name);
		File [] movedirs = Whereis.allmovedirs(_name);
		for(int index = 0 ; index < movedirs.length ; index++) {
			File   file 	= movedirs[index];
			String movename = Whereis.nameof(file);
			Move move 		= loadMove(movename);
			if(move != null) {
				Main.printf("Move loaded: >%s<\n", move.name());
				this.putMove(move);
			}					
		}
	}
	
	Move getMove(String name) {
		return _moves.get(name);
	}	
	
	void putMove(Move move) {
		_moves.put(move.name(), move);
	}
	
	public void draw(Camera camera) {
		if(_now == null) { 
			// Main.printf("Cannot draw %s\n", name()); 
			return; 
		} 
		// _now.draw(0, 0);
		_now.draw(x() - camera.x(), y() - camera.y());
	}	
	
	
}	


