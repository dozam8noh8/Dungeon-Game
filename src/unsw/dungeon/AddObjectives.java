package unsw.dungeon;

import java.util.ArrayList;

public class AddObjectives implements ObjectiveCheck {

	@Override
	public boolean checkComplete(ArrayList<Objective> children) {
		// TODO Auto-generated method stub
		for (Objective o : children) {
			if (!o.isComplete()) {
				return false;
			}
		}
		
		return true;
	}

}
