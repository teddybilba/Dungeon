package model.item;

public class Potion extends Item {
	private boolean goodPotion;
	
	public Potion(int x, int y,boolean bool){
		super(x,y);
		this.goodPotion=bool;
	}
	public boolean IsGood(){
		return this.goodPotion;
	}

}
