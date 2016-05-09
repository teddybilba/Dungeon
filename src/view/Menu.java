package view;
import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/*import javax.swing.Timer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;*/

import controller.KeyBoard;
import model.Game;
import view.Window;

public class Menu extends JFrame implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int gameSize = 50;
	private JButton start = new JButton ("Start Game");
	private JButton load = new JButton ("Load Game");
	private JButton small = new JButton ("Small");
	private JButton medium = new JButton ("Medium");
	private JButton large = new JButton ("Large");
	private JLabel image = new JLabel();
	private JFrame menu;
	
	
	/* ### CONSTRUCTEUR ### */
	public Menu () { 
		
		super("DONJON");
		this.setResizable(false);
		menu = new JFrame();
		menu.setTitle("DONJON");
		menu.setSize(1050,700);
		menu.setResizable(false);
		menu.setLocationRelativeTo(null);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel pan = new JPanel();
		pan.setLayout(null);
		menu.setContentPane(pan); 
		menu.setVisible(true);
		ImageIcon iMenu = new ImageIcon("images/donjon.png");

		image = new JLabel(iMenu);
		image.setBounds(0, 0, 1050, 700);

		start.setBounds((1050 - 300)/2, 750/2, 300, 50);
		Font police = new Font("Arial", Font.BOLD, 30);
		start.setFont(police);
		start.setForeground(Color.WHITE);
		start.setBackground(Color.BLACK);
		start.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {	
				lancerJeu();
				menu.dispose();
			}
		});

		load.setBounds((1050-200)/2, 850/2, 200, 50);
		load.setFont(police);
		load.setForeground(Color.WHITE);
		load.setBackground(Color.BLACK);
		load.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				reprendreJeu();
				menu.dispose();
			}

		});
		
		small.setBounds(200, 200, 150, 50);
		small.setFont(police);
		small.setForeground(Color.BLACK);
		small.setBackground(Color.WHITE);
		small.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				gameSize = 50;
			}

		});
		
		medium.setBounds(350, 200, 150, 50);
		medium.setFont(police);
		medium.setForeground(Color.BLACK);
		medium.setBackground(Color.WHITE);
		medium.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				gameSize = 70;
			}

		});
		large.setBounds(500, 200, 150, 50);
		large.setFont(police);
		large.setForeground(Color.BLACK);
		large.setBackground(Color.WHITE);
		large.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				gameSize = 100;
			}

		});
		pan.setBackground(Color.BLACK);
		pan.add(start);
		pan.add(load);
		pan.add(small);
		pan.add(medium);
		pan.add(large);
		pan.add(image);
		

	}
	
	private void lancerJeu(){
		
		Window window = new Window();
		Game game = new Game(window, gameSize);
		KeyBoard keyboard = new KeyBoard(game);
		window.setKeyListener(keyboard);
		game.startThreads();					// To begin all the threads
	}
	
	private void reprendreJeu(){
		FileInputStream file;
		ObjectInputStream i;
		try{
			file = new FileInputStream("savedGame");
			i = new ObjectInputStream(file);
			Game game = (Game) i.readObject();
			Window window = new Window();
			window.settings(game.getHero());
			KeyBoard keyboard = new KeyBoard(game);
			window.setKeyListener(keyboard);
			game.setListThreads();
			game.startThreads();
			window.draw(game.getMap());

			
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {}
	
	}

}