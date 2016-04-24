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
	
	public Map(){
		this.setFocusable(true);
		this.requestFocusInWindow();
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
					}
					else if(color == 1){
						g.setColor(Color.DARK_GRAY);
						//try{
						//Image img = ImageIO.read(new File("mur.jpg"));
						//g.drawImage(img,i,0,this);
						//}catch(IOException e){}
					}
					else if(color == 2){
						try{
							//final BufferedImage image = ImageIO.read(new File("/Users/teddybilba/Documents/Teddy/ULB/BA2/Informatique/Donjon/Donjon/src/View/goku.png"));
							final BufferedImage image =ImageIO.read(new File("/Users/coline/Documents/GitHub/Dungeon/Dungeon/src/images/goku.png"));
							for(model.character.Hero hero: model.Game.heroes)
							g.drawImage(image,  hero.getPosX()*40, hero.getPosY()*40, 50, 50,  null);
							}catch(IOException e){}
					}
					else if(color == 3){
						g.setColor(Color.YELLOW);
					}
					g.fillRect(x*50, y*50, 48, 48); 

					//try{
						//Image img = ImageIO.read(new File("mur.jpg"));
						//g.drawImage(img,  0, 0, this);
						//}catch(IOException e){}
					g.drawRect(x*50, y*50, 48, 48); 
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