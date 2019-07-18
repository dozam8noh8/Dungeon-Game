package unsw.dungeon;

public class Switch extends Entity implements Observer {

	public Switch(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Subject o) {
		// TODO Auto-generated method stub
		System.out.println("COMES HERE");
		if (((Boulder) o).getX() == getX() && ((Boulder) o).getY() == getY()) {
			System.out.println("On Switch");
		}
	}
	
	public void squareBehav(Entity e, String direction) {
		if (e instanceof Boulder) {
			
		}
	}

}
