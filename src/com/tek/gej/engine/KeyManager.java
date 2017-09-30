/*
 * The KeyManager class will handle key types and key states
 * 
 * @author RedstoneTek
 * @version 0.3
 */

package com.tek.gej.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{
	
	//This boolean array holds every pressed key, available through pressed[KeyEvent.VK_YOURKEY]
	public boolean[] pressed = new boolean[1024];
	
	//Holds the GameInstance instance
	GameInstance gi;
	
	/*
	 * Class constructor
	 * 
	 * @param gi GameInstance for the class to use
	 * @return KeyManager object
	 */
	public KeyManager(GameInstance gi) {
		//Sets the gameinstance object
		this.gi = gi;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		e.setKeyCode(e.getKeyChar());
		gi.keyTyped(e);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		pressed[e.getKeyCode()] = true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		while(pressed[e.getKeyCode()]) {
			pressed[e.getKeyCode()] = false;
		}
	}
	
}
