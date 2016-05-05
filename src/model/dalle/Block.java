package model.dalle;

import java.io.Serializable;

public class Block implements Serializable{

	private static final long serialVersionUID = 1L;
	private int posX;
	private int posY;
	private int type;							// Type : (0, wall); (1, tile); (2, teleportation); (3, enhancer)
	private boolean presence;					// Is there someone on the block?
	private boolean presenceAllowed = true;		// Is the presence allowed on the block for the players?
	
	/* ### CONSTRUCTEUR ### */
	public Block(int posX, int posY, int type){
		this.posX = posX;
		this.posY = posY;
		this.presence = false;
		this.type = type;
		if(type == 1){presenceAllowed = false;}
	}

	/* @@@ GETTERS & SETTERS @@@ */
	/* Getters */
	public int getPosX(){
		return posX;
	}
	public  int getPosY(){
		return posY;
	}
	public boolean getPresence(){
		return presence;
	}
	public boolean getPresenceAllowed(){
		return presenceAllowed;
	}
	
	/* Setters */
	public void setPosX(int posX){	
		if(posX >= 0){						//TODO add upper limit
			this.posX = posX;
		}else{System.out.println("Block posX out of bound");}
	}

	public void setPosY(int posY){
		if(posY >= 0){						//TODO add upper limit
			this.posY = posY;
		}else{System.out.println("Block posY out of bound");}
	}
	
	public void setPresence(boolean presence){
		this.presence = presence;
	}
	
}