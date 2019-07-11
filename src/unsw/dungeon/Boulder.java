package unsw.dungeon;

public class Boulder extends Entity {
	boolean canMove;
	Dungeon dungeon;
	public Boulder(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.canMove = true;
		this.dungeon = dungeon;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void squareBehav(Player p, String direction) {
		switch(direction){
		case "right":
			canMove = dungeon.makeMoveBoulder(getX()+ 1, getY(), this); //doesnt check if edge of map
			if (canMove) {
	            x().set(getX() + 1);
			}
			else {
				p.setCanMove(false);
			}
			break;
		case "left":
			canMove = dungeon.makeMoveBoulder(getX()- 1, getY(), this);
			if (canMove) {
	            x().set(getX() - 1);
			}
			else {
				p.setCanMove(false);
			}
			break;
		case "up":
			canMove = dungeon.makeMoveBoulder(getX(), getY()-1, this);
			if (canMove) {
	            y().set(getY() - 1);
			}
			else {
				p.setCanMove(false);
			}
			break;
		case "down":
			canMove = dungeon.makeMoveBoulder(getX(), getY()+1, this);
			if (canMove) {
	            y().set(getY() + 1);
			}
			else {
				p.setCanMove(false);
			}
			break;
		
		}
	}
	

}
