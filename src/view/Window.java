package view;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JSplitPane;

import view.Inventory; 
import view.HeroHealth;
import model.character.Hero;


public class Window {
private JSplitPane split1, split2;
private Map map = new Map();// cette ligne pose problème pour les labels!!
private Inventory inventory= new Inventory();
private HeroHealth health= new HeroHealth();	
private JFrame window;
	public Window(){	    
	    window = new JFrame("Game");
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
	public void settings(Hero hero){
		this.health.setCoinsNumber(hero.getCoinsNumber());
		this.health.setLife(hero.getLife());
		this.inventory.setPotionNumber(hero.getPotionNumInventory());
		this.inventory.setPowerNum(hero.getSpecialPowerNum());
	}
	public HeroHealth getHealth(){
		return this.health;
	}
	public Inventory getInventory(){
		return this.inventory;
	}
	//Mise à jour de la Map
	public void draw(int[][] mapMatrix){
		map.setMapMatrix(mapMatrix);
	}
	
	public void gameOver(){
		if (health.getHeroLife()==0){
			window.dispose();
		}

	}
	
	public void setKeyListener(KeyListener keyboard){
	    this.map.addKeyListener(keyboard);
	}
		
}