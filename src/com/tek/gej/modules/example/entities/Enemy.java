package com.tek.gej.modules.example.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.tek.gej.modules.example.Example;
import com.tek.gej.modules.example.Example.Facing;

public class Enemy {
	
	public int x, y, size;
	public double speed;
	public Facing view;
	public Example gi;
	
	public int health;
	
	public boolean dead;
	
	public Enemy(int x, int y, int size, double speed, Example gi) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.speed = speed;
		this.gi = gi;
		this.health = 10;
		this.dead = false;
		this.view = Facing.FRONT;
	}
	
	public void tick() {
		if(gi.player.x < x) {
			x -= speed;
		}
		if(gi.player.x > x) {
			x += speed;
		}
		if(gi.player.y < y) {
			y -= speed;
		}
		if(gi.player.y > y) {
			y += speed;
		}
		
		if(rectangle().intersects(gi.player.rectangle())) {
			System.out.println("You have been killed by the monster");
			gi.enemystreak = 0;
			gi.init();
		}
		
		for(Projectile projectile : new ArrayList<Projectile>(gi.projectiles)) {
			if(projectile.rectangle().intersects(rectangle())) {
				health--;
				projectile.dead = true;
				gi.projectiles.remove(projectile);
			}
		}
		
		if(health == 0) {
			this.dead = true;
		}
	}
	
	public void render(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.GREEN);
		g.fillRect(x, y, size, size);
		g.setColor(c);
	}
	
	public Rectangle rectangle() {
		return new Rectangle(x, y, size, size);
	}
	
}
