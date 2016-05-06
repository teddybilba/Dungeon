package model.item;

import java.io.Serializable;

public class Coin implements Serializable{

	private static final long serialVersionUID = 1L;
	private int posX;
	private int posY;
	
	/* CONSTRUCTOR */
	public Coin(int posX, int posY){
		this.posX = posX;
		this.posY = posY;
	}	
	
	/* GETTERS */
	public int getPosX(){
		return posX;
	}
	public int getPosY(){
		return posY;
	}
}
