package org.katawashoujofighter;

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
			//XXX: due to the current data structures i't not possible for a fighter to face 
			// itself since they are identically the same object.			
			_fighter1 = getFighter("mandy");
			_fighter2 = getFighter("stick");			
			_stage.enter(_fighter1, _fighter2);
		}
		_lastupdate	= container.getTime();
	} 
	
	public void handleInput(Input input) {
		char command = Command.IDLE;
		if(input.isControllerLeft(0) || input.isKeyDown(Input.KEY_J)) {
			command = (char) (command | Command.LEFT); 
			// These annoying casts are needed because Java upcasts an | of 
			// 2 chars to an int. :p  
		} else if (input.isControllerRight(0) || input.isKeyDown(Input.KEY_L)) {
			command = (char) (command | Command.RIGHT);
		}
		if(input.isControllerDown(0) || input.isKeyDown(Input.KEY_K)) {
			command = (char) (command | Command.DOWN);
		} else if (input.isControllerUp(0) || input.isKeyDown(Input.KEY_I)) {
			command = (char) (command | Command.UP);
		} 
		if(input.isButtonPressed(0, 0) || input.isKeyDown(Input.KEY_Y)) {
			command = (char) (command | Command.HIGHWEAK);
		} 
		if(input.isButtonPressed(0, 1) || input.isKeyDown(Input.KEY_T)) {
			command = (char) (command | Command.HIGHMEDIUM);
		} 
		if(input.isButtonPressed(0, 2) || input.isKeyDown(Input.KEY_H)) {
			command = (char) (command | Command.LOWWEAK);
		}
		if(input.isButtonPressed(0, 3) || input.isKeyDown(Input.KEY_G)) {
			command = (char) (command | Command.LOWMEDIUM);
		}		
		if(input.isButtonPressed(0, 4) || input.isKeyDown(Input.KEY_R)) {
			command = (char) (command | Command.HIGHSTRONG);
		}
		if(input.isButtonPressed(0, 5) || input.isKeyDown(Input.KEY_E)) {
			command = (char) (command | Command.HIGHBLOCK);
		} 
		if(input.isButtonPressed(0, 6) || input.isKeyDown(Input.KEY_F)) {
			command = (char) (command | Command.LOWSTRONG);
		} 
		if(input.isButtonPressed(0, 7) || input.isKeyDown(Input.KEY_D)) {
			command = (char) (command | Command.LOWBLOCK);
		} 		
		_fighter1.command(command);
	}
	
	@Override public void update(GameContainer container, int delta) 
    throws SlickException {
		// handle input directly rather than through callbacks for now.
		handleInput(container.getInput());
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
		// g.drawString("Hello, Slick!", 0, 100);		
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
