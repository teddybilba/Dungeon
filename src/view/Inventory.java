package view;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Inventory extends JPanel {
	private int potionNumber;
	JLabel label1 = new JLabel();
	public Inventory(){
		this.setBackground(Color.YELLOW);
		this.potionNumber=0;
		labelComponent();
		this.add(label1);
	}
	public void setPotionNumber(int pNumber){
		this.potionNumber=pNumber;
		label1.setText(String.valueOf(potionNumber));
	}
	public void labelComponent(){
		ImageIcon iPotion = new ImageIcon ("/Users/coline/Documents/GitHub/Dungeon/Dungeon/src/images/potion.png");
		label1 = new JLabel("text");
		label1 = new JLabel(iPotion);
		label1.setText("Nb Potions: "+String.valueOf(potionNumber));
		label1.setBounds(1050, 550,10,10);
	}
	

}
