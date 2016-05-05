package model.character;

import model.Game;
import java.util.ArrayList;
import java.io.Serializable;

public abstract class Player implements Serializable{
	
	private static final long serialVersionUID = 1L;
	protected Game game;
	private int posX;
	private int posY;
	private int vit = 1;
	private String direction;
	private int life;
	private int damage = 0;
	private int maxDamage;
	private int attackRange;
	private int attackDamage;
	private ArrayList<String> listPossibleMoves = new ArrayList<String>();
	
	
	/* ### CONSTRUCTEUR ### */
	public Player(int posX, int posY, int attackRange, Game game){
		this.game = game;
		this.posX = posX;
		this.posY = posY;
		this.direction = "S";
		life = 1;
		maxDamage = 10;
		this.attackRange= attackRange;
		attackDamage = 2;
	}
	// constructeur surcharge	
	public Player(int posX, int posY, int attackRange, Game game, int life, int attackDamage){
		this.game = game;
		this.posX = posX;
		this.posY = posY;
		this.direction = "S";
		this.life = life;
		maxDamage = 0;
		this.attackRange= attackRange;
		this.attackDamage = attackDamage;
	}
	
	public Player(int posX, int posY, int attackRange, Game game, int life, int attackDamage, int maxDamage){
		this.game = game;
		this.posX = posX;
		this.posY = posY;
		this.direction = "S";
		this.life = life;
		this.maxDamage = maxDamage;
		this.attackRange= attackRange;
		this.attackDamage = attackDamage;
	}
	
	
	/* @@@ GETTERS & SETTERS @@@ */
	public int getPosX(){
		return posX;
	}
	public void setPosX(int posX){
		if(game.getMapRange() <= posX && posX < game.getMapRange() + game.getSize()){	// pas de position en dehors du plateau
			this.posX = posX;
		}else{System.out.println("setPosX out of range :" + this);}
	}
			
	public int getPosY(){
		return posY;
	}
	public void setPosY(int posY){
		if(game.getMapRange() <= posY && posY < game.getMapRange() + game.getSize()){
			this.posY = posY;
		}else{System.out.println("setPosY out of range :" + this);}
	}
	
	/* Vitesse player */
	public int getVit(){
		return vit;
	}
	public void setVit(int vit){
		if(vit > 0 && vit <= 3){					// there is an arbitrary maximum speed
			this.vit = vit;
		}else{System.out.println("Vitesse out of range (1,3) : " + vit);}
	}
	
	/* Life */
	public int getLife(){
		return life;
	}
	public void setLife(int life){					// there is an arbitrary maximum life number
		if(life >= 0 && life < 10){
			this.life = life;
		}
	}
	
	/* Damage */
	public int getDamage(){
		return damage;
	}
	public void setDamage(int damage){
		if(damage > 0 && damage > maxDamage){
			loseLife();								//too much damage, hence player loses life & new damage set to 0
			this.damage = 0;
		}
		else if(damage < 0){						//reducing the damage (potions, special power)
			if(this.damage < -damage){				// damage must be >= 0
				this.damage = 0;
			}
			else{
				this.damage += damage;
				}
			}
		else{										// if damage not out of bound, adds damage
			int totalDamage = this.damage + damage;
			if(totalDamage > maxDamage){
				loseLife();							// too much total damage (previous + new damage) , hence player loses life
				this.damage = 0;					// and damage set to 0
				System.out.println("lose a life");
			}else{									// if totalDamage <= maxDamage, just inflict damage to player
				this.damage = totalDamage;
			}
		}
	}

	public int getMaxDamage(){
		return maxDamage;
	}
	public void setMaxDamage(int maxDamage){
		if(maxDamage > 0 && maxDamage <= 20){		// arbitrary maximum damage
			this.maxDamage = maxDamage;
		}
	}
	
	/*Attack Range*/
	public int getAttackRange(){
		return attackRange;
	}
	public void setAttackRange(int attackRange){
		if(attackRange > 0 && attackRange < 5){		// arbitrary max attackRange
			this.attackRange = attackRange;
		}
	}
	
	public int getAttackDamage(){
		return attackDamage;
	}
	public void setAttackDamage(int attackDamage){
		if(attackDamage > 0 && attackDamage <= 10){		// arbitrary maxDamage (half of maxDamage)
			this.attackDamage = attackDamage;
		}
	}
	
	/* Moves and direction */
	protected ArrayList<String> getListPossibleMoves(){
		listPossibleMoves.clear();						// new list each time the function is called
		if(! game.Collision(posX-1, posY)){
			listPossibleMoves.add("W");					// N,S,E,W directions
		}
		if(! game.Collision(posX+1, posY)){
			listPossibleMoves.add("E");
		}
		if(! game.Collision(posX, posY+1)){
			listPossibleMoves.add("N");
		}
		if(! game.Collision(posX, posY-1)){
			listPossibleMoves.add("S");
		}
		return listPossibleMoves;
	}
	
	private String getDirection(){
		return direction;
	}
	private void setDirection(String direction){
		if(direction.equals("N") || direction.equals("S") || direction.equals("E") || direction.equals("W")){
			this.direction = direction;
		}else{System.out.println("Mauvais setDirection de " + this + "direction demandee : " + direction);}
	}
	
	/* Position just in face of a given position determined by the direction of the player */
	public int getPosXInFace(){
		int returnedPosition = posX;
		if(this.getDirection().equals("E")){
			returnedPosition = posX + 1;
		}
		else if(this.getDirection().equals("W")){
			returnedPosition = posX - 1;
		}
		return returnedPosition;
	}
	
	public int getPosYInFace(){
		int returnedPosition = posY;
		if(this.getDirection().equals("N")){
			returnedPosition = posY + 1;
		}
		else if(this.getDirection().equals("S")){
			returnedPosition = posY - 1;
		}
		return returnedPosition;
	}
	
	
	/* °°°° Methods °°° */
	
	public void move(int X, int Y){
		setPosX(getPosX() + X);
		setPosY(getPosY() + Y);
	}
	
	public void winLife(){
		setLife(this.life + 1);
	}
	public void loseLife(){
		if(this.life > 0){
			setLife(this.life - 1);
		}
		else{
			System.out.println("You have lost all your lives. "+" GAME OVER! ");
			this.die();
			//TODO End game
		}
	}
	
	abstract public void die();
		
		// TODO Game Over pour le personnage et eliminer les ennemis.
		// TODO abstract class? 
	
	/* public void MoreDamage(int injury){
		int level= getDamage();
		if (level+injury >= this.maxDamage){		// UTILE ??? (j'ai defini la methode setDamage() )
			LoseLife();
			this.damage=0;
		}
		else{
			this.damage= level+injury;
		}
	} */
	
/*	
	public boolean attackIsPossible( Player target){
		// permet de voir si la port�e de l' attaquant permet de toucher la cible
		boolean res= false;
		int xTar = target.getPosX();
		int yTar = target.getPosY();
		int xAtt = this.getPosX();
		int yAtt = this.getPosY();
		int range = this.getAttackRange();
		
		if(range <= Math.abs(xTar-xAtt) && range <= Math.abs(yTar-yAtt)){
			res=true;
		}
		return res;
	}*/
	
	public void attack(){
		int posEnemyX = getPosXInFace();
		int posEnemyY = getPosYInFace();
		//PNJ enemy = game.getPlayerUsingPosition()
		setDamage(this.getAttackDamage());
		
	}
	
	
}