package org.katawashojoufighter;

/** A simple superclass for every object that has a name. 
 * Which is mostly anything. 
 * */
public class Named {
	String _name;
	
	Named(String name) {
		_name = name;
	}
	
	String name() { return _name; }
}
