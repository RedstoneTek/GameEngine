/*
 * The Spritesheet class is used to split a spritesheet into
 * small parts depending on the user's input
 * 
 * @author RedstoneTek
 * @version 0.31
 */

package com.tek.gej.engine;

import java.awt.image.BufferedImage;

public class Spritesheet {
	/*
	 * These variables hold the information relative to the spritesheet
	 */
	BufferedImage sheet;
	int casewidth, caseheight, rows, columns;
	public BufferedImage[][] crops;
	
	/*
	 * Class constructor
	 * 
	 * @param sheet The default, uncut image
	 * @param casewidth The width of one texture cell
	 * @param caseheight The height of one texture cell
	 * @param rows The amount of rows of texture cells
	 * @param columns The amount of columns of texture cells
	 * @return Spritesheet object
	 */
	public Spritesheet(BufferedImage sheet, int casewidth, int caseheight, int rows, int columns) {
		//Sets the values relative to the spritesheet class
		this.sheet = sheet;
		this.casewidth = casewidth;
		this.caseheight = caseheight;
		this.rows = rows;
		this.columns = columns;
		
		//Initializes the sheet image array
		crops = new BufferedImage[sheet.getWidth() / casewidth][sheet.getHeight() / caseheight];
		
		//Calls the init function
		init();
	}

	/*
	 * The init function will split the spritesheet into texture cells and
	 * store them into the crops multidimensional array
	 * 
	 * @return void
	 */
	public void init() {
		//Loops through every rows and columns of the spritesheet
		for(int x = 0; x < rows; x++) {
			for(int y = 0; y < columns; y++) {
				//Stores the cut sprite into the crops multidimensional array
				crops[x][y] = sheet.getSubimage(x * casewidth, y * caseheight, casewidth, caseheight);
			}
		}
	}
	
}
