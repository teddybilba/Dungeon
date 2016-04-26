package model.character;

public class Player {
	private int posX;
	private int posY;
	private int vitX;
	private int vitY;
	private int life;
	private int damage;
	private int maxDamage;
	private int attackRange;
	
	
	public Player(int posX, int posY, int attackRange){
		this.posX = posX;
		this.posY = posY;
		vitX = 1;
		vitY = 1;
		life = 3;
		damage = 0;
		maxDamage = 10;
		this.attackRange= attackRange;
	}
	
	/* ##### Getters & Setters ##### */
	public int getPosX(){
		return posX;
	}
	
	public void setPosX(int posX){
		this.posX = posX;
	}
	
	/*public void setPosX(int X)*/ //TODO  Needed? 
		
	public int getPosY(){
		return posY;
	}
	public void setPosY(int posY){
		this.posY = posY;
	}
	/* Vitesse player */
	public int getVitX(){
		return vitX;
	}
	public void setVitX(int vitX){
		if(vitX > 0 && vitX < 5){
			this.vitX = vitX;
		}
	}
	
	public int getVitY(){
		return vitY;
	}
	public void setVitY(int vitY){
		if(vitY > 0 && vitY < 5){
			this.vitY = vitY;
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
	
	/* #### Methods #### */
	
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
			//TODO End game
		}
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
	}
	
	public void attack( Player target, int damage){
		if(attackIsPossible( target) == true){
			target.setDamage(damage);
			   
		}
		
	}
	
	
}