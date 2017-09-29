package com.tek.gej.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{
	
	public boolean[] pressed = new boolean[1024];
	
	GameInstance gi;
	
	public KeyManager(GameInstance gi) {
		this.gi = gi;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		e.setKeyCode(e.getKeyChar());
		gi.keyTyped(e);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		pressed[e.getKeyCode()] = true;
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		while(pressed[e.getKeyCode()]) {
			pressed[e.getKeyCode()] = false;
		}
	}
	
}
