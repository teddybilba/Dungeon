package view;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Inventory extends JPanel {
	
	private int potionNumber;
	private int powerNum;
	
	JLabel label1 = new JLabel();
	JLabel label2 = new JLabel();
	
	/* ### CONSTRUCTEUR ### */
	public Inventory(){
		this.setBackground(Color.YELLOW);
		this.potionNumber=0;
		this.powerNum=0;
		labelComponent();
		this.add(label1);
		this.add(label2);
	}
	
	/* @@@ GETTERS & SETTERS @@@ */
	public void setPotionNumber(int pNumber){
		this.potionNumber=pNumber;
		label1.setText("Nb Potions: "+String.valueOf(potionNumber));
	}
	public void setPowerNum(int Number){
		this.powerNum=Number;
		label2.setText("Nb PouvoirsBonus: "+String.valueOf(powerNum));
	}
	
	public void labelComponent(){
		ImageIcon iPotion = new ImageIcon ("images/potion.png");
		ImageIcon iPower = new ImageIcon ("images/shield.png");
		label1 = new JLabel("text");
		label1 = new JLabel(iPotion);
		label1.setText("Nb Potions: "+String.valueOf(potionNumber));
		label1.setBounds(1050, 450,10,10);
		
		label2 = new JLabel("text");
		label2 = new JLabel(iPower);
		label2.setText("Nb PouvoirsBonus: "+String.valueOf(powerNum));
		label2.setBounds(1050, 750,10,10);
	}
	

}
