package com.tek.gej.modules.example.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.tek.gej.modules.example.Example;
import com.tek.gej.modules.example.Example.Facing;

public class Projectile {
	
	public Facing view;
	public int x, y, speed, size;
	public Example gi;
	public boolean dead = false;
	
	public Projectile(Facing view, int x, int y, int size, int speed, Example gi) {
		this.view = view;
		this.x = x;
		this.y = y;
		this.size = size;
		this.speed = speed;
		this.gi = gi;
	}
	
	public void tick() {
		if(x > gi.width || x < 0 || y > gi.height || y < 0) {
			dead = true;
			return;
		}
		
		switch(view) {
			case FRONT:
				y += speed;
				break;
			case BACK:
				y -= speed;
				break;
			case LEFT:
				x -= speed;
				break;
			case RIGHT:
				x += speed;
				break;
			default:
				break;
		}
	}
	
	public void render(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillRect(x - size / 2, y - size / 2, size, size);
		g.setColor(c);
	}
	
	public Rectangle rectangle() {
		return new Rectangle(x, y, size, size);
	}
	
}
