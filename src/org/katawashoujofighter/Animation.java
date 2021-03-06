package org.katawashoujofighter;

import java.util.Vector;

/** An animation consists of a list of different frames, 
 * In a sense, an animation is the visual representation of a Thing
 * In case of a Projectile, there's only one animation. 
 * In case of a Fighter, there is one Animation for each of it's moves. 
 * */
public class Animation {
	 Vector<Frame> _frames;
	 Frame _now;
	 int   _active;
	 long  _phase;	 
	 
	 Animation() {
		 _frames = new Vector<Frame>();
		 rewind();
	 }
	 
	 void rewind() {
		 _phase  = 0;
		 if(_frames.size() > 0) { 
			 _active = 0;
			 _now 	 = _frames.get(_active);
		 } else {
			 _now 	 = null;		 
			 _active = -1;		
		 }
	 }
	 
	 Frame add(Frame frame) {
		 _frames.add(frame);
		 rewind();
		 return frame;
	 }
	 
	 /** draws this animation, flipped or not so. */
	 void draw(int x, int y, boolean flip) {
		 if(_now == null) return;
		 _now.draw(x, y, flip);
	 }
	 
	 // Updates the animation. pass in how much time has passed since the last 
	 // call update in milliseconds.
	 void update(long ms) {
		 if(_now == null) return;
		 _phase += ms;
		 // if the phase is bigger than the delay, the frame is over 
		 if (_phase > _now._delay) {
			 _phase 	 = 0;
			 _active 	+= 1;
			 // active is reset to  0 if it's more than the last frame
			 _active     = Tool.wraplimit(_active, _frames.size() - 1);
			 _now		 = _frames.get(_active); 
		 }
	 }

	public int height() { if(_now == null) return 0; return _now.height(); } 
	 

}
