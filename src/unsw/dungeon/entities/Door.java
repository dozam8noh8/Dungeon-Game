package unsw.dungeon.entities;

public class Door extends Entity{
	private boolean opened = false;
	private int id;
	
	
	public Door(int x, int y, int id) {
		super(x, y);
		this.id = id;
		// TODO Auto-generated constructor stub
	} //may need two doors that implement this for state.
	
	public void openDoor(Player p) {
		if (p.getKeyId() == id) {
			opened = true; //could make a use key function for these
			p.getKey().setPickedUp(false);
			p.getDungeon().getEntities().remove(p.getKey());
			p.setKey(null);
			
			System.out.println("Door is opened!");
		}
	}
	
	@Override
	public void squareBehav(Player p, String direction) {

		if (!opened) {
			openDoor(p);
		}
		if (!opened) {
			p.setCanMove(false);
		}
	}
}
