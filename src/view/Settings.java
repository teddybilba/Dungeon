package view;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JFrame;
import view.Inventory; 
import view.HeroHealth;
import java.awt.BorderLayout;

public class Settings extends JFrame{
	private Inventory inventory= new Inventory();
	private HeroHealth health= new HeroHealth();
	public Settings(){
		this.setTitle("Settings");
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setBounds(1000, 0, 600, 1020);
	    this.getContentPane().setBackground(Color.gray);
	    this.setVisible(true);
	    this.setResizable(true);
	    this.getContentPane().add(this.inventory,BorderLayout.CENTER);
	    this.getContentPane().add(this.health,BorderLayout.CENTER);
	}
}
