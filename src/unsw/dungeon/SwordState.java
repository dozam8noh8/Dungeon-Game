package unsw.dungeon;

public interface SwordState {
	public SwordState changeToSwordState();
	public SwordState changeToNoSwordState();
	public void attack(Player player);
}
