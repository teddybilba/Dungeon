package model.character;

import model.Game;
import java.util.ArrayList;
import java.io.Serializable;

public class Player implements Serializable{
	
	private static final long serialVersionUID = 1L;
	protected Game game;
	private int posX;
	private int posY;
	private int vit;
	private String direction;
	private int life;
	private int damage;
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
		vit = 1;
		life = 1;
		damage = 0;
		maxDamage = 10;
		this.attackRange= attackRange;
		attackDamage = 1;
	}
	
	/* @@@ GETTERS & SETTERS @@@ */
	public int getPosX(){
		return posX;
	}
	public void setPosX(int posX){
		this.posX = posX;
	}
			
	public int getPosY(){
		return posY;
	}
	public void setPosY(int posY){
		this.posY = posY;
	}
	
	/* Vitesse player */
	public int getVit(){
		return vit;
	}
	public void setVit(int vit){
		if(vit > 0 && vit < 3){
			this.vit = vit;
		}
	}
	
	/* Life */
	public int getLife(){
		return life;
	}
	public void setLife(int life){
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
		else if(damage < 0){						//reducing the damage (potions,etc)
			if(this.damage < -damage){				// damage must be >= 0
				this.damage = 0;
			}
			else{
				this.damage += damage;
				System.out.println("damage effective");
				}
			}
		else{										// if damage not out of bound, adds damage
			this.damage += damage;
			if(damage > maxDamage){
				loseLife();							// too much damage, hence player loses life
				System.out.println("lose a life");
			}
		}
	}

	public int getMaxDamage(){
		return maxDamage;
	}
	public void setMaxDamage(int maxDamage){
		if(maxDamage > 0){
			this.maxDamage = maxDamage;
		}
	}
	
	/*Attack Range*/
	public int getAttackRange(){
		return attackRange;
	}
	public void setAttackRange(int range){
		if(range > 0){
			this.attackRange = range;
		}
	}
	
	public int getAttackDamage(){
		return attackDamage;
	}
	public void setAttackDamage(int attackDamage){
		if(attackDamage > 0 && attackDamage < 10){
			this.attackDamage = attackDamage;
		}
	}
	
	/* Moves and direction */
	public ArrayList<String> getListPossibleMoves(){
		listPossibleMoves.clear();
		if(! game.Collision(posX-1, posY)){
			listPossibleMoves.add("W");
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
	
	public String getDirection(){
		return direction;
	}
	public void setDirection(String direction){
		if(direction.equals("N") || direction.equals("S") || direction.equals("E") || direction.equals("W")){
			this.direction = direction;
		}else{System.out.println("Mauvais setDirection de " + this);}
	}
	
	/* Position just in face of a given position determined by the direction of the player */
	public int getPosXInFace(int posX){
		int returnedPosition = posX;
		if(this.getDirection().equals("E")){
			returnedPosition = posX + 1;
		}
		else if(this.getDirection().equals("W")){
			returnedPosition = posX - 1;
		}
		return returnedPosition;
	}
	
	public int getPosYInFace(int posY){
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
	
	public void die(){
		setLife(0);
		
		// TODO Game Over pour le personnage et eliminer les ennemis.
	}
	
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
		int posEnnemyX = getPosXInFace(this.getPosX());
		int posEnnemyY = getPosYInFace(this.getPosY());
		
		setDamage(this.getAttackDamage());
		
	}
	
	
}