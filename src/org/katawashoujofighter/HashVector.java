package org.katawashoujofighter;

import java.util.HashMap;
import java.util.Vector;

/** A HashVector combines a hashmap and a vector so 
 * the stored objects can be kept track of better.
 * */
public class HashVector<K, V> {
	HashMap<K,V> _map;
	Vector<K>	 _vector;
	
	HashVector() {
		_map 	= new HashMap<K,V>();
		_vector = new Vector<K>();
	}
	
	/** Puts a value in the hashvector*/
	void put(K key, V value) {
		_map.put(key,  value);
		_vector.add(key);
	}
	
	/** Gets a value from the HashVector by key. */
	V get(K key) {		
		return _map.get(key);
	}
	
	/** Gets the nth key added. */
	K getkey(int index) {
		return _vector.get(index);
	} 
	
	/** Gets the nth value added. */
	V getvalue(int index) {
		return get(getkey(index));
	}
	
	/** Returns the amount of keys set in this hashvector. */
	int amount() {
		return _vector.size();
	}

}
