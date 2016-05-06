package model.character;
import model.item.*;
import outils.Fonctions;

import java.util.ArrayList;

import model.Game;

public abstract class PNJ extends Player implements Runnable{

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
					this.move(Integer.signum(differenceX),0);										
				}else if(! game.Collision(posX, newPosY)){
					this.move(0, Integer.signum(differenceY));
				}else{
					this.moveBlocked();
					}
			}else{
				if(! game.Collision(posX, newPosY)){				
					this.move(0, Integer.signum(differenceY));									
				}else if(! game.Collision(newPosX, posY)){
					this.move(Integer.signum(differenceX),0);
				}else{
					this.moveBlocked();
					}	
				}
		}else{
			randomMove();																						// *** Just moves randomly because player not in view 
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
			if(Math.abs(differenceX) >= Math.abs(differenceY)){
				if(! game.Collision(newPosX, posY)){				
					this.move(-Integer.signum(differenceX),0);											
				}else if(! game.Collision(posX, newPosY)){
					this.move(0, -Integer.signum(differenceY));
				}else{
					this.moveBlocked();
					}
			}else{
				if(! game.Collision(posX, newPosY)){				
					this.move(0, -Integer.signum(differenceY));											
				}else if(! game.Collision(newPosX, posY)){
					this.move(-Integer.signum(differenceX),0);
				}else{
					this.moveBlocked();
					}	
				}
		}else{
			randomMove();																						// *** Just moves randomly because player not in view 
			}	
		}
	
	protected void randomMove(){
		ArrayList<String> listPossibleMoves = this.getListPossibleMoves();
		if(listPossibleMoves.size() != 0){
			int randomNum = Fonctions.randomNum(0, listPossibleMoves.size() -1);
			String direction = listPossibleMoves.get(randomNum);
			if(direction.equals("E")){this.move(1, 0);}
			if(direction.equals("W")){this.move(-1, 0);}
			if(direction.equals("N")){this.move(0, 1);}
			if(direction.equals("S")){this.move(0, -1);}
		}else{this.moveBlocked();}
	}
	
	
	abstract public void moveBlocked();				// Each type of pnj behaves differently if blocked
	
	abstract public void behaviour();				// Abstract method, implemented differently for each type of pnj
	
	public void attack(){
		Hero hero = game.getHero();
		if(Math.abs(hero.getPosX() - this.getPosX()) == 1 || Math.abs(hero.getPosY() - this.getPosY()) == 1){
			hero.setDamage(this.getAttackDamage());
		}
		
	}
	
	
	public void run(){
		try{
			while(true){
				Thread.sleep(500);
				behaviour();
				notifyObservers();
				while (game.isOnPause()){
					Thread.sleep(200);
				}
				}
			}catch(Exception e){} 
		}
}