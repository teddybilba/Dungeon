package view;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import controller.*;
import model.*;
import view.Window;

public class Menu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton start = new JButton ("Start Game");
	private JButton exit = new JButton ("Exit");
	private JLabel image = new JLabel();
	private JFrame menu;
	
	

	public Menu () { 
		
		super("DONJON");
		this.setResizable(false);
		menu = new JFrame();
		menu.setTitle("DONJON");
		menu.setSize(1024,681);
		menu.setResizable(false);
		menu.setLocationRelativeTo(null);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel pan = new JPanel();
		pan.setLayout(null);
		menu.setContentPane(pan); 
		menu.setVisible(true);
		ImageIcon iMenu = new ImageIcon("/Users/coline/Documents/GitHub/Dungeon/Dungeon/src/images/donjon.png");

		image = new JLabel(iMenu);
		image.setBounds(0, 0,1024,681);

		start.setBounds((1024 - 300)/2, 750/2, 300, 50);
		Font police = new Font("Arial", Font.BOLD, 30);
		start.setFont(police);
		start.setForeground(Color.BLUE);
		start.setBackground(Color.BLACK);
		start.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {	
				lancerJeu();
				menu.dispose();
			}	
		});

		exit.setBounds((1024-200)/2, 850/2, 200, 50);
		exit.setFont(police);
		exit.setForeground(Color.BLUE);
		exit.setBackground(Color.BLACK);
		exit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				menu.dispose();
			}

		});
		pan.setBackground(Color.BLACK);
		pan.add(start);
		pan.add(exit);
		pan.add(image);

	}
	
	private void lancerJeu() {
		
		
	}

}