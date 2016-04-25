package model.character;

public class Rat extends PNJ {
	public Rat(int posX, int posY, int visionRange, int attackRange){
		super(posX, posY,visionRange, attackRange);
	}
	public void flee(Player target){
	// fuite du rat face au héros ( fuit aussi les squelettes...?)
	}
}
