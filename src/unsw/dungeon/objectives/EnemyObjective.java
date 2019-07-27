package unsw.dungeon.objectives;

/**
 * EnemyObjective class to represent enemy objective in objectives
 */
import java.util.ArrayList;

public class EnemyObjective implements Objective {
	public boolean complete = false;
	
	/**
	 * Returns whether enemy objective is complete or not
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
	 * Completes the enemyObjective
	 */
	@Override
	public void complete(Objective o) {
		this.complete = true;
	}

	/**
	 * Changes enemyobjective from complete to incomplete if conditions change
	 */
	@Override
	public void incomplete(Objective o) {
		this.complete = false;
	}

	/**
	 * Returns list of objectives
	 */
	@Override
	public ArrayList<Objective> getObjectives() {
			return null;
		}


}
