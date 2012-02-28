package org.katawashojoufighter;

/**
 * A Fighter is any of the characters that can be played or can be fought against. 
 * A Fighter has different "moves" and different parameters such as maximum health, etc.    
 * */
public class Fighter {
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
	
	Fighter(String name, int health_max, int rage_max) {
		_name		= name;
		_health 	= _health_max = health_max;
		_rage_max 	= rage_max;
		_rage   	= 0; 		
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
	
	
	
	
	
	

}
