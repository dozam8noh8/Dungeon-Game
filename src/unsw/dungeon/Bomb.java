package unsw.dungeon;

import java.util.ArrayList;

public class Bomb extends Entity implements Runnable{
	private int fuseLength;
	private Player player;
	private Dungeon dungeon;
	private int droppedX;
	private int droppedY;
	public Bomb(int x, int y, Dungeon dungeon) {
		super(x, y);
		fuseLength = 3;
		this.dungeon = dungeon;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void squareBehav(Player p, String direction) {
		System.out.println("STEPPED ON A Bomb");
		Dungeon dungeon = p.getDungeon();
		p.addBomb(this);
		this.player = p;
		dungeon.removeEntity(this);
	}
	public void lightBomb() {
		/*BombThread bt = new BombThread();
		bt.start();*/
		setX(player.getX());
		setY(player.getY());
		Thread t1 = new Thread(this);
		t1.start();
		
		
	}
	public void setX(int x1) {
		// TODO Auto-generated method stub
		x().set(x1);
	}
	
	public void setY(int x1) {
		// TODO Auto-generated method stub
		y().set(x1);
	}
	public void detonateBomb() {
		System.out.println("DETONATING BOMB");
		int x = this.getX();
		int y = this.getY();
		if (x> 0) {
			checkBombRadius(x-1, y);
		}
		if (y > 0) {
			checkBombRadius(x, y-1);
		}
		if (x < dungeon.getWidth() - 1) {
			checkBombRadius(x+1, y);
		}
		if (y < dungeon.getHeight() - 1) {
			checkBombRadius(x, y+1 );
		}
	}
	private void checkBombRadius(int x, int y) {
		ArrayList<Entity> entOnSq = dungeon.getEntOnSq(x, y);
		for (Entity e : entOnSq) {
			if (e instanceof Enemy) {
				((Enemy) e).killEnemy();
				dungeon.removeEntity(e);
			} else if (e instanceof Boulder) {
				((Boulder) e).killBoulder();
				dungeon.removeEntity(e);
			}

		}
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
			fuseLength--; //this should be observed by frontend i think.
			Thread.sleep(1000);
			fuseLength--;
			Thread.sleep(1000);
			fuseLength--;
			this.detonateBomb(); //Can add another second here so we can see the explosion.
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


}
