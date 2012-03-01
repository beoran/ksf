package org.katawashoujofighter;

import java.io.Serializable;
import org.newdawn.slick.Image;
import org.newdawn.slick.Color;



/** A Hitmap is a bitmask that allows for pixel perfect collision detections.*/
public class Hitmap implements Serializable {
   private static final long serialVersionUID = -5909146496764863208L;
   private boolean _pixels[][];
   private int _width, _height;
   
   public Hitmap(Image image, Color nohit)
         throws Exception {
      this.read(image, nohit);
   }

   public static Hitmap create(Image image, Color nohit) throws Exception {
	      return new Hitmap(image, nohit);
  }

   public static Hitmap create( Image image) throws Exception {
      return create(image, image.getColor(0, 0));
   }

   /* Reads in the data in an Image and fills in this Hitmap from it. */
   Hitmap read(Image image, Color nohit) throws Exception {
	  int _width =  image.getWidth();
	  int _height =  image.getHeight();	  
	  if(_width < 1 || _height < 1) return null;	  
      _pixels = new boolean[_height][_width];
      for(int y = 0; y <  _height ; y++) {
         for(int x = 0; x < _width; x++) {
        	Color 	color = image.getColor(x, y);
        	boolean solid = true;
        	if (nohit == null) {
        		if(color.getAlpha() == 0) { 	solid = false; }
        	} else {
        		if(color.equals(nohit))   { 	solid = false; }
        	}
        	_pixels[y][x] = solid; 
         }
      }
      return this;
   }

   public int width()  {   return _width;  	 }
   public int height() {   return _height;   }
   
   public boolean collides(int atx, int aty, Hitmap other, int ox, int oy) {
	   return false;	   
   }

}
