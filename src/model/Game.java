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
import outils.Fonctions;


public class Game implements Serializable{

	private static final long serialVersionUID = 1L;
	private ArrayList<Block> walls = new ArrayList<Block>();
	private ArrayList<Block> tiles = new ArrayList<Block>();
	private ArrayList<TeleportationTile> teleportationTiles = new ArrayList<TeleportationTile>();
	private ArrayList<DamageTile> damTiles = new ArrayList<DamageTile>();
	private ArrayList<Coin> coinsOnFloor = new ArrayList<Coin>();
	private ArrayList<Potion> potions = new ArrayList<Potion>();
	private Hero hero;
	private ArrayList<PNJ> PNJs = new ArrayList<PNJ>();
	private ArrayList<Thread> listThreads = new ArrayList<Thread>();
	
	private boolean pause;
	private Window window;
	private Perdu perdu;
	private int size;
	protected final int MAP_RANGE = 9; /*1*/  // wall is 9 cases thick to avoid any problem with the painting of the map centered of the hero
	private int teleNum;						
	private int ratNumber;
	private int saibamanNumber;
	private int coinNumber;
	private int potionNumber;
	private int blockNum;
	private int damNum;
	
	
	
	/* ### CONSTRUCTEUR ### */
		
	public Game(Window window, int size){
		this.window = window;
		this.size = size;
		this.pause = false;
		int totalSize = size + 2*MAP_RANGE;
		
		// Map building (and determining the total number of all elements)
		modifyNumbers(size);
		
		// Wall building (9-blocks thick in order to avoid problem with getting a 19x19 frame when player adjacent to wall)
		for(int i = 0; i<totalSize; i++){
			if(i < MAP_RANGE || i >= MAP_RANGE + this.size){
				for(int j = 0; j<totalSize; j++){
					walls.add(new Block(i, j, 0));
					}
				}
			else{
				for(int j = 0; j<MAP_RANGE; j++){
					walls.add(new Block(i, j, 0));
					}
				for(int j = MAP_RANGE + this.size; j < totalSize; j++){
					walls.add(new Block(i, j, 0));
					}
				}
			}
			
		for(int i = MAP_RANGE; i < size + MAP_RANGE; i++){
				tiles.add(new Block(i, i, 1));// les cases
				}
		
		for (int i=0; i < blockNum; i++){ 		// create random walls on the map to block the players
			walls.add(new Block(Fonctions.randomNum(MAP_RANGE, size+MAP_RANGE-1), Fonctions.randomNum(MAP_RANGE, size+MAP_RANGE-1), 0));
		}
	
		
		//creating teleportation tiles
		for (int i=0; i<teleNum; i++){
			int tX = Fonctions.randomNum(MAP_RANGE, size+MAP_RANGE-1);
			int tY = Fonctions.randomNum(MAP_RANGE, size+MAP_RANGE-1);
			while(collisionWall(tX, tY) == true){
				tX = Fonctions.randomNum(MAP_RANGE, size+MAP_RANGE-1);
				tY = Fonctions.randomNum(MAP_RANGE, size+MAP_RANGE-1);
			}
			teleportationTiles.add(new TeleportationTile(tX, tY, this));
		}
		//creation des dalles blessantes
		for (int i=0; i<damNum; i++){
			int dX = Fonctions.randomNum(MAP_RANGE, size+MAP_RANGE-1);
			int dY = Fonctions.randomNum(MAP_RANGE, size+MAP_RANGE-1);
			while(collisionWall(dX,dY) == true || beOnTeleTile(dX,dY) == true ){
				dX = Fonctions.randomNum(MAP_RANGE, size+MAP_RANGE-1);
				dY = Fonctions.randomNum(MAP_RANGE, size+MAP_RANGE-1);
			}
			damTiles.add(new DamageTile(dX, dY));
<<<<<<< HEAD
=======

>>>>>>> origin/master
		}
		
		// Creating hero
		int hX=Fonctions.randomNum(MAP_RANGE, size+MAP_RANGE-1);
		int hY=Fonctions.randomNum(MAP_RANGE, size+MAP_RANGE-1);
		while (beOnDamTile(hX,hY) == true || collisionWall(hX,hY) == true){ 
			hX=Fonctions.randomNum(MAP_RANGE, size+MAP_RANGE-1);
			hY=Fonctions.randomNum(MAP_RANGE, size+MAP_RANGE-1);		
		}
		hero = new Hero(hX, hY , 3, this, 3, 2, 20);		//attackRange = 3; life = 3; attackDamage = 2; maxDamage = 20;
		
		
		// Creating rats
		for (int i=0; i<ratNumber; i++){
			int posX = Fonctions.randomNum(MAP_RANGE, size+MAP_RANGE-1);
			int posY = Fonctions.randomNum(MAP_RANGE, size+MAP_RANGE-1);
			
			while(Collision(posX, posY)==true || beOnTeleTile(posX,posY)==true || beOnDamTile(posX, posY)== true){
				posX = Fonctions.randomNum(MAP_RANGE, size+MAP_RANGE-1);// avant (1,size-2)
				posY = Fonctions.randomNum(MAP_RANGE, size+MAP_RANGE-1);
			}
			Rat rat = new Rat(posX, posY, this);
			PNJs.add(rat);
			Thread thread = new Thread(rat);
			listThreads.add(thread);
		}
		// creating Saibaman
		for (int i=0; i<saibamanNumber; i++){
			int posX = Fonctions.randomNum(MAP_RANGE, size+MAP_RANGE-1);
			int posY = Fonctions.randomNum(MAP_RANGE, size+MAP_RANGE-1);
			
			while(Collision(posX, posY)==true || beOnTeleTile(posX,posY)==true || beOnDamTile(posX, posY)== true){
				posX = Fonctions.randomNum(MAP_RANGE, size+MAP_RANGE-1);// avant (1,size-2)
				posY = Fonctions.randomNum(MAP_RANGE, size+MAP_RANGE-1);
			}
			Saibaman saibaman = new Saibaman(posX, posY, this);
			PNJs.add(saibaman);
			Thread thread = new Thread(saibaman);
			listThreads.add(thread);
		}
		// Creation des pieces
		for (int i=0; i<coinNumber; i++){
			int cX = Fonctions.randomNum(MAP_RANGE, size+MAP_RANGE-1);
			int cY = Fonctions.randomNum(MAP_RANGE, size+MAP_RANGE-1);
			
			while(Collision(cX, cY)==true || beOnTeleTile(cX,cY)==true){
				cX = Fonctions.randomNum(MAP_RANGE, size+MAP_RANGE-1);
				cY = Fonctions.randomNum(MAP_RANGE, size+MAP_RANGE-1);
			}
			coinsOnFloor.add(new Coin(cX,cY));
		}
		//Creation des potions
		for (int i=0; i<potionNumber; i++){
			int pX = Fonctions.randomNum(MAP_RANGE, size+MAP_RANGE-1);
			int pY = Fonctions.randomNum(MAP_RANGE, size+MAP_RANGE-1);
			
			while(Collision(pX, pY)==true && beOnTeleTile(pX,pY)==true){
				pX = Fonctions.randomNum(MAP_RANGE, size+MAP_RANGE-1);
				pY = Fonctions.randomNum(MAP_RANGE, size+MAP_RANGE-1);
			}
			potions.add(new Potion(pX,pY));
		}
		
		window.settings(hero);
		window.draw(this.getMap());
}
	
	
	
	
	//*GETTERS*//
	public Hero getHero(){
		return hero;
	}
	public ArrayList<PNJ> getPNJs(){
		return PNJs;
	}
	public ArrayList<Block> getWalls(){
		return walls;
	}
	public ArrayList<Block> getTiles(){
		return tiles;
	}
	public ArrayList<Coin> getCoins(){
		return coinsOnFloor;
	}
	public ArrayList<Potion> getPotions(){
		return potions;
	}
	public ArrayList<Thread> getListThreads(){
		return listThreads;
	}
	public int getSize(){
		return size;
	}
	public int getMapRange(){
		return MAP_RANGE;
	}
	
