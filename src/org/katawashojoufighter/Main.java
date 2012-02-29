package org.katawashojoufighter;

import java.io.File;

import org.newdawn.slick.BasicGame; 
import org.newdawn.slick.GameContainer; 
import org.newdawn.slick.Graphics; 
import org.newdawn.slick.SlickException; 
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Input;

/** Main has the main function and assorted data. */
public class Main extends BasicGame {
	Stage _stage; // current stage, if any.
	long _lastupdate;
	// Time of last update
	// All stages
	static HashVector<String, Stage> 	   _stages;
	// All fighters 
	static HashVector<String, Fighter> 	   _fighters;
	// All projectiles
	static HashVector<String, Projectile>  _projectiles;
	// This is so we can keep track of all known
	
	// Active fighters, if any. 
	Fighter _fighter1, _fighter2;
	
	/** Print formatted messages to stdout. */
	public static void printf(String format, Object ... args) {
		System.out.format(format, args);		
	}
	
	/** Print formatted error messages to stderr. */
	public static void perrorf(String format, Object ... args) {
		System.err.format(format, args);		
	}
	
	public Stage getStage(String id) {
		return _stages.get(id);
	}
	
	public Fighter getFighter(String id) {
		return _fighters.get(id);
	}
	
	public Projectile getProjectile(String id) {
		return _projectiles.get(id);
	}
	
	public void putStage(Stage stage) {
		_stages.put(stage.name(), stage);
	}
	
	public void putFighter(Fighter fighter) {
		_fighters.put(fighter.name(), fighter);
	}
	
	public void  putProjectile(Projectile projectile) {
		_projectiles.put(projectile.name(), projectile);
	}
	
	// Loads all data 
	void loadAll() throws SlickException {
		loadStages();
		loadFighters();
		loadProjectiles();
	}
	
	/** Load a single stage, return null if it failed.
	 *  Also stores the stage if successful in _stages
	 **/
	Stage loadStage(String name) {
		try {
			System.out.format("Loading stage %s: ", name);
			Stage stage = new Stage(name);
			putStage(stage);
			System.out.format(" OK!\n", name);
			return stage;
		} catch(Exception ex) {					
			System.out.format(" Error!\n", name);
			ex.printStackTrace();
			return null;
		}
	} 
	
	/** Load a single fighter, return null if it failed.
	 *  Also stores the stage if successul in _stages
	 **/
	Fighter loadFighter(String name) {
		try {
			System.out.format("Loading fighter %s: ", name);
			Fighter fighter = new Fighter(name);
			putFighter(fighter);
			System.out.format(" OK!\n", name);
			return fighter;
		} catch(Exception ex) {					
			System.out.format(" Error!\n", name);
			ex.printStackTrace();
			return null;
		}
	} 

	
	/** Loads all stages */
	void loadStages() {
		File [] dirs = Whereis.allstagedirs(); 
		for(int index = 0; index < dirs.length; index ++) {
			File   file = dirs[index];
			String name = Whereis.nameof(file);
			loadStage(name);
		}
	}

	/** Loads all fighters */
	void loadFighters() {
		File [] dirs = Whereis.allfighterdirs(); 
		for(int index = 0; index < dirs.length; index ++) {
			File   file = dirs[index];
			String name = Whereis.nameof(file);
			loadFighter(name);
		}
	}
	
	/** Loads all projectiles. */
	void loadProjectiles() throws SlickException  {
		// TODO: implement projectiles.
	}
	
	
	public Main() throws SlickException { 
		super("Katawa Shoujo Fighter"); 
		_stages 	= new HashVector<String, Stage>();
		_fighters 	= new HashVector<String, Fighter>();
		_projectiles= new HashVector<String, Projectile>();
	} 
	
	@Override public void init(GameContainer container) 
	throws SlickException {
		loadAll();
		_stage 		= getStage("outside");
		if(_stage  != null) {
			_fighter1 = getFighter("hanako");
			_fighter2 = getFighter("mandy");			
			_stage.enter(_fighter1, _fighter2);
		}
		_lastupdate	= container.getTime();
	} 
	@Override public void update(GameContainer container, int delta) 
    throws SlickException {
		long time_now   = container.getTime();
		long time_delta = time_now - _lastupdate;
		time_delta 		= Tool.clamp(time_delta, 0 , 1000);
		if(_stage != null) {
			_stage.update(time_delta);
		}				
		_lastupdate		= time_now;  
	} 
	@Override public void render(GameContainer container, Graphics g) 
    throws SlickException  {
		_stage.draw();
		g.drawString("Hello, Slick!", 0, 100);		
    } 
	
	@Override public void keyPressed(int key, char c) {
		printf("Key pressed! %d %c\n", key, c);
		switch(key) { 
			case Input.KEY_UP:
				_fighter1.move(0, -10);
			break;	
			case Input.KEY_DOWN:
				_fighter1.move(0, 10);
			break;	
			case Input.KEY_LEFT:
				_fighter1.move(-10, 0);
			break;	
			case Input.KEY_RIGHT:
				_fighter1.move(10, 0);
			break;
			default:
			break;
		}
	}  
	
	public static void main(String[] args) { 
		try { 
			Main game = new Main(); 
			AppGameContainer app = new AppGameContainer(game);
			app.setDisplayMode(800, 600, false);
			app.start();			
		} catch (SlickException e) { 
			e.printStackTrace(); 
		} 
	} 
}
