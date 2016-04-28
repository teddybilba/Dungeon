package view;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import view.Inventory; 
import view.HeroHealth;


public class Window {

private Map map = new Map();// cette ligne pose problème pour les labels!!
private Inventory inventory= new Inventory();
private HeroHealth health= new HeroHealth();	
	public Window(){	    
	    JFrame window = new JFrame("Game");
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setBounds(0, 0, 1000, 1020);
	    window.getContentPane().setBackground(Color.gray);
	    window.getContentPane().add(this.map,BorderLayout.CENTER);
	    window.getContentPane().add(this.inventory,BorderLayout.EAST);
	    window.getContentPane().add(this.health,BorderLayout.EAST);
	    window.setVisible(true);
	    window.setResizable(true);
	    
	}	
	
	public void draw(int[][] mapMatrix){
		map.setMapMatrix(mapMatrix);
	}
	public void uploadCoins(int coinsNumber){
		health.setCoinsNumber(coinsNumber);
	}
	public void uploadLife(int life){
		health.setLife(life);
	}
	
	public void setKeyListener(KeyListener keyboard){
	    this.map.addKeyListener(keyboard);
	}
		
}