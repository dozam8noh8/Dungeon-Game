package unsw.dungeon;

public class BombStatePlayer implements BombState {

	@Override
	public BombState changeToBombState() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public BombState changeToNoBombState() {
		// TODO Auto-generated method stub
		return new NoBombState();
	}

}
