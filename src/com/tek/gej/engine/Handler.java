package com.tek.gej.engine;

import java.util.ArrayList;

public class Handler {
	
	boolean exit = false;
	
	ArrayList<GameInstance> modules = new ArrayList<GameInstance>();
	
	int fps;
	double rate;
	
	public Handler(int fps) {
		this.fps = fps;
		this.rate = 1000 / fps;
	}
	
	public void init() {
		
		for(GameInstance instance : modules) {
			instance.preInit();
			instance.init();
		}
		
		startLoop();
	}
	
	private void startLoop() {
		long lastTime = System.nanoTime();
		double ns = 1000000000 / fps;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(!exit) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				for(GameInstance instance : modules) {
					instance.tick();
					
					if(!instance.preRender()) continue;
					instance.render();
					instance.postRender();
				}
				frames++;
				delta--;
			}
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				for(GameInstance instance : modules) {
					instance.currentfps = frames;
				}
				frames = 0;
			}
		}
	}
	
	public void registerModule(GameInstance instance) {
		modules.add(instance);
		instance.fps = fps;
	}
	
}
