package model.dalle;
import model.character.*;

public class DamageTile extends Tile {
	private int damageVal;
	public DamageTile(int posX, int posY){
		super(posX, posY);
		this.damageVal=3;
	}
	public void hurt(Player player){
		int x=this.getPosX();
		int y= this.getPosY();
		if(player.getPosX()==x && player.getPosY()==y){
			player.setDamage(this.damageVal);
		}
	}
}
