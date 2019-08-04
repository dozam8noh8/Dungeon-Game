package unsw.dungeon;

/**
 * A class representing the potion state of the player.
 * This is the NoPotionStatePlayer state, meaning the player is
 * under the effects of a potion. In this state, enemies
 * run away from players and enemies will die if stepped on.
 * @authors Waqif Alam and Owen Silver
 *
 */
public class PotionStatePlayer implements PotionState {

	/**
	 * Change potionState of player to Potion state
	 * return PotionState - returns NoPotionStatePlayer to change state
	 */
	@Override
	public PotionState changeToPotionState() {
		return this;
	}

	/**
	 * Change potionState of player to noPotion state
	 * return PotionState - returns NoPotionStatePlayer to change state
	 */
	@Override
	public PotionState changeToNoPotionState() {
		return new NoPotionState();
	}

}
