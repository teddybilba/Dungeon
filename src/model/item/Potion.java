package model.item;

import outils.Fonctions;

public class Potion extends Item {

	private static final long serialVersionUID = 1L;
	private boolean goodPotion;
	
	//permet de donner une valeur aleatoire a l' attribut goodPotion.
	
	public Potion(int x, int y){
		super(x,y);
		int val = Fonctions.randomNum(1,10);		// arbitrary choice of 7/10 chances of good potion
		if (val <= 7){
			this.goodPotion = true;
		}
		else{
			this.goodPotion = false;
		}
	}
	public boolean IsGood(){
		return this.goodPotion;
	}

}
