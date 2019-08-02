package unsw.dungeon.objectives;

import java.util.ArrayList;

/**
 * Strategy Objective to create objectives with AND or OR strategies
 * @author Waqif Alam, Owen Silver
 *
 */
public class StrategyObjective implements Objective {
	private ArrayList<Objective> children = new ArrayList<Objective>();
	private ObjectiveCheck strategy = new AndObjectives(); //change this accordingly
	
	public StrategyObjective(ArrayList<Objective> children, ObjectiveCheck ANDorOR) {
		this.children = children;
		this.strategy = ANDorOR;
	}
	/*public void PrintAllChildren(int level) {
		if (level == 0) {
			
		}
		for (Objective child : this.getObjectives())
	}*/

	/**
	 * Check if objective is complete or not
	 * return true if complete, false if not complete
	 */
	@Override
	public boolean isComplete() {
		return strategy.checkComplete(children);
	}

	
	@Override
	public void addChild(Objective o) {
		children.add(o);
	}

	@Override
	public void removeChild(Objective o) {
		children.remove(o);
	}

	@Override
	public void complete(Objective o) {
		
	}

	@Override
	public void incomplete(Objective o) {
		
	}

	/**
	 * Get all the objectives in StrategyObjective class
	 */
	@Override
	public ArrayList<Objective> getObjectives() {
		return this.children;
	}

}
