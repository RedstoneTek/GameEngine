/*
 * The Window class creates a JFrame with a Canvas inside of it.
 * 
 * @author RedstoneTek
 * @version 0.3
 */

package com.tek.gej.engine;

import java.awt.Canvas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Window extends JFrame {

	/*
	 * Default JFrame related variables
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public Canvas canvas;
	public int width, height;

	/*
	 * Window constructor
	 * 
	 * @param width Width of the JFrame
	 * @param height Height of the JFrame
	 */
	public Window(int width, int height) {
		//Sets the values relative to the window
		this.width = width;
		this.height = height;
		
		//Configurates the JFrame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, width, height);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Makes the new canvas and makes it accessible to the user
		canvas = new Canvas();
		canvas.setBounds(0, 0, width, height);
		contentPane.add(canvas);
		
		//Makes the JFrame visible
		setVisible(true);
	}
}
