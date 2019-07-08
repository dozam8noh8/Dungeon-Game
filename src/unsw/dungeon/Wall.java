package unsw.dungeon;

public class Wall extends Entity implements Observer{

    public Wall(int x, int y) {
        super(x, y);
    }

	@Override
	public void update(Subject obj, String prev) {
		if (getX() == obj.getX() && getY() == obj.getY()) {
			switch (prev) {
			case "up":
				obj.moveDown();
				break;
			case "down":
				obj.moveUp();
				break;
			case "left":
				obj.moveRight();
				break;
			case "right":
				obj.moveLeft();
				break;
			}
		}
	}

}
