package unsw.dungeon;

public interface Subject {
	public void registerObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObservers(String prev);
	public int getX();
	public int getY();
	public void setX(int x);
	public void setY(int y);
	public void moveUp();
	public void moveDown();
	public void moveLeft();
	public void moveRight();
}
