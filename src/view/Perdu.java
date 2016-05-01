package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.KeyBoard;
import model.Game;


public class Perdu extends JFrame {
	
	private JButton restart = new JButton ("Try Again!");
	private JButton exit = new JButton ("Exit");
	private JLabel image = new JLabel();
	private JFrame perdu;
	public Perdu(){
		super("DONJON");
		perdu=new JFrame();
		this.setResizable(false);
		perdu.setTitle("DONJON:GAME OVER");
		perdu.setSize(1024,681);
		perdu.setResizable(false);
		perdu.setLocationRelativeTo(null);
		perdu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel pan = new JPanel();
		pan.setLayout(null);
		perdu.setContentPane(pan); 
		perdu.setVisible(true);
		ImageIcon iPerdu = new ImageIcon("images/donjon.png");

		image = new JLabel(iPerdu);
		image.setBounds(0, 0,1024,681);
		
		
		restart.setBounds((1024 - 300)/2, 750/2, 300, 50);
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
		exit.setBounds((1024-200)/2, 850/2, 200, 50);
		
		exit.setFont(police);
		exit.setForeground(Color.WHITE);
		exit.setBackground(Color.BLACK);
		exit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				perdu.dispose();
			}

		});
		pan.setBackground(Color.BLACK);
		pan.add(restart);
		pan.add(exit);
		pan.add(image);

	}
	public void restartGame(){
		
		Window window = new Window();
		Game game = new Game(window);
		KeyBoard keyboard = new KeyBoard(game);
		window.setKeyListener(keyboard);
			
		
	}
}
