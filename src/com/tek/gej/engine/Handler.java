/*
 * The Handler class will handle your modules
 * extending the GameInstance class
 * @see GameInstance.java
 * It will also manage the gameloop and framerate
 * 
 * @author RedstoneTek
 * @version 0.31
 */

package com.tek.gej.engine;

import java.util.ArrayList;

public class Handler {
	
	//Used to stop the gameloop
	public boolean exit = false;
	
	//Stores every loaded module
	ArrayList<GameInstance> modules = new ArrayList<GameInstance>();
	
	//Used to calculate framerate and timing
	int fps;
	
	/*
	 * Handler constructor
	 * @param fps This is the desired framerate
	 * that the handle will try to achieve
	 * @return Handler
	 */
	public Handler(int fps) {
		this.fps = fps;
	}
	
	/*
	 * Initializes the modules and starts
	 * the main gameloop, will initialize
	 * every module and then proceed to
	 * start ticking and rendering each and
	 * every one of them
	 * @return void
	 */
	public void init() {
		
		/*
		 * Initializes every window and then
		 * proceeds to run the user made
		 * init()
		 * @see Example.java
		 */
		for(GameInstance instance : modules) {
			instance.preInit();
			instance.init();
		}
		
		/*
		 * Starts the gameloop
		 */
		startLoop();
	}
	
	/*
	 * Gameloop, it will simply tick and render
	 * every module and regulate it at the desired
	 * framerate 
	 * @see Class constructor
	 * @return void
	 */
	private void startLoop() {
		/*
		 * Initializes the timing variables to
		 * calculate and regulate the framerate
		 */
		long lastTime = System.nanoTime();
		double ns = 1000000000 / fps;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		/*
		 * Gameloop, will run unless exit is
		 * turned to true.
		 */
		while(!exit) {
			/*
			 * Regulating framerate and continuing
			 * if it doesn't match the desired framerate
			 */
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				/*
				 * Ticks and renders every module
				 */
				for(GameInstance instance : modules) {
					instance.tick();
					
					if(!instance.preRender()) continue;
					instance.render();
					instance.postRender();
				}
				/*
				 * Adds up the frames counted and resets the delta
				 */
				frames++;
				delta--;
			}
			
			/*
			 * Every second, send the new current framerate
			 * to the modules. Stored in the currentfps var
			 * @see Example.java
			 */
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				for(GameInstance instance : modules) {
					instance.currentfps = frames;
				}
				frames = 0;
			}
		}
	}
	
	/*
	 * Adds a module to the gameloop and 
	 * stores the framerate in.
	 * @return void
	 */
	public void registerModule(GameInstance instance) {
		modules.add(instance);
		instance.fps = fps;
	}
	
}
