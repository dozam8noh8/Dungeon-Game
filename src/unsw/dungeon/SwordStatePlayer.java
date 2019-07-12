package unsw.dungeon;

public class SwordStatePlayer implements SwordState {

	@Override
	public SwordState changeToSwordState() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public SwordState changeToNoSwordState() {
		// TODO Auto-generated method stub
		return new NoSwordState();
	}

}
