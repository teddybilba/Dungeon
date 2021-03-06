package view;


import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;


public class Map extends JPanel implements Serializable{

	private static final long serialVersionUID = 1L;
	private int[][] mapMatrix;
	private transient BufferedImage wallImage;
	private transient BufferedImage coinImage;

	private transient BufferedImage gokuImage;
	private transient BufferedImage krilinImage;
	private transient BufferedImage tileImage;
	private transient BufferedImage ratImage;
	private transient BufferedImage saibamanImage;
	private transient BufferedImage freezaImage;
	private transient BufferedImage potionImage;
	private transient BufferedImage teleTileImage;
	private transient BufferedImage damTileImage;
	
	
	/* ### CONSTRUCTEUR ### */
	public Map(){
		
		this.setFocusable(true);
		this.requestFocusInWindow();
		try {
			wallImage = ImageIO.read(new File("images/wall.png"));
			gokuImage = ImageIO.read(new File("images/goku.png"));
			krilinImage = ImageIO.read(new File("images/krilin.png"));
			coinImage = ImageIO.read(new File("images/coin.png"));
			tileImage = ImageIO.read(new File("images/tile.png"));
			ratImage  = ImageIO.read(new File("images/rat.png"));
			saibamanImage = ImageIO.read(new File("images/saibaman.png"));
			freezaImage = ImageIO.read(new File("images/freeza.png"));
			potionImage = ImageIO.read(new File("images/potion.png"));
			teleTileImage = ImageIO.read(new File("images/teletile.png"));
			damTileImage = ImageIO.read(new File("images/damtile.png"));
		} catch (IOException e) {
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
			System.out.println("mapMatrix is null !");
		}
		else{
			for(int i = 0; i<mapMatrix.length; i++){
				for(int j = 0; j<mapMatrix.length; j++){
					int x = i;
					int y = j;
					int color = mapMatrix[i][j];
																				// Could also use {switch and case}
					if(color == 0){												// Picture drawn according to color
						try {
							g.drawImage(tileImage,  x*50, y*50, 48, 48,  null);
						} 
						catch (Exception e) {e.printStackTrace();}
					}
					else if(color == 1){
						try {
								g.drawImage(wallImage, x*50,y*50, 48, 48,  null);
						} 
						catch (Exception e) {e.printStackTrace();}
					}
					else if(color == 2){
						try{							
							g.drawImage(gokuImage, x*50, y*50, 48, 48,  null);
						}
						catch(Exception e){e.printStackTrace();}
					}
					
					else if(color == 3){
						try {
							g.drawImage(ratImage,  x*50, y*50, 48, 48,  null);
						}
						catch (Exception e) {e.printStackTrace();}
					}
					
					else if(color == 4){
						try {
							g.drawImage(coinImage,  x*50, y*50, 48, 48,  null);
						}
						catch (Exception e) {e.printStackTrace();}
					}
					else if(color == 5){
						try{							
							g.drawImage(potionImage, x*50, y*50, 48, 48,  null);
						}
						catch(Exception e){e.printStackTrace();}
					}
					else if(color == 6){
						try{							
							g.drawImage(teleTileImage, x*50, y*50, 48, 48,  null);
						}
						catch(Exception e){e.printStackTrace();}
					}
					else if(color == 7){
						try{							
							g.drawImage(damTileImage, x*50, y*50, 48, 48,  null);
						}
						catch(Exception e){e.printStackTrace();}
					}
					else if(color == 8){
						try {
							g.drawImage(saibamanImage,  x*50, y*50, 48, 48,  null);
						}
						catch (Exception e) {e.printStackTrace();}
					}
					else if(color == 9){
						try {
							g.drawImage(freezaImage,  x*50, y*50, 48, 48,  null);
						}
						catch (Exception e) {e.printStackTrace();}
					}
					else if(color == 10){
						try {
							g.drawImage(krilinImage,  x*50, y*50, 48, 48,  null);
						}
						catch (Exception e) {e.printStackTrace();}
					}
					
					//System.out.print(color);
					//System.out.print(" ");
				}
				//System.out.println("");
			}
		}
		
	}		

}