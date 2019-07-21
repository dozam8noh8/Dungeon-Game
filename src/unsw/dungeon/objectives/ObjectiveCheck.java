package unsw.dungeon.objectives;

import java.util.ArrayList;

public interface ObjectiveCheck {
	public boolean checkComplete(ArrayList<Objective>  children);
	public boolean checkNonExitObjectives(ArrayList<Objective> children);
}
