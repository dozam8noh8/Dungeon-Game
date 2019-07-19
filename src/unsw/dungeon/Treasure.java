package unsw.dungeon;

public class Treasure extends Entity{

	public Treasure(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void squareBehav(Player p, String direction) {
		System.out.println("STEPPED ON AN Treasure");
		Dungeon dungeon = p.getDungeon();
		p.addTreasure(this);
		dungeon.removeEntity(this);
		dungeon.completeTreasureObjective(dungeon.getObjective()); //should loop through all treasures! FIX
	}

}
