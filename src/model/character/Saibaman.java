package model.character;

import model.Game;

public final class Saibaman extends PNJ{
	
	private static final long serialVersionUID = 1L;


	/* ### CONSTRUCTEUR ### */
	public Saibaman(int posX, int posY, Game game){
		super(posX, posY, 1, game, 3);				// attackRange = 1 ; visionRange = 3;
	}
	
	private void kamikaze(Player target){
		int differenceX = this.getPosX() - target.getPosX();
		int differenceY = this.getPosY() - target.getPosY();
		
		if(Math.abs(differenceX) == 1 || Math.abs(differenceY) == 1){
			target.setDamage(this.getAttackDamage()*this.getLife());			// the more lives, the more the damage
			this.die();
		}
		else{super.approach(target);}					// call to method of superClass
	}
	
	public void behaviour(){
		kamikaze(game.getHero());
	}
	public void moveBlocked(){
		this.setDamage(-2); 			// Rests and gains energy
	}



}
