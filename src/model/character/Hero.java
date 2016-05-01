package model.character;

import java.util.ArrayList;

import model.item.Potion;


public class Hero extends Player{
	private int coinsNumber;
	private int maxCoinsNumber;
	private int maxPotion;
	private ArrayList<Potion> potionsInventory=new ArrayList<Potion> ();
	private int specialPowerNum;
	private int maxPowerNum;
	/*CONSTRUCTEUR*/
	public Hero(int posX,int posY, int attackRange){
		super(posX, posY, attackRange);
		this.coinsNumber=0;
		this.maxCoinsNumber=20;
		this.maxPotion=5;
		this.specialPowerNum=0;
		this.maxPowerNum=3;
		
	}
	//GETTERS
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
	public int getSpecialPowerNum(){
		return this.specialPowerNum;
	}
	//FCT RANDOM	
	public int randomNum(int min, int max){
		int nb = min + (int)(Math.random() * ((max - min) + 1));
		return nb;
	}
	//MANIPULATION DES PIECES
	
	public void grabCoin (){
		//Si on a 50 pièces, on gagne un pouvoir bonus (3 max) si
		//on en a deja 3 on gagne une vie.
		this.coinsNumber+=1;
		if (coinsNumber>=maxCoinsNumber){
			this.coinsNumber-=maxCoinsNumber;
			if (specialPowerNum==maxPowerNum){
				winLife();
			}
			else{
				this.specialPowerNum+=1;
			}
		}
			
				
	}
	//POUVOIR SPECIAL( INVINCIBILITE)
	public boolean powerPossible(){
		boolean res= false;
		if (specialPowerNum>0){
			res=true;
		}
		return res;
	}
	public void specialPower(){
		// TODO implementer l' invincibilt" du joueur pendant une certaine durée
		//( thread je suppose)...
	}
	public void usePower(){
		if (powerPossible()==true){
			specialPower();
		}
	}
	//MANUPULATION DES POTIONS
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