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

import controller.KeyBoard;
import model.Game;




public class Perdu extends JFrame {
	private static final long serialVersionUID = 1L;
	private JButton restart = new JButton ("Try Again!");
	private JButton exit = new JButton ("Exit");
	private JButton small = new JButton ("Small");
	private JButton medium = new JButton ("Medium");
	private JButton large = new JButton ("Large");
	private int gameSize;
	private JLabel image = new JLabel();
	private JFrame perdu;//TODO placer des boutons de choix de taille à nouveau pour relancer une nouvelle partie
	public Perdu(){		// DONE
		super("DONJON");
		perdu=new JFrame();
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
				perdu.dispose();
			}	
		});
		exit.setBounds((1050-200)/2, 850/2, 200, 50);
		
		exit.setFont(police);
		exit.setForeground(Color.WHITE);
		exit.setBackground(Color.BLACK);
		exit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				perdu.dispose();
			}

		});
		small.setBounds(200, 200, 150, 50);
		small.setFont(police);
		small.setForeground(Color.BLACK);
		small.setBackground(Color.WHITE);
		small.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				gameSize = 30;
			}

		});
		
		medium.setBounds(350, 200, 150, 50);
		medium.setFont(police);
		medium.setForeground(Color.BLACK);
		medium.setBackground(Color.WHITE);
		medium.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				gameSize = 40;
			}

		});
		large.setBounds(500, 200, 150, 50);
		large.setFont(police);
		large.setForeground(Color.BLACK);
		large.setBackground(Color.WHITE);
		large.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				gameSize = 50;
			}

		});
		pan.setBackground(Color.BLACK);
		pan.add(restart);
		pan.add(exit);
		pan.add(image);

	}
	public void restartGame(){
		
		Window window = new Window();
		Game game = new Game(window,gameSize);
		KeyBoard keyboard = new KeyBoard(game);
		window.setKeyListener(keyboard);
			
		
	}
}
