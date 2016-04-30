package view;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JSplitPane;

import view.Inventory; 
import view.HeroHealth;


public class Window {
private JSplitPane split1, split2;
private Map map = new Map();// cette ligne pose problème pour les labels!!
private Inventory inventory= new Inventory();
private HeroHealth health= new HeroHealth();	
	public Window(){	    
	    JFrame window = new JFrame("Game");
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setBounds(0, 0, 1500, 1020);
	    window.getContentPane().setBackground(Color.gray);
	    
	    split1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, health,inventory);
	    //On place le premier séparateur
	    split1.setDividerLocation(100);
	    
	    split2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,map, split1);
	    //On place le deuxième séparateur
	    split2.setDividerLocation(1000);
	    
	    window.getContentPane().add(split2,BorderLayout.CENTER);
	    
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
	public void uploadPotion(int pNum){
		inventory.setPotionNumber(pNum);
	}
	
	public void setKeyListener(KeyListener keyboard){
	    this.map.addKeyListener(keyboard);
	}
		
}