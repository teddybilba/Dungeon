package model.dalle;
import model.character.*;

public class DamageTile extends Block {

	private static final long serialVersionUID = 1L;
	private int damageValue;
	
	public DamageTile(int posX, int posY){
//<<<<<<< HEAD
		super(posX, posY, 3);
		this.damageValue = 3;
//=======
		/*super(posX, posY);
		this.damageVal=4;*/
//>>>>>>> origin/master
	}
	public void hurt(Player player){
		int x = this.getPosX();
		int y = this.getPosY();
		if(player.getPosX()==x && player.getPosY()==y){
			player.setDamage(damageValue);
		}
	}
}
