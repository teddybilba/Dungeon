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
			
			if(! game.isOnPause()){
				switch (key){
				/*		Movement	*/
					case KeyEvent.VK_RIGHT: 
						game.movePlayerRight();
						break;
					case KeyEvent.VK_LEFT:
						game.movePlayerLeft();
						break;
					case KeyEvent.VK_DOWN:
						game.movePlayerDown();
						break;
					case KeyEvent.VK_UP:
						game.movePlayerUp();
						break;
						
				/*		Attack 		*/
					case KeyEvent.VK_SPACE:
						game.heroAttacks();
						
						break;
				/*		Potions 		*/
					case KeyEvent.VK_P:
						game.takePotion();
						break;
					case KeyEvent.VK_D:
						game.drinkPotion();
						break;
					case KeyEvent.VK_J:
						game.getHero().dropPotion();
						
				/*		Special Powers 		*/
					case KeyEvent.VK_U:
						game.usePower();;
						break;
					case KeyEvent.VK_V:
						game.getHero().gainLife();
						break;
					case KeyEvent.VK_X:
						game.getHero().attackArea();
						break;
					case KeyEvent.VK_ESCAPE:
						System.out.println("Pause game !");
						game.pauseGame();
						break;
					case KeyEvent.VK_S:
						System.out.println("Save game !");
						game.save("savedGame");
				}
				
			}
			if(key == KeyEvent.VK_R){
						System.out.print("Resume game !");
						game.resumeGame();
			}
			}

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}


}