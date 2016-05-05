package model.dalle;

import java.io.Serializable;

public class Block implements Serializable{

	private static final long serialVersionUID = 1L;
	private int posX;
	private int posY;
	private int type;							// Type : (0, wall); (1, tile); (2, teleportation); (3, hurt, trap tile)
	
	/* ### CONSTRUCTEUR ### */
	public Block(int posX, int posY, int type){
		this.posX = posX;
		this.posY = posY;
		this.type = type;
	}

	/* @@@ GETTERS & SETTERS @@@ */
	/* Getters */
	public int getPosX(){
		return posX;
	}
	public  int getPosY(){
		return posY;
	}
	public int getType(){
		return type;
	}

	
	/* Setters */
	public void setPosX(int posX){	
		if(posX >= 0){						
			this.posX = posX;
		}else{System.out.println("Block posX out of bound");}
	}

	public void setPosY(int posY){
		if(posY >= 0){						
			this.posY = posY;
		}else{System.out.println("Block posY out of bound");}
	}
	
	
}