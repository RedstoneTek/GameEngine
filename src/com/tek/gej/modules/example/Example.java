package com.tek.gej.modules.example;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.tek.gej.engine.GameInstance;
import com.tek.gej.modules.example.entities.Enemy;
import com.tek.gej.modules.example.entities.Player;
import com.tek.gej.modules.example.entities.Projectile;

public class Example extends GameInstance{
	
	public Player player;
	public Enemy enemy;
	
	public ArrayList<Projectile> projectiles;
	
	public BufferedImage exit, background;
	
	public int enemystreak = 0;
	
	public Example() {
		super(500, 500, "Example", true);
	}
	
	public void destroyProjectile(Projectile p) {
		projectiles.remove(p);
	}
	
	public void launchProjectile(Facing view) {
		projectiles.add(new Projectile(player.view, player.x + player.size / 2, player.y + player.size / 2, player.size / 4, 20, this));
	}
	
	public void drawUI() {
		graphics.drawString("WASD - Move around", 5, 15);
		graphics.drawString("SPACE - Shoot projectile", 5, 30);
		graphics.drawString("Goal - Defeat the monstarrr", 5, 45);
		graphics.drawString("Monster's Health - " + enemy.health, 5, 75);
		graphics.drawString("Monster Deathstreak - " + enemystreak, 5, 90);
		graphics.drawString("FPS: " + currentfps, 5, 120);
		
		graphics.drawImage(exit, width - exit.getWidth() - 5, 0, exit.getWidth(), exit.getHeight(), null);
	}
	
	public void init() {
		projectiles = new ArrayList<Projectile>();
		projectiles.clear();
		player = new Player(250, 250, 50, 5, this);
		enemy = new Enemy(50, 50, 40, 2, this);
		exit = getImage("textures/exit.png");
		background = getImage("textures/background.png");
	}
	
	public void tick() {
		for(Projectile proj : new ArrayList<Projectile>(projectiles)) {
			proj.tick();
			if(proj.dead) projectiles.remove(proj);
		}
		
		if(!enemy.dead) enemy.tick();
		if(enemy.dead) {
			enemy = new Enemy(50, 50, 40, 2, this);
			System.out.println("You have killed the enemy!");
			enemystreak++;
		}
		player.tick();
	}
	
	public void keyTyped(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			launchProjectile(player.view);
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		if(e.getX() > width - exit.getWidth() - 5) {
			if(e.getY() < exit.getHeight()) {
				System.out.println("Exiting... Thanks for viewing this example");
				window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
			}
		}
	}
	
	public void render() {
		graphics.drawImage(background, 0, 0, width, height, null);
		
		for(Projectile proj : projectiles) {
			proj.render(graphics);
		}
		
		if(!enemy.dead) enemy.render(graphics);
		player.render(graphics);

		drawUI();
	}
	
	public enum Facing{
		BACK,
		FRONT,
		LEFT,
		RIGHT;
	}

}
