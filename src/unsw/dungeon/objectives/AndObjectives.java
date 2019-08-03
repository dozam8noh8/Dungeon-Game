package unsw.dungeon.objectives;

import java.util.ArrayList;

/**
 * This class represents a composite objective in which all children must be complete
 * in order to be considered complete.
 * @authors Waqif Alam and Owen Silver
 *
 */
public class AndObjectives implements ObjectiveCheck { 

	/**
	 * Checks all children objectives, if any are incomplete it will return false,
	 * true otherwise.
	 */

	@Override
	public boolean checkComplete(ArrayList<Objective> children) {
		for (Objective o : children) {
			if (!o.isComplete()) {
				return false;
			}
		}
		return true;
	}
	

}
