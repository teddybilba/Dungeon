package view;

import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Map extends JPanel {
	private int[][] mapMatrix;
	private BufferedImage wallImage;
	private BufferedImage coinImage;
	private BufferedImage gokuImage;
	
	public Map(){
		this.setFocusable(true);
		this.requestFocusInWindow();
		try {
			wallImage =ImageIO.read(new File("/Users/coline/Documents/GitHub/Dungeon/Dungeon/src/images/wall.png"));
			gokuImage =ImageIO.read(new File("/Users/coline/Documents/GitHub/Dungeon/Dungeon/src/images/goku.png"));
			coinImage =ImageIO.read(new File("/Users/coline/Documents/GitHub/Dungeon/Dungeon/src/images/coin.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g) { 
		if(mapMatrix == null){
		}else{
			for(int i = 0; i<mapMatrix.length; i++){
				for(int j = 0; j<mapMatrix.length; j++){
					int x = i;
					int y = j;
					int color = mapMatrix[i][j];
					
					if(color == 0){
						g.setColor(Color.GRAY);
						g.fillRect(x*50, y*50, 48, 48); 

						g.drawRect(x*50, y*50, 48, 48); 
					}
					else if(color == 1){
						/*g.setColor(Color.DARK_GRAY);
						g.fillRect(x*50, y*50, 48, 48); 

						g.drawRect(x*50, y*50, 48, 48);*/
						try {
							for(model.dalle.Wall wall : model.Game.walls){
								g.drawImage(wallImage,  (wall.getPosX())*50, (wall.getPosY())*50, 48, 48,  null);
				
							}
							
						} 
						catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
						}
					}
					
					else if(color == 3){
						g.setColor(Color.RED);
						g.fillRect(x*50, y*50, 48, 48); 

						g.drawRect(x*50, y*50, 48, 48); 
					}
					else if(color == 4){
						try {
							for(model.item.Coin coin : model.Game.coinsOnFloor){
								g.drawImage(coinImage,  (coin.getPosX())*50, (coin.getPosY())*50, 48, 48,  null);
			
							}
						
						} 
						catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else if(color == 2){
						try{
							//final BufferedImage image = ImageIO.read(new File("/Users/teddybilba/Documents/Teddy/ULB/BA2/Informatique/Donjon/Donjon/src/View/goku.png"));
							for(model.character.Hero hero: model.Game.heroes)
							g.drawImage(gokuImage,  hero.getPosX()*50, hero.getPosY()*50, 48, 48,  null);
							}catch(Exception e){}
					}
					
					System.out.print(color);
					System.out.print(" ");
				}
				System.out.println("");
			}
		}
	}
	
	public void setMapMatrix(int[][] mapMatrix){
		this.mapMatrix = mapMatrix;
		this.repaint();
	}

}