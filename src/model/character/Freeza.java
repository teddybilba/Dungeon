package model.character;

import model.Game;

public class Freeza extends PNJ implements Runnable{

	private static final long serialVersionUID = 1L;

	public Freeza(int posX, int posY, int attackRange, Game game, int life, int attackDamage){
		super(posX, posY, attackRange, game, 4, life, attackDamage, 25); 		// visionRange = 4; maxDamage = 25;
	}
	
	public void behaviour(){
		Hero hero = game.getHero();
		if(Math.abs(hero.getPosX() - this.getPosX()) <= this.getAttackRange() || Math.abs(hero.getPosY() - this.getPosY()) <= this.getAttackRange()){
			hero.setDamage(this.getAttackDamage());
		}else{super.randomMove(hero);}
	}
	
	public void moveBlocked(){			// rests and gets energy back
		this.setDamage(-2);
	}
	
	
}
