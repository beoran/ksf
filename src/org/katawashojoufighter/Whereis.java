package org.katawashojoufighter;
import java.io.File;

/**
 * Whereis is a class that helps finding files (imagesn xml).
 * 
 * */
public class Whereis {
	static File data() {
		File datadir 	= new File("data");
		return datadir; 
	}
	
	static File fighters() {		
		File fighterdir = new File(data(), "fighter");
		return fighterdir;
	}
	
	static File fighter(String fightername) {
		File fightersub = new File(fighters(), fightername);
		return fightersub;
	}
	
	static File fighterxml(String fightername) {
		File fightersub = new File(fighter(fightername), fightername + ".xml");
		return fightersub;
	}
	
	static File movepng(String fightername, String movename) { 
		File movepng    = new File(fighter(fightername),  fightername + "_" + movename + ".png");
		return movepng;
	}
	
	static File movegif(String fightername, String movename) {
		File movegif    = new File(fighter(fightername),  fightername + "_" + movename + ".gif");
		return movegif;
	} 
	

}
