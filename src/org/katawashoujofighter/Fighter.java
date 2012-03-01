package org.katawashoujofighter;

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
	char _command; // Currently active command.
	
	HashMap<String, Move> _moves;
	
	/* Creates a new Fighter by loading all it's resources from the data 
	 * directory. 
	 * */	
	Fighter(String name) {
		super(name);
		_command	= Command.NONE;
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
	
	@Override
	public void update(long ms) {
		super.update(ms);
		if(_now == null) { 
			// Main.printf("Cannot draw %s\n", name()); 
			return; 
		} 
		// _now.draw(0, 0);
		_now.update(ms);
	}	
	
	/* Tries to set the move to the named move. If the move is not found, the current move is maintained. */
	public boolean trymove(String name) {
		Move move = getMove(name);
		if(move == null) return false;
		_now      = move;
		return true;
	}
	
	public final static int WALK_SPEED = 200;
	
	/* Accept a basic command, and changes the move and direction, etc of the fighter accordingly. */
	public void command(char command) {
		if (command == _command) return; // ignore same command as last one
		_command = command;
		// XXX: add command buffer and special actions around here somewhere.
		// motion command
		if (command == Command.IDLE) { // back to idle command
			trymove("idle"); stop(); 
		} else if(Command.isMotion(_command)) 	  {	
			if(_command == Command.RIGHT)		  {
				trymove("walk"); setspeed(WALK_SPEED, 0);  
			} else if (_command == Command.LEFT)  {
				trymove("walk"); setspeed(-WALK_SPEED, 0);
			} else if (_command == Command.DOWN)  {
				trymove("knee"); stop();
			} else if (_command == Command.UP)  {
				trymove("jump");
				// ensure the feet go off the floor.
				set(x(), y() - 50);  air(true); setaccel(10, -10000);
			}
		} else { // action command
			if (Command.is(_command, Command.HIGHWEAK)) {
				trymove("punchweak");
			} else if (Command.is(_command, Command.LOWWEAK)) {
				trymove("kickweak");
			}
			
		}
	}

	public int height() { if(_now == null) return 0; return _now.height();  }
	
	
}	


