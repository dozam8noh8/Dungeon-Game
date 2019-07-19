package unsw.dungeon.entities;

import unsw.dungeon.Dungeon;
import unsw.dungeon.NoPotionState;
import unsw.dungeon.Observer;
import unsw.dungeon.PotionStatePlayer;
import unsw.dungeon.Subject;

public class Enemy extends Entity implements Observer {
	private Dungeon dungeon;
	private int moveCounter;
	private boolean alive;
	public Enemy(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
		this.moveCounter = 0;
		this.alive = true;

		// TODO Auto-generated constructor stub
	}

	@Override
	public void squareBehav(Player p, String direction) {
		if (alive) {
			if (p.getPotionState() instanceof NoPotionState) {
				System.out.println("STEPPED ON AN ENEMY");
				System.exit(1);
			} else {
				System.out.println("You are invincible!!!!");
				this.killEnemy();
			}
		}
	}

	@Override
	public void update(Subject o) {
		if (this.alive) {
			System.out.println("Enemy updating");
			this.moveCounter ++;
			if (this.moveCounter == 3) {
				System.out.println("moving enemy");
				moveTowardsPlayer();
				this.moveCounter = 0;
			}
		}
	}

	public void decideMoveCoords() {
		
	}
	public void moveTowardsPlayer() { //currently this wont change from x to y if one is blocked.
		int calcdX = 0;
		int calcdY = 0;
		int xDir = 1; //first priority direction move
		int yDir = 1; //second priority direction move
		int xDiff = dungeon.getPlayer().getX() - this.getX();
		String priority = "x";

		//if diff is positive player is on right of enemy
		int yDiff = dungeon.getPlayer().getY() - this.getY();
		//if diff is positive player is below enemy
		
		//if abs(xDiff) > abs(yDiff) decrease xDiff by moving toward playerX.
		if (Math.abs(xDiff) > Math.abs(yDiff)) {
			//move x
			priority = "x";
			if (xDiff > 0) {
				xDir = 1;
				if (yDiff <  0) {
					yDir = -1;
				}
			}
			else if (xDiff < 0){
				xDir = -1;
				if (yDiff <  0) {
					yDir = -1;
				}
			}
		}
		else if (Math.abs(xDiff) < Math.abs(yDiff)){
			//move y
			priority = "y";
			if (yDiff > 0) {
				yDir = 1;
				if (xDiff < 0) {
					xDir = -1;
				}
			}
			else if (yDiff < 0){
				yDir = -1;
				if (xDiff < 0) {
					xDir = -1;
				}
			}
		}
		else { //dont move?
			return;
		}
		if (this.dungeon.getPlayer().getPotionState() instanceof PotionStatePlayer) {
			System.out.println("Potion state reversion");
			xDir = xDir * -1;
			yDir = yDir * -1;
			//could change direction here too.
		}
		if (priority == "x") {
			System.out.println(priority + xDir + yDir);
			calcdX = this.getX() + xDir;
			calcdY = this.getY();
			if (dungeon.makeMoveBoulderOrEnemy(calcdX, calcdY)) {
				x().set(calcdX);
				y().set(calcdY);
			}
			else {
				calcdX = this.getX();
				calcdY = this.getY() + yDir;
				if (dungeon.makeMoveBoulderOrEnemy(calcdX, calcdY)) {
					x().set(calcdX);
					y().set(calcdY);
				} 
			}
		}
		else if (priority == "y"){
			calcdX = this.getX();
			calcdY = this.getY() + yDir;
			if (dungeon.makeMoveBoulderOrEnemy(calcdX, calcdY)) {
				x().set(calcdX);
				y().set(calcdY);
			}
			else {
				calcdX = this.getX() + xDir;
				calcdY = this.getY();
				if (dungeon.makeMoveBoulderOrEnemy(calcdX, calcdY)) {
					x().set(calcdX);
					y().set(calcdY);
				} 
			}
		}
		else { //try the other direction
			
		}
		
	}
	public void killEnemy() {
		this.alive = false;
		System.out.println("Killed the enemy");
		dungeon.completeEnemyObjective(dungeon.getObjective());
	}
	

}