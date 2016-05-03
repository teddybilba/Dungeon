package model.item;

import java.io.Serializable;

public class Item implements Serializable{
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


