package model.character;

public class PNJ extends Player{
	private int visionRange;
	public PNJ(int posX, int posY, int visionRange, int attackRange){
		super(posX, posY, attackRange);
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
}