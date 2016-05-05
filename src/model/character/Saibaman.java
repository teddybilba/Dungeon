package model.character;

import model.Game;

public class Saibaman extends PNJ implements Runnable{
	
	private static final long serialVersionUID = 1L;


	/* ### CONSTRUCTEUR ### */
	public Saibaman(int posX, int posY, Game game){
		super(posX, posY, 1, game, 3);				// attackRange = 1 ; visionRange = 3;
	}
	
	public void kamikaze(Player target){
		int targetPosX = target.getPosX();
		int targetPosY = target.getPosY();
		int posX = this.getPosX();
		int posY = this.getPosY();
		int differenceX = posX - targetPosX;
		int differenceY = posY - targetPosY;
		if(differenceX == 1 || differenceY == 1){
			target.setDamage((this.getMaxDamage()-this.getDamage())*this.getLife());
			this.die();
		}
		else{this.approach(target);}
	}
	
	public void behaviour(){
		kamikaze(game.getHero());
	}
	public void moveBlocked(){
		System.out.println("I, saibaman, cannot move !" + this);
	}



}
