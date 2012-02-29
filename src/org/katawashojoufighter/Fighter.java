package org.katawashojoufighter;

/**
 * A Fighter is any of the characters that can be played or can be fought against. 
 * A Fighter has different "moves" and different parameters such as maximum health, etc.    
 * */
public class Fighter extends Thing {
	final static int DEFAULT_HEALTH 	= 10000;
	final static int DEFAULT_RAGE   	= 10000;
	final static int DEFAULT_STRENGTH   = 10;
	final static int DEFAULT_TOUGHNESS  = 10;
	final static int DEFAULT_AGILITY   	= 10;
	
	String _name;
	
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
	
	java.util.Hashtable<String, Move> _moves;
	
	/* Creates a new Fighter by loading all it's resources from the data 
	 * directory. 
	 * */	
	Fighter(String name) {
		super(name);
		_health 	= _health_max = DEFAULT_HEALTH;
		_rage_max 	= DEFAULT_RAGE;
		_strength   = DEFAULT_STRENGTH;
		_toughness  = DEFAULT_TOUGNESS;
		_agility    = DEFAULT_AGILITY;
		
	}
	
	void loadMoves() {
		
	}
	
	Move getMove(String name) {
		return _moves.get(name);
	}	
	
	void putMove(Move move) {
		_moves.put(move._name, move);
	}
	
	static java.util.Hashtable<String, Fighter> _fighters;
	
	static Fighter getFighter(String name) {
		return _fighters.get(name);
	}	
	
	static void putFighter(Fighter fighter) {
		_fighters.put(fighter._name, fighter);
	}
	
	
	
	
	
}	


