package unsw.dungeon.entities;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Observer;
import unsw.dungeon.Subject;
import unsw.dungeon.ApplicationClasses.DungeonController;
import unsw.dungeon.ApplicationClasses.DungeonControllerLoader;

public class BossEnemy extends Enemy implements Runnable, Subject{
	private boolean alive = true;
	private boolean bombing = false;
	private Thread bombThread;
	private int orbSleepCount = 0;
	private ArrayList<Observer> observers;
	private ArrayList<Bomb> bombs;
	private ArrayList<Orb> orbs;
	public BossEnemy(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		System.out.println("In Constructor");
		observers = new ArrayList<Observer>();
		bombs = new ArrayList<Bomb>();
		orbs = new ArrayList<Orb>();
	}
	//We may want a way to return two coordinates from a function (a point).
	
	/**
	 * Update method called every time a player moves.
	 * Increases a counter, after 2 increases the enemy will move towards the player.
	 */
	@Override
	public void update(Subject o) {
		if (bombing == false) {
			System.out.println("Do something");
			bombThread = new Thread(this);
			bombThread.start();
			bombing = true;
			}
		if (isAlive().getValue()) {
			this.setMoveCounter(this.getMoveCounter() + 1);
			if (this.getMoveCounter() == 2) {
				System.out.println("Doing something");
				makeMove();
				this.setMoveCounter(0);
			}
		}
	}
	
	/**
	 * Trying to move the enemy to another square in game
	 * @param xChange - the change of x-coordinate for the move
	 * @param yChange - the change of y-coordinate for the move
	 * @return true if enemy can move, or false if enemy cannot move
	 */
	public boolean move(int xChange, int yChange) {
		if (xChange == 0 && yChange == 0)return false;
		int x = getX() + xChange;
		int y = getY() + yChange;
		if (getDungeon().makeMoveEntity(x, y)) {
			x().set(x);
			y().set(y);
			killPlayerAdjacent(x, y);
			return true;
		}
		return false;
	}
	public int getOrbSleepCount() {
		return this.orbSleepCount;
	}
	private void killPlayerAdjacent(int x, int y) {
		Dungeon dungeon = getDungeon();
		if (x < dungeon.getWidth()) {
			bossKillsPlayer(x+1, y, dungeon);
		}
		if (x > 0) {
			bossKillsPlayer(x-1, y, dungeon);
		}
		if (y > 0) {
			bossKillsPlayer(x, y-1, dungeon);
		}
		if (y < dungeon.getHeight()) {
			bossKillsPlayer(x, y+1, dungeon);
		}
	}

	private void bossKillsPlayer(int x, int y, Dungeon dungeon) {
		ArrayList<Entity> entOnSq = dungeon.getEntOnSq(x, y);
		for (Entity e: entOnSq) {
			if (e instanceof Player) {
				getPlayer().killPlayer();
			}
		}
	}
	public void spawnBomb () { //not actually added to list of entities.
		Player player = getDungeon().getPlayer();

        int max = 3; 
        int min = -3; 
        int range = max - min + 1; 
  
        // generate random numbers within 1 to 10  
        int yDiff = (int)(Math.random() * range) + min; 
        int xDiff = (int)(Math.random() * range) + min; 
		int playerX = player.getX();
		int playerY = player.getY();
        int xLoc = playerX + xDiff;
        int yLoc = playerY + yDiff;
        if (xLoc < 0) xLoc = 0;
        if (yLoc < 0) yLoc = 0;
        if (xLoc > this.getDungeon().getWidth()-1) xLoc = this.getDungeon().getWidth()-1;
        if (yLoc > this.getDungeon().getHeight()-1) yLoc = this.getDungeon().getHeight()-1;
  

		Bomb bomb = new Bomb(xLoc, yLoc, getDungeon());
		bombs.add(bomb);
		notifyObservers();
		//this.getDungeon().addEntity(bomb);
		bomb.lightBomb(xLoc, yLoc);
	}
	public void spawnOrb() {
		int max = this.getDungeon().getHeight()-1;
		int min = 0;
        int range = max - min + 1; 
		System.out.println("Spawning orb");
        int y = (int)(Math.random() * range) + min; 
        int x = (int)(Math.random() * range) + min; 
        Orb orb = new Orb(x,y);
        orbs.add(orb);
        this.getDungeon().addEntity(orb);
		notifyObservers();
        
		
	}
	@Override
	public void run() {
		while (this.alive == true && getDungeon().getPlayerStatus()) {
			if (this.getDungeon().isComplete()) break;
			orbSleepCount++;
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Platform.runLater(new Runnable() {
				@Override public void run() {
					spawnBomb();
					if (getOrbSleepCount() % 15 == 0) {
						spawnOrb();
					}
				}
			});
		}
	}

	@Override
	public void registerObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);
	}

	@Override
	public void notifyObservers() {
		for (Observer o : observers) {
			o.update(this);
		}
	}
	
	public Bomb getLastBomb() {
		if (bombs.size() == 0) {
			return null;
		}
		return bombs.get(bombs.size()-1);
	}

	public Orb getLastOrb() {
		if (orbs.size() == 0) {
			return null;
		}
		return orbs.get(orbs.size()-1);
	}

}
