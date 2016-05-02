package model.character;
import model.item.*;
import model.Game;

public class PNJ extends Player{
	private int visionRange;
	
	public PNJ(int posX, int posY, int visionRange, int attackRange, Game game){
		super(posX, posY, attackRange, game);
		this.visionRange = visionRange;
	}
	
	public int getVisionRange(){
		return visionRange;
	}
	public void setVisionRange(int visionRange){
		if(visionRange > 0){
			this.visionRange = visionRange;
		}
	}
	public int randomNum(int min, int max){
		int nb = min + (int)(Math.random() * ((max - min) + 1));
		return nb;
	}
	public int dropPNJ(){
		// retourne un indice pour placer un objet aleatoire � sa place.
		int i=randomNum(1,2);
		return i;
	}
}