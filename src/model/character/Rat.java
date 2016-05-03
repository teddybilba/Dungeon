package model.character;

import model.Game;
import java.util.ArrayList;

public class Rat extends PNJ implements Runnable{
	
	private int type;
	
	public Rat(int posX, int posY, int visionRange, int attackRange, Game game){
		super(posX, posY, visionRange, attackRange, game);
		if(randomNum(1,10) == 1){
			type = 0;
			}
		else{type = 1;
		} 
	}
	
	public int getType(){
		return type;
	}
	
	private void changeType(){
		type = (type+1) % 2;			// Changes '1 in 0' and "0 in 1"
		}
	
	public void flee(Player target){
		int targetPosX = target.getPosX();
		int targetPosY = target.getPosY();
		int posX = this.getPosX();
		int posY = this.getPosY();
		int differenceX = posX - targetPosX;
		int differenceY = posY - targetPosY;
		int newPosX = posX + (Integer.signum(differenceX));
		int newPosY = posY + (Integer.signum(differenceY));
		
		if(Math.abs(differenceX) <= this.getVisionRange() && Math.abs(differenceY) <= this.getVisionRange()){  			// If player in visionRange PNJ flees, if not in view just moves randomly ***
				if(Math.abs(differenceX) <= Math.abs(differenceY)){
					if(! game.Collision(newPosX, posY)){				
						this.setPosX(newPosX);										
					}else if(! game.Collision(posX, newPosY)){
						this.setPosY(newPosY);
					}else{
						this.changeType();
						}
				}else{
					if(! game.Collision(posX, newPosY)){				
						this.setPosY(newPosY);										
					}else if(! game.Collision(newPosX, posY)){
						this.setPosX(newPosX);
					}else{
						this.changeType();
						}	
					}
		}else{
			randomMove(this);																						// *** Just moves randomly because player not in view 
		}
}

	public void approach(Player target){
		int targetPosX = target.getPosX();
		int targetPosY = target.getPosY();
		int posX = this.getPosX();
		int posY = this.getPosY();
		int differenceX = posX - targetPosX;
		int differenceY = posY - targetPosY;
		int newPosX = posX - (Integer.signum(differenceX));
		int newPosY = posY - (Integer.signum(differenceY));
		
		if(Math.abs(differenceX) <= this.getVisionRange() && Math.abs(differenceY) <= this.getVisionRange()){  			// If player in visionRange PNJ approaches, if not in view just moves randomly ***
				if(Math.abs(differenceX) <= Math.abs(differenceY)){
					if(! game.Collision(newPosX, posY)){				
						this.setPosX(newPosX);											
					}else if(! game.Collision(posX, newPosY)){
						this.setPosY(newPosY);
					}else{
						this.changeType();
						}
				}else{
					if(! game.Collision(posX, newPosY)){				
						this.setPosY(newPosY);											
					}else if(! game.Collision(newPosX, posY)){
						this.setPosX(newPosX);
					}else{
						this.changeType();
						}	
					}
		}else{
			randomMove(this);																						// *** Just moves randomly because player not in view 
		}
}

	
/*	
	public void approach(Player target){
		int targetPosX = target.getPosX();
		int targetPosY = target.getPosY();
		int posX = this.getPosX();
		int posY = this.getPosY();
		int differenceX = posX - targetPosX;
		int differenceY = posY - targetPosY;
		if(Math.abs(differenceX) <= this.getVisionRange() && Math.abs(differenceY) <= this.getVisionRange()){
			if(Math.abs(differenceX) <= Math.abs(differenceY)){
				this.setPosX(posX - (Integer.signum(differenceX)*this.getVit()));
			}else{
				this.setPosY(posY - (Integer.signum(differenceY)*this.getVit()));
			}
		}
	}
	*/
	public void randomMove(Player target){
		ArrayList<String> listPossibleMoves = this.getListPossibleMoves();
		if(listPossibleMoves.size() != 0){
			int randomNum = randomNum(0, listPossibleMoves.size());
			String direction = listPossibleMoves.get(randomNum);
			if(direction.equals("E")){this.setPosX(this.getPosX()+1);}
			if(direction.equals("W")){this.setPosX(this.getPosX()-1);}
			if(direction.equals("N")){this.setPosY(this.getPosY()+1);}
			if(direction.equals("S")){this.setPosY(this.getPosY()-1);}
		}else{this.changeType();}
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
			this.die(this);
		}
		else{this.approach(target);}
	}
	
	public int randomNum(int min, int max){
		int nb = min + (int)(Math.random() * ((max - min) + 1));
		return nb;
	}

	public void run(){
		try{
			while(true){
				Thread.sleep(50);
				if(type == 0){
					kamikaze(game.getHero());
				}else{
					flee(game.getHero());
				}
				while (game.isOnPause()){
					Thread.sleep(1000);
					System.out.println("Je suis en pause !");
				}
		}
	}catch(Exception e){System.out.println("Problem with thread rat !");} 
	}

}
