package unsw.dungeon;

public class Enemy extends Entity{
	private Dungeon dungeon;
	public Enemy(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;

		// TODO Auto-generated constructor stub
	}

	@Override
	public void squareBehav(Player p, String direction) {
		System.out.println("STEPPED ON AN ENEMY");
		System.exit(1);
	}
	

}