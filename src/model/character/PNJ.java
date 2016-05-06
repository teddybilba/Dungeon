package model.character;
import model.item.*;
import outils.Fonctions;

import java.util.ArrayList;

import model.Game;

public abstract class PNJ extends Player{

	private static final long serialVersionUID = 1L;
	private int visionRange;
	
	/* ### CONSTRUCTEUR ### */
	public PNJ(int posX, int posY, int attackRange, Game game, int visionRange){
		super(posX, posY, attackRange, game);
		this.visionRange = visionRange;
	}

	public PNJ(int posX, int posY, int attackRange, Game game, int visionRange, int life, int attackDamage){
		super(posX, posY, attackRange, game, life, attackDamage);
		this.visionRange = visionRange;
	}
	public PNJ(int posX, int posY, int attackRange, Game game, int visionRange, int life, int attackDamage, int maxDamage){
		super(posX, posY, attackRange, game, life, attackDamage, maxDamage);
		this.visionRange = visionRange;
	}
	
	/* @@@ GETTERS & SETTERS @@@ */
	public int getVisionRange(){
		return visionRange;
	}
	
	public void setVisionRange(int visionRange){
		if(visionRange > 0){
			this.visionRange = visionRange;
		}
	}
	
	public void dropItem(){
		int randomNum = Fonctions.randomNum(1,2);
		if(randomNum == 1){
			Coin coin = new Coin(this.getPosX(), this.getPosY());
			game.addCoins(coin);
		}
		else{
			Potion potion = new Potion(this.getPosX(), this.getPosY());
			game.addPotion(potion);
		}
	}
	
	public void die(){
		dropItem();
		game.pnjDie(this);
	}
	
	/* §§§ MOVEMENT §§§ */
	
	protected void flee(Player target){
		int targetPosX = target.getPosX();
		int targetPosY = target.getPosY();
		int posX = this.getPosX();
		int posY = this.getPosY();
		int differenceX = posX - targetPosX;
		int differenceY = posY - targetPosY;
		int newPosX = posX + (Integer.signum(differenceX));				// Just use the sign of the difference to determine which way to go
		int newPosY = posY + (Integer.signum(differenceY));
	
		if(Math.abs(differenceX) <= this.getVisionRange() && Math.abs(differenceY) <= this.getVisionRange()){  			// If player in visionRange PNJ flees, if not in view just moves randomly ***
			if(Math.abs(differenceX) <= Math.abs(differenceY)){
				if(! game.Collision(newPosX, posY)){				
					this.setPosX(newPosX);										
				}else if(! game.Collision(posX, newPosY)){
					this.setPosY(newPosY);
				}else{
					this.moveBlocked();
					}
			}else{
				if(! game.Collision(posX, newPosY)){				
					this.setPosY(newPosY);										
				}else if(! game.Collision(newPosX, posY)){
					this.setPosX(newPosX);
				}else{
					this.moveBlocked();
					}	
				}
		}else{
			randomMove(this);																						// *** Just moves randomly because player not in view 
		}
	}

	protected void approach(Player target){
		int targetPosX = target.getPosX();
		int targetPosY = target.getPosY();
		int posX = this.getPosX();
		int posY = this.getPosY();
		int differenceX = posX - targetPosX;
		int differenceY = posY - targetPosY;
		int newPosX = posX - (Integer.signum(differenceX));				
		int newPosY = posY - (Integer.signum(differenceY));
	
		if(Math.abs(differenceX) <= this.getVisionRange() && Math.abs(differenceY) <= this.getVisionRange()){  			// If player in visionRange PNJ approaches, if not in view just moves randomly ***
			if(Math.abs(differenceX) <= Math.abs(differenceY)){
				if(! game.Collision(newPosX, posY)){				
					this.setPosX(newPosX);											
				}else if(! game.Collision(posX, newPosY)){
					this.setPosY(newPosY);
				}else{
					this.moveBlocked();
					}
			}else{
				if(! game.Collision(posX, newPosY)){				
					this.setPosY(newPosY);											
				}else if(! game.Collision(newPosX, posY)){
					this.setPosX(newPosX);
				}else{
					this.moveBlocked();
					}	
				}
		}else{
			randomMove(this);																						// *** Just moves randomly because player not in view 
			}	
		}
	
	protected void randomMove(Player target){
		ArrayList<String> listPossibleMoves = this.getListPossibleMoves();
		if(listPossibleMoves.size() != 0){
			int randomNum = Fonctions.randomNum(0, listPossibleMoves.size() -1);
			String direction = listPossibleMoves.get(randomNum);
			if(direction.equals("E")){this.setPosX(this.getPosX()+1);}
			if(direction.equals("W")){this.setPosX(this.getPosX()-1);}
			if(direction.equals("N")){this.setPosY(this.getPosY()+1);}
			if(direction.equals("S")){this.setPosY(this.getPosY()-1);}
		}else{this.moveBlocked();}
	}
	
	
	abstract public void moveBlocked();				// Each type of pnj behaves differently if blocked
	
	abstract public void behaviour();				// Abstract method, implemented differently for each type of pnj
	
	public void attack(){
		Hero hero = game.getHero();
		if(Math.abs(hero.getPosX() - this.getPosX()) == 1 || Math.abs(hero.getPosY() - this.getPosY()) == 1){
			hero.setDamage(this.getAttackDamage());
			System.out.println("I, " + this + "inflict : " +getAttackDamage());
		}
		
	}
	
	
	public void run(){
		try{
			while(true){
				Thread.sleep(1000);
				behaviour();
				notifyObservers();
				while (game.isOnPause()){
					Thread.sleep(500);
				}
				}
			}catch(Exception e){System.out.println("Problem with thread !");
			e.printStackTrace();} 
		}
}