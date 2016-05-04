package model;

import java.util.ArrayList;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import view.Perdu;
import view.Window;
import model.character.*;
import model.dalle.*;
import model.item.*;


public class Game implements Serializable{

	private static final long serialVersionUID = 1L;
	private ArrayList<Wall> walls = new ArrayList<Wall>();
	private ArrayList<Tile> tiles= new ArrayList<Tile>();
	private ArrayList<Tile> teleportationTiles= new ArrayList<Tile>();
	private ArrayList<Coin> coinsOnFloor = new ArrayList<Coin>();
	private ArrayList<Potion> potions =new ArrayList<Potion>();
	private Hero hero;
	private ArrayList<PNJ> PNJs = new ArrayList<PNJ>();
	private ArrayList<Thread> listThreads = new ArrayList<Thread>();
	
	private boolean pause;
	private Window window;
	private Perdu perdu;
	private int size;
	private int mapRange = 9; /*1*/
	private int teleNum;
	private int PNJNumber;
	private int coinNumber;
	private int potionNumber;
	private int blockNum;
	//* !!!1!!!on voit 19 cases dans la fenetre: il faut donc afficher 9 cases de part et d' autre du h�ros.*//
	
	
	
	/* ### CONSTRUCTEUR ### */
		
	public Game(Window window, int size){
		this.window = window;
		this.size = size;
		this.pause = false;
		int totalSize = size + 2*mapRange;
		// Map building
		modifyNumbers(size);
		
		// Wall building (9-blocks thick in order to avoid problem with getting a 19x19 frame when player adjacent to wall)
		for(int i = 0; i<totalSize; i++){
			if(i < mapRange || i >= mapRange + this.size){
				for(int j = 0; j<mapRange; j++){
					walls.add(new Wall(i,j));
					}
				}
			else{
				for(int j = 0; j<mapRange; j++){
					walls.add(new Wall(i,j));
					}
				for(int j = mapRange + this.size; j < totalSize; j++){
					walls.add(new Wall(i,j));
					}
				}
			}
			
		for(int i = mapRange; i < size + mapRange; i++){
				tiles.add(new Tile(i,i));// les cases
				}
		
		for (int i=0; i<blockNum; i++){
			int bX = randomNum(mapRange, size+mapRange-1); //avant il y avait (1,size-2)
			int bY = randomNum(mapRange, size+mapRange-1);
			walls.add(new Wall(bX,bY));
		}
	
		
		//creating teleportation tiles
		for (int i=0; i<teleNum; i++){
			int tX = randomNum(mapRange, size+mapRange-1);
			int tY = randomNum(mapRange, size+mapRange-1);
			while(collisionWall(tX,tY)==true){
				tX = randomNum(mapRange, size+mapRange-1);
				tY = randomNum(mapRange, size+mapRange-1);
			}
			teleportationTiles.add(new Tile(tX,tY));
		}
	
		// Creating hero
		int hX=randomNum(mapRange, size+mapRange-1);
		int hY=randomNum(mapRange, size+mapRange-1);
		while (beOnTeleTile(hX,hY)==true || collisionWall(hX,hY)==true){ 	//TODO enlever beOnTeleTile ?
			hX=randomNum(mapRange, size+mapRange-1);
			hY=randomNum(mapRange, size+mapRange-1);		
		}
		hero=new Hero(hX,hY,1,this);
		//hero=new Hero(size/2,size/2,1,this);
		
		// Creating PNJ's
		for (int i=0; i<PNJNumber; i++){
			int posX = randomNum(mapRange, size+mapRange-1);
			int posY = randomNum(mapRange, size+mapRange-1);
			
			while(Collision(posX, posY)==true || beOnTeleTile(posX,posY)==true){
				posX = randomNum(mapRange, size+mapRange-1);// avant (1,size-2)
				posY = randomNum(mapRange, size+mapRange-1);
			}
			Rat rat = new Rat(posX,posY,1,1, this);
			PNJs.add(rat);
			Thread thread = new Thread(rat);
			listThreads.add(thread);
		}
		// Creation des pieces
		for (int i=0; i<coinNumber; i++){
			int cX = randomNum(mapRange, size+mapRange-1);
			int cY = randomNum(mapRange, size+mapRange-1);
			
			while(Collision(cX, cY)==true || beOnTeleTile(cX,cY)==true){
				cX = randomNum(mapRange, size+mapRange-1);
				cY = randomNum(mapRange, size+mapRange-1);
			}
			coinsOnFloor.add(new Coin(cX,cY));
		}
		//Creation des potions
		for (int i=0; i<potionNumber; i++){
			int pX = randomNum(mapRange, size+mapRange-1);
			int pY = randomNum(mapRange, size+mapRange-1);
			
			while(Collision(pX, pY)==true && beOnTeleTile(pX,pY)==true){
				pX = randomNum(mapRange, size+mapRange-1);
				pY = randomNum(mapRange, size+mapRange-1);
			}
			potions.add(new Potion(pX,pY));
		}
		
		window.settings(hero);
		window.draw(this.getMap());
}
	/*
	public Game(Window window, int size){
		this.window = window;
		this.size = size;
		this.pause = false;
		int totalSize = size + 2*mapRange;
		// Map building
		modifyNumbers(size);
		for (int j=0;j<mapRange;j++){  //il faut ajouter des murs plus "epais" pour eviter que la map ignore les objets qu'elle doit afficher
			for(int i = j; i < totalSize-j; i++){
				walls.add(new Wall(i,j));
				walls.add(new Wall(j,i));
				walls.add(new Wall(i, totalSize-1-j));
				walls.add(new Wall(totalSize-1-j, i));// les murs
			}
		}
			
		for(int i = mapRange; i < size+mapRange-1; i++){
			for(int j = mapRange; j < size+mapRange-1; j++){
				tiles.add(new Tile(i,j));// les cases
			}
		}
		
		for (int i=0; i<blockNum; i++){
			int bX = randomNum(mapRange, size+mapRange-1); //avant il y avait (1,size-2)
			int bY = randomNum(mapRange, size+mapRange -1);
			walls.add(new Wall(bX,bY));
		}
	
		
		//creating teleportation tiles
		for (int i=0; i<teleNum; i++){
			int tX = randomNum(mapRange, size+mapRange-1);
			int tY = randomNum(mapRange, size+mapRange-1);
			while(collisionWall(tX,tY)==true){
				tX = randomNum(mapRange, size+mapRange-1);
				tY = randomNum(mapRange, size+mapRange-1);
			}
			teleportationTiles.add(new Tile(tX,tY));
		}
	
		// Creating hero
		int hX=randomNum(mapRange, size+mapRange-1);
		int hY=randomNum(mapRange, size+mapRange-1);
		while (beOnTeleTile(hX,hY)==true || collisionWall(hX,hY)==true){
			hX=randomNum(mapRange, size+mapRange-1);
			hY=randomNum(mapRange, size+mapRange-1);		
		}
		hero=new Hero(hX,hY,1,this);
		//hero=new Hero(size/2,size/2,1,this);
		
		// Creating PNJ's
		for (int i=0; i<PNJNumber; i++){
			int posX = randomNum(mapRange, size+mapRange-1);
			int posY = randomNum(mapRange, size+mapRange-1);
			
			while(Collision(posX, posY)==true || beOnTeleTile(posX,posY)==true){
				posX = randomNum(mapRange, size+mapRange-1);// avant (1,size-2)
				posY = randomNum(mapRange, size+mapRange-1);
			}
			Rat rat = new Rat(posX,posY,1,1, this);
			PNJs.add(rat);
			Thread thread = new Thread(rat);
			listThreads.add(thread);
		}
		// Creation des pieces
		for (int i=0; i<coinNumber; i++){
			int cX = randomNum(mapRange, size+mapRange-1);
			int cY = randomNum(mapRange, size+mapRange-1);
			
			while(Collision(cX, cY)==true || beOnTeleTile(cX,cY)==true){
				cX = randomNum(mapRange, size+mapRange-1);
				cY = randomNum(mapRange, size+mapRange-1);
			}
			coinsOnFloor.add(new Coin(cX,cY));
		}
		//Creation des potions
		for (int i=0; i<potionNumber; i++){
			int pX = randomNum(mapRange, size+mapRange-1);
			int pY = randomNum(mapRange, size+mapRange-1);
			
			while(Collision(pX, pY)==true && beOnTeleTile(pX,pY)==true){
				pX = randomNum(mapRange, size+mapRange-1);
				pY = randomNum(mapRange, size+mapRange-1);
			}
			potions.add(new Potion(pX,pY));
		}
		
		window.settings(hero);
		window.draw(this.getMap());
}*/
	
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
	public ArrayList<Thread> getListThreads(){
		return listThreads;
	}
	public boolean isOnPause(){
		return pause;
	}
	public void pauseGame(){
		this.pause = true;
	}
	public void resumeGame(){
		this.pause = false;
	}
	
