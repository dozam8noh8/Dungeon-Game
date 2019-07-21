package unsw.dungeon;
/**
 * A thread used to control the potion state
 * of a player. The thread starts when player state to PotionState
 * after stepping on a potion. The thread keeps it that way for ten seconds
 * and changes to NoPotionState after that
 * @authors Waqif Alam and Owen Silver
 *
 */
import unsw.dungeon.entities.Player;

public class PotionStateThread extends Thread{
	private Player player;
	
	/**
	 * Constructor for PotionStateThread and store player to change state
	 * @param p - Player in the game
	 */
	public PotionStateThread(Player p) {
		this.player = p;
	}
	
	/**
	 * Runs the thread and changes the PotionState of player to
	 * NoPotionState after ten seconds
	 */
	public void run() {
		try { Thread.sleep(10000); } catch (Exception e) {}
		player.changeToNoPotionState();
	}
}
