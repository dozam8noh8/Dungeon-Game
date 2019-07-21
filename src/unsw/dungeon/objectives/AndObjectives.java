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
		System.out.println("Checking non exit objectives");
		// TODO Auto-generated method stub
		for (Objective o : children) {
			System.out.println("--------"+o.isComplete());
			if (!o.isComplete() && (!(o instanceof ExitObjective))) {
				System.out.println(o);
				return false;
			}
		}
		System.out.println("All objectives complete!!");
		return true;
	}
	
	/**
	 * Checks if all objectives other than exit objective is done
	 */
	@Override
	public boolean checkNonExitObjectives(ArrayList<Objective> children) {
		System.out.println("Checking non exit objectives");
		// TODO Auto-generated method stub
		for (Objective o : children) {
			System.out.println("--------"+o.isComplete());
			if (!o.isComplete() && (!(o instanceof ExitObjective))) {
				System.out.println(o);
				return false;
			}
		}
		System.out.println("All objectives complete!!");
		return true;
	}

}
