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
	
	public int width;
	public int height;
	
	public String title;
	
	private Canvas canvas;
	public Graphics graphics;
	public Window window;
	
	public Color backgroundColor;
	
	public boolean canvasRefresh;
	
	private BufferStrategy bs;
	
	public KeyManager keyManager;
	public MouseManager mouseManager;
	public int fps = 0, currentfps = fps;
	
	public GameInstance(int width, int height, String title, boolean canvasRefresh) {
		this.width = width;
		this.height = height;
		this.title = title;
		this.canvasRefresh = canvasRefresh;
	}
	
	public final BufferedImage getImage(String fileName) {
		try {
			return ImageIO.read(new File(fileName));
		} catch (IOException e) {
			System.out.println("Image not found " + fileName);
		}
		return null;
	}
	
	public final void clearGraphics() {
		Color c = graphics.getColor();
		graphics.setColor(canvas.getBackground());
		graphics.fillRect(0, 0, width, height);
		graphics.setColor(c);
	}
	
	public final void preInit() {
		window = new Window(width, height);
		window.setSize(width, height);
		window.setTitle(title);
		window.setResizable(false);
		this.canvas = window.canvas;
		
		keyManager = new KeyManager(this);
		mouseManager = new MouseManager(this);
		window.addKeyListener(keyManager);
		canvas.addKeyListener(keyManager);
		window.addMouseListener(mouseManager);
		canvas.addMouseListener(mouseManager);
	}
	
	public final boolean preRender() {
		bs = canvas.getBufferStrategy();
		if(bs == null) {
			canvas.createBufferStrategy(2);
			return false;
		}
		graphics = (Graphics2D)bs.getDrawGraphics();
		
		if(canvasRefresh) clearGraphics();
		
		return true;
	}
	
	public final void postRender() {
		graphics.dispose();
		bs.show();
	}
	
	public abstract void init();
	
	public abstract void tick();
	
	public abstract void keyTyped(KeyEvent e);
	
	public abstract void mouseClicked(MouseEvent e);
	
	public abstract void render();
	
}
