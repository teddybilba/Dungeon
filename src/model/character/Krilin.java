package model.character;

import java.util.ArrayList;

import model.Game;
import outils.Fonctions;

public class Krilin extends PNJ implements Runnable{
	

	private static final long serialVersionUID = 1L;
	public Krilin(int posX, int posY, Game game, int maxDamage){
		super(posX, posY, 2, game, 3, 2, 4, maxDamage);		//attackRange = 2; vision range = 3; life = 2; attackDamage = 4;
	}
	
	/*private void approachPNJ(Player target){
		int targetPosX = target.getPosX();
		int targetPosY = target.getPosY();
		int posX = this.getPosX();
		int posY = this.getPosY();
		int differenceX = posX - targetPosX;
		int differenceY = posY - targetPosY;
		int newPosX = posX - (Integer.signum(differenceX));				
		int newPosY = posY - (Integer.signum(differenceY));
	
		if(Math.abs(differenceX) <= this.getVisionRange() && Math.abs(differenceY) <= this.getVisionRange()){  			// If player in visionRange PNJ approaches, if not in view just approaches hero ***
			if(Math.abs(differenceX) <= Math.abs(differenceY)){
				if(! game.collisionWall(newPosX, posY)){				
					this.setPosX(newPosX);											
				}else if(! game.collisionWall(posX, newPosY)){
					this.setPosY(newPosY);
				}else{
					followHero();
					}
			}else{
				if(! game.collisionWall(posX, newPosY)){				
					this.setPosY(newPosY);											
				}else if(! game.collisionWall(newPosX, posY)){
					this.setPosX(newPosX);
				}else{
					followHero();
					}	
				}
		}else{
			followHero();																						// *** Just follows goku instead
			}	
		}*/
	
	// Krilin approches goku no matter where he is. Vision unlimited for that (difference with other pnjs)
	private void followHero(){
		int heroPosX = game.getHero().getPosX();
		int heroPosY = game.getHero().getPosY();
		int posX = this.getPosX();
		int posY = this.getPosY();
		int differenceX = posX - heroPosX;
		int differenceY = posY - heroPosY;
		int newPosX = posX - (Integer.signum(differenceX));				
		int newPosY = posY - (Integer.signum(differenceY));
																	
		if(Math.abs(differenceX) >= Math.abs(differenceY)){
			if(! game.collisionWall(newPosX, posY)){				
				this.setPosX(newPosX);											
			}else if(! game.collisionWall(posX, newPosY)){
				this.setPosY(newPosY);
			}else{
				moveBlocked();
				}
		}else{
			if(! game.collisionWall(posX, newPosY)){				
				this.setPosY(newPosY);											
			}else if(! game.collisionWall(newPosX, posY)){
				this.setPosX(newPosX);
			}else{
				moveBlocked();
				}	
			}
		
		}
	
	
	// Determines if krilin is close to goku
	private boolean isCloseToHero(){
		return (Math.abs(this.getPosX()-game.getHero().getPosX()) < this.getVisionRange() || Math.abs(this.getPosY()-game.getHero().getPosY()) < this.getVisionRange());
	}
	// Return random pnj in view of Krilin
	/*private PNJ getPnjInView(){
		ArrayList<PNJ> pnjs = new ArrayList<PNJ>();
		for(PNJ pnj: game.getPNJs()){
			if(Math.abs(this.getPosX()-pnj.getPosX()) <= this.getVisionRange() || Math.abs(this.getPosY()-pnj.getPosY()) < this.getVisionRange()){
				pnjs.add(pnj);
				} 
			}
		return pnjs.get(Fonctions.randomNum(0, pnjs.size()));
	}*/
	
	private void attackPNJ(){
		for(PNJ pnj : game.getPNJs()){
			if(Math.abs(this.getPosX()-pnj.getPosX()) <= this.getAttackRange() || Math.abs(this.getPosY()-pnj.getPosY()) < this.getAttackRange()){
				pnj.setDamage(this.getAttackDamage());
			}
			/*else{
				PNJ target = getPnjInView();
				if(target != null){
					approachPNJ(target);
				}else{followHero();}
			}*/
		}
	}
	
	public void moveBlocked(){
		this.setDamage(-3);				// rests and gains energy
	}
	public void behaviour(){
		followHero();
		if(isCloseToHero()){
			attackPNJ();
			}
		}
}
