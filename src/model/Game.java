package model;

import java.util.ArrayList;

import view.Perdu;
import view.Window;
import model.character.*;
import model.dalle.*;
import model.item.*;

public class Game {
	private ArrayList<Wall> walls = new ArrayList<Wall>();// Liste des murs
	private ArrayList<Tile> tiles= new ArrayList<Tile>();// liste des cases (normales ici)
	private ArrayList<Tile> teleportationTiles= new ArrayList<Tile>();// TODO teleportation du hero
	private ArrayList<Coin> coinsOnFloor = new ArrayList<Coin>();
	private ArrayList<Potion> potions =new ArrayList<Potion>();
	private Hero hero;
	private ArrayList<PNJ> PNJs = new ArrayList<PNJ>();
	private Window window;
	private Perdu perdu;
	private int size;
	private int mapRange=10;
	private int teleNum;
	private int PNJNumber;
	private int coinNumber;
	private int potionNumber;
	
	//*GETTERS*//
	public Hero getHero(){
		return hero;
	}
	
	public ArrayList<Wall> getWalls(){
		return walls;
	}
	public ArrayList<Tile> getTiles(){
		return tiles;
	}
	public ArrayList<Coin> getCoins(){
		return coinsOnFloor;
	}
	
	public ArrayList<PNJ> getPNJs(){
		return PNJs;
	}
	public ArrayList<Potion> getPotions(){
		return potions;
	}
	
	//* FONCTIONS RANDOM *//
	
		
	public Game(Window window, int size){
		this.window = window;
		this.size = size;
		// Map building
		modifyNumbers(size);
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
		
		//creating teleportation tiles
		for (int i=0; i<teleNum; i++){
			int tX = randomNum(1, size - 2);
			int tY = randomNum(1, size - 2);
			teleportationTiles.add(new Tile(tX,tY));
		}
	
		// Creating hero
		int hX=randomNum(1,size-2);
		int hY=randomNum(1,size-2);
		while (beOnTeleTile(hX,hY)==true){
			hX=randomNum(1,size-2);
			hY=randomNum(1,size-2);		
		}
		hero=new Hero(hX,hY,1,this);
		//hero=new Hero(size/2,size/2,1,this);
		
		// Creating PNJ's
		for (int i=0; i<PNJNumber; i++){
			int posX = randomNum(1, size - 2);
			int posY = randomNum(1, size - 2);
			
			while(Collision(posX, posY)==true && beOnTeleTile(posX,posY)==true){
				posX = randomNum(1,size-2);
				posY = randomNum(1,size-2);
			}
			Rat rat = new Rat(posX,posY,1,1, this);
			PNJs.add(rat);
			Thread t1 = new Thread(rat);
			t1.start();
		}
		// Creation des pieces
		for (int i=0; i<coinNumber; i++){
			int cX = randomNum(1, size - 2);
			int cY = randomNum(1, size - 2);
			
			while(Collision(cX, cY)==true && beOnTeleTile(cX,cY)==true){
				cX = randomNum(1,size-2);
				cY = randomNum(1,size-2);
			}
			coinsOnFloor.add(new Coin(cX,cY));
		}
		//Creation des potions
		for (int i=0; i<potionNumber; i++){
			int pX = randomNum(1, size - 2);
			int pY = randomNum(1, size - 2);
			
			while(Collision(pX, pY)==true && beOnTeleTile(pX,pY)==true){
				pX = randomNum(1,size-2);
				pY = randomNum(1,size-2);
			}
			potions.add(new Potion(pX,pY));
		}
		
		window.settings(hero);
		window.draw(this.getMap());
	}
	private void modifyNumbers(int size){
		if(size==30){
			this.teleNum=5;
			this.PNJNumber=10;
			this.coinNumber=20;
			this.potionNumber=8;
			
		}
		else if (size==40){
			this.teleNum=8;
			this.PNJNumber=15;
			this.coinNumber=30;
			this.potionNumber=10;
		}
		else{
			this.teleNum=10;
			this.PNJNumber=20;
			this.coinNumber=45;
			this.potionNumber=12;
		}
		
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
			if(tile.getPresence()==true){           // servait � utiliser l' attribut pr�sence des dalles
				res=true;                           // plut�t que de parcourir les listes de joueurs et d' ennemis
			}
		}*/
		for (PNJ pnj: PNJs){
			int pnjPosX = pnj.getPosX();
			int pnjPosY = pnj.getPosY();
			if(pnjPosX == posX && pnjPosY == posY ){
				res = true;
			}
		}
		int heroPosX = hero.getPosX();
		int heroPosY = hero.getPosY();
		if(heroPosX == posX && heroPosY == posY ){
			res = true;
		}
		
