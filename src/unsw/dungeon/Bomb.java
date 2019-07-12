package unsw.dungeon;

public class Bomb extends Entity{

	public Bomb(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void squareBehav(Player p, String direction) {
		System.out.println("STEPPED ON AN Bomb");
		Dungeon dungeon = p.getDungeon();
		p.changeToBombState();
		dungeon.removeEntity(this);
	}
}
