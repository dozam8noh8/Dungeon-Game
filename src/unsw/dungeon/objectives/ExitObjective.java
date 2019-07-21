package unsw.dungeon.objectives;

import java.util.ArrayList;

/**
 * ExitObjective class to represent an exitObjective
 * @author z5188982
 *
 */
public class ExitObjective implements Objective {
	public boolean complete = false;
	
	/**
	 * returns whether the objective is complete or not
	 * return true if complete and false if not complete
	 */
	@Override
	public boolean isComplete() {
		return complete;
	}

	@Override
	public void addChild(Objective o) {
		
	}

	@Override
	public void removeChild(Objective o) {
		
	}

	/**
	 * Complete the exitObjective
	 */
	@Override
	public void complete(Objective o) {
		this.complete = true;
	}

	/**
	 * Change completed to exitobjective to incomplete if conditions change
	 */
	@Override
	public void incomplete(Objective o) {
		this.complete = false;
	}

	@Override
	public ArrayList<Objective> getObjectives() {
		return null;
	}

	@Override
	public boolean allButExitsComplete(ArrayList<Objective> children) {
		return complete;
	}

}