		return res;
	}
	public boolean beOnTeleTile(int x, int y){
		boolean res=false;
		for (Tile tile: teleportationTiles){
			if (tile.getPosX()==x && tile.getPosY()==y){
				res=true;
			}
		}
		return res;
	}
	
	//retourne un nombre aleatoire
	public int randomNum(int min, int max){
		int nb = min + (int)(Math.random() * ((max - min) + 1));
		return nb;
	}
	
	
	//revoie l' indice d' un ennemi dans la liste et sachant sa position
	public int listIndexPNJ(ArrayList<PNJ> list,int x,int y){
		int range= hero.getAttackRange();
		int res=0;
		for(int i=0; i<list.size();i++){
			if(Math.abs(list.get(i).getPosX()-x )<=range&& Math.abs( list.get(i).getPosY()-y)<=range){
				res=i;
			}
		}
		return res;
	}
	
	public void movePlayerLeft(){
		if (Collision(hero.getPosX() - 1, hero.getPosY()) == false){
			hero.move(-1, 0);
			takeCoin();
			teleportation();
			window.settings(hero);
			window.draw(this.getMap());
		}
	}
	public void movePlayerRight(){
		if (Collision(hero.getPosX() + 1, hero.getPosY()) == false){
			hero.move(1,0);
			takeCoin();
			teleportation();
			window.settings(hero);
			window.draw(this.getMap());
		}
	}
	public void movePlayerDown(){
		if (Collision(hero.getPosX(), hero.getPosY() + 1) == false){
			hero.move(0,1);
			takeCoin();
			teleportation();
			window.settings(hero);
			window.draw(this.getMap());
			
		}
	}
	public void movePlayerUp(){
		if (Collision(hero.getPosX(), hero.getPosY() - 1) == false){
			hero.move(0,-1);
			takeCoin();
			teleportation();
			window.settings(hero);
			window.draw(this.getMap());
		}
	}
	public int randomTeleTile(){
		int index =randomNum(0,teleNum-1);
		return index;
	}
	public void teleportation(){
		int x=hero.getPosX();
		int y= hero.getPosY();
		if(beOnTeleTile(x,y)==true){
			int i=randomTeleTile();
			hero.setPosX(teleportationTiles.get(i).getPosX());
			hero.setPosY(teleportationTiles.get(i).getPosY());
		}
		
	}
	
	public void heroAttacks(){
		int x=hero.getPosX();
		int y= hero.getPosY();
		int targetIndex=listIndexPNJ(PNJs,x,y);
		hero.attack(PNJs.get(targetIndex), 5);
		System.out.println("Attack !");
	}
	public void playerDeath (){ // Diparition du joueur ou des ennemis car morts
		
		if(hero.getLife()==0){
			System.out.println("Game Over!!!"); 		//TODO Game Over
			window.gameOver();
			perdu=new Perdu();
		}
		
		for (int i=0; i<PNJs.size();i++){
			if(PNJs.get(i).getLife()==0){
				dropItem(PNJs.get(i));
				PNJs.remove(i);
				System.out.println("Well Done!!!"); 		
			}
		}
		window.settings(hero);
		window.draw(this.getMap());
		
	}
	public void dropItem(PNJ pnj){
		//remplacement par un objet pi�ce ou potion
		int i= pnj.dropPNJ();
		if (i==1){
			coinsOnFloor.add(new Coin(pnj.getPosX(),pnj.getPosY()));
		}
		else{
			potions.add(new Potion(pnj.getPosX(),pnj.getPosY()));
		}
	}
	public void takeCoin(){
		int x= hero.getPosX();
		int y= hero.getPosY();
		for( int i=0;i< coinsOnFloor.size(); i++){
			if(coinsOnFloor.get(i).getPosX()==x && coinsOnFloor.get(i).getPosY()==y){
				hero.grabCoin();
				coinsOnFloor.remove(i);
				window.settings(hero);
			}
		}
		
	}
	public void takePotion (){
		int x= hero.getPosX();
		int y= hero.getPosY();
		int max=hero.getMaxPotion();
		for( int i=0;i< potions.size(); i++){
			if(potions.get(i).getPosX()==x && potions.get(i).getPosY()==y&& hero.getPotionNumInventory()<max){
				hero.grabPotion(potions.get(i));
				potions.remove(i);
				window.settings(hero);
				
			}
		}
	}
	public void drinkPotion(){
		if (hero.getPotionNumInventory()>0){
			hero.drinkPotion();
			window.settings(hero);
			playerDeath();
		}
	}
		

// donne des images a la map	
	public int[][] getMap(){
		int[][] map = new int[this.size][this.size];
		//int xc=hero.getPosX();
		//int yc= hero.getPosY();
		for (Tile tile: tiles){
			int x= tile.getPosX();
			int y= tile.getPosY();
			
				map[x][y]=0;
			
		}

		for(Wall wall: walls){
			int x = wall.getPosX();
			int y = wall.getPosY();
			
				map[x][y]=1;
			
		}
		for(Tile teleTile: teleportationTiles){
			int x = teleTile.getPosX();
			int y = teleTile.getPosY();
			
				map[x][y]=6;
			
		}
		
		for(Coin coin: coinsOnFloor){
			int x = coin.getPosX();
			int y = coin.getPosY();
			
				map[x][y]=4;
			
		}
		for(Potion potion: potions){
			int x = potion.getPosX();
			int y = potion.getPosY();
			
				map[x][y]=5;
			
		}
		for(PNJ pnj: PNJs){
			int x = pnj.getPosX();
			int y = pnj.getPosY();
			
				map[x][y]=3;
			
		}
		
			int x = hero.getPosX();
			int y = hero.getPosY();
			map[x][y] = 2;
		
		
		
		System.out.println(map);
		return map;
	}
	



}