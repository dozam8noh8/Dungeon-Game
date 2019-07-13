package unsw.dungeon;

import java.util.ArrayList;

public interface Objective {
	public boolean isComplete();
	public void addChild(Objective o);
	public void removeChild(Objective o);
	public void complete(Objective o);
	public void incomplete(Objective o);
	public ArrayList<Objective> getObjectives();
}