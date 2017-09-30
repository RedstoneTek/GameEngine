/*
 * The GameInstance class is a template with every default function needed for a game
 * 
 * @author RedstoneTek
 * @version 0.31
 */

package com.tek.gej.engine;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class GameInstance {
	
	/*
	 * Variables related to the gui
	 */
	public int width;
	public int height;
	public String title;
	private Canvas canvas;
	public Graphics graphics;
	public Window window;
	public boolean canvasRefresh;
	private BufferStrategy bs;
	
	/*
	 * Handlers for mouse movement, mouse clicks, key clicks and key states
	 */
	public KeyManager keyManager;
	public MouseManager mouseManager;
	public int fps = 0, currentfps = fps;
	
	/*
	 * GameInstance constructor, this is the class that will hold your code when extended
	 * and provide you with the basic functions needed to make a simple game
	 * @param width is the windows width
	 * @param height is the windows height
	 * @param title is the window's title
	 * @param canvasRefresh defines if you want the window to refresh the canvas on every render or not
	 * @return GameInstance object
	 */
	public GameInstance(int width, int height, String title, boolean canvasRefresh) {
		/*
		 * Sets every variable supplied
		 */
		this.width = width;
		this.height = height;
		this.title = title;
		this.canvasRefresh = canvasRefresh;
	}
	
	/*
	 * Gets the bufferedimage from a file in the project's root
	 * 
	 * @param fileName is the file path from the root of the project, example yourpic.png or textures/yourpic.png
	 * @return BufferedImage
	 */
	public final BufferedImage getImage(String fileName) {
		/*
		 * Try and catch to tell the user whether or not his path is valid
		 */
		try {
			/*
			 * Fetches the bufferedimage through the ImageIO library
			 */
			return ImageIO.read(new File(fileName));
		} catch (IOException e) {
			System.out.println("Image not found " + fileName);
		}
		return null;
	}
	
	/*
	 * This will clear the screen (refresh)
	 * 
	 * @return void
	 */
	public final void clearGraphics() {
		/*
		 * Saves the graphics color to put it back afterwards
		 */
		Color c = graphics.getColor();
		graphics.setColor(canvas.getBackground());
		/*
		 * Fills the screen with the background color
		 */
		graphics.fillRect(0, 0, width, height);
		graphics.setColor(c);
	}
	
	/*
	 * This is the non-overridable preinit function, it creates the gui and initializes the keymanager
	 * as well as the mousemanager
	 * 
	 * @return void
	 */
	public final void preInit() {
		/*
		 * Initializes the gui through the Window class
		 * @see Window.java
		 */
		window = new Window(width, height);
		window.setSize(width, height);
		window.setTitle(title);
		window.setResizable(false);
		this.canvas = window.canvas;
		
		/*
		 * Initializes the keymanager and mousemanager to handle movements and clicks
		 */
		keyManager = new KeyManager(this);
		mouseManager = new MouseManager(this);
		window.addKeyListener(keyManager);
		canvas.addKeyListener(keyManager);
		window.addMouseListener(mouseManager);
		canvas.addMouseListener(mouseManager);
	}
	
	/*
	 * This is the non-overridable prerender function, it will create a bufferstrategy and 
	 * initialize the graphics object
	 * 
	 * @return boolean success
	 */
	public final boolean preRender() {
		//Tries to fetch the buffer strategy
		bs = canvas.getBufferStrategy();
		//If the buffer strategy isnt initialized, it makes it and returns false (not successful)
		if(bs == null) {
			canvas.createBufferStrategy(2);
			return false;
		}
		/*If it didnt return, this means it was initialized so it gets the graphics object and makes it 
		  public to the user*/
		graphics = (Graphics2D)bs.getDrawGraphics();
		
		//If the canvasRefresh boolean is true, then refresh the screen
		if(canvasRefresh) clearGraphics();
		
		return true;
	}
	
	/*
	 * This is the non-overridable postRender function, it disposes of the graphics object and shows
	 * the buffer strategy
	 * 
	 * @return void
	 */
	public final void postRender() {
		graphics.dispose();
		bs.show();
	}
	
	/*
	 * This is the user available init function, the user can write his own code into it to get it
	 * ran the first time the Handler initializes
	 * @see Handler.java
	 * 
	 * @return void
	 */
	public abstract void init();
	
	/*
	 * This is the user available tick function, the user can write his own code into it to get it
	 * ran *fps* times a second
	 * 
	 * @return void
	 */
	public abstract void tick();
	
	/*
	 * This is the user available keyTyped function, it will get called every time a user
	 * types a letter on his keyboard
	 */
	public abstract void keyTyped(KeyEvent e);
	
	/*
	 * This is the user available keyTyped function, it will get called every time a user
	 * clicks with his mouse
	 */
	public abstract void mouseClicked(MouseEvent e);
	
	/*
	 * This is the user available render function, the user can write his own code into it to get it
	 * ran *fps* times a second
	 * 
	 * @return void
	 */
	public abstract void render();
	
}
