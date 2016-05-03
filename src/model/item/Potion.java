package model.item;

import java.util.Random;

public class Potion extends Item {
	private boolean goodPotion;
	//permet de donner une valeur aléatoire à l' attribut goodPotion.
	
	public Potion(int x, int y){
		super(x,y);
		int val= randomNum(1,5);
		if (val==1){
			this.goodPotion = true;
		}
		else{
			this.goodPotion = false;
		}
	}
	public boolean IsGood(){
		return this.goodPotion;
	}
	public int randomNum(int min, int max){
		int nb = min + (int)(Math.random() * ((max - min) + 1));
		return nb;
	}

}
