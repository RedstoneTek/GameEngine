package com.tek.gej.modules.example;

import com.tek.gej.engine.Handler;

public class Launcher {

	public static void main(String[] args) {
		Handler it = new Handler(50);
		it.registerModule(new Example());
		it.init();
	}

}