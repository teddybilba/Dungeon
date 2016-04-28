package model.item;

public class Item {
	private int posX;
	private int posY;
	
	public Item(int x, int y){
		this.posX=x;
		this.posY=y;
	}
	public int getPosX(){
		return posX;
	}
	public int getPosY(){
		return posY;
	}
}


