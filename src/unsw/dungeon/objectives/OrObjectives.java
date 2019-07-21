package unsw.dungeon.objectives;

import java.util.ArrayList;

public class OrObjectives implements ObjectiveCheck {

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
