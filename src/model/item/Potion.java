package model.item;

import java.io.Serializable;
import outils.Fonctions;

public class Potion implements Serializable{

	private static final long serialVersionUID = 1L;
	private int posX;
	private int posY;
	private boolean isGoodPotion;			// existe des bonnes et des mauvaises potions
	
	/*	CONSTRUCTOR	*/
	public Potion(int posX, int posY){
		this.posX = posX;
		this.posY = posY;
		int val = Fonctions.randomNum(1,10);		// arbitrary choice of 7/10 chances of good potion
		if (val <= 7){
			this.isGoodPotion = true;
		}
		else{
			this.isGoodPotion = false;
		}
	}
	
	/*	GETTERS	*/
	public int getPosX(){
		return posX;
	}
	public int getPosY(){
		return posY;
	}
	
	public boolean IsGood(){
		return this.isGoodPotion;
	}


	

	
}
