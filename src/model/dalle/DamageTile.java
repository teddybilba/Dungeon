package model.dalle;
import model.character.*;

public class DamageTile extends Block {

	private static final long serialVersionUID = 1L;
	private int damageValue;
	
	public DamageTile(int posX, int posY){
		super(posX, posY, 3);
		this.damageValue = 4;
	}
	public void hurt(Player player){
		int x = this.getPosX();
		int y = this.getPosY();
		if(player.getPosX()==x && player.getPosY()==y){
			player.setDamage(damageValue);
		}
	}
}
