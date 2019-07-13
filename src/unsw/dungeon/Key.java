package unsw.dungeon;

public class Key extends Entity {
	private int id;
	
	public Key(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	public void setX(int x1) {
		// TODO Auto-generated method stub
		x().set(x1);
	}
	
	public void setY(int x1) {
		// TODO Auto-generated method stub
		y().set(x1);
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	@Override
	public void squareBehav(Player p, String direction) {
		p.setKey(this);
	}
}
