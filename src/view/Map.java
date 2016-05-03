package view;


import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Map extends JPanel implements Serializable{

	private static final long serialVersionUID = 1L;
	private int[][] mapMatrix;
	private BufferedImage wallImage;
	private BufferedImage coinImage;
	private BufferedImage gokuImage;
	private BufferedImage tileImage;
	private BufferedImage ratImage;
	private BufferedImage potionImage;
	private BufferedImage teleTileImage;
	
	
	/* ### CONSTRUCTEUR ### */
	public Map(){
		
		this.setFocusable(true);
		this.requestFocusInWindow();
		try {
			wallImage = ImageIO.read(new File("images/wall.png"));
			gokuImage = ImageIO.read(new File("images/goku.png"));
			coinImage = ImageIO.read(new File("images/coin.png"));
			tileImage = ImageIO.read(new File("images/tile.png"));
			ratImage  = ImageIO.read(new File("images/rat.png"));
			potionImage = ImageIO.read(new File("images/potion.png"));
			teleTileImage = ImageIO.read(new File("images/teletile.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur import images dans Map.java !");
		}	
	}
	
	
	/* @@@ GETTERS & SETTERS @@@ */
	public void setMapMatrix(int[][] mapMatrix){
		this.mapMatrix = mapMatrix;
		this.repaint();
	}
	
	
	
	public void paint(Graphics g) { 
		if(mapMatrix == null){
		}
		else{
			for(int i = 0; i<mapMatrix.length; i++){
				for(int j = 0; j<mapMatrix.length; j++){
					int x = i;
					int y = j;
					int color = mapMatrix[i][j];
					
					if(color == 0){
						
						try {
							g.drawImage(tileImage,  x*50, y*50, 48, 48,  null);
					
						} 
						catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
						}
					}
					else if(color == 1){
						try {
								g.drawImage(wallImage, x*50,y*50, 48, 48,  null);
							
						} 
						catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
						}
					}
					
					else if(color == 3){
						/*g.setColor(Color.RED);
						g.fillRect(x*50, y*50, 48, 48); 

						g.drawRect(x*50, y*50, 48, 48);*/
						try {
							g.drawImage(ratImage,  x*50, y*50, 48, 48,  null);
						}
						catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					else if(color == 4){
						try {
							g.drawImage(coinImage,  x*50, y*50, 48, 48,  null);
						}
						catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else if(color == 5){
						try{
							//final BufferedImage image = ImageIO.read(new File("/Users/teddybilba/Documents/Teddy/ULB/BA2/Informatique/Donjon/Donjon/src/View/goku.png"));
							
							g.drawImage(potionImage, x*50, y*50, 48, 48,  null);
						}
						catch(Exception e){}
					}
					else if(color == 6){
						try{
							//final BufferedImage image = ImageIO.read(new File("/Users/teddybilba/Documents/Teddy/ULB/BA2/Informatique/Donjon/Donjon/src/View/goku.png"));
							
							g.drawImage(teleTileImage, x*50, y*50, 48, 48,  null);
						}
						catch(Exception e){}
					}
					else if(color == 2){
						try{
							//final BufferedImage image = ImageIO.read(new File("/Users/teddybilba/Documents/Teddy/ULB/BA2/Informatique/Donjon/Donjon/src/View/goku.png"));
							
							g.drawImage(gokuImage, x*50, y*50, 48, 48,  null);
						}
						catch(Exception e){}
					}
					
					System.out.print(color);
					System.out.print(" ");
				}
				System.out.println("");
			}
		}
		
	}
	
	

}