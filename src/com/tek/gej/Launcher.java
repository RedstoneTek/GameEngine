package com.tek.gej;

import com.tek.gej.engine.Handler;
import com.tek.gej.modules.example.Example;

public class Launcher {

	public static void main(String[] args) {
		Handler it = new Handler(50);
		it.registerModule(new Example());
		it.init();
	}

}