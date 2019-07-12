package unsw.dungeon;

public class NoBombState implements BombState {

	@Override
	public BombState changeToBombState() {
		// TODO Auto-generated method stub
		return new BombStatePlayer();
	}

	@Override
	public BombState changeToNoBombState() {
		// TODO Auto-generated method stub
		return this;
	}

}
