package outils;

public class Fonctions {
	
	/* Returns random number between two given extrema */
	public static int randomNum(int min, int max){
		int nb = min + (int)(Math.random() * ((max - min) + 1));
		return nb;
	}
}
