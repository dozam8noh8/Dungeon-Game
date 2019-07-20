package unsw.dungeon.objectives;

import java.util.ArrayList;

public class AndObjectives implements ObjectiveCheck { //Should change name to "And"

	@Override
	public boolean checkComplete(ArrayList<Objective> children) {
		System.out.println("Calling AND checkComplete");
		// TODO Auto-generated method stub
		for (Objective o : children) {
			System.out.println("--------"+o.isComplete());
			if (!o.isComplete()) {
				System.out.println("NOT COMPLETE");
				System.out.println(o);
				return false;
			}
		}
		System.out.println("All objectives complete!!");
		return true;
	}

}
