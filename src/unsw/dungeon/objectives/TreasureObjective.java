package unsw.dungeon.objectives;

import java.util.ArrayList;

/**
 * TreasureObjective class to represent a treasure Objective in game
 * @author Waqif Alam, Owen Silver
 *
 */
public class TreasureObjective implements Objective {
	public boolean complete = false;
	
	/**
	 * Changes treasure objective to true
	 */
	@Override
	public void complete(Objective o) {
		this.complete = true;
	}

	/**
	 * Changes completed objective to false if conditions change
	 */
	@Override
	public void incomplete(Objective o) {
		this.complete = false;
	}
	
	/**
	 * Check if objective is complete or not
	 * return true if complete, false if not
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

	@Override
	public ArrayList<Objective> getObjectives() {
		return null;
	}

	/**
	 * Check if all objectives other than exit objective is complete or not
	 */
	@Override
	public boolean allButExitsComplete(ArrayList<Objective> children) {
		return complete;
	}
	
}
