package unsw.dungeon.objectives;

import java.util.ArrayList;

/**
 * BoulderObjective to represent BoulderObjective in objectives
 * @author Waqif Alam, Owen Silver
 *
 */
public class BoulderObjective implements Objective {
	public boolean complete = false;
	
	/**
	 * returns if boulder objective is complete or not
	 * return complete - true if boulderobjective is complete and false if it's not
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
	 * Method to complete boulder Objective
	 */
	@Override
	public void complete(Objective o) {
		this.complete = true;
	}

	/**
	 * Method to turn completed object incomplete if conditions change
	 */
	@Override
	public void incomplete(Objective o) {
		System.out.println("incompleting " + this);
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
