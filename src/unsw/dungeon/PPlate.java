package unsw.dungeon;

public class PPlate extends Entity implements Observer {

	private int complete = 0;
	
	public PPlate(int x, int y) {
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
	
	public boolean isComplete() {
		if (complete == 0) {
			return false;
		}
		return true;
	}
	
	public void willComplete() {
		complete = 1;
	}

}
