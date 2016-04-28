package view;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;





public class HeroHealth extends JPanel{
	private int heroLife;
	private int heroCoins;
	public HeroHealth(){
		this.setBackground(Color.RED);
		this.heroCoins=0;
		this.heroLife=3;
		
		
		
	}
	
	
	public void setCoinsNumber(int coinsNumber){
		this.heroCoins=coinsNumber;
		System.out.print(String.valueOf(heroCoins));
	}
	public void setLife(int life){
		this.heroLife=life;
		System.out.println(String.valueOf(heroLife));
	}
	
}