package unsw.dungeon;

public class NoPotionState implements PotionState {

	@Override
	public PotionState changeToPotionState() {
		// TODO Auto-generated method stub
		return new PotionStatePlayer();
	}

	@Override
	public PotionState changeToNoPotionState() {
		// TODO Auto-generated method stub
		return this;
	}

}
