package unsw.dungeon;

public class Door extends Entity{
	public boolean opened = false;
	public int id;
	
	public Door(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	} //may need two doors that implement this for state.
	
	public void openDoor(Player p) {
		if (p.getKey() == id) {
			opened = true;
		}
	}
	
	@Override
	public void squareBehav(Player p, String direction) {
		if (!opened) {
			p.setCanMove(false);
		}
	}
}
