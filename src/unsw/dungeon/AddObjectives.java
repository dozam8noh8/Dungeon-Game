package unsw.dungeon;

import java.util.ArrayList;

public class AddObjectives implements ObjectiveCheck {

	@Override
	public boolean checkComplete(ArrayList<Objective> children) {
		// TODO Auto-generated method stub
		for (Objective o : children) {
			System.out.println(o);
			if (!o.isComplete()) {
				System.out.println(o);
				return false;
			}
		}
		
		return true;
	}

}