	private void modifyNumbers(int size){
		if(size==30){
			this.teleNum = 5;
			this.PNJNumber = 10;
			this.coinNumber = 20;
			this.potionNumber = 8;
			this.blockNum = 50;
			
		}
		else if (size==40){
			this.teleNum = 8;
			this.PNJNumber = 15;
			this.coinNumber = 30;
			this.potionNumber = 10;
			this.blockNum = 100;
		}
		else{
			this.teleNum = 10;
			this.PNJNumber = 20;
			this.coinNumber = 45;
			this.potionNumber = 12;
			this.blockNum = 300;
			
		}
		
	}
		
	//  collision avec un mur?:
	public boolean Collision(int posX, int posY){
		boolean res = false;
		if (collisionWall(posX,posY)==true){
			res=true;
		}
		/*for (Tile tile: tiles){					//TODO voir avec Coline (lignes inutiles?)
			if(tile.getPresence()==true){           // servait � utiliser l' attribut pr�sence des dalles
				res=true;                           // plut�t que de parcourir les listes de joueurs et d' ennemis
			}
		}*/
		else if(collisionPNJ(posX, posY)==true){	//TODO enlever else?
			res=true;
		}
		else if(collisionHero(posX,posY)==true){	//TODO idem?
			res=true;
		}
		
		return res;
	}
	/*Sous fonctions de Collision*/
	private boolean beOnTeleTile(int x, int y){
		boolean res=false;
		for (Tile tile: teleportationTiles){
			if (tile.getPosX()==x && tile.getPosY()==y){
				res=true;
			}
		}
		return res;
	}
	private boolean collisionWall(int posX, int posY){
		boolean res=false;
		for (Wall wall: walls){						//TODO Juste voir si presenceAllowed sur la case en question.
			int WallPosX = wall.getPosX();
			int WallPosY = wall.getPosY();
			if(WallPosX == posX && WallPosY == posY ){
				res = true;
			}
		}
		return res;
	}
	private boolean collisionPNJ(int posX, int posY){
		boolean res=false;
		for (PNJ pnj: PNJs){
			int pnjPosX = pnj.getPosX();
			int pnjPosY = pnj.getPosY();
			if(pnjPosX == posX && pnjPosY == posY ){
				res = true;
			}
		}
		return res;
	}
	private boolean collisionHero (int posX, int posY){
		boolean res = false;
		int heroPosX = hero.getPosX();
		int heroPosY = hero.getPosY();
		if(heroPosX == posX && heroPosY == posY ){
			res = true;
		}
		return res;
	}
	
