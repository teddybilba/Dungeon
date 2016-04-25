package model;

import java.util.ArrayList;

import view.Window;
import model.character.*;
import model.dalle.*;
import model.item.*;

public class Game {
	public static ArrayList<Hero> heroes = new ArrayList<Hero>();
	private ArrayList<PNJ> PNJs = new ArrayList<PNJ>();
	public static ArrayList<Wall> walls = new ArrayList<Wall>();// Liste des murs
	private ArrayList<Tile> tiles= new ArrayList<Tile>();// liste des cases (normales ici)
	public static ArrayList<Coin> coinsOnFloor = new ArrayList<Coin>();
	private Window window;
	private int size = 20;
	private int PNJNumber=5;
		
	public Game(Window window){
		this.window = window;
		// Map building 
		for(int i = 0; i < size; i++){
			walls.add(new Wall(i,0));
			walls.add(new Wall(0,i));
			walls.add(new Wall(i, size-1));
			walls.add(new Wall(size-1, i));// les murs
		}
		for(int i = 1; i < size-1; i++){
			for(int j = 1; j < size-1; j++){
				tiles.add(new Tile(i,j));// les cases
			}
		}
		// Creating one Player at position (1,1)
		heroes.add(new Hero(10,10,1));
		for (int i=0; i<PNJNumber; i++){
			int posX = randomNum(1, size - 2);
			int posY = randomNum(1, size - 2);
			
			while(Collision(posX, posY)==true){
				posX = randomNum(1,size-2);
				posY = randomNum(1,size-2);
			}
			PNJs.add(new PNJ(posX,posY,1,1));
		}
		
		coinsOnFloor.add(new Coin(4,4));
		window.draw(this.getMap());
	}
		
	//  collision avec un mur?:
	public boolean Collision(int posX, int posY){
		boolean res = false;
		for (Wall wall: walls){						//TODO Juste voir si presenceAllowed sur la case en question.
			int WallPosX = wall.getPosX();
			int WallPosY = wall.getPosY();
			if(WallPosX == posX && WallPosY == posY ){
				res = true;
			}
		}
		/*for (Tile tile: tiles){					//TODO voir avec Coline (lignes inutiles?)
			if(tile.getPresence()==true){           // servait à utiliser l' attribut présence des dalles
				res=true;                           // plutôt que de parcourir les listes de joueurs et d' ennemis
			}
		}*/
		for (PNJ pnj: PNJs){
			int pnjPosX = pnj.getPosX();
			int pnjPosY = pnj.getPosY();
			if(pnjPosX == posX && pnjPosY == posY ){
				res = true;
			}
		}
		for (Hero hero : heroes){
			int heroPosX = hero.getPosX();
			int heroPosY = hero.getPosY();
			if(heroPosX == posX && heroPosY == posY ){
				res = true;
			}
		}
		return res;
	}
	
	//retourne un nombre aleatoire
	public int randomNum(int min, int max){
		int nb = min + (int)(Math.random() * ((max - min) + 1));
		return nb;
	}
	
	//renvoie l' indice d' une dalle etant dans une liste et dont on a la position.
	
	public int tileIndex(ArrayList<Tile> tiles,int x,int y){
		int res=0;
		for(int i=0; i<tiles.size();i++){
			if(tiles.get(i).getPosX()==x && tiles.get(i).getPosY()==y){
				res=i;
			}
		}
		return res;
	}
	
	public void movePlayerLeft(){
		if (Collision(heroes.get(0).getPosX() - 1, heroes.get(0).getPosY()) == false){
			heroes.get(0).move(-1, 0);
			window.draw(this.getMap());
		}
	}
	public void movePlayerRight(){
		if (Collision(heroes.get(0).getPosX() + 1, heroes.get(0).getPosY()) == false){
			heroes.get(0).move(1,0);
			window.draw(this.getMap());
		}
	}
	public void movePlayerDown(){
		if (Collision(heroes.get(0).getPosX(), heroes.get(0).getPosY() + 1) == false){
			heroes.get(0).move(0,1);
			window.draw(this.getMap());
		}
	}
	public void movePlayerUp(){
		if (Collision(heroes.get(0).getPosX(), heroes.get(0).getPosY() - 1) == false){
			heroes.get(0).move(0,-1);
			window.draw(this.getMap());
		}
	}
	
	public void heroAttacks(){
		heroes.get(0).attack(heroes.get(0),PNJs.get(0), 5);
		System.out.println("Attack !");
	}
	public void PlayerDeath (){ // Diparition du joueur ou des ennemis car morts
		for (Hero hero : heroes){
			if(hero.getLife()==0){
				heroes.remove(hero);
				System.out.println("Game Over!!!"); 		//TODO Game Over
			}
		}
		for (PNJ pnj:PNJs){
			if(pnj.getLife()==0){
				PNJs.remove(pnj);
				System.out.println("Well Done!!!"); 		
			}
		}
		
	}
	public void takeCoin(){
		
		
	}

	
	public int[][] getMap(){
		int[][] map = new int[this.size][this.size];
		for (Tile tile: tiles){
			int x= tile.getPosX();
			int y= tile.getPosY();
			map[x][y]=0;
		}

		for(Wall wall: walls){
			int x = wall.getPosX();
			int y = wall.getPosY();
			map[x][y] = 1;
		}
		for(Hero hero: heroes){
			int x = hero.getPosX();
			int y = hero.getPosY();
			map[x][y] = 2;
		}
		for(PNJ pnj: PNJs){
			int x = pnj.getPosX();
			int y = pnj.getPosY();
			map[x][y] = 3;
		}
		for(Coin coin: coinsOnFloor){
			int x = coin.getPosX();
			int y = coin.getPosY();
			map[x][y] = 4;
		}
		System.out.println(map);
		return map;
	}



}