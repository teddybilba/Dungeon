package outils;

import model.Game;

public class Fonctions {
	
	public static boolean collide(int posX, int posY){
		boolean res = false;
		
		return res;
	}
	
	/* Returns random number between two given extrema */
	public static int randomNum(int min, int max){
		int nb = min + (int)(Math.random() * ((max - min) + 1));
		return nb;
	}
}
