package com.tek.gej.engine;

import java.awt.image.BufferedImage;

public class Spritesheet {
	
	BufferedImage sheet;
	int casewidth, caseheight, rows, columns;
	public BufferedImage[][] crops;
	
	public Spritesheet(BufferedImage sheet, int casewidth, int caseheight, int rows, int columns) {
		this.sheet = sheet;
		this.casewidth = casewidth;
		this.caseheight = caseheight;
		this.rows = rows;
		this.columns = columns;
		
		crops = new BufferedImage[sheet.getWidth() / casewidth][sheet.getHeight() / caseheight];
		
		init();
	}

	public void init() {
		for(int x = 0; x < rows; x++) {
			for(int y = 0; y < columns; y++) {
				crops[x][y] = sheet.getSubimage(x * casewidth, y * caseheight, casewidth, caseheight);
			}
		}
	}
	
}