	//retourne un nombre aleatoire
	public int randomNum(int min, int max){
		int nb = min + (int)(Math.random() * ((max - min) + 1));
		return nb;
	}
	
	
	//revoie l' indice d' un ennemi dans la liste et sachant sa position
	private int listIndexPNJ(ArrayList<PNJ> list,int x,int y){
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
	
	private int randomTeleTile(){
		int index =randomNum(0,teleNum-1);
		return index;
	}
	private void teleportation(){
		int x=hero.getPosX();
		int y= hero.getPosY();
		if(beOnTeleTile(x,y)==true){
			int i=randomTeleTile();
			hero.setPosX(teleportationTiles.get(i).getPosX());
			hero.setPosY(teleportationTiles.get(i).getPosY());
		}
		
	}
	
	public void heroAttacks(){
		hero.attack();
		window.settings(hero);
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
	private void dropItem(PNJ pnj){
		//remplacement par un objet pi�ce ou potion
		int i= pnj.dropPNJ();
		if (i==1){
			coinsOnFloor.add(new Coin(pnj.getPosX(),pnj.getPosY()));
		}
		else{
			potions.add(new Potion(pnj.getPosX(),pnj.getPosY()));
		}
	}
	private void takeCoin(){
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
	public void usePower(){
		hero.usePower();
		window.settings(hero);
	}
	
	public void startThreads(){
		for(Thread thread : listThreads){
			thread.start();
		}
	}


// donne des images a la map	
	public int[][] getMap(){
		int[][] map = new int[1+2*this.mapRange][1+2*this.mapRange];
		int posX = hero.getPosX();
		int posY = hero.getPosY();
		for (Tile tile: tiles){
			int x = tile.getPosX();
			int y = tile.getPosY();
			if(Math.abs(x-posX) <= mapRange && Math.abs(y-posY) <= mapRange){
				map[x-posX-mapRange][y-posX-mapRange]=0;
			}
		}
		for(Wall wall: walls){
			int x = wall.getPosX();
			int y = wall.getPosY();
			if(Math.abs(x-posX)<=mapRange&& Math.abs(y-posY)<=mapRange){
				map[x-posX-mapRange][y-posY-mapRange]=1;
			}
		}
		for(Tile teleTile: teleportationTiles){
			int x = teleTile.getPosX();
			int y = teleTile.getPosY();
			if(Math.abs(x-posX)<=mapRange&& Math.abs(y-posY)<=mapRange){
				map[x-posX-mapRange][y-posY-mapRange]=6;
			}
		}
		for(Coin coin: coinsOnFloor){
			int x = coin.getPosX();
			int y = coin.getPosY();
			if(Math.abs(x-posX)<=mapRange&& Math.abs(y-posY)<=mapRange){
				map[x-posX-mapRange][y-posY-mapRange]=4;
			}
		}
		for(Potion potion: potions){
			int x = potion.getPosX();
			int y = potion.getPosY();
			if(Math.abs(x-posX)<=mapRange&& Math.abs(y-posY)<=mapRange){
				map[x-posX-mapRange][y-posY-mapRange]=5;
			}
		}
		for(PNJ pnj: PNJs){
			int x = pnj.getPosX();
			int y = pnj.getPosY();
			if(Math.abs(x-posX)<=mapRange&& Math.abs(y-posY)<=mapRange){
				map[x-posX+mapRange][y-posY+mapRange]=3;
			}
		}
		
			
			map[mapRange][mapRange] = 2;
		
		
		
		System.out.println(map);
		return map;
	}
	/*
	public int[][] getMap(){
		int[][] map = new int[size][size];
		for (Tile tile: tiles){
			map[tile.getPosX()][tile.getPosY()]=0;
			}
		
		for(Wall wall: walls){
			map[wall.getPosX()][wall.getPosY()]=1;
			}
		for(Tile teleTile: teleportationTiles){
			map[teleTile.getPosX()][teleTile.getPosY()]=6;
			}

		for(Coin coin: coinsOnFloor){
			map[coin.getPosX()][coin.getPosY()] = 4;
			}
		for(Potion potion: potions){
				map[potion.getPosX()][potion.getPosY()]=5;
				}
		for(PNJ pnj: PNJs){
				map[pnj.getPosX()][pnj.getPosY()]=3;
			}
		
		map[hero.getPosX()][hero.getPosY()] = 2;
		
		System.out.println(map);
		return map;
	}*/
	

	public Game load(String filename){
		FileInputStream file;
		ObjectInputStream i;
		Game game;
		try{
			file = new FileInputStream(filename);
			i = new ObjectInputStream(file);
			game = (Game) i.readObject();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return game;
	}
	
	public void save(String filename, Game game){
		FileOutputStream file;
		ObjectOutputStream o;
		try{
			file = new FileOutputStream(filename);
			o = new ObjectOutputStream(file);
			o.writeObject(game);
			o.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}