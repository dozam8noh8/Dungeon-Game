package unsw.dungeon;

public class Sword extends Entity{

	public Sword(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void squareBehav(Player p, String direction) {
		System.out.println("STEPPED ON AN Sword");
		Dungeon dungeon = p.getDungeon();
		dungeon.removeEntity(this);
		p.changeToSwordState();
	}
}
