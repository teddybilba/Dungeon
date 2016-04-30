package model.character;

import java.util.ArrayList;

import model.item.Potion;


public class Hero extends Player{
	private int coinsNumber;
	private int maxCoinsNumber;
	private int maxPotion;
	private ArrayList<Potion> potionsInventory=new ArrayList<Potion> ();
	
	public Hero(int posX,int posY, int attackRange){
		super(posX, posY, attackRange);
		this.coinsNumber=0;
		this.maxCoinsNumber=50;
		this.maxPotion=5;
		
	}
	public int getCoinsNumber(){
		return this.coinsNumber;
	}
	public int getMaxCoinsNumber(){
		return this.maxCoinsNumber;
	}
	public int getPotionNumInventory(){
		return this.potionsInventory.size();
	}
	public int getMaxPotion(){
		return this.maxPotion;
	}
	public int randomNum(int min, int max){
		int nb = min + (int)(Math.random() * ((max - min) + 1));
		return nb;
	}
	
	public void grabCoin (){
		this.coinsNumber+=1;
				
	}
	public void grabPotion(Potion potion){
		potionsInventory.add(potion);
		
		
	}
	public int choseRandomPotion(){
		int index =randomNum(0,getPotionNumInventory()-1);
		return index;
	}
	public void drinkPotion(){
		int index= choseRandomPotion();
		Potion potion=potionsInventory.get(index);
		potionsInventory.remove(index);
		if (potion.IsGood()==true){
			winLife();
		}
		else{
			loseLife();
		}
		
	}
}