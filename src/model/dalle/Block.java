package model.dalle;

public class Block {


	private int posX;
	private int posY;
	private boolean presence;				// Is there someone on the block?
	private boolean presenceAllowed;		// Is the presence allowed on the block for the players?
	
	/* ### CONSTRUCTEUR ### */
	public Block(int posX, int posY, boolean presenceAllowed){
		this.posX = posX;
		this.posY = posY;
		this.presence = false;
		this.presenceAllowed = presenceAllowed;
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
	public void setPresenceAllowed(boolean presenceAllowed){
		this.presenceAllowed = presenceAllowed;
	}
}