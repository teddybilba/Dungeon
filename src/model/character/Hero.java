package model.character;

//import model.item.*;


public class Hero extends Player{
	private int coinsNumber;
	private int maxCoinsNumber;
	public Hero(int posX,int posY, int attackRange){
		super(posX, posY, attackRange);
		this.coinsNumber=0;
		this.maxCoinsNumber=50;
	}
	public int getCoinsNumber(){
		return this.coinsNumber;
	}
	public int getMaxCoinsNumber(){
		return this.maxCoinsNumber;
	}
	
	public void grabCoin (){
		this.coinsNumber+=1;
				
	}
}