package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import model.Game;
public class KeyBoard implements KeyListener{
		private Game game;
		
		public KeyBoard (Game game){
			this.game = game;
		}

		@Override
		public void keyPressed(KeyEvent event) {
			int key = event.getKeyCode();
			
			switch (key){
				case KeyEvent.VK_RIGHT: 
					//System.out.println("Right");
					game.movePlayerRight();
					break;
				case KeyEvent.VK_LEFT:
					//System.out.println("Left");
					game.movePlayerLeft();
					break;
				case KeyEvent.VK_DOWN:
					//System.out.println("Down");
					game.movePlayerDown();
					break;
				case KeyEvent.VK_UP:
					//System.out.println("Up");
					game.movePlayerUp();
					break;	
				case KeyEvent.VK_SPACE:
					//System.out.println("Do something...");
					game.heroAttacks();
					game.playerDeath();
					break;
				case KeyEvent.VK_P:
					//System.out.println("potion taken");
					game.takePotion();
					break;
				case KeyEvent.VK_D:
					System.out.println("potion drank");
					game.drinkPotion();
					break;
				case KeyEvent.VK_S:
					System.out.println("specialPower used");
					game.getHero().usePower();;
					break;
					
					
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}


}