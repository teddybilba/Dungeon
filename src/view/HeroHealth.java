package view;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;





public class HeroHealth extends JPanel{
	private int heroLife;
	private int heroCoins;
	JLabel label1 = new JLabel();
	JLabel label2 = new JLabel();
	public HeroHealth(){
		this.setBackground(Color.WHITE);
		this.heroCoins=0;
		this.heroLife=3;
		labelComponent();
		this.add(label1);
		this.add(label2);
		
		
		
	}
	
	
	public void setCoinsNumber(int coinsNumber){
		this.heroCoins=coinsNumber;
		label1.setText("Nb Pieces: "+String.valueOf(heroCoins));
		//System.out.print(String.valueOf(heroCoins));
	}
	public void setLife(int life){
		this.heroLife=life;
		label2.setText("Nb vies: "+String.valueOf(heroLife));
		//System.out.println(String.valueOf(heroLife));
	}
	public void labelComponent(){
		ImageIcon iCoin = new ImageIcon ("images/coin.png");
		ImageIcon iLife = new ImageIcon ("images/life.png");
		
		
		label1 = new JLabel("text");
		//label1 = new JLabel(iCoin);
		label1.setText("Nb Pieces: "+String.valueOf(heroCoins));//TODO r�gler le probl�me
		label1.setBounds(1050, 50,10,10);                       // d apparition des images en arri�re plan!!
		                                                        //set bounds inefficaces on dirait
		label2 = new JLabel("text");
		//label2 = new JLabel(iLife);
		label2.setText("Nb Vies "+String.valueOf(heroLife));
		label2.setBounds(1200, 200,10,10);
	}
	
}