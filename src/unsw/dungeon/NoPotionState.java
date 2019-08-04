package unsw.dungeon;

/**
 * A class representing the potion state of the player.
 * This is the default player state, meaning the player is
 * not under the effects of a potion. In this state, enemies
 * chase players and will kill them if stepped on.
 * @authors Waqif Alam and Owen Silver
 *
 */
public class NoPotionState implements PotionState {

	/**
	 * Changes from no potion state to potion state.
	 */
	@Override
	public PotionState changeToPotionState() {
		return new PotionStatePlayer();
	}

	/**
	 * Changes from no potion state to no potion state.
	 */
	@Override
	public PotionState changeToNoPotionState() {
		return this;
	}
}
