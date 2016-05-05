package model.character;

import java.util.ArrayList;
import model.Game;
import model.item.Potion;
import outils.Fonctions;


public class Hero extends Player{

	private static final long serialVersionUID = 1L;
	private int coinsNumber;
	private int maxCoinsNumber;
	private int maxPotion;
	private ArrayList<Potion> potionsInventory = new ArrayList<Potion> ();
	private int specialPowerNum;
	private int maxPowerNum;
	
	
	/* ### CONSTRUCTEUR ### */
	public Hero(int posX,int posY, int attackRange, Game game, int life, int attackDamage, int maxDamage){
		super(posX, posY, attackRange, game, life, attackDamage, maxDamage);
		
		this.coinsNumber = 0;
		this.maxCoinsNumber = 30;
		this.maxPotion = 5;
		this.specialPowerNum = 0;
		this.maxPowerNum = 3;
		
	}
	
	/* @@@ GETTERS & SETTERS @@@ */
	public int getCoinsNumber(){
		return coinsNumber;
	}	
	public int getMaxCoinsNumber(){
		return maxCoinsNumber;
	}
	public int getPotionNumInventory(){
		return potionsInventory.size();
	}
	public int getMaxPotion(){
		return maxPotion;
	}
	public int getSpecialPowerNum(){
		return specialPowerNum;
	}

	//MANIPULATION DES PIECES
	private void addCoins(int coinsNumber){
		int totalCoinsNumber = this.coinsNumber + coinsNumber;
		if (totalCoinsNumber >= maxCoinsNumber){				// If hero grabs enough coins, he wins a life
			this.coinsNumber = coinsNumber - maxCoinsNumber;
			if(specialPowerNum < maxPowerNum){
				specialPowerNum += 1;
				}
			else{
				winLife();										// If max number of special power, hero wins life
				this.specialPowerNum = 0;
				}
			}
		else{
			this.coinsNumber = totalCoinsNumber;
			}
		}
	
	public void grabCoin (){
		addCoins(Fonctions.randomNum(1, 10));
		}
	
	//POUVOIR SPECIAL( INVINCIBILITE)
	public boolean powerPossible(){
		boolean res = false;
		if (specialPowerNum > 0){
			res = true;
		}
		return res;
	}	

	public void reduceDamagePower(){
		if (powerPossible() == true){
			setDamage(-this.getDamage());
			specialPowerNum -= 1;
		}
	}
	
	//MANUPULATION DES POTIONS
	public void grabPotion(Potion potion){
		potionsInventory.add(potion);		
		}
	
	public void drinkPotion(){
		if (potionsInventory.size() >= 1){
			Potion potion = potionsInventory.get(0);		// Hero drinks the potions in the same order he grabbed them
			potionsInventory.remove(0);
			if (potion.IsGood() == true){					// There are good and bad potions
				winLife();
				}
			else{
				loseLife();
				}
			}	
		}
}