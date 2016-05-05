package model.character;

import model.Game;

public class Rat extends PNJ implements Runnable{
	
	private static final long serialVersionUID = 1L;


	/* ### CONSTRUCTEUR ### */
	public Rat(int posX, int posY, Game game){
		super(posX, posY, 1, game, 3);				// attackRange = 1 ; visionRange = 3;
	}
	
	public void behaviour(){
		flee(game.getHero());
	}
	public void moveBlocked(){
		System.out.println("I, rat, cannot move !" + this);
	}


}
