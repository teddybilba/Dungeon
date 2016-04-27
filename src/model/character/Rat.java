package model.character;

//import model.Game;

public class Rat extends PNJ {
	public Rat(int posX, int posY, int visionRange, int attackRange){
		super(posX, posY,visionRange, attackRange);
	}
	public void flee(Player target){
	// fuite du rat face au h�ros ( fuit aussi les squelettes...?)
	}
/*	
	public void move(){
		int posX = this.getPosX();
		int posY = this.getPosY();
		int visionRange = this.getVisionRange();
		for(int i=1; i >= visionRange; i++){
			for(int j=1; j <= visionRange; j++){}
			
		}
	}
	
	public void move2(Player target){
		int targetPosX = target.getPosX();
		int targetPosY = target.getPosY();
		int posX = this.getPosX();
		int posY = this.getPosY();
		int differenceX = posX - targetPosX;
		int differenceY = posY - targetPosY;
		if(Math.abs(differenceX) <= this.getVisionRange() && Math.abs(differenceY) <= this.getVisionRange()){
			if(Math.abs(differenceX) >= Math.abs(differenceY)){
				this.setPosX(posX + differenceX);
				//this.setPosX(posX + (Integer.signum(differenceX)*this.getVitX()));
			}else{
				this.setPosY(posY + differenceY);
				//this.setPosY(posY + (Integer.signum(differenceY)*this.getVitY()));
			}
		}
		
	} 
/*	
	public int randomNum(int min, int max){
		int nb = min + (int)(Math.random() * ((max - min) + 1));
		return nb;
	}

	public void actionPerformed(){
		int random = randomNum(1, 10);
		if(random < 2){
			kamikaze();
		}else{
			flee(hero);
		}
	} 
*/

}
