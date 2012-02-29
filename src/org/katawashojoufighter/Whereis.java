package org.katawashojoufighter;
import java.io.File;
import java.io.FileFilter;

/**
 * Whereis is a class that helps finding files (imagesn xml).
 * 
 * */
public class Whereis {
	static File datadir() {
		String dataname = System.getenv("KSF_DATA");
		if(dataname == null) { dataname = "data"; }
		File datadir 	= new File(dataname);
		return datadir; 
	}
	
	static File stagedir() {		
		File stagedir = new File(datadir(), "stage");
		return stagedir;
	}
	
	static File fighterdir() {		
		File fighterdir = new File(datadir(), "fighter");
		return fighterdir;
	}
	
	static File fighter(String fightername) {
		File fightersub = new File(fighterdir(), fightername);
		return fightersub;
	}
	
	static File stage(String stagename) {
		File stage = new File(stagedir(), stagename);
		return stage;
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
	
	static public class IsDir implements FileFilter {
		@Override
		public boolean accept(File file) { return file.isDirectory(); }		
	}
	
	
	static public class IsFile implements FileFilter {
		@Override
		public boolean accept(File file) { return file.isFile(); }		
	}
	
	/** Returns name of background file for the named stage or null if not found. */
	static public File background(String stagename) {
		File aid 	= stage(stagename);
		File maybe	= new File(aid, stagename + ".png");
		if(maybe.exists()) return maybe;
		maybe		= new File(aid, stagename + ".jpg");
		if(maybe.exists()) return maybe;
		maybe		= new File(aid, stagename + ".gif");
		if(maybe.exists()) return maybe;
		return null;
	}
	
	
	/** Returns a list of the different fighter directories. */
	public static File[] allfighterdirs() {
		return fighterdir().listFiles(new IsDir());
	}
	
	/** Returns a list of the different stage directories. */
	public static File[] allstagedirs() {
		return stagedir().listFiles(new IsDir());
	}
	
	/** Returns a list of the different move directories for the given fighter. */
	public static File[] allmovedirs(String fightername) {
		return fighter(fightername).listFiles(new IsDir());
	}
	
	
	public static String nameof(File file) {
		String aid = file.getName();
		return aid.split("\\.")[0];
	}

}
