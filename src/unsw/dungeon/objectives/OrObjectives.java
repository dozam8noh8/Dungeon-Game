package unsw.dungeon.objectives;

import java.util.ArrayList;

/**
 * OrObjective class to represent an OR objective in game
 * @author Waqif Alam, Owen Silver
 *
 */
public class OrObjectives implements ObjectiveCheck {

	/**
	 * Check if objective is complete or not
	 * return true if complete and false if not complete
	 */
	@Override
	public boolean checkComplete(ArrayList<Objective> children) {
		for (Objective o : children) {
			if (o.isComplete()) {
				return true;
			}
		}

		return false;
	}



}
