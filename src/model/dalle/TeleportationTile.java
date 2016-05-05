package model.dalle;

import model.character.Player;
import outils.Observer;

public final class TeleportationTile extends Block implements Observer{

	private static final long serialVersionUID = 1L;
	
	
	public TeleportationTile(int posX, int posY){
		super(posX, posY, 2);
	}
	
	private void teleport(Player player, TeleportationTile tile){
		player.setPosX(tile.getPosX());
		player.setPosY(tile.getPosY());		
	}
	
	public void update(){
		
	}

}
