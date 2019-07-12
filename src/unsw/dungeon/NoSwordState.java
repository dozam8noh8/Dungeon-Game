package unsw.dungeon;

public class NoSwordState implements SwordState {

	@Override
	public SwordState changeToSwordState() {
		// TODO Auto-generated method stub
		return new SwordStatePlayer();
	}

	@Override
	public SwordState changeToNoSwordState() {
		// TODO Auto-generated method stub
		return this;
	}

}
