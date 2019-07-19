package unsw.dungeon.entities;

import unsw.dungeon.Dungeon;
import unsw.dungeon.NoPotionState;
import unsw.dungeon.Observer;
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

	
	public void moveTowardsPlayer() { //currently this wont change from x to y if one is blocked.
		int calcdX = 0;
		int calcdY = 0;
		int xDiff = dungeon.getPlayer().getX() - this.getX();
		//if diff is positive player is on right of enemy
		int yDiff = dungeon.getPlayer().getY() - this.getY();
		//if diff is positive player is below enemy
		
		//if abs(xDiff) > abs(yDiff) decrease xDiff by moving toward playerX.
		if (Math.abs(xDiff) > Math.abs(yDiff)) {
			//move x
			if (xDiff > 0) {
				calcdX = this.getX()+ 1;
				calcdY = this.getY();
			}
			else {
				calcdX = this.getX() - 1;
				calcdY = this.getY();
			}
		}
		else if (Math.abs(xDiff) < Math.abs(yDiff)){
			//move y
			if (yDiff > 0) {
				calcdX = this.getX();
				calcdY = this.getY() + 1;
			}
			else {
				calcdX = this.getX();
				calcdY = this.getY() - 1;
			}
		}
		else { //dont move?
			
		}
		if (dungeon.makeMoveBoulderOrEnemy(calcdX, calcdY)) {
			x().set(calcdX);
			y().set(calcdY);
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