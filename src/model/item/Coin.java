package model.item;

public class Coin {
	private int posX;
	private int posY;
	
	public Coin(int x, int y){
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
