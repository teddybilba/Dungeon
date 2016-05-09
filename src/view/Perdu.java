package view;

import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.KeyBoard;
import model.Game;


public class Perdu extends JFrame implements Serializable{

	private static final long serialVersionUID = 1L;
	private JButton restart = new JButton ("Try Again!");
	private JButton load = new JButton ("Load last saved game");
	private JButton small = new JButton ("Small");
	private JButton medium = new JButton ("Medium");
	private JButton large = new JButton ("Large");
	private int hey;
	private int gameSize = 50;
	private JLabel image = new JLabel();
	private JFrame perdu; 
	
	
	/* ### CONSTRUCTEUR ### */
	
	public Perdu(){		
		super("DONJON");
		perdu = new JFrame();
		this.setResizable(false);
		
		perdu.setTitle("DONJON:GAME OVER");
		perdu.setSize(1050,700);
		perdu.setResizable(false);
		perdu.setLocationRelativeTo(null);
		perdu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel pan = new JPanel();
		pan.setLayout(null);
		perdu.setContentPane(pan); 
		perdu.setVisible(true);
		ImageIcon iPerdu = new ImageIcon("images/donjon.png");

		image = new JLabel(iPerdu);
		image.setBounds(0, 0,1050,700);
		
		
		restart.setBounds((1050 - 300)/2, 750/2, 300, 50);
		Font police = new Font("Arial", Font.BOLD, 30);
		restart.setFont(police);
		restart.setForeground(Color.WHITE);
		restart.setBackground(Color.BLACK);
		restart.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {	
				restartGame();
				perdu.setVisible(false);
				perdu.dispose();
				
			}	
		});
		load.setBounds((1050-200)/2, 850/2, 200, 50);
		
		load.setFont(police);
		load.setForeground(Color.WHITE);
		load.setBackground(Color.BLACK);
		load.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//game.load();
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
		pan.add(restart);
		pan.add(load);
		pan.add(small);
		pan.add(medium);
		pan.add(large);
		pan.add(image);

	}
	
	
	public void restartGame(){
		Window window = new Window();
		Game game = new Game(window,gameSize);
		game.startThreads();
		KeyBoard keyboard = new KeyBoard(game);
		window.setKeyListener(keyboard);
	}
}
