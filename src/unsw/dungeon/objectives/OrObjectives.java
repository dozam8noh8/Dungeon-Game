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
		if (children.size() == 1 && children.get(0) instanceof ExitObjective) {
			return true;
		}
		for (Objective o : children) {
			if (o.isComplete() && (!(o instanceof ExitObjective))) {
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Check if all objectives other than exit is complete or not
	 * return true if complete and false if not complete
	 */
	@Override
	public boolean checkNonExitObjectives(ArrayList<Objective> children) {
		if (children.size() == 1 && children.get(0) instanceof ExitObjective) {
			return true;
		}
		for (Objective o : children) {
			if (o.isComplete() && (!(o instanceof ExitObjective))) {
				return true;
			}
		}
		
		return false;
	}

}