	/* +++ Methods on attributes +++ */
	public void addPotion(Potion potion){
		potions.add(potion);
	}
	public void addCoins(Coin coins){
		coinsOnFloor.add(coins);
	}
	public void deletePNJ(PNJ pnj){
		PNJs.remove(this);
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
	
	// Determine the number of each elements based on the size of the map.
	private void modifyNumbers(int size){
		if(size == 30){
			this.teleNum = 5;
			this.ratNumber = 10;
			this.saibamanNumber = 10;
			this.coinNumber = 20;
			this.potionNumber = 8;
			this.blockNum = 70;
			this.damNum = 10;
			
		}
		else if (size == 50){
			this.teleNum = 8;
			this.ratNumber = 15;
			this.saibamanNumber = 15;
			this.coinNumber = 30;
			this.potionNumber = 10;
			this.blockNum = 120;
			this.damNum = 16;
		}
		else{
			this.teleNum = 15;
			this.ratNumber = 20;
			this.saibamanNumber = 20;
			this.coinNumber = 45;
			this.potionNumber = 15;
			this.blockNum = 300;
			this.damNum = 25;
			
		}
		
	}
		
	/* £££ Collision functions £££ */
	
	public boolean Collision(int posX, int posY){
		boolean res = false;
		if (collisionWall(posX,posY)==true){
			res = true;
		}
		else if(collisionPNJ(posX, posY)==true){	
			res = true;
		}
		else if(collisionHero(posX,posY)==true){	
			res = true;
		}
		return res;
	}
	
	/*Sous fonctions de Collision*/
	public boolean beOnTeleTile(int posX, int posY){
		boolean res = false;
		for (Block tile: teleportationTiles){
			if (tile.getPosX() == posX && tile.getPosY() == posY){
				res = true;
			}
		}
		return res;
	}
	private boolean beOnDamTile(int posX, int posY){			// collision with trap tiles
		boolean res = false;
		for (DamageTile dTile: damTiles){
			if (dTile.getPosX() == posX && dTile.getPosY() == posY){
				res=true;
			}
		}
		return res;
	}
	
	private boolean collisionWall(int posX, int posY){
		boolean res = false;
		for (Block wall: walls){						//TODO Juste voir si presenceAllowed sur la case en question.
			if(wall.getPosX() == posX && wall.getPosY() == posY ){
				res = true;
			}
		}
		return res;
	}
	private boolean collisionPNJ(int posX, int posY){
		boolean res=false;
		for (PNJ pnj: PNJs){
			if(pnj.getPosX() == posX && pnj.getPosY() == posY ){
				res = true;
			}
		}
		return res;
	}
	private boolean collisionHero (int posX, int posY){
		boolean res = false;
		if(hero.getPosX() == posX && hero.getPosY() == posY ){
			res = true;
		}
		return res;
	}

	
	
	/*revoie l' indice d' un ennemi dans la liste et sachant sa position
	private int listIndexPNJ(ArrayList<PNJ> list,int x,int y){
		int range= hero.getAttackRange();
		int res=0;
		for(int i=0; i<list.size();i++){
			if(Math.abs(list.get(i).getPosX()-x )<=range&& Math.abs( list.get(i).getPosY()-y)<=range){
				res=i;
			}
		}
		return res;
	}*/
	
	public void movePlayerLeft(){
		if (Collision(hero.getPosX() - 1, hero.getPosY()) == false){
			hero.move(-1, 0);
			takeCoin();
			teleportation();
			hurt();
			playerDeath();
			window.settings(hero);
			window.draw(this.getMap());
		}
	}
	public void movePlayerRight(){
		if (Collision(hero.getPosX() + 1, hero.getPosY()) == false){
			hero.move(1,0);
			takeCoin();
			teleportation();
			hurt();
			playerDeath();
			window.settings(hero);
			window.draw(this.getMap());
		}
	}
	public void movePlayerDown(){
		if (Collision(hero.getPosX(), hero.getPosY() + 1) == false){
			hero.move(0,1);
			takeCoin();
			teleportation();
			hurt();
			playerDeath();
			window.settings(hero);
			window.draw(this.getMap());
			
		}
	}
	public void movePlayerUp(){
		if (Collision(hero.getPosX(), hero.getPosY() - 1) == false){
			hero.move(0,-1);
			takeCoin();
			teleportation();
			hurt();
			playerDeath();
			window.settings(hero);
			window.draw(this.getMap());
		}
	}
	
	public TeleportationTile randomTeleTile(){
		int index = Fonctions.randomNum(0,teleNum-1);
		return teleportationTiles.get(index);
	}
	private void teleportation(){
		for(TeleportationTile teleTile: teleportationTiles){
			if(teleTile.getPosX() == hero.getPosX() && teleTile.getPosY() == hero.getPosY()){
				teleTile.teleportation(hero);
			}
		}
	}
	private void hurt(){
		for(DamageTile dTile: damTiles){
			dTile.hurt(hero);
			playerDeath();
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
	
	public void gameOver(){
		window.gameOver();
		perdu = new Perdu();
		window.settings(hero);
		window.draw(this.getMap());
	}
	private void dropItem(PNJ pnj){
		//remplacement par un objet pi�ce ou potion
		pnj.dropItem();
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
			//playerDeath();
		}
	}
	public void usePower(){
		hero.reduceDamagePower();
		window.settings(hero);
	}
	
	public void startThreads(){
		for(Thread thread : listThreads){
			thread.start();
		}
	}
	
	public void pnjDie(PNJ pnj){
		int indexPNJ = PNJs.indexOf(pnj);
		Thread thread = listThreads.get(indexPNJ);
		thread.interrupt();
		listThreads.remove(thread);
		PNJs.remove(pnj);	
	}


	public int[][] getMap(){
		int[][] map = new int[1+2*this.MAP_RANGE][1+2*this.MAP_RANGE];
		int posX = hero.getPosX();
		int posY = hero.getPosY();
		
		for(DamageTile dTile: damTiles){
			int x = dTile.getPosX();
			int y = dTile.getPosY();
			if(Math.abs(x-posX)<=MAP_RANGE&& Math.abs(y-posY)<=MAP_RANGE){
				map[x-posX+MAP_RANGE][y-posY+MAP_RANGE] = 7;
			}
		}
		for (Block tile: tiles){
			int x = tile.getPosX();
			int y = tile.getPosY();
			if(Math.abs(x-posX) <= MAP_RANGE && Math.abs(y-posY) <= MAP_RANGE){
				map[x-posX+MAP_RANGE][y-posX+MAP_RANGE] = 0;
			}
		}
		for(Block wall: walls){
			int x = wall.getPosX();
			int y = wall.getPosY();
			if(Math.abs(x-posX)<=MAP_RANGE&& Math.abs(y-posY)<=MAP_RANGE){
				map[x-posX+MAP_RANGE][y-posY+MAP_RANGE] = 1;
			}
		}
		
		for(Coin coin: coinsOnFloor){
			int x = coin.getPosX();
			int y = coin.getPosY();
			if(Math.abs(x-posX)<=MAP_RANGE&& Math.abs(y-posY)<=MAP_RANGE){
				map[x-posX+MAP_RANGE][y-posY+MAP_RANGE] = 4;
			}
		}
		for(Potion potion: potions){
			int x = potion.getPosX();
			int y = potion.getPosY();
			if(Math.abs(x-posX)<=MAP_RANGE&& Math.abs(y-posY)<=MAP_RANGE){
				map[x-posX+MAP_RANGE][y-posY+MAP_RANGE] = 5;
			}
		}
		for(Block teleTile: teleportationTiles){
			int x = teleTile.getPosX();
			int y = teleTile.getPosY();
			if(Math.abs(x-posX)<=MAP_RANGE&& Math.abs(y-posY)<=MAP_RANGE){
				map[x-posX+MAP_RANGE][y-posY+MAP_RANGE] = 6;
			}
		}
		for(PNJ pnj: PNJs){
			int x = pnj.getPosX();
			int y = pnj.getPosY();
			if(Math.abs(x-posX)<=MAP_RANGE&& Math.abs(y-posY)<=MAP_RANGE){
				int color = 3;
				if(pnj instanceof Saibaman){color = 8;}
				map[x-posX+MAP_RANGE][y-posY+MAP_RANGE] = color;
			}
		}
		
		map[MAP_RANGE][MAP_RANGE] = 2;				// Hero is in the center of the map

		//System.out.println(map);
		return map;
	}
/*	
	

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
*/
}