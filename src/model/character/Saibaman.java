package model.character;

import model.Game;

public final class Saibaman extends PNJ implements Runnable{
	
	private static final long serialVersionUID = 1L;


	/* ### CONSTRUCTEUR ### */
	public Saibaman(int posX, int posY, Game game){
		super(posX, posY, 1, game, 3);				// attackRange = 1 ; visionRange = 3;
	}
	
	private void kamikaze(Player target){
		int targetPosX = target.getPosX();
		int targetPosY = target.getPosY();
		int posX = this.getPosX();
		int posY = this.getPosY();
		int differenceX = posX - targetPosX;
		int differenceY = posY - targetPosY;
		
		if(Math.abs(differenceX) == 1 || Math.abs(differenceY) == 1){
			System.out.println(differenceX);
			System.out.println(differenceX + "   " + differenceY + "");
			int totalDamage = (this.getAttackDamage()+2)*this.getLife();
			target.setDamage(totalDamage);
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
