package unsw.dungeon.entities;

import java.util.ArrayList;

import javafx.scene.image.ImageView;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Subject;
import unsw.dungeon.ApplicationClasses.DungeonController;
import unsw.dungeon.ApplicationClasses.DungeonControllerLoader;

public class BossEnemy extends Enemy implements Runnable{
	private boolean alive = true;
	private boolean bombing = false;
	private Thread bombThread;
	private DungeonControllerLoader controller;
	public BossEnemy(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		System.out.println("In Constructor");


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
				moveTowardsPlayer();
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
		int yDiff = (int) (Math.random()*2 -1 );
		int xDiff = (int) (Math.random()*2 -1);
		System.out.println("yDiff =" + yDiff );
		System.out.println("xDiff =" + xDiff );
		int playerX = player.getX();
		int playerY = player.getY();
		Bomb bomb = new Bomb(playerX+ xDiff, playerY+yDiff, getDungeon());
		Sword sword = new Sword(5,5);
		controller.onLoad(bomb);
		controller.onLoad(sword);
		bomb.lightBomb(playerX + xDiff, playerY+yDiff);
	}
	public void setController(DungeonControllerLoader dgc) {
		this.controller = dgc;
	}
	@Override
	public void run() {
		System.out.println("Running");
		while (this.alive == true && getDungeon().getPlayerStatus()) {
			spawnBomb();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
