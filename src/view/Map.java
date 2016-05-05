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
	private BufferedImage wallImage;
	private BufferedImage coinImage;
	/*private BufferedImage gokuN;
	private BufferedImage gokuS;
	private BufferedImage gokuE;
	private BufferedImage gokuW;*/
	private BufferedImage goku;
	private BufferedImage tileImage;
	private BufferedImage ratImage;
	private BufferedImage saibamanImage;
	private BufferedImage potionImage;
	private BufferedImage teleTileImage;
	private BufferedImage damTileImage;
	
	
	/* ### CONSTRUCTEUR ### */
	public Map(){
		
		this.setFocusable(true);
		this.requestFocusInWindow();
		try {
			wallImage = ImageIO.read(new File("images/wall.png"));
			/*gokuN = ImageIO.read(new File("images/goku/gokuN.png"));
			gokuS = ImageIO.read(new File("images/goku/gokuS.png"));
			gokuE = ImageIO.read(new File("images/goku/gokuE.png"));
			gokuW = ImageIO.read(new File("images/goku/gokuW.png"));*/
			goku = ImageIO.read(new File("images/goku/gokuS.png"));
			coinImage = ImageIO.read(new File("images/coin.png"));
			tileImage = ImageIO.read(new File("images/tile.png"));
			ratImage  = ImageIO.read(new File("images/rat.png"));
			saibamanImage = ImageIO.read(new File("images/saibaman.png"));
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
	/*public void setGokuImage(String direction){
		if(direction.equals("N")){goku = gokuN;}
		if(direction.equals("S")){goku = gokuS;}
		if(direction.equals("W")){goku = gokuW;}
		if(direction.equals("E")){goku = gokuE;}
	}*/				//If there is time to put different images for different directions
	
	
	
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
							g.drawImage(goku, x*50, y*50, 48, 48,  null);
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
					
					System.out.print(color);
					System.out.print(" ");
				}
				System.out.println("");
			}
		}
		
	}		

}