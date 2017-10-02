package com.tek.gej.modules.example.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import com.tek.gej.engine.GameInstance;
import com.tek.gej.engine.Spritesheet;
import com.tek.gej.modules.example.Example.Facing;

public class Player {
	
	public int x,y,size;
	public Facing view;
	public double speed;
	public GameInstance gi;
	
	public Spritesheet textures;
	
	public Player(int x, int y, int size, double speed, GameInstance gi) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.speed = speed;
		this.gi = gi;
		this.view = Facing.FRONT;
		
		textures = new Spritesheet(gi.getImage("textures/example/spritesheet.png"), 20, 27, 12, 8);
	}
	
	public int centerx() {
		return x + size / 2;
	}
	
	public int centery() {
		return y + size / 2;
	}
	
	public void tick() {
		if(gi.keyManager.pressed[KeyEvent.VK_W]) {
			y -= speed;
			view = Facing.BACK;
		}
		if(gi.keyManager.pressed[KeyEvent.VK_S]) {
			y += speed;
			view = Facing.FRONT;
		}
		if(gi.keyManager.pressed[KeyEvent.VK_A]) {
			x -= speed;
			view = Facing.LEFT;
		}
		if(gi.keyManager.pressed[KeyEvent.VK_D]) {
			x += speed;
			view = Facing.RIGHT;
		}
		
		if(x < 0) {
			x = 0;
		}
		if(y < 0) {
			y = 0;
		}
		if(x > gi.width - 45) {
			x = gi.width - 45;
		}
		if(y > gi.height - 90) {
			y = gi.height - 90;
		}
	}
	
	public void render(Graphics g) {
		switch(view) {
			case FRONT:
				g.drawImage(textures.crops[0][2], x, y, size, size, null);
				break;
			case BACK:
				g.drawImage(textures.crops[0][0], x, y, size, size, null);
				break;
			case LEFT:
				g.drawImage(textures.crops[0][3], x, y, size, size, null);
				break;
			case RIGHT:
				g.drawImage(textures.crops[0][1], x, y, size, size, null);
				break;
			default:
				break;
		}
	}
	
	public Rectangle rectangle() {
		return new Rectangle(x, y, size, size);
	}
	
}
