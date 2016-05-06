package model.dalle;

import model.Game;
import model.character.Player;

public class TeleportationTile extends Block{
	
	private static final long serialVersionUID = 1L;
	private Game game;
	
	public TeleportationTile(int posX, int posY, Game game){
		super(posX, posY, 2);
		this.game = game;
	}

	public void teleportation(Player player){
		TeleportationTile teleTile = game.randomTeleTile(this);
		player.setPosX(teleTile.getPosX());
		player.setPosY(teleTile.getPosY());
		}
		
	
}
