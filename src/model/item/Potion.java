package model.item;

import java.util.Random;

public class Potion extends Item {
	private boolean goodPotion;
	private Random random=new Random();//permet de donner une valeur aléatoire à l' attribut goodPotion.
	
	public Potion(int x, int y){
		super(x,y);
		this.goodPotion=random.nextBoolean();
	}
	public boolean IsGood(){
		return this.goodPotion;
	}

}
