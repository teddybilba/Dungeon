package model.item;

public class Potion extends Item {
	private boolean goodPotion;
	
	public Potion(boolean bool,int x, int y){
		super(x,y);
		this.goodPotion=bool;
	}
	public boolean IsGood(){
		return this.goodPotion;
	}

}
