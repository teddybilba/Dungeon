package view;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.io.Serializable;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JSplitPane;

import view.Inventory; 
import view.HeroHealth;
import model.character.Hero;


public class Window implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private JSplitPane split1, split2;
	private Map map = new Map();// cette ligne pose probl�me pour les labels!!
	private Inventory inventory= new Inventory();
	private HeroHealth health= new HeroHealth();	
	private JFrame window;


	/* ### CONSTRUCTEUR ### */

	public Window(){	    
	    window = new JFrame("Game");
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setBounds(0, 0, 1500, 998);
	    window.getContentPane().setBackground(Color.gray);
	    
	    split1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, health,inventory);
	    //On place le premier separateur
	    split1.setDividerLocation(100);
	    
	    split2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,map, split1);
	    //On place le deuxieme separateur
	    split2.setDividerLocation(950);
	    
	    window.getContentPane().add(split2,BorderLayout.CENTER);
	    
	    window.setVisible(true);
	    window.setResizable(true);	   	    
	}
	
	
	/* @@@ GETTERS & SETTERS @@@ */
	public HeroHealth getHealth(){
		return this.health;
	}
	public Inventory getInventory(){
		return this.inventory;
	}
	
	
	public void settings(Hero hero){
		this.health.setCoinsNumber(hero.getCoinsNumber());
		this.health.setLife(hero.getLife());
		this.health.setDamage(hero.getDamage());
		this.inventory.setPotionNumber(hero.getPotionNumInventory());
		this.inventory.setPowerNum(hero.getSpecialPowerNum());
	}
	
	//Mise a jour de la Map
	public void draw(int[][] mapMatrix){
		map.setMapMatrix(mapMatrix);
	}
	
	public void gameOver(){
		window.dispose();
		
	}
	
	public void setKeyListener(KeyListener keyboard){
	    this.map.addKeyListener(keyboard);
	}
		
}