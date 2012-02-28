package org.katawashojoufighter;

import org.newdawn.slick.BasicGame; 
import org.newdawn.slick.GameContainer; 
import org.newdawn.slick.Graphics; 
import org.newdawn.slick.SlickException; 
import org.newdawn.slick.AppGameContainer;

public class Main extends BasicGame {
	Stage stage_;
	
	public Main() throws SlickException { 
		super("Katawa Shoujo Fighter"); 
	} 
	@Override public void init(GameContainer container) 
	throws SlickException {
		stage_ = new Stage(800, 600, "test");
	} 
	@Override public void update(GameContainer container, int delta) 
    throws SlickException {} 
	@Override public void render(GameContainer container, Graphics g) 
    throws SlickException  {
		stage_.draw();
		g.drawString("Hello, Slick world!", 0, 100);		
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
