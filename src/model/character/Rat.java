package model.character;

import model.Game;
import outils.Fonctions;

public final class Rat extends PNJ {
	
	private static final long serialVersionUID = 1L;


	/* ### CONSTRUCTEUR ### */
	public Rat(int posX, int posY, Game game){
		super(posX, posY, 1, game, 3, Fonctions.randomNum(1, 3), Fonctions.randomNum(2, 5));	
		// attackRange = 1 ; visionRange = 3; life = random(1,3); attackDamage = random(3,5);
	}
	
	public void behaviour(){
		flee(game.getHero());
	}
	public void moveBlocked(){
		super.attack(); 				// If rat cannot move, it will attack
	}


}
