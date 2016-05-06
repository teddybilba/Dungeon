package view;

import java.awt.Color;
import java.io.Serializable;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class HeroHealth extends JPanel implements Serializable{

	private static final long serialVersionUID = 1L;
	private int heroLife;
	private int heroCoins;
	private int heroDamage;
	private int heroMaxDamage;
	
	JLabel label1 = new JLabel();
	JLabel label2 = new JLabel();
	JLabel label3 = new JLabel();
	JLabel label4 = new JLabel();
	
	/* ### CONSTRUCTEUR ### */
	public HeroHealth(){
		this.setBackground(Color.WHITE);
		this.heroCoins = 0;
		this.heroLife = 50;
		this.heroDamage = 0;
		this.heroMaxDamage = 50;
		labelComponent();
		this.add(label1);
		this.add(label2);
		this.add(label3);
		this.add(label4);
	}
	
	
	/* @@@ GETTERS & SETTERS @@@ */
	public int getHeroLife(){
		return this.heroLife;
	}
	
	public void setCoinsNumber(int coinsNumber){
		this.heroCoins=coinsNumber;
		label1.setText("Coins: "+String.valueOf(heroCoins));
	}
	public void setLife(int life){
		this.heroLife=life;
		label2.setText("Lives: "+String.valueOf(heroLife));
	}
	public void setDamage(int damage){
		this.heroDamage=damage;
		label3.setText("Dommages: "+String.valueOf(heroDamage));
	}
	public void setMaxDamage(int maxDamage){
		this.heroMaxDamage = maxDamage;
		label3.setText("Max dommages: "+String.valueOf(heroMaxDamage));
	}
	
	public void labelComponent(){
		
		label1 = new JLabel("text");
		label1.setText("Coins: "+String.valueOf(heroCoins));
		label1.setBounds(1050, 50,10,10);                      

		label2 = new JLabel("text");
		label2.setText("Lives: "+String.valueOf(heroLife));
		label2.setBounds(1200, 200,10,10);
		
		label3=new JLabel("text");
		label3.setText("Dommages: "+String.valueOf(heroDamage));
		label3.setBounds(1200, 200,10,10);
		
		label4=new JLabel("text");
		label4.setText("Max dommage: "+String.valueOf(heroMaxDamage));
		label4.setBounds(1200, 200,10,10);
		
	}
	
}